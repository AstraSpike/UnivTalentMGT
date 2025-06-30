package com.hmall.team.domain.dto;

import lombok.Data;
import java.util.List;

/**
 * 分页响应DTO
 */
@Data
public class PageResultDTO<T> {
    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 当前页的数据列表
     */
    private List<T> records;

    public static <T> PageResultDTO<T> of(List<T> records, Long total, Integer pageNum, Integer pageSize) {
        PageResultDTO<T> result = new PageResultDTO<>();
        result.setRecords(records);
        result.setTotal(total);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setPages((total + pageSize - 1) / pageSize);
        return result;
    }
} 