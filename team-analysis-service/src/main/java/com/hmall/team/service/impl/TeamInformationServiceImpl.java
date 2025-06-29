package com.hmall.team.service.impl;

import com.hmall.common.exception.BadRequestException;
import com.hmall.team.domain.dto.TeamCreateDTO;
import com.hmall.team.domain.dto.TeamUpdateDTO;
import com.hmall.team.domain.dto.TeamAnalysisResult;
import com.hmall.team.domain.dto.TeamRequirementDTO;
import com.hmall.team.domain.entity.Team;
import com.hmall.team.domain.entity.TeamMember;
import com.hmall.team.mapper.TeamMapper;
import com.hmall.team.mapper.TeamMemberMapper;
import com.hmall.team.service.TeamAnalysisService;
import com.hmall.team.service.TeamInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 团队信息服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamInformationServiceImpl implements TeamInformationService {

    private final TeamMapper teamMapper;
    private final TeamMemberMapper teamMemberMapper;
    private final TeamAnalysisService teamAnalysisService;

    @Override
    @Transactional
    public Team createTeam(TeamCreateDTO createDTO) {
        // 1. 创建团队基本信息
        Team team = new Team();
        team.setTeamName(createDTO.getTeamName());
        team.setTeamDescription(createDTO.getTeamDescription());
        team.setRequirementId(createDTO.getRequirementId());
        team.setCreateTime(LocalDateTime.now());
        team.setUpdateTime(LocalDateTime.now());
        
        teamMapper.insert(team);
        
        // 2. 添加团队成员
        if (createDTO.getMemberIds() != null && !createDTO.getMemberIds().isEmpty()) {
            List<TeamMember> members = createDTO.getMemberIds().stream()
                .map(memberId -> {
                    TeamMember member = new TeamMember();
                    member.setTeamId(team.getId());
                    member.setPersonnelId(memberId);
                    member.setCreateTime(LocalDateTime.now());
                    member.setUpdateTime(LocalDateTime.now());
                    return member;
                })
                .collect(Collectors.toList());
            
            teamMemberMapper.insertBatch(members);
        }
        
        return team;
    }

    @Override
    @Transactional
    public Team updateTeam(TeamUpdateDTO updateDTO) {
        // 1. 检查团队是否存在
        Team team = teamMapper.selectById(updateDTO.getId());
        if (team == null) {
            throw new BadRequestException("团队不存在");
        }
        
        // 2. 更新团队基本信息
        team.setTeamName(updateDTO.getTeamName());
        team.setTeamDescription(updateDTO.getTeamDescription());
        team.setUpdateTime(LocalDateTime.now());
        teamMapper.updateById(team);
        
        // 3. 处理成员变更
        if (updateDTO.getAddMemberIds() != null && !updateDTO.getAddMemberIds().isEmpty()) {
            List<TeamMember> newMembers = updateDTO.getAddMemberIds().stream()
                .map(memberId -> {
                    TeamMember member = new TeamMember();
                    member.setTeamId(team.getId());
                    member.setPersonnelId(memberId);
                    member.setCreateTime(LocalDateTime.now());
                    member.setUpdateTime(LocalDateTime.now());
                    return member;
                })
                .collect(Collectors.toList());
            
            teamMemberMapper.insertBatch(newMembers);
        }
        
        if (updateDTO.getRemoveMemberIds() != null && !updateDTO.getRemoveMemberIds().isEmpty()) {
            teamMemberMapper.deleteByTeamIdAndPersonnelIds(team.getId(), updateDTO.getRemoveMemberIds());
        }
        
        return team;
    }

    @Override
    public Team getTeamInfo(Integer teamId) {
        return teamMapper.selectById(teamId);
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
} 