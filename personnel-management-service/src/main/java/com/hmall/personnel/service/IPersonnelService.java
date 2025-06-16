// PersonnelService.java
package com.hmall.personnel.service;

import com.hmall.personnel.domain.dto.MeetingOperationLogsDTO;
import com.hmall.personnel.domain.dto.PersonnelBasicInfoDTO;
import com.hmall.personnel.domain.dto.PersonnelDetailInfoDTO;
import com.hmall.personnel.domain.dto.TagsDTO;

import java.util.List;

// 人员服务接口，定义业务方法
public interface IPersonnelService {
    // 获取所有人员的基础信息
    List<PersonnelBasicInfoDTO> getAllPersonnelBasicInfo();

    // 保存系统生成的标签
    void saveSystemGeneratedTags(List<TagsDTO> tags);

    // 保存用户自定义的标签
    void saveUserDefinedTag(TagsDTO tag);

    // 根据人员 ID 获取人员的详细信息
    PersonnelDetailInfoDTO getPersonnelDetailInfoById(Long personnelId);

    // 记录会议操作日志
    void recordMeetingOperationLog(MeetingOperationLogsDTO log);
}