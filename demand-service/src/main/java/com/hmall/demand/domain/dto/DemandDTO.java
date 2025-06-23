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
     * 需求标题
     */
    private String title;

    /**
     * 需求类型
     */
    private String type;

    /**
     * 需求部门
     */
    private String department;

    /**
     * 需求内容
     */
    private String requirements;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 期望完成时间
     */
    private LocalDate expectedCompletionTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 发起人ID
     */
    private Long initiatorId;

    /**
     * 发起人姓名
     */
    private String initiatorName;

    /**
     * 处理人ID
     */
    private Long receiverId;

    /**
     * 处理人姓名
     */
    private String receiverName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 