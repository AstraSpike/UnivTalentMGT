package com.hmall.personnel.service.Impl;

import com.hmall.personnel.domain.dto.MeetingOperationLogsDTO;
import com.hmall.personnel.domain.po.MeetingOperationLogsPO;
import com.hmall.personnel.repository.MeetingOperationLogsRepository;
import com.hmall.personnel.service.IMeetingService;
import com.hmall.personnel.util.convert.MeetingOperationLogsConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MeetingServiceImpl implements IMeetingService {

    @Autowired
    private MeetingOperationLogsRepository meetingLogRepository;

    @Override
    public void recordMeetingOperation(String operation) {
        // 创建PO对象
        MeetingOperationLogsPO meetingLogPO = new MeetingOperationLogsPO();
        meetingLogPO.setOperationType( operation);
        meetingLogPO.setOperationTime(new Date());

        // 保存到数据库
        meetingLogRepository.save(meetingLogPO);
    }

    @Override
    public List<MeetingOperationLogsDTO> getAllMeetingLogs() {
        // 查询PO列表
        List<MeetingOperationLogsPO> poList = meetingLogRepository.findAll();

        // PO -> DTO
        return MeetingOperationLogsConvert.poListToDtoList(poList);
    }
}