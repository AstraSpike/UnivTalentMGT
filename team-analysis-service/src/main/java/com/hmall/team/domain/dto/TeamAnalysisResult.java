package com.hmall.team.domain.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * 班子分析结果DTO
 */
@Data
public class TeamAnalysisResult {
    /**
     * 分析ID
     */
    private Long analysisId;

    /**
     * 推荐人员列表
     */
    private List<RecommendedStaff> recommendedStaff;

    /**
     * 团队分析结果
     */
    private TeamAnalysisInfo teamAnalysis;

    /**
     * 分析时间戳
     */
    private Long timestamp;

    /**
     * 分析状态
     * 0: 进行中
     * 1: 已完成
     * 2: 失败
     */
    private Integer status;

    /**
     * 分析结果说明
     */
    private String description;

    @Data
    public static class RecommendedStaff {
        private Long id;
        private String name;
        private String position;
        private String department;
        private Integer matchScore;
        private List<String> skills;
        private String experience;
    }

    @Data
    public static class TeamAnalysisInfo {
        private String ageDistribution;
        private String genderRatio;
        private String skillCoverage;
        private Map<String, Map<String, String>> teamStructure;
        private List<String> recommendationReason;
    }
} 