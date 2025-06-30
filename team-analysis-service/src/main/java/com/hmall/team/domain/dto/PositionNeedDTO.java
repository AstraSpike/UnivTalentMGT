package com.hmall.team.domain.dto;

import lombok.Data;
import java.util.List;

/**
 * 职位需求DTO
 */
@Data
public class PositionNeedDTO {
    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 所需人数
     */
    private Integer personNum;

    /**
     * 职位
     */
    private String position;

    /**
     * 学历要求
     */
    private String education;

    /**
     * 专业要求
     */
    private String major;

    /**
     * 最大年龄
     */
    private Integer maxAge;

    /**
     * 最小年龄
     */
    private Integer minAge;

    /**
     * 已选择的人员ID列表
     */
    private List<Integer> selectedPersonnelIds;
} 