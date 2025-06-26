package com.hmall.demand.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.common.domain.PageDTO;
import com.hmall.common.domain.R;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.UserContext;
import com.hmall.demand.domain.dto.DemandDTO;
import com.hmall.demand.domain.entity.TaskOrder;
import com.hmall.demand.mapper.TaskOrderMapper;
import com.hmall.demand.service.DemandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 需求服务实现类
 */
@Service
public class DemandServiceImpl implements DemandService {

    @Resource
    private TaskOrderMapper taskOrderMapper;

    @Override
    public R<String> submitDemand(DemandDTO demandData) {
        // 创建任务工单
        TaskOrder taskOrder = new TaskOrder();
        BeanUtils.copyProperties(demandData, taskOrder);
        
        // 设置基本信息
        taskOrder.setInitiatorId(UserContext.getUser());
        taskOrder.setTaskStatus("pending");
        taskOrder.setCreateTime(LocalDateTime.now());
        taskOrder.setUpdateTime(LocalDateTime.now());

        // 如果没有设置接收者，则返回错误
        if (taskOrder.getReceiverId() == null) {
            return R.error("请指定任务接收者");
        }

        // 保存到数据库
        taskOrderMapper.insert(taskOrder);
        return R.ok("需求提交成功");
    }

    @Override
    public R<PageDTO<DemandDTO>> getMyDemands(String status, LocalDate startDate, LocalDate endDate, Integer page, Integer pageSize) {
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
        return R.ok(PageDTO.of(taskPage, demandList));
    }

    @Override
    public R<PageDTO<DemandDTO>> getTodoTasks(String priority, String type, Integer page, Integer pageSize) {
        // 构建查询条件
        LambdaQueryWrapper<TaskOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskOrder::getReceiverId, UserContext.getUser())
              .eq(TaskOrder::getTaskStatus, "pending");
        
        // 添加筛选条件
        if (priority != null) {
            wrapper.eq(TaskOrder::getTaskPriority, priority);
        }
        // TODO: 添加类型筛选
        
        // 分页查询
        Page<TaskOrder> taskPage = taskOrderMapper.selectPage(new Page<>(page, pageSize), wrapper);
        
        // 转换为DTO
        List<DemandDTO> demandList = BeanUtils.copyList(taskPage.getRecords(), DemandDTO.class);
        return R.ok(PageDTO.of(taskPage, demandList));
    }

    @Override
    public R<PageDTO<DemandDTO>> getCompletedTasks(LocalDate startDate, LocalDate endDate, String department, Integer page, Integer pageSize) {
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
        // TODO: 添加部门筛选
        
        // 分页查询
        Page<TaskOrder> taskPage = taskOrderMapper.selectPage(new Page<>(page, pageSize), wrapper);
        
        // 转换为DTO
        List<DemandDTO> demandList = BeanUtils.copyList(taskPage.getRecords(), DemandDTO.class);
        return R.ok(PageDTO.of(taskPage, demandList));
    }

    @Override
    public R<DemandDTO> getDemandDetail(Long id) {
        // 查询任务详情
        TaskOrder taskOrder = taskOrderMapper.selectById(id);
        if (taskOrder == null) {
            return R.error("需求不存在");
        }
        
        // 转换为DTO
        DemandDTO demandDTO = BeanUtils.copyBean(taskOrder, DemandDTO.class);
        return R.ok(demandDTO);
    }

    @Override
    public R<String> processDemand(Long id) {
        // 查询任务
        TaskOrder taskOrder = taskOrderMapper.selectById(id);
        if (taskOrder == null) {
            return R.error("需求不存在");
        }
        
        // 更新状态
        taskOrder.setTaskStatus("completed");
        taskOrder.setUpdateTime(LocalDateTime.now());
        taskOrderMapper.updateById(taskOrder);
        
        return R.ok("需求处理成功");
    }
} 