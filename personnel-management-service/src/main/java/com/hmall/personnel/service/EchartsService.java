package com.hmall.personnel.service;

import com.hmall.personnel.domain.vo.StatisticsVO;

import java.util.List;
import java.util.Map;

public interface EchartsService {
    /**
     * 获取年龄分布统计
     * @return 年龄分布统计结果
     */
    Map<String, Integer> getAgeDistribution();

    /**
     * 获取职称统计
     * @return 职称统计结果
     */
    Map<String, Integer> getTitleStatistics();

    /**
     * 获取学位统计
     * @return 学位统计结果
     */
    Map<String, Integer> getDegreeStatistics();

    /**
     * 获取政治面貌统计
     * @return 政治面貌统计结果
     */
    Map<String, Integer> getPoliticalStatusStatistics();

    /**
     * 获取所有统计信息
     * @return 所有统计信息
     */
    StatisticsVO getAllStatistics();
}
