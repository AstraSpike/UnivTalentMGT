package com.hmall.team.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("position_need_members")
public class PositionNeedMember {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long positionNeedId;
    private Integer personnelId;
    private BigDecimal matchDegree;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 