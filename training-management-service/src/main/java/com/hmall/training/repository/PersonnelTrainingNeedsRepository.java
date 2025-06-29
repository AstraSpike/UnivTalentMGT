package com.hmall.training.repository;

import com.hmall.training.domain.po.PersonnelBasicInfoPO;
import com.hmall.training.domain.po.PersonnelTrainingNeedsPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonnelTrainingNeedsRepository extends JpaRepository<PersonnelTrainingNeedsPO, Integer> {
    Optional<PersonnelTrainingNeedsPO> findByPersonnelId(Integer personnelId);
}
