package com.hmall.personnel.domain.vo;

import lombok.Data;

import java.util.Map;

@Data
public class StatisticsVO {
    /**
     * 年龄分布统计
     */
    private Map<String, Integer> ageDistribution;

    /**
     * 职称统计
     */
    private Map<String, Integer> titleStatistics;

    /**
     * 学位统计
     */
    private Map<String, Integer> degreeStatistics;

    /**
     * 政治面貌统计
     */
    private Map<String, Integer> politicalStatusStatistics;
}
