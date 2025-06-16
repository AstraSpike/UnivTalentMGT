package com.hmall.personnel.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TagsDTO {
    private Long id;
    private String tagName;
    private String tagType;
    private Date createTime;
    private Date updateTime;
}