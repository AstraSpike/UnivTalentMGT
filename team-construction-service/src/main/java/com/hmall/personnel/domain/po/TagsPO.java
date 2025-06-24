package com.hmall.personnel.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@TableName("tags")
@Table(name = "tags")
public class TagsPO {
    @Id
    private Long id;
    private String tagName;
    private String tagType;
    private Date createTime;
    private Date updateTime;
}