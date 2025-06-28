package com.hmall.team.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 班子需求实体
 */
@Data
@TableName("team_requirements")
public class TeamRequirement {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 团队规模
     */
    private Integer teamSize;

    /**
     * 职位要求（JSON格式）
     */
    private String positionRequirements;

    /**
     * 能力要求（JSON格式）
     */
    private String abilityRequirements;

    /**
     * 团队协作能力要求（JSON格式）
     */
    private String teamworkAbilityRequirements;

    /**
     * 是否需要组建团队
     */
    private Boolean needTeamFormation;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 