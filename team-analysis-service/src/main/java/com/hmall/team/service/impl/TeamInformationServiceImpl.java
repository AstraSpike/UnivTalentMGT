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
        
        // 2. 获取团队成员信息
        List<Map<String, Object>> memberInfos = teamMemberMapper.selectMemberInfosByTeamId(teamId);
        
        // 3. 构建分析请求
        TeamRequirementDTO requirementDTO = new TeamRequirementDTO();
        requirementDTO.setTeamSize(memberInfos.size());
        
        // 提取职位信息
        List<TeamRequirementDTO.PositionRequirement> positions = memberInfos.stream()
            .map(info -> {
                TeamRequirementDTO.PositionRequirement position = new TeamRequirementDTO.PositionRequirement();
                position.setName(info.get("position").toString());
                // 这里可以根据需要设置其他属性
                position.setSkills(Collections.emptyList());
                position.setExperience(0);
                position.setEducationLevel("");
                return position;
            })
            .collect(Collectors.toList());
        requirementDTO.setPositions(positions);
        
        // 4. 调用分析服务
        TeamAnalysisResult result = teamAnalysisService.analyzeTeam(requirementDTO);
        result.setTeamId(teamId);
        
        return result;
    }
} 