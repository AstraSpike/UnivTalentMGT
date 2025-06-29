package com.hmall.training.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "personnel_basic_info")
public class PersonnelBasicInfoPO {
    @Id
    private Integer id;
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
    private Date createTime;
    private Date updateTime;
}
