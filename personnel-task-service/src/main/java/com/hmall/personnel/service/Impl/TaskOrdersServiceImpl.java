package com.hmall.personnel.service.Impl;

import com.hmall.personnel.domain.dto.TaskOrdersDTO;
import com.hmall.personnel.domain.po.TaskOrdersPO;
import com.hmall.personnel.domain.vo.TaskOrdersVO;
import com.hmall.personnel.repository.TaskOrdersRepository;
import com.hmall.personnel.service.TaskOrdersService;
import com.hmall.personnel.util.convert.TaskOrdersConvert;
import java.util.Optional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class TaskOrdersServiceImpl implements TaskOrdersService {
    @Autowired
    private TaskOrdersRepository taskOrdersRepository;

    @Override
    public TaskOrdersDTO createTask(TaskOrdersDTO taskOrdersDTO) {
        TaskOrdersPO taskOrdersPO = TaskOrdersConvert.dtoToPo(taskOrdersDTO);
        taskOrdersPO.setCreateTime(new Date());
        taskOrdersPO.setUpdateTime(new Date());
        taskOrdersPO = taskOrdersRepository.save(taskOrdersPO);
        return TaskOrdersConvert.poToDto(taskOrdersPO);
    }

    @Override
    public TaskOrdersVO getTaskById(Integer id) {
        Optional<TaskOrdersPO> optional = taskOrdersRepository.findById(id);
        if (optional.isPresent()) {
            TaskOrdersDTO dto = TaskOrdersConvert.poToDto(optional.get());
            return TaskOrdersConvert.dtoToVo(dto);
        }
        return null;
    }

    @Override
    public List<TaskOrdersVO> getTasksByReceiver(Integer receiverId) {
        List<TaskOrdersPO> poList = taskOrdersRepository.findByReceiverId(receiverId);
        return poList.stream()
                .map(TaskOrdersConvert::poToDto)
                .map(TaskOrdersConvert::dtoToVo)
                .collect(Collectors.toList());
    }

    @Override
    public TaskOrdersDTO updateTaskStatus(Integer id, String status) {
        int rows = taskOrdersRepository.updateStatus(id, status);
        if (rows > 0) {
            Optional<TaskOrdersPO> optional = taskOrdersRepository.findById(id);
            if (optional.isPresent()) {
                TaskOrdersPO po = optional.get();
                po.setUpdateTime(new Date());
                po = taskOrdersRepository.save(po);
                return TaskOrdersConvert.poToDto(po);
            }
        }
        return null;
    }

    @Override
    public boolean deleteTask(Integer id) {
        taskOrdersRepository.deleteById(id);
        return true;
    }
}
