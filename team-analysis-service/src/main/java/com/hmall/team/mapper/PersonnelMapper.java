package com.hmall.team.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface PersonnelMapper {
    
    @Select("SELECT * FROM personnel_basic_info " +
           "WHERE position = #{position} " +
           "AND education = #{education} " +
           "AND major = #{major} " +
           "AND age BETWEEN #{minAge} AND #{maxAge}")
    List<Map<String, Object>> findMatchingPersonnel(String position, String education, 
                                                   String major, Integer minAge, Integer maxAge);

    @Select("SELECT * FROM personnel_basic_info WHERE id = #{id}")
    Map<String, Object> findById(Integer id);
} 