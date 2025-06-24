// personnel-management-service/src/main/java/com/hmall/personnel/po/PersonnelBasicInfoPO.java
package com.hmall.personnel.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("personnel_basic_info")
public class PersonnelBasicInfoPO {
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
}