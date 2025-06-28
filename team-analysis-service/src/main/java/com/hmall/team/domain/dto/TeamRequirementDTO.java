package com.hmall.team.domain.dto;

import lombok.Data;
import java.util.List;

/**
 * 班子需求DTO
 */
@Data
public class TeamRequirementDTO {
    /**
     * 需求ID
     */
    private Long id;

    /**
     * 团队规模
     */
    private Integer teamSize;

    /**
     * 职位要求
     */
    private List<String> positionRequirements;

    /**
     * 能力要求
     */
    private List<String> abilityRequirements;

    /**
     * 团队协作能力要求
     */
    private List<String> teamworkAbilityRequirements;

    /**
     * 是否需要组建团队
     */
    private Boolean needTeamFormation;

    /**
     * 职位需求列表
     */
    private List<PositionRequirement> positions;

    /**
     * 其他条件
     */
    private OtherConditions otherConditions;

    @Data
    public static class PositionRequirement {
        /**
         * 职位名称
         */
        private String name;

        /**
         * 所需技能
         */
        private List<String> skills;

        /**
         * 所需经验（年）
         */
        private Integer experience;

        /**
         * 学历要求
         */
        private String educationLevel;
    }

    @Data
    public static class OtherConditions {
        /**
         * 年龄范围
         */
        private List<Integer> ageRange;

        /**
         * 是否需要性别平衡
         */
        private Boolean genderBalance;
    }
} 