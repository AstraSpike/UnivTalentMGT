package com.hmall.personnel.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * 任务订单数据传输对象（DTO, Data Transfer Object）
 * 用于服务层之间的数据传递
 * 通常会包含一些业务处理需要的字段
 */
@Data
public class TaskOrdersDTO {
    private Integer id;               // 任务ID
    private Integer initiatorId;      // 任务发起人ID
    private Integer receiverId;       // 任务接收人ID
    private String title;             // 任务标题
    private String type;              // 任务类型
    private String department;        // 所属部门
    private String taskRequirements;  // 任务要求
    private String taskPriority;      // 任务优先级
    private Date expectedCompletionTime; // 预期完成时间
    private String taskStatus;        // 任务状态
    private Date createTime;          // 创建时间
    private Date updateTime;          // 更新时间
    private Boolean isUrgent;         // 是否紧急（业务计算字段）

}