package com.hmall.portrait.controller;

import com.hmall.common.domain.R;
import com.hmall.portrait.domain.dto.PortraitAnalysisDTO;
import com.hmall.portrait.service.PortraitAnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 干部画像分析控制器
 */
@RestController
@RequestMapping("/portrait")
public class PortraitAnalysisController {

    @Resource
    private PortraitAnalysisService portraitAnalysisService;

    /**
     * 分析干部画像
     * @param personnelId 人员ID
     * @return 分析结果
     */
    @GetMapping("/analyze/{personnelId}")
    public R<PortraitAnalysisDTO> analyzePortrait(@PathVariable Long personnelId) {
        return portraitAnalysisService.analyzePortrait(personnelId);
    }
} 