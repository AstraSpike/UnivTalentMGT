package com.hmall.personnel.repository;

import com.hmall.personnel.domain.po.PersonnelDetailInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;

// 人员详细信息仓库接口，继承 JpaRepository 用于操作 PersonnelDetailInfo 实体类
public interface PersonnelDetailInfoRepository extends JpaRepository<PersonnelDetailInfoPO, Long> {
    PersonnelDetailInfoPO findBypersonnelId(Long personnelId);
}