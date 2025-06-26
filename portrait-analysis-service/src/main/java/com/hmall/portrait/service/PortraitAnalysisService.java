package com.hmall.portrait.service;

import com.hmall.common.domain.R;
import com.hmall.portrait.domain.dto.PortraitAnalysisDTO;

public interface PortraitAnalysisService {
    /**
     * 分析干部画像
     * @param personnelId 人员ID
     * @return 分析结果
     */
    R<PortraitAnalysisDTO> analyzePortrait(Long personnelId);
} 