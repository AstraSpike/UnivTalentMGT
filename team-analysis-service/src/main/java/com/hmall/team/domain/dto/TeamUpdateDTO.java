package com.hmall.team.domain.dto;

import lombok.Data;
import java.util.List;

/**
 * 团队更新请求DTO
 */
@Data
public class TeamUpdateDTO {
    /**
     * 团队ID
     */
    private Integer id;
    
    /**
     * 团队名称
     */
    private String teamName;
    
    /**
     * 团队概述
     */
    private String teamDescription;
    
    /**
     * 要添加的成员ID列表
     */
    private List<Integer> addMemberIds;
    
    /**
     * 要移除的成员ID列表
     */
    private List<Integer> removeMemberIds;
} 