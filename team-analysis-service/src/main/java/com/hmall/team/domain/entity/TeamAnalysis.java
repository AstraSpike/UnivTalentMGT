package com.hmall.team.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 班子分析实体
 */
@Data
@TableName("team_analysis_results")
public class TeamAnalysis {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 需求ID
     */
    private Long requirementId;

    /**
     * 推荐的人员ID列表（JSON格式）
     */
    private String recommendedStaffIds;

    /**
     * 分析状态
     * 0: 进行中
     * 1: 已完成
     * 2: 失败
     */
    private Integer status;

    /**
     * 匹配度评分（0-100）
     */
    private Integer matchScore;

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
     * 团队结构（JSON格式，包含职称、学历等分布）
     */
    private String teamStructure;

    /**
     * 推荐原因（JSON格式）
     */
    private String recommendationReason;

    /**
     * 分析描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 