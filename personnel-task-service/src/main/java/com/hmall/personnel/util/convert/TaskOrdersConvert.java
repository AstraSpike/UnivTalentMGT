package com.hmall.personnel.util.convert;

import com.hmall.personnel.domain.dto.TaskOrdersDTO;
import com.hmall.personnel.domain.po.TaskOrdersPO;
import com.hmall.personnel.domain.vo.TaskOrdersVO;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;

public class TaskOrdersConvert {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * PO 转 DTO
     */
    public static TaskOrdersDTO poToDto(TaskOrdersPO po) {
        if (po == null) {
            return null;
        }
        TaskOrdersDTO dto = new TaskOrdersDTO();
        BeanUtils.copyProperties(po, dto);

        // 设置业务计算字段
        dto.setIsUrgent(isTaskUrgent(po.getExpectedCompletionTime()));

        return dto;
    }

    /**
     * DTO 转 PO
     */
    public static TaskOrdersPO dtoToPo(TaskOrdersDTO dto) {
        if (dto == null) {
            return null;
        }
        TaskOrdersPO po = new TaskOrdersPO();
        BeanUtils.copyProperties(dto, po);
        return po;
    }

    /**
     * DTO 转 VO
     */
    public static TaskOrdersVO dtoToVo(TaskOrdersDTO taskOrdersDTO) {
        // 修复时间类型转换问题
        LocalDateTime createTime = taskOrdersDTO.getCreateTime() != null 
            ? taskOrdersDTO.getCreateTime().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() : null;
        // 继续其他字段的映射...
        TaskOrdersVO taskOrdersVO = new TaskOrdersVO();
        taskOrdersVO.setId(taskOrdersDTO.getId());
        taskOrdersVO.setCreateTime(createTime != null ? Date.from(createTime.atZone(java.time.ZoneId.systemDefault()).toInstant()) : null);
        // 其他字段赋值...
        return taskOrdersVO;
    }

    /**
     * 判断任务是否紧急（示例业务逻辑）
     */
    private static Boolean isTaskUrgent(Date expectedCompletionTime) {
        // 实际业务中可以根据预期完成时间与当前时间的差值判断
        return expectedCompletionTime != null;
    }

    /**
     * 优先级代码转文本
     */
    private static String convertPriorityToText(String priorityCode) {
        if (priorityCode == null) {
            return "";
        }
        switch (priorityCode) {
            case "H":
                return "高";
            case "M":
                return "中";
            case "L":
                return "低";
            default:
                return priorityCode;
        }
    }

    /**
     * 状态代码转文本
     */
    private static String convertStatusToText(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode) {
            case "CREATED":
                return "已创建";
            case "PROCESSING":
                return "进行中";
            case "COMPLETED":
                return "已完成";
            case "CANCELLED":
                return "已取消";
            default:
                return statusCode;
        }
    }
}
