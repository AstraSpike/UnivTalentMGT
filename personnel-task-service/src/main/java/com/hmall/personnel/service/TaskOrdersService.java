package com.hmall.personnel.service;

import com.hmall.personnel.domain.dto.TaskOrdersDTO;
import com.hmall.personnel.domain.vo.TaskOrdersVO;

import java.util.List;

public interface TaskOrdersService {
    /**
     * 创建任务订单
     * @param taskOrdersDTO 任务订单DTO
     * @return 创建后的任务订单DTO
     */
    TaskOrdersDTO createTask(TaskOrdersDTO taskOrdersDTO);

    /**
     * 根据ID获取任务订单
     * @param id 任务ID
     * @return 任务订单VO（视图对象）
     */
    TaskOrdersVO getTaskById(Integer id);

    /**
     * 获取用户接收的所有任务
     * @param receiverId 接收人ID
     * @return 任务订单VO列表
     */
    List<TaskOrdersVO> getTasksByReceiver(Integer receiverId);

    /**
     * 更新任务状态
     * @param taskId 任务ID
     * @param status 新状态
     * @return 更新后的任务订单DTO
     */
    TaskOrdersDTO updateTaskStatus(Integer taskId, String status);

    /**
     * 删除任务
     * @param taskId 任务ID
     * @return 是否删除成功
     */
    boolean deleteTask(Integer taskId);
}
