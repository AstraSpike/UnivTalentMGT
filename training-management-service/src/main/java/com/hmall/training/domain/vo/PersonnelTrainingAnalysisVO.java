package com.hmall.training.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonnelTrainingAnalysisVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer personnelId;
    private String name;
    private String position;
    private String department;
    private String abilityShortcomings;
    private String recommendedCourses;
    private Integer trainingPriority;
}
