package com.hmall.team.domain.dto;

import lombok.Data;

/**
 * 分页请求DTO
 */
@Data
public class PageDTO {
    /**
     * 页码，从1开始
     */
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;
} 