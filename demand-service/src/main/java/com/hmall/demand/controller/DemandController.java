package com.hmall.demand.controller;

import com.hmall.common.domain.PageDTO;
import com.hmall.common.domain.R;
import com.hmall.demand.domain.dto.DemandDTO;
import com.hmall.demand.service.DemandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * 需求管理控制器
 */
@RestController
@RequestMapping("/demand")
public class DemandController {

    @Resource
    private DemandService demandService;

    /**
     * 提交人才需求
     */
    @PostMapping("/submit")
    public R<String> submitDemand(@RequestBody DemandDTO demandData) {
        return demandService.submitDemand(demandData);
    }

    /**
     * 获取我的需求列表
     */
    @GetMapping("/myDemands")
    public R<PageDTO<DemandDTO>> getMyDemands(
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
    public R<PageDTO<DemandDTO>> getTodoTasks(
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
    public R<PageDTO<DemandDTO>> getCompletedTasks(
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
    public R<DemandDTO> getDemandDetail(@PathVariable Long id) {
        return demandService.getDemandDetail(id);
    }

    /**
     * 处理需求
     */
    @PostMapping("/process/{id}")
    public R<String> processDemand(@PathVariable Long id) {
        return demandService.processDemand(id);
    }
} 