package com.hmall.portrait.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 画像分析结果DTO
 */
@Data
public class PortraitAnalysisDTO {
    /**
     * 人员ID
     */
    private String staffId;

    /**
     * 总体能力评分
     */
    private Double overallScore;

    /**
     * 各维度能力评分
     */
    private Map<String, Double> dimensionScores;

    /**
     * 能力标签
     */
    private Map<String, String> capabilityTags;

    /**
     * 发展建议
     */
    private Map<String, String> developmentSuggestions;

    /**
     * 分析时间戳
     */
    private Long analysisTimestamp;

    @Data
    public static class Features {
        private Teaching teaching;
        private Research research;
        private Management management;
        private Innovation innovation;
    }

    @Data
    public static class Teaching {
        private Integer courseCount;
        private BigDecimal studentEvaluation;
        private Integer awards;
    }

    @Data
    public static class Research {
        private Integer publications;
        private Integer patents;
        private BigDecimal projectFunding;
    }

    @Data
    public static class Management {
        private Integer teamSize;
        private Integer collaborations;
    }

    @Data
    public static class Innovation {
        private Integer innovativeProjects;
        private Integer awards;
    }
} 