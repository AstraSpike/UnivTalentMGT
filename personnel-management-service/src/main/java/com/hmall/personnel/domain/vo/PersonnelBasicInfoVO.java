package com.hmall.personnel.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("人员基础信息视图对象")
public class PersonnelBasicInfoVO {
    @ApiModelProperty("人员ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("职务")
    private String position;

    @ApiModelProperty("职称")
    private String title;

    @ApiModelProperty("所属部门")
    private String department;

    @ApiModelProperty("入职时间")
    private String entryTime;

    @ApiModelProperty("学历")
    private String education;

    @ApiModelProperty("学位")
    private String degree;

    @ApiModelProperty("毕业院校")
    private String university;

    @ApiModelProperty("专业")
    private String major;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty("政治面貌")
    private String politicalStatus;

    @ApiModelProperty("籍贯")
    private String nativePlace;

    @ApiModelProperty("婚姻状况")
    private String maritalStatus;

    @ApiModelProperty("民族")
    private String ethnicity;

    @ApiModelProperty("标签列表")
    private List<TagsVO> tags;

    @ApiModelProperty("详细信息")
    private PersonnelDetailInfoVO detailInfo;
}