package com.hmall.personnel.controller;


import com.hmall.personnel.domain.dto.TaskOrdersDTO;
import com.hmall.personnel.domain.vo.TaskOrdersVO;
import com.hmall.personnel.service.TaskOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务订单控制器
 * 处理任务订单相关的HTTP请求
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskOrdersController {

    @Autowired
    private TaskOrdersService taskOrdersService;

    /**
     * 创建任务
     */
    @PostMapping
    public TaskOrdersDTO createTask(@RequestBody TaskOrdersDTO taskOrdersDTO) {
        return taskOrdersService.createTask(taskOrdersDTO);
    }

    /**
     * 获取任务详情
     */
    @GetMapping("/{id}")
    public TaskOrdersVO getTask(@PathVariable Integer id) {
        return taskOrdersService.getTaskById(id);
    }

    /**
     * 获取用户接收的任务列表
     */
    @GetMapping("/receiver/{receiverId}")
    public List<TaskOrdersVO> getTasksByReceiver(@PathVariable Integer receiverId) {
        return taskOrdersService.getTasksByReceiver(receiverId);
    }

    /**
     * 更新任务状态
     */
    @PutMapping("/{id}/status")
    public TaskOrdersDTO updateTaskStatus(@PathVariable Integer id, @RequestParam String status) {
        return taskOrdersService.updateTaskStatus(id, status);
    }

    /**
     * 删除任务
     */
    @DeleteMapping("/{id}")
    public Boolean deleteTask(@PathVariable Integer id) {
        return taskOrdersService.deleteTask(id);
    }
}