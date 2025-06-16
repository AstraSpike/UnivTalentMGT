package com.hmall.personnel.service;

import com.hmall.personnel.domain.dto.MeetingOperationLogsDTO;
import java.util.List;

public interface IMeetingService {
    /**
     * 记录会议操作日志
     * @param operation 操作描述
     */
    void recordMeetingOperation(String operation);

    /**
     * 获取所有会议日志
     * @return 会议日志DTO列表
     */
    List<MeetingOperationLogsDTO> getAllMeetingLogs();
}