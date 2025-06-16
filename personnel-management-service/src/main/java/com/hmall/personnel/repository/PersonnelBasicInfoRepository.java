package com.hmall.personnel.repository;

import com.hmall.personnel.domain.po.PersonnelBasicInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;

// 人员基础信息仓库接口，继承 JpaRepository 用于操作 PersonnelBasicInfo 实体类
public interface PersonnelBasicInfoRepository extends JpaRepository<PersonnelBasicInfoPO, Long> {
}