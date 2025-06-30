package com.hmall.team.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("position_need")
public class PositionNeed {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer teamId;
    private String positionName;
    private Integer personNum;
    private String position;
    private String education;
    private String major;
    private Integer maxAge;
    private Integer minAge;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 