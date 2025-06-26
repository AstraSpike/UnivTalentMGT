package com.hmall.demand.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务工单实体类
 */
@Data
@TableName("task_orders")
public class TaskOrder {
    /**
     * 工单ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 任务发起用户ID
     */
    private Long initiatorId;

    /**
     * 任务接收用户ID
     */
    private Long receiverId;

    /**
     * 任务需求内容
     */
    private String taskRequirements;

    /**
     * 任务优先级：high, medium, low
     */
    private String taskPriority;

    /**
     * 期望完成时间
     */
    private LocalDate expectedCompletionTime;

    /**
     * 任务状态：pending, in_progress, completed
     */
    private String taskStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 