package com.hmall.personnel.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tags")
public class TagsPO {
    private Long id;
    private String tagName;
    private String tagType;
    private Date createTime;
    private Date updateTime;
}