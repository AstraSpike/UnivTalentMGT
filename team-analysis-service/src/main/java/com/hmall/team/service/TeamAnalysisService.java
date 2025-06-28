package com.hmall.team.service;

import com.hmall.common.domain.PageDTO;
import com.hmall.team.domain.dto.TeamRequirementDTO;
import com.hmall.team.domain.dto.TeamAnalysisResult;

/**
 * 班子分析服务接口
 */
public interface TeamAnalysisService {
    /**
     * 分析班子
     * @param requirement 班子需求
     * @return 分析结果
     */
    TeamAnalysisResult analyzeTeam(TeamRequirementDTO requirement);

    /**
     * 获取分析历史
     * @param page 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    PageDTO<TeamAnalysisResult> getAnalysisHistory(Integer page, Integer pageSize);

    /**
     * 获取分析详情
     * @param id 分析ID
     * @return 分析结果
     */
    TeamAnalysisResult getAnalysisDetail(Long id);
} 