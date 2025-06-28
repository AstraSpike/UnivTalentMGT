package com.hmall.team.controller;

import com.hmall.common.domain.PageDTO;
import com.hmall.team.domain.dto.ApiResponse;
import com.hmall.team.domain.dto.TeamRequirementDTO;
import com.hmall.team.domain.dto.TeamAnalysisResult;
import com.hmall.team.service.TeamAnalysisService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * 班子分析控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/team")
public class TeamAnalysisController {

    @Resource
    private TeamAnalysisService teamAnalysisService;

    /**
     * 生成班子推荐和分析
     */
    @PostMapping("/analyze")
    public ApiResponse<TeamAnalysisResult> analyzeTeam(@RequestBody TeamRequirementDTO requirement) {
        try {
            TeamAnalysisResult result = teamAnalysisService.analyzeTeam(requirement);
            return ApiResponse.success(result);
        } catch (Exception e) {
            log.error("班子分析失败", e);
            return ApiResponse.error("班子分析失败：" + e.getMessage());
        }
    }

    /**
     * 获取分析历史记录
     */
    @GetMapping("/history")
    public ApiResponse<PageDTO<TeamAnalysisResult>> getAnalysisHistory(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            PageDTO<TeamAnalysisResult> result = teamAnalysisService.getAnalysisHistory(page, pageSize);
            return ApiResponse.success(result);
        } catch (Exception e) {
            log.error("获取分析历史失败", e);
            return ApiResponse.error("获取分析历史失败：" + e.getMessage());
        }
    }

    /**
     * 获取分析详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TeamAnalysisResult> getAnalysisDetail(@PathVariable Long id) {
        try {
            TeamAnalysisResult result = teamAnalysisService.getAnalysisDetail(id);
            return result != null ? ApiResponse.success(result) : ApiResponse.error("分析记录不存在");
        } catch (Exception e) {
            log.error("获取分析详情失败", e);
            return ApiResponse.error("获取分析详情失败：" + e.getMessage());
        }
    }
} 