package com.hmall.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.common.exception.BadRequestException;
import com.hmall.team.domain.dto.TeamCreateDTO;
import com.hmall.team.domain.dto.TeamCreateResultDTO;
import com.hmall.team.domain.dto.TeamUpdateDTO;
import com.hmall.team.domain.dto.TeamAnalysisResult;
import com.hmall.team.domain.dto.TeamRequirementDTO;
import com.hmall.team.domain.dto.PositionNeedDTO;
import com.hmall.team.domain.dto.TeamListItemDTO;
import com.hmall.team.domain.dto.PageDTO;
import com.hmall.team.domain.dto.PageResultDTO;
import com.hmall.team.domain.entity.Team;
import com.hmall.team.domain.entity.TeamMember;
import com.hmall.team.domain.entity.PositionNeed;
import com.hmall.team.domain.entity.PositionNeedMember;
import com.hmall.team.domain.entity.TeamRequirement;
import com.hmall.team.mapper.TeamMapper;
import com.hmall.team.mapper.TeamMemberMapper;
import com.hmall.team.mapper.PositionNeedMapper;
import com.hmall.team.mapper.PositionNeedMemberMapper;
import com.hmall.team.mapper.PersonnelMapper;
import com.hmall.team.mapper.TeamRequirementMapper;
import com.hmall.team.service.TeamAnalysisService;
import com.hmall.team.service.TeamInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 团队信息服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamInformationServiceImpl implements TeamInformationService {

    private final TeamMapper teamMapper;
    private final TeamMemberMapper teamMemberMapper;
    private final PositionNeedMapper positionNeedMapper;
    private final PositionNeedMemberMapper positionNeedMemberMapper;
    private final PersonnelMapper personnelMapper;
    private final TeamAnalysisService teamAnalysisService;
    private final TeamRequirementMapper teamRequirementMapper;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public TeamCreateResultDTO createTeam(TeamCreateDTO createDTO) {
        log.info("开始创建团队: {}", createDTO.getTeamName());
        log.info("能力要求: {}", createDTO.getAbilityRequirements());
        log.info("团队协作能力要求: {}", createDTO.getTeamworkAbilityRequirements());
        
        // 1. 创建团队需求
        TeamRequirement requirement = new TeamRequirement();
        requirement.setUserId(1); // TODO: 从上下文获取当前用户ID
        requirement.setTeamSize(createDTO.getTotalSize());
        
        // 设置能力要求和团队协作能力要求
        requirement.setAbilityRequirements(createDTO.getAbilityRequirements());
        requirement.setTeamworkAbilityRequirements(createDTO.getTeamworkAbilityRequirements());
        requirement.setNeedTeamFormation(true);
        requirement.setCreateTime(LocalDateTime.now());
        requirement.setUpdateTime(LocalDateTime.now());
        
        // 处理职位需求
        try {
            // 提取所有职位名称并拼接
            String positionNames = createDTO.getPositionNeeds().stream()
                .map(PositionNeedDTO::getPositionName)
                .collect(Collectors.joining("、"));
            requirement.setPositionRequirements(positionNames);
            
            // 将完整的职位需求对象转换为JSON格式存储为备用数据
            String fullPositionRequirements = objectMapper.writeValueAsString(createDTO.getPositionNeeds());
            log.info("职位需求JSON: {}", fullPositionRequirements);
            
            // 添加日志记录设置后的值
            log.info("设置到实体的能力要求: {}", requirement.getAbilityRequirements());
            log.info("设置到实体的团队协作能力要求: {}", requirement.getTeamworkAbilityRequirements());
            
        } catch (JsonProcessingException e) {
            throw new RuntimeException("职位需求处理失败", e);
        }
        
        // 添加日志记录插入前的完整实体信息
        log.info("准备插入数据库的团队需求实体: {}", requirement);
        
        teamRequirementMapper.insert(requirement);
        log.info("团队需求创建成功, id={}, 能力要求={}, 团队协作能力要求={}", 
            requirement.getId(), 
            requirement.getAbilityRequirements(), 
            requirement.getTeamworkAbilityRequirements());
        
        // 2. 创建团队基本信息
        Team team = new Team();
        team.setTeamName(createDTO.getTeamName());
        team.setTeamDescription(createDTO.getTeamDescription());
        team.setRequirementId(requirement.getId());
        team.setCreateTime(LocalDateTime.now());
        team.setUpdateTime(LocalDateTime.now());
        
        teamMapper.insert(team);
        log.info("团队基本信息创建成功, id={}", team.getId());
        
        // 3. 创建职位需求并筛选推荐人员
        List<PositionNeed> positionNeeds = new ArrayList<>();
        for (PositionNeedDTO needDTO : createDTO.getPositionNeeds()) {
            PositionNeed need = new PositionNeed();
            need.setTeamId(team.getId());
            need.setPositionName(needDTO.getPositionName());
            need.setPersonNum(needDTO.getPersonNum());
            need.setPosition(needDTO.getPosition());
            need.setEducation(needDTO.getEducation());
            need.setMajor(needDTO.getMajor());
            need.setMaxAge(needDTO.getMaxAge());
            need.setMinAge(needDTO.getMinAge());
            need.setCreateTime(LocalDateTime.now());
            need.setUpdateTime(LocalDateTime.now());
            
            positionNeedMapper.insert(need);
            positionNeeds.add(need);
            
            // 根据条件筛选匹配的人员
            List<Map<String, Object>> matchedPersonnel = personnelMapper.findMatchingPersonnel(
                needDTO.getPosition(),
                needDTO.getEducation(),
                needDTO.getMajor(),
                needDTO.getMinAge(),
                needDTO.getMaxAge()
            );
            
            log.info("职位 {} 匹配到 {} 个人员", needDTO.getPositionName(), matchedPersonnel.size());
            
            // 计算匹配度并保存推荐关系
            List<PositionNeedMember> recommendMembers = new ArrayList<>();
            for (Map<String, Object> person : matchedPersonnel) {
                BigDecimal matchDegree = calculateMatchDegree(needDTO, person);
                
                PositionNeedMember member = new PositionNeedMember();
                member.setPositionNeedId(need.getId());
                member.setPersonnelId((Integer) person.get("id"));
                member.setMatchDegree(matchDegree);
                member.setCreateTime(LocalDateTime.now());
                member.setUpdateTime(LocalDateTime.now());
                
                recommendMembers.add(member);
            }
            
            // 根据匹配度排序
            recommendMembers.sort((a, b) -> b.getMatchDegree().compareTo(a.getMatchDegree()));
            
            // 只保留前N个推荐（每个岗位需求人数的3倍）
            int maxRecommend = needDTO.getPersonNum() * 3;
            if (recommendMembers.size() > maxRecommend) {
                recommendMembers = recommendMembers.subList(0, maxRecommend);
                log.info("职位 {} 限制推荐人数为 {}", needDTO.getPositionName(), maxRecommend);
            }
            
            // 保存推荐关系
            for (PositionNeedMember member : recommendMembers) {
                positionNeedMemberMapper.insert(member);
            }
        }
        
        // 4. 构建返回结果
        return buildTeamCreateResult(team, positionNeeds);
    }
    
    private BigDecimal calculateMatchDegree(PositionNeedDTO need, Map<String, Object> person) {
        int matchPoints = 0;
        int totalPoints = 0;

        // 职位匹配
        if (need.getPosition().equals(person.get("position"))) {
            matchPoints += 30;
        }
        totalPoints += 30;

        // 学历匹配
        if (need.getEducation().equals(person.get("education"))) {
            matchPoints += 25;
        }
        totalPoints += 25;

        // 专业匹配
        if (need.getMajor().equals(person.get("major"))) {
            matchPoints += 25;
        }
        totalPoints += 25;

        // 年龄匹配
        int age = (Integer) person.get("age");
        if (age >= need.getMinAge() && age <= need.getMaxAge()) {
            matchPoints += 20;
        }
        totalPoints += 20;

        return new BigDecimal(matchPoints)
            .divide(new BigDecimal(totalPoints), 2, RoundingMode.HALF_UP);
    }
    
    private TeamCreateResultDTO buildTeamCreateResult(Team team, List<PositionNeed> positionNeeds) {
        TeamCreateResultDTO result = new TeamCreateResultDTO();
        result.setMessage("团队拟组建成功");
        result.setTeamName(team.getTeamName());
        result.setTeamDescription(team.getTeamDescription());
        
        List<TeamCreateResultDTO.PositionResult> positionResults = new ArrayList<>();
        List<Map<String, Object>> allMembers = new ArrayList<>();
        
        // 获取每个职位的推荐人员
        for (PositionNeed need : positionNeeds) {
            TeamCreateResultDTO.PositionResult positionResult = new TeamCreateResultDTO.PositionResult();
            positionResult.setPositionName(need.getPositionName());
            positionResult.setPosition(need.getPosition());
            
            // 只获取已保存的推荐人员（按匹配度排序）
            List<Map<String, Object>> recommendedPersonnel = positionNeedMemberMapper.findRecommendedPersonnel(need.getId());
            log.info("职位 {} 获取到 {} 个已保存的推荐人员", need.getPositionName(), recommendedPersonnel.size());
            
            allMembers.addAll(recommendedPersonnel);
            
            positionResult.setRecommendedPersonnel(recommendedPersonnel.stream()
                .map(this::convertToPersonnelInfo)
                .collect(Collectors.toList()));
                
            positionResults.add(positionResult);
        }
        
        // 设置团队总人数
        result.setTotalSize(allMembers.size());
        
        // 计算年龄分布
        result.setAgeDistribution(calculateAgeDistribution(allMembers));
        
        // 计算性别比例
        result.setGenderRatio(calculateGenderRatio(allMembers));
        
        result.setPositionResults(positionResults);
        
        return result;
    }
    
    private TeamCreateResultDTO.PersonnelInfo convertToPersonnelInfo(Map<String, Object> person) {
        TeamCreateResultDTO.PersonnelInfo info = new TeamCreateResultDTO.PersonnelInfo();
        info.setId((Integer) person.get("id"));
        info.setName((String) person.get("name"));
        info.setGender((String) person.get("gender"));
        info.setAge((Integer) person.get("age"));
        info.setPosition((String) person.get("position"));
        info.setTitle((String) person.get("title"));
        info.setDepartment((String) person.get("department"));
        info.setEducation((String) person.get("education"));
        info.setMajor((String) person.get("major"));
        info.setMatchDegree((BigDecimal) person.get("match_degree"));
        return info;
    }
    
    private String calculateAgeDistribution(List<Map<String, Object>> personnel) {
        Map<String, Integer> distribution = new HashMap<>();
        distribution.put("20-30", 0);
        distribution.put("31-40", 0);
        distribution.put("41-50", 0);
        distribution.put("51+", 0);
        
        for (Map<String, Object> person : personnel) {
            int age = (Integer) person.get("age");
            if (age <= 30) distribution.put("20-30", distribution.get("20-30") + 1);
            else if (age <= 40) distribution.put("31-40", distribution.get("31-40") + 1);
            else if (age <= 50) distribution.put("41-50", distribution.get("41-50") + 1);
            else distribution.put("51+", distribution.get("51+") + 1);
        }
        
        return distribution.entrySet().stream()
            .map(e -> e.getKey() + "岁: " + e.getValue() + "人")
            .collect(Collectors.joining(", "));
    }
    
    private String calculateGenderRatio(List<Map<String, Object>> personnel) {
        long maleCount = personnel.stream()
            .filter(p -> "male".equals(p.get("gender")))
            .count();
        long femaleCount = personnel.size() - maleCount;
        return String.format("男:女 = %d:%d", maleCount, femaleCount);
    }

    @Override
    @Transactional
    public Team updateTeam(TeamUpdateDTO updateDTO) {
        log.info("开始更新团队信息: {}", updateDTO.getId());
        
        // 1. 获取并更新团队基本信息
        Team team = teamMapper.selectById(updateDTO.getId());
        if (team == null) {
            throw new BadRequestException("团队不存在");
        }
        
        team.setTeamName(updateDTO.getTeamName());
        team.setTeamDescription(updateDTO.getTeamDescription());
        team.setUpdateTime(LocalDateTime.now());
        teamMapper.updateById(team);
        
        // 2. 更新团队需求信息
        TeamRequirement requirement = teamRequirementMapper.selectById(team.getRequirementId());
        if (requirement == null) {
            throw new BadRequestException("团队需求不存在");
        }
        
        requirement.setTeamSize(updateDTO.getTotalSize());
        requirement.setAbilityRequirements(updateDTO.getAbilityRequirements());
        requirement.setTeamworkAbilityRequirements(updateDTO.getTeamworkAbilityRequirements());
        requirement.setUpdateTime(LocalDateTime.now());
        
        // 处理职位需求
        try {
            // 提取所有职位名称并拼接
            String positionNames = updateDTO.getPositionNeeds().stream()
                .map(PositionNeedDTO::getPositionName)
                .collect(Collectors.joining("、"));
            requirement.setPositionRequirements(positionNames);
            
            teamRequirementMapper.updateById(requirement);
        } catch (Exception e) {
            throw new RuntimeException("职位需求处理失败", e);
        }
        
        // 3. 更新职位需求和成员
        // 3.1 获取当前所有职位需求
        List<PositionNeed> currentPositionNeeds = positionNeedMapper.findByTeamId(team.getId());
        Map<String, PositionNeed> currentPositionMap = currentPositionNeeds.stream()
            .collect(Collectors.toMap(PositionNeed::getPositionName, p -> p));
        
        // 3.2 处理每个职位需求
        for (PositionNeedDTO needDTO : updateDTO.getPositionNeeds()) {
            final PositionNeed positionNeed;
            
            if (!currentPositionMap.containsKey(needDTO.getPositionName())) {
                // 新增职位需求
                PositionNeed newNeed = new PositionNeed();
                newNeed.setTeamId(team.getId());
                newNeed.setPositionName(needDTO.getPositionName());
                newNeed.setPersonNum(needDTO.getPersonNum());
                newNeed.setPosition(needDTO.getPosition());
                newNeed.setEducation(needDTO.getEducation());
                newNeed.setMajor(needDTO.getMajor());
                newNeed.setMaxAge(needDTO.getMaxAge());
                newNeed.setMinAge(needDTO.getMinAge());
                newNeed.setCreateTime(LocalDateTime.now());
                newNeed.setUpdateTime(LocalDateTime.now());
                
                positionNeedMapper.insert(newNeed);
                positionNeed = newNeed;
            } else {
                // 更新职位需求
                PositionNeed existingNeed = currentPositionMap.get(needDTO.getPositionName());
                existingNeed.setPersonNum(needDTO.getPersonNum());
                existingNeed.setPosition(needDTO.getPosition());
                existingNeed.setEducation(needDTO.getEducation());
                existingNeed.setMajor(needDTO.getMajor());
                existingNeed.setMaxAge(needDTO.getMaxAge());
                existingNeed.setMinAge(needDTO.getMinAge());
                existingNeed.setUpdateTime(LocalDateTime.now());
                
                positionNeedMapper.updateById(existingNeed);
                currentPositionMap.remove(needDTO.getPositionName());
                positionNeed = existingNeed;
            }
            
            // 更新该职位的推荐人员
            // 获取当前职位的所有推荐人员
            List<PositionNeedMember> currentMembers = positionNeedMemberMapper.selectByPositionNeedId(positionNeed.getId());
            Set<Integer> currentMemberIds = currentMembers.stream()
                .map(PositionNeedMember::getPersonnelId)
                .collect(Collectors.toSet());
            
            // 计算需要添加和删除的成员
            Set<Integer> newMemberIds = new HashSet<>(needDTO.getSelectedPersonnelIds());
            
            // 需要删除的成员
            Set<Integer> toRemove = currentMemberIds.stream()
                .filter(id -> !newMemberIds.contains(id))
                .collect(Collectors.toSet());
            
            // 需要添加的成员
            Set<Integer> toAdd = newMemberIds.stream()
                .filter(id -> !currentMemberIds.contains(id))
                .collect(Collectors.toSet());
            
            // 删除不再需要的成员
            if (!toRemove.isEmpty()) {
                positionNeedMemberMapper.deleteByPositionNeedIdAndPersonnelIds(positionNeed.getId(), toRemove);
            }
            
            // 添加新成员
            if (!toAdd.isEmpty()) {
                List<PositionNeedMember> newMembers = toAdd.stream()
                    .map(personnelId -> {
                        PositionNeedMember member = new PositionNeedMember();
                        member.setPositionNeedId(positionNeed.getId());
                        member.setPersonnelId(personnelId);
                        member.setMatchDegree(calculateMatchDegree(needDTO, personnelMapper.findById(personnelId)));
                        member.setCreateTime(LocalDateTime.now());
                        member.setUpdateTime(LocalDateTime.now());
                        return member;
                    })
                    .collect(Collectors.toList());
                
                for (PositionNeedMember member : newMembers) {
                    positionNeedMemberMapper.insert(member);
                }
            }
        }
        
        // 3.3 删除不再需要的职位需求
        if (!currentPositionMap.isEmpty()) {
            List<Long> positionNeedIdsToDelete = currentPositionMap.values().stream()
                .map(PositionNeed::getId)
                .collect(Collectors.toList());
            positionNeedMapper.deleteBatchIds(positionNeedIdsToDelete);
        }
        
        return team;
    }

    @Override
    public TeamCreateResultDTO getTeamInfo(Integer teamId) {
        // 1. 获取团队基本信息
        Team team = teamMapper.selectById(teamId);
        if (team == null) {
            throw new BadRequestException("团队不存在");
        }

        // 2. 获取团队的职位需求
        List<PositionNeed> positionNeeds = positionNeedMapper.findByTeamId(teamId);
        if (positionNeeds.isEmpty()) {
            throw new BadRequestException("团队职位需求不存在");
        }

        // 3. 构建返回结果
        return buildTeamCreateResult(team, positionNeeds);
    }

    @Override
    public TeamAnalysisResult analyzeTeam(Integer teamId) {
        // 1. 获取团队信息
        Team team = teamMapper.selectById(teamId);
        if (team == null) {
            throw new BadRequestException("团队不存在");
        }
        log.info("开始分析团队, teamId={}, teamName={}", teamId, team.getTeamName());
        
        // 2. 获取团队成员信息
        List<Map<String, Object>> memberInfos = teamMemberMapper.selectMemberInfosByTeamId(teamId);
        log.info("获取到团队成员数量: {}", memberInfos.size());
        
        // 输出每个成员的详细信息
        memberInfos.forEach(info -> {
            log.info("成员信息: id={}, name={}, position={}, title={}, department={}, education={}, age={}, gender={}, experience={}, skills={}",
                info.get("id"),
                info.get("name"),
                info.get("position"),
                info.get("title"),
                info.get("department"),
                info.get("education"),
                info.get("age"),
                info.get("gender"),
                info.get("experience"),
                info.get("skills")
            );
        });
        
        // 3. 构建分析请求
        TeamRequirementDTO requirementDTO = new TeamRequirementDTO();
        requirementDTO.setTeamSize(memberInfos.size());
        
        // 提取职位信息和人员详细信息
        List<TeamRequirementDTO.PositionRequirement> positions = memberInfos.stream()
            .map(info -> {
                TeamRequirementDTO.PositionRequirement position = new TeamRequirementDTO.PositionRequirement();
                position.setName(info.get("position") != null ? info.get("position").toString() : "未知职位");
                
                // 处理技能信息
                String skillsStr = (String) info.get("skills");
                List<String> skills = new ArrayList<>();
                if (skillsStr != null && !skillsStr.isEmpty()) {
                    // 假设技能是以逗号分隔的字符串
                    skills = Arrays.asList(skillsStr.split(","));
                }
                position.setSkills(skills);
                
                // 处理经验和教育信息
                position.setExperience(info.get("experience") != null ? 
                    Integer.parseInt(info.get("experience").toString()) : 0);
                position.setEducationLevel(info.get("education") != null ? 
                    info.get("education").toString() : "");
                
                // 添加其他可能需要的信息
                position.setTitle(info.get("title") != null ? 
                    info.get("title").toString() : "");
                position.setDepartment(info.get("department") != null ? 
                    info.get("department").toString() : "");
                position.setAge(info.get("age") != null ? 
                    Integer.parseInt(info.get("age").toString()) : 0);
                position.setGender(info.get("gender") != null ? 
                    info.get("gender").toString() : "");
                    
                return position;
            })
            .collect(Collectors.toList());
        requirementDTO.setPositions(positions);
        
        // 输出构建的分析请求
        log.info("准备调用分析服务, 请求参数: {}", requirementDTO);
        
        // 4. 调用分析服务
        TeamAnalysisResult result = teamAnalysisService.analyzeTeam(requirementDTO);
        result.setTeamId(teamId);
        
        // 输出分析结果
        log.info("分析服务返回结果: {}", result);
        
        return result;
    }

    @Override
    @Transactional
    public void deleteTeam(Integer teamId) {
        log.info("开始删除团队: {}", teamId);
        
        // 1. 获取团队信息
        Team team = teamMapper.selectById(teamId);
        if (team == null) {
            throw new BadRequestException("团队不存在");
        }

        // 2. 获取并删除所有职位需求及其关联的成员
        List<PositionNeed> positionNeeds = positionNeedMapper.findByTeamId(teamId);
        for (PositionNeed positionNeed : positionNeeds) {
            // 删除职位需求的成员关联
            positionNeedMemberMapper.deleteByPositionNeedId(positionNeed.getId());
            log.info("删除职位需求成员关联成功: {}", positionNeed.getId());
        }

        // 3. 删除所有职位需求
        if (!positionNeeds.isEmpty()) {
            List<Long> positionNeedIds = positionNeeds.stream()
                .map(PositionNeed::getId)
                .collect(Collectors.toList());
            positionNeedMapper.deleteBatchIds(positionNeedIds);
            log.info("删除职位需求成功: {}", positionNeedIds);
        }

        // 4. 删除团队基本信息（这会同时删除外键引用）
        teamMapper.deleteById(teamId);
        log.info("删除团队基本信息成功: {}", teamId);

        // 5. 删除团队需求
        TeamRequirement requirement = teamRequirementMapper.selectById(team.getRequirementId());
        if (requirement != null) {
            teamRequirementMapper.deleteById(requirement.getId());
            log.info("删除团队需求成功: {}", requirement.getId());
        }
    }

    @Override
    public PageResultDTO<TeamListItemDTO> getTeamList(PageDTO pageDTO) {
        log.info("开始获取团队列表, pageNum={}, pageSize={}", pageDTO.getPageNum(), pageDTO.getPageSize());
        
        // 1. 创建分页对象
        Page<Team> page = new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize());
        
        // 2. 创建查询条件（按创建时间倒序）
        LambdaQueryWrapper<Team> queryWrapper = new LambdaQueryWrapper<Team>()
            .orderByDesc(Team::getCreateTime);
        
        // 3. 执行分页查询
        page = teamMapper.selectPage(page, queryWrapper);
        
        // 4. 转换为DTO列表
        List<TeamListItemDTO> records = page.getRecords().stream().map(team -> {
            TeamListItemDTO dto = new TeamListItemDTO();
            dto.setId(team.getId());
            dto.setTeamName(team.getTeamName());
            dto.setTeamDescription(team.getTeamDescription());
            
            // 获取团队的所有职位需求
            List<PositionNeed> positionNeeds = positionNeedMapper.findByTeamId(team.getId());
            
            // 获取所有成员信息
            List<Map<String, Object>> allMembers = new ArrayList<>();
            for (PositionNeed need : positionNeeds) {
                List<Map<String, Object>> members = positionNeedMemberMapper.findRecommendedPersonnel(need.getId());
                allMembers.addAll(members);
            }
            
            // 设置团队总人数
            dto.setTotalSize(allMembers.size());
            
            // 计算年龄分布
            dto.setAgeDistribution(calculateAgeDistribution(allMembers));
            
            // 计算性别比例
            dto.setGenderRatio(calculateGenderRatio(allMembers));
            
            return dto;
        }).collect(Collectors.toList());
        
        // 5. 构建分页结果
        return PageResultDTO.of(records, page.getTotal(), pageDTO.getPageNum(), pageDTO.getPageSize());
    }
} 