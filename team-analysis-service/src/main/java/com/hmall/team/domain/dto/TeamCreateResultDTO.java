package com.hmall.team.domain.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

@Data
public class TeamCreateResultDTO {
    private String message;
    private String teamName;
    private String teamDescription;
    private Integer totalSize;
    private String ageDistribution;
    private String genderRatio;
    private List<PositionResult> positionResults;

    @Data
    public static class PositionResult {
        private String positionName;
        private String position;
        private List<PersonnelInfo> recommendedPersonnel;
    }

    @Data
    public static class PersonnelInfo {
        private Integer id;
        private String name;
        private String gender;
        private Integer age;
        private String position;
        private String title;
        private String department;
        private String education;
        private String major;
        private BigDecimal matchDegree;
    }
} 