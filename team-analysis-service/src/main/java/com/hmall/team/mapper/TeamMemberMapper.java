package com.hmall.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.team.domain.entity.TeamMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 团队成员Mapper接口
 */
@Mapper
public interface TeamMemberMapper extends BaseMapper<TeamMember> {
    /**
     * 批量插入团队成员
     * @param members 团队成员列表
     * @return 影响的行数
     */
    int insertBatch(@Param("members") List<TeamMember> members);
    
    /**
     * 根据团队ID和人员ID列表删除团队成员
     * @param teamId 团队ID
     * @param personnelIds 人员ID列表
     * @return 影响的行数
     */
    int deleteByTeamIdAndPersonnelIds(@Param("teamId") Integer teamId, @Param("personnelIds") List<Integer> personnelIds);
    
    /**
     * 查询团队成员详细信息
     * @param teamId 团队ID
     * @return 成员信息列表
     */
    List<Map<String, Object>> selectMemberInfosByTeamId(@Param("teamId") Integer teamId);
} 