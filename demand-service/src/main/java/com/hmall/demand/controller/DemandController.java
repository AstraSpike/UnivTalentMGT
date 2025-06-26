package com.hmall.demand.controller;

import com.hmall.common.domain.PageDTO;
import com.hmall.demand.domain.dto.ApiResponse;
import com.hmall.demand.domain.dto.DemandDTO;
import com.hmall.demand.service.DemandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * 需求管理控制器
 */
@RestController
@RequestMapping("/api/demand")
public class DemandController {

    @Resource
    private DemandService demandService;

    /**
     * 提交人才需求
     */
    @PostMapping("/submit")
    public ApiResponse<Void> submitDemand(@RequestBody DemandDTO demandData) {
        return demandService.submitDemand(demandData);
    }

    /**
     * 获取我的需求列表
     */
    @GetMapping("/myDemands")
    public ApiResponse<PageDTO<DemandDTO>> getMyDemands(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return demandService.getMyDemands(status, startDate, endDate, page, pageSize);
    }

    /**
     * 获取待办任务列表
     */
    @GetMapping("/todoTasks")
    public ApiResponse<PageDTO<DemandDTO>> getTodoTasks(
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return demandService.getTodoTasks(priority, type, page, pageSize);
    }

    /**
     * 获取已完成任务列表
     */
    @GetMapping("/completedTasks")
    public ApiResponse<PageDTO<DemandDTO>> getCompletedTasks(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String department,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return demandService.getCompletedTasks(startDate, endDate, department, page, pageSize);
    }

    /**
     * 获取需求详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<DemandDTO> getDemandDetail(@PathVariable Long id) {
        return demandService.getDemandDetail(id);
    }

    /**
     * 处理需求
     */
    @PostMapping("/process/{id}")
    public ApiResponse<Void> processDemand(@PathVariable Long id) {
        return demandService.processDemand(id);
    }
} 