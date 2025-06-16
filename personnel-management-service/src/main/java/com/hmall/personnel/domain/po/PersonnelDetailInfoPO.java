package com.hmall.personnel.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("personnel_detail_info")
public class PersonnelDetailInfoPO {
    private Long id;
    private Long personnelId;
    private String idCardNumber;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String homeAddress;
    private String teachingCourses;
    private Integer courseHours;
    private Integer teachingClasses;
    private Double teachingEvaluationScore;
    private String teachingAwards;
    private String teachingMaterials;
    private String researchJournals;
    private String researchPapers;
    private Date publicationTime;
    private Double impactFactor;
    private String researchProjects;
    private String projectNumbers;
    private Double projectFunds;
    private String projectTimePeriod;
    private String patents;
    private String patentTypes;
    private Date patentAuthorizationTime;
    private String managementDepartments;
    private String managementResponsibilities;
    private Integer managementTeamSize;
    private String managementDecisions;
    private String trainingNames;
    private String trainingTimes;
    private String trainingLocations;
    private String trainingContents;
    private String trainingCertificates;
    private Date createTime;
    private Date updateTime;
}