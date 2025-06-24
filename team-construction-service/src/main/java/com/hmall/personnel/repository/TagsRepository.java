package com.hmall.personnel.repository;

import com.hmall.personnel.domain.po.TagsPO;
import org.springframework.data.jpa.repository.JpaRepository;

// 标签仓库接口，继承 JpaRepository 用于操作 Tags 实体类
public interface TagsRepository extends JpaRepository<TagsPO, Long> {
}