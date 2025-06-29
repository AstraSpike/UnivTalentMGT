package com.hmall.team.controller;

import com.hmall.team.domain.dto.ApiResponse;
import com.hmall.team.domain.dto.TeamCreateDTO;
import com.hmall.team.domain.dto.TeamUpdateDTO;
import com.hmall.team.domain.dto.TeamAnalysisResult;
import com.hmall.team.domain.entity.Team;
import com.hmall.team.service.TeamInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 团队信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/team/info")
@RequiredArgsConstructor
public class TeamInformationController {

    private final TeamInformationService teamInformationService;

    /**
     * 创建团队
     */
    
    @PostMapping("/create")
    public ApiResponse<Team> createTeam(@RequestBody TeamCreateDTO createDTO) {
        try {
            Team team = teamInformationService.createTeam(createDTO);
            return ApiResponse.success(team);
        } catch (Exception e) {
            log.error("创建团队失败", e);
            return ApiResponse.error("创建团队失败：" + e.getMessage());
        }
    }

    /**
     * 更新团队信息
     */
    @PutMapping("/update")
    public ApiResponse<Team> updateTeam(@RequestBody TeamUpdateDTO updateDTO) {
        try {
            Team team = teamInformationService.updateTeam(updateDTO);
            return ApiResponse.success(team);
        } catch (Exception e) {
            log.error("更新团队失败", e);
            return ApiResponse.error("更新团队失败：" + e.getMessage());
        }
    }

    /**
     * 获取团队信息
     */
    @GetMapping("/{teamId}")
    public ApiResponse<Team> getTeamInfo(@PathVariable Integer teamId) {
        try {
            Team team = teamInformationService.getTeamInfo(teamId);
            return team != null ? ApiResponse.success(team) : ApiResponse.error("团队不存在");
        } catch (Exception e) {
            log.error("获取团队信息失败", e);
            return ApiResponse.error("获取团队信息失败：" + e.getMessage());
        }
    }

    /**
     * 分析团队
     */
    @PostMapping("/analyze/{teamId}")
    public ApiResponse<TeamAnalysisResult> analyzeTeam(@PathVariable Integer teamId) {
        try {
            TeamAnalysisResult result = teamInformationService.analyzeTeam(teamId);
            return ApiResponse.success(result);
        } catch (Exception e) {
            log.error("团队分析失败", e);
            return ApiResponse.error("团队分析失败：" + e.getMessage());
        }
    }
} 