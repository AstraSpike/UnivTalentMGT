package com.hmall.team.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * 团队创建请求DTO
 */
@Data
public class TeamCreateDTO {
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
     * 能力要求
     */
    @JsonProperty("ability_requirements")
    private String abilityRequirements;

    /**
     * 团队协作能力要求
     */
    @JsonProperty("teamwork_ability_requirements")
    private String teamworkAbilityRequirements;

    /**
     * 职位需求列表
     */
    private List<PositionNeedDTO> positionNeeds;
} 