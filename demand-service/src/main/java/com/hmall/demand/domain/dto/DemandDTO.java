package com.hmall.demand.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 需求数据传输对象
 */
@Data
public class DemandDTO {
    /**
     * 需求ID
     */
    private Long id;

    /**
     * 需求内容
     */
    private String taskRequirements;

    /**
     * 优先级：high, medium, low
     */
    private String taskPriority;

    /**
     * 期望完成时间
     */
    private LocalDate expectedCompletionTime;

    /**
     * 状态：pending, in_progress, completed
     */
    private String taskStatus;

    /**
     * 发起人ID
     */
    private Long initiatorId;

    /**
     * 处理人ID
     */
    private Long receiverId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 