package com.hmall.demand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.demand.domain.entity.TaskOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务工单Mapper接口
 */
@Mapper
public interface TaskOrderMapper extends BaseMapper<TaskOrder> {
} 