package com.hmall.portrait.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("personnel_detail_info")
public class PersonnelDetailInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long personnelId;
    private String idCardNumber;
    private String teachingCourses;
    private Integer courseHours;
    private Integer teachingClasses;
    private BigDecimal teachingEvaluationScore;
    private String teachingAwards;
    private String teachingMaterials;
    private String researchJournals;
    private String researchPapers;
    private LocalDate publicationTime;
    private BigDecimal impactFactor;
    private String researchProjects;
    private String projectNumbers;
    private BigDecimal projectFunds;
    private String projectTimePeriod;
    private String patents;
    private String patentTypes;
    private LocalDate patentAuthorizationTime;
    private String managementDepartments;
    private String managementResponsibilities;
    private Integer managementTeamSize;
    private String managementDecisions;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 