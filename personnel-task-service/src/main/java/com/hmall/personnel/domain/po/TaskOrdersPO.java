package com.hmall.personnel.domain.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 任务订单持久化对象（PO）
 * 使用JPA注解映射数据库表
 */
@Data
@Entity
@Table(name = "task_orders")
public class TaskOrdersPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "initiator_id")
    private Integer initiatorId;

    @Column(name = "receiver_id")
    private Integer receiverId;

    private String title;
    private String type;
    private String department;

    @Column(name = "task_requirements")
    private String taskRequirements;

    @Column(name = "task_priority")
    private String taskPriority;

    @Column(name = "expected_completion_time")
    private Date expectedCompletionTime;

    @Column(name = "task_status")
    private String taskStatus;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}