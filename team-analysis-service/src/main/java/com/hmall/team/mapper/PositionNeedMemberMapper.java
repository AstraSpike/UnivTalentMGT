package com.hmall.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.team.domain.entity.PositionNeedMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 职位需求成员关联Mapper
 */
public interface PositionNeedMemberMapper extends BaseMapper<PositionNeedMember> {
    /**
     * 根据职位需求ID查询推荐人员
     */
    @Select("SELECT p.*, pnm.match_degree " +
            "FROM personnel_basic_info p " +
            "JOIN position_need_members pnm ON p.id = pnm.personnel_id " +
            "WHERE pnm.position_need_id = #{positionNeedId} " +
            "ORDER BY pnm.match_degree DESC")
    List<Map<String, Object>> findRecommendedPersonnel(@Param("positionNeedId") Long positionNeedId);

    /**
     * 根据职位需求ID和人员ID列表删除关联关系
     */
    @Delete("<script>" +
            "DELETE FROM position_need_members " +
            "WHERE position_need_id = #{positionNeedId} " +
            "AND personnel_id IN " +
            "<foreach collection='personnelIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    void deleteByPositionNeedIdAndPersonnelIds(@Param("positionNeedId") Long positionNeedId, @Param("personnelIds") Set<Integer> personnelIds);

    /**
     * 根据职位需求ID查询所有成员关联
     */
    @Select("SELECT * FROM position_need_members WHERE position_need_id = #{positionNeedId}")
    List<PositionNeedMember> selectByPositionNeedId(@Param("positionNeedId") Long positionNeedId);

    /**
     * 根据职位需求ID删除所有成员关联
     */
    @Delete("DELETE FROM position_need_members WHERE position_need_id = #{positionNeedId}")
    void deleteByPositionNeedId(@Param("positionNeedId") Long positionNeedId);
} 