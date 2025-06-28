package com.hmall.personnel.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * 任务订单视图对象（VO, View Object）
 * 用于前端展示的数据模型
 * 通常会包含格式化后的字段或组合字段
 */
@Data
public class TaskOrdersVO {
    private Integer id;               // 任务ID
    private String initiatorName;     // 任务发起人姓名（前端展示用）
    private String receiverName;      // 任务接收人姓名（前端展示用）
    private String title;             // 任务标题
    private String type;              // 任务类型
    private String department;        // 所属部门
    private String taskRequirements;  // 任务要求
    private String taskPriority;      // 任务优先级
    private String expectedCompletionTimeStr; // 预期完成时间（格式化字符串）
    private Date expectedCompletionTime; // 预期完成时间（原始日期类型）
    private String taskStatus;        // 任务状态
    private String createTimeStr;     // 创建时间（格式化字符串）
    private Date createTime;          // 创建时间（原始日期类型）
    private String taskPriorityText;  // 优先级文本描述（如：高/中/低）
    private String taskStatusText;    // 状态文本描述（如：未开始/进行中/已完成）
}