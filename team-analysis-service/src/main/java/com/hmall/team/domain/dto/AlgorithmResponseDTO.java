package com.hmall.team.domain.dto;

import lombok.Data;
import java.util.List;

/**
 * 算法服务响应DTO
 */
@Data
public class AlgorithmResponseDTO {
    /**
     * 班子ID
     */
    private String teamId;

    /**
     * 风险警告列表
     */
    private List<String> riskWarnings;

    /**
     * 优化建议列表
     */
    private List<String> optimizationSuggestions;
} 