package com.hmall.personnel.repository;

import com.hmall.personnel.domain.po.TaskOrdersPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for TaskOrdersPO entities.
 */
@Repository
public interface TaskOrdersRepository extends JpaRepository<TaskOrdersPO, Integer> {

    /**
     * 根据接收人ID查询任务列表
     *
     * @param receiverId 接收人ID
     * @return 任务订单列表
     */
    List<TaskOrdersPO> findByReceiverId(Integer receiverId);

    /**
     * 更新任务状态
     *
     * @param id     任务订单ID
     * @param status 新的任务状态
     * @return 影响的记录数
     */
    @Modifying
    @Query("UPDATE TaskOrdersPO t SET t.taskStatus = :status WHERE t.id = :id")
    int updateStatus(@Param("id") Integer id, @Param("status") String status);
}