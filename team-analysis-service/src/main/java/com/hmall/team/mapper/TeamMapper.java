package com.hmall.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.team.domain.entity.Team;
import org.apache.ibatis.annotations.Mapper;

/**
 * 团队Mapper接口
 */
@Mapper
public interface TeamMapper extends BaseMapper<Team> {
} 