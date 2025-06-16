package com.hmall.personnel.domain.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PersonnelBasicInfoDTO {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String position;
    private String title;
    private String department;
    private Date entryTime;
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

    // 关联对象
    private List<TagsDTO> tags;
    private PersonnelDetailInfoDTO detailInfo;
}