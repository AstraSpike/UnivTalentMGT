package com.hmall.training.domain.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "personnel_training_needs")
public class PersonnelTrainingNeedsPO {
    @Id
    private Integer id;

    @Column(name = "personnel_id")
    private Integer personnelId;

    private String abilityShortcomings;
    private String recommendedCourses;
    private Date createTime;
    private Date updateTime;
}
