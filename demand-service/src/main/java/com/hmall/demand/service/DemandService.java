package com.hmall.demand.service;

import com.hmall.common.domain.PageDTO;
import com.hmall.demand.domain.dto.ApiResponse;
import com.hmall.demand.domain.dto.DemandDTO;

import java.time.LocalDate;

/**
 * 需求服务接口
 */
public interface DemandService {
    /**
     * 提交需求
     */
    ApiResponse<Void> submitDemand(DemandDTO demandData);

    /**
     * 获取我的需求列表
     */
    ApiResponse<PageDTO<DemandDTO>> getMyDemands(String status, LocalDate startDate, LocalDate endDate, Integer page, Integer pageSize);

    /**
     * 获取待办任务列表
     */
    ApiResponse<PageDTO<DemandDTO>> getTodoTasks(String priority, String type, Integer page, Integer pageSize);

    /**
     * 获取已完成任务列表
     */
    ApiResponse<PageDTO<DemandDTO>> getCompletedTasks(LocalDate startDate, LocalDate endDate, String department, Integer page, Integer pageSize);

    /**
     * 获取需求详情
     */
    ApiResponse<DemandDTO> getDemandDetail(Long id);

    /**
     * 处理需求
     */
    ApiResponse<Void> processDemand(Long id);
} 