package com.hmall.team.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 团队成员实体类
 */
@Data
@TableName("team_members")
public class TeamMember {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 团队ID
     */
    private Integer teamId;
    
    /**
     * 人员ID
     */
    private Integer personnelId;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 