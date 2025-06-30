package com.hmall.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.team.domain.entity.PositionNeed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface PositionNeedMapper extends BaseMapper<PositionNeed> {
    
    @Select("SELECT * FROM position_need WHERE team_id = #{teamId}")
    List<PositionNeed> findByTeamId(Integer teamId);
} 