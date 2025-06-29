package com.hmall.personnel.repository;

import com.hmall.personnel.domain.po.PersonnelBasicInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EchartsRepository extends JpaRepository<PersonnelBasicInfoPO, Long> {
    /**
     * 统计年龄分布
     * @return 年龄分布统计结果
     */
    @Query(nativeQuery = true, value = "SELECT " +
            "CASE " +
            "WHEN age BETWEEN 0 AND 20 THEN '0-20岁' " +
            "WHEN age BETWEEN 21 AND 30 THEN '21-30岁' " +
            "WHEN age BETWEEN 31 AND 40 THEN '31-40岁' " +
            "WHEN age BETWEEN 41 AND 50 THEN '41-50岁' " +
            "ELSE '50岁以上' " +
            "END AS age_group, " +
            "COUNT(*) AS count " +
            "FROM personnel_basic_info " +
            "GROUP BY age_group")
    List<Map<String, Object>> countAgeDistribution();

    /**
     * 统计职称分布
     * @return 职称分布统计结果
     */
    @Query(nativeQuery = true, value = "SELECT title, COUNT(*) AS count FROM personnel_basic_info GROUP BY title")
    List<Map<String, Object>> countTitleDistribution();

    /**
     * 统计学位分布
     * @return 学位分布统计结果
     */
    @Query(nativeQuery = true, value = "SELECT degree, COUNT(*) AS count FROM personnel_basic_info GROUP BY degree")
    List<Map<String, Object>> countDegreeDistribution();

    /**
     * 统计政治面貌分布
     * @return 政治面貌分布统计结果
     */
    @Query(nativeQuery = true, value = "SELECT political_status, COUNT(*) AS count FROM personnel_basic_info GROUP BY political_status")
    List<Map<String, Object>> countPoliticalStatusDistribution();
}
