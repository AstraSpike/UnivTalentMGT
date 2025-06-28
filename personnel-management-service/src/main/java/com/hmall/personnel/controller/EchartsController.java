package com.hmall.personnel.controller;

import com.hmall.personnel.domain.vo.StatisticsVO;
import com.hmall.personnel.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class EchartsController {
    @Autowired
    private EchartsService echartsService;

    @GetMapping("/age")
    public Map<String, Integer> getAgeDistribution() {
        return echartsService.getAgeDistribution();
    }

    @GetMapping("/title")
    public Map<String, Integer> getTitleStatistics() {
        return echartsService.getTitleStatistics();
    }

    @GetMapping("/degree")
    public Map<String, Integer> getDegreeStatistics() {
        return echartsService.getDegreeStatistics();
    }

    @GetMapping("/political-status")
    public Map<String, Integer> getPoliticalStatusStatistics() {
        return echartsService.getPoliticalStatusStatistics();
    }

    @GetMapping("/all")
    public StatisticsVO getAllStatistics() {
        return echartsService.getAllStatistics();
    }
}
