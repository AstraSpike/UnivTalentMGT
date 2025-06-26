package com.hmall.portrait.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("personnel_basic_info")
public class PersonnelBasicInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String position;
    private String title;
    private String department;
    private LocalDate entryTime;
    private String education;
    private String degree;
    private String university;
    private String major;
    private String phone;
    private String email;
    private String politicalStatus;
    private String nativePlace;
    private String maritalStatus;
    private String ethnicity;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 