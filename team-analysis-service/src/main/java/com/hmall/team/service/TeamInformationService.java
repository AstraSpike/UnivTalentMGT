package com.hmall.team.service;

import com.hmall.team.domain.dto.*;
import com.hmall.team.domain.entity.Team;
import java.util.List;

/**
 * 团队信息服务接口
 */
public interface TeamInformationService {
    /**
     * 创建团队
     * @param createDTO 创建团队的请求数据
     * @return 创建结果
     */
    TeamCreateResultDTO createTeam(TeamCreateDTO createDTO);
    
    /**
     * 更新团队信息
     * @param updateDTO 更新团队的请求数据
     * @return 更新后的团队信息
     */
    Team updateTeam(TeamUpdateDTO updateDTO);
    
    /**
     * 获取团队信息
     * @param teamId 团队ID
     * @return 团队详细信息
     */
    TeamCreateResultDTO getTeamInfo(Integer teamId);
    
    /**
     * 删除团队
     * @param teamId 团队ID
     */
    void deleteTeam(Integer teamId);
    
    /**
     * 分析团队
     * @param teamId 团队ID
     * @return 分析结果
     */
    TeamAnalysisResult analyzeTeam(Integer teamId);

    /**
     * 获取团队列表
     * @param pageDTO 分页参数
     * @return 分页后的团队列表
     */
    PageResultDTO<TeamListItemDTO> getTeamList(PageDTO pageDTO);
} 