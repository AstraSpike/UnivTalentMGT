package com.hmall.training.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonnelTrainingDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer personnelId;
    private String trainingCourse;
    private Integer trainingPriority;
}
