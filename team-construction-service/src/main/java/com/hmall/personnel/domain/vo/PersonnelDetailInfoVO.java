package com.hmall.personnel.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("人员详细信息视图对象")
public class PersonnelDetailInfoVO {
    @ApiModelProperty("详细信息ID")
    private Long id;

    @ApiModelProperty("关联的人员ID")
    private Long personnelId;

    @ApiModelProperty("身份证号")
    private String idCardNumber;

    @ApiModelProperty("紧急联系人姓名")
    private String emergencyContactName;

    @ApiModelProperty("紧急联系人电话")
    private String emergencyContactPhone;

    @ApiModelProperty("家庭住址")
    private String homeAddress;

    @ApiModelProperty("主讲课程名称")
    private String teachingCourses;

    @ApiModelProperty("课程学时")
    private Integer courseHours;

    @ApiModelProperty("授课班级数量")
    private Integer teachingClasses;

    @ApiModelProperty("教学评价分数")
    private Double teachingEvaluationScore;

    @ApiModelProperty("教学成果奖名称及等级")
    private String teachingAwards;

    @ApiModelProperty("编写教材名称及出版时间")
    private String teachingMaterials;

    @ApiModelProperty("论文发表期刊名称")
    private String researchJournals;

    @ApiModelProperty("论文题目")
    private String researchPapers;

    @ApiModelProperty("发表时间")
    private String publicationTime;

    @ApiModelProperty("影响因子")
    private Double impactFactor;

    @ApiModelProperty("科研项目名称")
    private String researchProjects;

    @ApiModelProperty("项目编号")
    private String projectNumbers;

    @ApiModelProperty("项目经费")
    private Double projectFunds;

    @ApiModelProperty("项目起止时间")
    private String projectTimePeriod;

    @ApiModelProperty("专利名称")
    private String patents;

    @ApiModelProperty("专利类型")
    private String patentTypes;

    @ApiModelProperty("授权时间")
    private String patentAuthorizationTime;

    @ApiModelProperty("所管理部门名称")
    private String managementDepartments;

    @ApiModelProperty("管理职责描述")
    private String managementResponsibilities;

    @ApiModelProperty("管理团队人数")
    private Integer managementTeamSize;

    @ApiModelProperty("重大管理决策事项描述")
    private String managementDecisions;

    @ApiModelProperty("参加培训名称")
    private String trainingNames;

    @ApiModelProperty("培训时间")
    private String trainingTimes;

    @ApiModelProperty("培训地点")
    private String trainingLocations;

    @ApiModelProperty("培训内容概述")
    private String trainingContents;

    @ApiModelProperty("培训证书名称")
    private String trainingCertificates;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("更新时间")
    private String updateTime;
}