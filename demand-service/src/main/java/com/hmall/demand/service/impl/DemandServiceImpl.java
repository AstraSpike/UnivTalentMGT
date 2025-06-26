package com.hmall.demand.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.common.domain.PageDTO;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.UserContext;
import com.hmall.demand.domain.dto.ApiResponse;
import com.hmall.demand.domain.dto.DemandDTO;
import com.hmall.demand.domain.entity.TaskOrder;
import com.hmall.demand.mapper.TaskOrderMapper;
import com.hmall.demand.service.DemandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 需求服务实现类
 */
@Slf4j
@Service
public class DemandServiceImpl implements DemandService {

    @Resource
    private TaskOrderMapper taskOrderMapper;

    @Override
    public ApiResponse<Void> submitDemand(DemandDTO demandData) {
        // 参数校验
        if (demandData == null) {
            return ApiResponse.error("需求数据不能为空");
        }
        if (StringUtils.isBlank(demandData.getTitle())) {
            return ApiResponse.error("需求标题不能为空");
        }
        if (StringUtils.isBlank(demandData.getTaskRequirements())) {
            return ApiResponse.error("需求内容不能为空");
        }
        if (StringUtils.isBlank(demandData.getTaskPriority())) {
            return ApiResponse.error("需求优先级不能为空");
        }
        if (demandData.getReceiverId() == null) {
            return ApiResponse.error("请指定任务接收者");
        }
        if (demandData.getExpectedCompletionTime() == null) {
            return ApiResponse.error("请设置期望完成时间");
        }
//        if (demandData.getExpectedCompletionTime().isBefore(LocalDate.now())) {
//            return ApiResponse.error("期望完成时间不能早于当前日期");
//        }

        try {
            // 获取当前用户
            Long currentUser = UserContext.getUser();
            if (currentUser == null) {
                return ApiResponse.error("用户未登录");
            }

            // 创建任务工单
            TaskOrder taskOrder = new TaskOrder();
            BeanUtils.copyProperties(demandData, taskOrder);
            
            // 设置基本信息
            taskOrder.setInitiatorId(currentUser);
            taskOrder.setTaskStatus("pending");
            taskOrder.setCreateTime(LocalDateTime.now());
            taskOrder.setUpdateTime(LocalDateTime.now());

            // 保存到数据库
            int rows = taskOrderMapper.insert(taskOrder);
            if (rows != 1) {
                return ApiResponse.error("需求提交失败，请重试");
            }

            return ApiResponse.success("需求提交成功");
        } catch (Exception e) {
            log.error("提交需求失败", e);
            return ApiResponse.error("系统异常，请稍后重试");
        }
    }

    @Override
    public ApiResponse<PageDTO<DemandDTO>> getMyDemands(String status, LocalDate startDate, LocalDate endDate, Integer page, Integer pageSize) {
        // 构建查询条件
        LambdaQueryWrapper<TaskOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskOrder::getInitiatorId, UserContext.getUser());
        
        // 添加筛选条件
        if (status != null) {
            wrapper.eq(TaskOrder::getTaskStatus, status);
        }
        if (startDate != null) {
            wrapper.ge(TaskOrder::getCreateTime, startDate.atStartOfDay());
        }
        if (endDate != null) {
            wrapper.le(TaskOrder::getCreateTime, endDate.plusDays(1).atStartOfDay());
        }
        
        // 分页查询
        Page<TaskOrder> taskPage = taskOrderMapper.selectPage(new Page<>(page, pageSize), wrapper);
        
        // 转换为DTO
        List<DemandDTO> demandList = BeanUtils.copyList(taskPage.getRecords(), DemandDTO.class);
        return ApiResponse.success(PageDTO.of(taskPage, demandList));
    }

    @Override
    public ApiResponse<PageDTO<DemandDTO>> getTodoTasks(String priority, String type, Integer page, Integer pageSize) {
        // 构建查询条件
        LambdaQueryWrapper<TaskOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskOrder::getReceiverId, UserContext.getUser())
              .eq(TaskOrder::getTaskStatus, "pending");
        
        // 添加筛选条件
        if (priority != null) {
            wrapper.eq(TaskOrder::getTaskPriority, priority);
        }
        if (type != null) {
            wrapper.eq(TaskOrder::getType, type);
        }
        
        // 分页查询
        Page<TaskOrder> taskPage = taskOrderMapper.selectPage(new Page<>(page, pageSize), wrapper);
        
        // 转换为DTO
        List<DemandDTO> demandList = BeanUtils.copyList(taskPage.getRecords(), DemandDTO.class);
        return ApiResponse.success(PageDTO.of(taskPage, demandList));
    }

    @Override
    public ApiResponse<PageDTO<DemandDTO>> getCompletedTasks(LocalDate startDate, LocalDate endDate, String department, Integer page, Integer pageSize) {
        // 构建查询条件
        LambdaQueryWrapper<TaskOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskOrder::getReceiverId, UserContext.getUser())
              .eq(TaskOrder::getTaskStatus, "completed");
        
        // 添加筛选条件
        if (startDate != null) {
            wrapper.ge(TaskOrder::getCreateTime, startDate.atStartOfDay());
        }
        if (endDate != null) {
            wrapper.le(TaskOrder::getCreateTime, endDate.plusDays(1).atStartOfDay());
        }
        if (department != null) {
            wrapper.eq(TaskOrder::getDepartment, department);
        }
        
        // 分页查询
        Page<TaskOrder> taskPage = taskOrderMapper.selectPage(new Page<>(page, pageSize), wrapper);
        
        // 转换为DTO
        List<DemandDTO> demandList = BeanUtils.copyList(taskPage.getRecords(), DemandDTO.class);
        return ApiResponse.success(PageDTO.of(taskPage, demandList));
    }

    @Override
    public ApiResponse<DemandDTO> getDemandDetail(Long id) {
        // 查询任务详情
        TaskOrder taskOrder = taskOrderMapper.selectById(id);
        if (taskOrder == null) {
            return ApiResponse.<DemandDTO>error("需求不存在");
        }
        
        // 转换为DTO
        DemandDTO demandDTO = BeanUtils.copyBean(taskOrder, DemandDTO.class);
        return ApiResponse.success(demandDTO);
    }

    @Override
    public ApiResponse<Void> processDemand(Long id) {
        // 查询任务
        TaskOrder taskOrder = taskOrderMapper.selectById(id);
        if (taskOrder == null) {
            return ApiResponse.error("需求不存在");
        }
        
        // 更新状态
        taskOrder.setTaskStatus("completed");
        taskOrder.setUpdateTime(LocalDateTime.now());
        taskOrderMapper.updateById(taskOrder);
        
        return ApiResponse.success("需求处理成功");
    }
} 