package com.hmall.team.domain.dto;

import lombok.Data;

/**
 * 团队列表项DTO
 */
@Data
public class TeamListItemDTO {
    /**
     * 团队ID
     */
    private Integer id;

    /**
     * 团队名称
     */
    private String teamName;
    
    /**
     * 团队描述
     */
    private String teamDescription;
    
    /**
     * 团队总人数
     */
    private Integer totalSize;

    /**
     * 年龄分布
     */
    private String ageDistribution;

    /**
     * 性别比例
     */
    private String genderRatio;
} 