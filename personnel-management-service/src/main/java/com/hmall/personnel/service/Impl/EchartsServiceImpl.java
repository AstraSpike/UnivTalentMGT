package com.hmall.personnel.service.Impl;

import com.hmall.personnel.domain.vo.StatisticsVO;
import com.hmall.personnel.repository.EchartsRepository;
import com.hmall.personnel.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    private EchartsRepository echartsRepository;

    @Override
    @Transactional(readOnly = true) // 添加事务管理
    public Map<String, Integer> getAgeDistribution() {
        List<Map<String, Object>> resultList = echartsRepository.countAgeDistribution();
        return mapToDistribution(resultList, "age_group");
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Integer> getTitleStatistics() {
        List<Map<String, Object>> resultList = echartsRepository.countTitleDistribution();
        return mapToDistribution(resultList, "title");
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Integer> getDegreeStatistics() {
        List<Map<String, Object>> resultList = echartsRepository.countDegreeDistribution();
        return mapToDistribution(resultList, "degree");
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Integer> getPoliticalStatusStatistics() {
        List<Map<String, Object>> resultList = echartsRepository.countPoliticalStatusDistribution();
        return mapToDistribution(resultList, "political_status");
    }

    @Override
    @Transactional(readOnly = true)
    public StatisticsVO getAllStatistics() {
        StatisticsVO statisticsVO = new StatisticsVO();
        statisticsVO.setAgeDistribution(getAgeDistribution());
        statisticsVO.setTitleStatistics(getTitleStatistics());
        statisticsVO.setDegreeStatistics(getDegreeStatistics());
        statisticsVO.setPoliticalStatusStatistics(getPoliticalStatusStatistics());
        return statisticsVO;
    }

    // 提取公共方法减少代码重复
    private Map<String, Integer> mapToDistribution(List<Map<String, Object>> resultList, String keyField) {
        Map<String, Integer> distribution = new HashMap<>();
        for (Map<String, Object> result : resultList) {
            String key = (String) result.get(keyField);
            Integer count = ((Number) result.get("count")).intValue();
            distribution.put(key, count);
        }
        return distribution;
    }
}
