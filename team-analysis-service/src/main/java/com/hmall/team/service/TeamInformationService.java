package com.hmall.team.service;

import com.hmall.team.domain.dto.TeamCreateDTO;
import com.hmall.team.domain.dto.TeamUpdateDTO;
import com.hmall.team.domain.dto.TeamAnalysisResult;
import com.hmall.team.domain.entity.Team;

/**
 * 团队信息服务接口
 */
public interface TeamInformationService {
    /**
     * 创建团队
     * @param createDTO 团队创建信息
     * @return 创建的团队信息
     */
    Team createTeam(TeamCreateDTO createDTO);
    
    /**
     * 更新团队信息
     * @param updateDTO 团队更新信息
     * @return 更新后的团队信息
     */
    Team updateTeam(TeamUpdateDTO updateDTO);
    
    /**
     * 获取团队信息
     * @param teamId 团队ID
     * @return 团队信息
     */
    Team getTeamInfo(Integer teamId);
    
    /**
     * 分析团队
     * @param teamId 团队ID
     * @return 分析结果
     */
    TeamAnalysisResult analyzeTeam(Integer teamId);
} 