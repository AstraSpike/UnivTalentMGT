package com.hmall.team.controller;

import com.hmall.common.domain.ApiResponse;
import com.hmall.team.domain.dto.TeamCreateDTO;
import com.hmall.team.domain.dto.TeamCreateResultDTO;
import com.hmall.team.domain.dto.TeamUpdateDTO;
import com.hmall.team.domain.dto.TeamAnalysisResult;
import com.hmall.team.domain.dto.TeamListItemDTO;
import com.hmall.team.domain.dto.PageDTO;
import com.hmall.team.domain.dto.PageResultDTO;
//import com.hmall.team.domain.dto.TeamDetailDTO;
import com.hmall.team.domain.entity.Team;
import com.hmall.team.service.TeamInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 团队信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/team/info")
@RequiredArgsConstructor
@Tag(name = "团队信息管理", description = "团队的CRUD操作和分析功能")
public class TeamInformationController {

    private final TeamInformationService teamInformationService;

    /**
     * 获取团队列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取团队列表")
    public ApiResponse<PageResultDTO<TeamListItemDTO>> getTeamList(PageDTO pageDTO) {
        try {
            PageResultDTO<TeamListItemDTO> result = teamInformationService.getTeamList(pageDTO);
            return ApiResponse.ok(result);
        } catch (Exception e) {
            log.error("获取团队列表失败", e);
            return ApiResponse.error("获取团队列表失败: " + e.getMessage());
        }
    }

    /**
     * 创建团队
     */
    @PostMapping("/create")
    @Operation(summary = "创建团队")
    public ApiResponse<TeamCreateResultDTO> createTeam(@RequestBody TeamCreateDTO createDTO) {
        try {
            TeamCreateResultDTO result = teamInformationService.createTeam(createDTO);
            return ApiResponse.ok(result);
        } catch (Exception e) {
            log.error("创建团队失败", e);
            return ApiResponse.error("创建团队失败: " + e.getMessage());
        }
    }

    /**
     * 更新团队信息
     */
    @PutMapping("/update")
    @Operation(summary = "更新团队信息")
    public ApiResponse<TeamCreateResultDTO> updateTeam(@RequestBody TeamUpdateDTO updateDTO) {
        try {
            Team team = teamInformationService.updateTeam(updateDTO);
            // 更新完成后，获取最新的团队详细信息
            TeamCreateResultDTO result = teamInformationService.getTeamInfo(team.getId());
            return ApiResponse.ok(result);
        } catch (Exception e) {
            log.error("更新团队失败", e);
            return ApiResponse.error("更新团队失败: " + e.getMessage());
        }
    }

    /**
     * 获取团队信息
     */
    @GetMapping("/{teamId}")
    @Operation(summary = "获取团队信息")
    public ApiResponse<TeamCreateResultDTO> getTeamInfo(@PathVariable Integer teamId) {
        try {
            TeamCreateResultDTO result = teamInformationService.getTeamInfo(teamId);
            return ApiResponse.ok(result);
        } catch (Exception e) {
            log.error("获取团队信息失败", e);
            return ApiResponse.error("获取团队信息失败: " + e.getMessage());
        }
    }

    /**
     * 删除团队
     */
    @DeleteMapping("/{teamId}")
    @Operation(summary = "删除团队")
    public ApiResponse<Void> deleteTeam(@PathVariable Integer teamId) {
        try {
            teamInformationService.deleteTeam(teamId);
            return ApiResponse.ok(null);
        } catch (Exception e) {
            log.error("删除团队失败", e);
            return ApiResponse.error("删除团队失败: " + e.getMessage());
        }
    }

    /**
     * 分析团队
     */
    @PostMapping("/analyze/{teamId}")
    @Operation(summary = "分析团队")
    public ApiResponse<TeamAnalysisResult> analyzeTeam(@PathVariable Integer teamId) {
        try {
            TeamAnalysisResult result = teamInformationService.analyzeTeam(teamId);
            return ApiResponse.ok(result);
        } catch (Exception e) {
            log.error("团队分析失败", e);
            return ApiResponse.error("团队分析失败: " + e.getMessage());
        }
    }
} 