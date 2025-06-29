package com.hmall.team.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 团队实体类
 */
@Data
@TableName("teams")
public class Team {
    /**
     * 团队ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 需求ID
     */
    private Long requirementId;
    
    /**
     * 团队名称
     */
    private String teamName;
    
    /**
     * 团队描述
     */
    private String teamDescription;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 