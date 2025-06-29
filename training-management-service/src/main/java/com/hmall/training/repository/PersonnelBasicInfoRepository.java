package com.hmall.training.repository;

import com.hmall.training.domain.po.PersonnelBasicInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelBasicInfoRepository extends JpaRepository<PersonnelBasicInfoPO, Integer> {
}
