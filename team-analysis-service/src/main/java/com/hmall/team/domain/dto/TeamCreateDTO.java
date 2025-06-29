package com.hmall.team.domain.dto;

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
     * 团队概述
     */
    private String teamDescription;
    
    /**
     * 团队成员ID列表
     */
    private List<Integer> memberIds;
    
    /**
     * 团队需求ID（可选，如果有现成的需求可以关联）
     */
    private Long requirementId;
} 