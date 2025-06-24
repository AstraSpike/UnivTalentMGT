package com.hmall.personnel.repository;

import com.hmall.personnel.domain.po.MeetingOperationLogsPO;
import org.springframework.data.jpa.repository.JpaRepository;

// 会议操作日志仓库接口，继承 JpaRepository 用于操作 MeetingOperationLogs 实体类
public interface MeetingOperationLogsRepository extends JpaRepository<MeetingOperationLogsPO, Long> {
}