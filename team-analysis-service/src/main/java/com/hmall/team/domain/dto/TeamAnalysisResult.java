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
        /**
         * 人员ID
         */
        private String id;
        
        /**
         * 姓名
         */
        private String name;
        
        /**
         * 职位
         */
        private String position;
        
        /**
         * 部门
         */
        private String department;
        
        /**
         * 匹配分数
         */
        private Integer matchScore;
        
        /**
         * 技能列表
         */
        private List<String> skills;
        
        /**
         * 工作经验
         */
        private String experience;
    }

    @Data
    public static class TeamAnalysisInfo {
        /**
         * 年龄分布
         */
        private String ageDistribution;
        
        /**
         * 性别比例
         */
        private String genderRatio;
        
        /**
         * 技能覆盖度
         */
        private String skillCoverage;
        
        /**
         * 团队结构
         */
        private Map<String, Map<String, String>> teamStructure;
        
        /**
         * 推荐原因
         */
        private List<String> recommendationReason;
    }
} 