// PersonnelServiceImpl.java
package com.hmall.personnel.service.Impl;

import com.hmall.personnel.util.convert.*;
import com.hmall.personnel.domain.dto.*;
import com.hmall.personnel.domain.po.*;
import com.hmall.personnel.repository.*;
import com.hmall.personnel.service.IPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class PersonnelServiceImpl implements IPersonnelService {

    @Autowired
    private PersonnelBasicInfoRepository personnelBasicInfoRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private PersonnelDetailInfoRepository personnelDetailInfoRepository;

    @Autowired
    private MeetingOperationLogsRepository meetingOperationLogsRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<PersonnelBasicInfoDTO> getAllPersonnelBasicInfo() {
        List<PersonnelBasicInfoPO> pos = personnelBasicInfoRepository.findAll();
        return pos.stream()
                .map(PersonnelConvert::poToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveSystemGeneratedTags(List<TagsDTO> tags) {
        List<TagsPO> pos = tags.stream()
                .map(TagsConvert::dtoToPo)
                .collect(Collectors.toList());

        tagsRepository.saveAll(pos);

        // 更新 Redis 缓存
        pos.forEach(po ->
                redisTemplate.opsForValue().set("tag:" + po.getId(), TagsConvert.poToDto(po))
        );
    }

    @Override
    public void saveUserDefinedTag(TagsDTO dto) {
        TagsPO po = TagsConvert.dtoToPo(dto);
        tagsRepository.save(po);

        // 更新 Redis 缓存
        redisTemplate.opsForValue().set("tag:" + po.getId(), dto);
    }

    @Override
    public PersonnelDetailInfoDTO getPersonnelDetailInfoById(Long personnelId) {
        PersonnelDetailInfoPO po = personnelDetailInfoRepository.findBypersonnelId(personnelId);
        return PersonnelConvert.poToDto(po);
    }

    @Override
    public void recordMeetingOperationLog(MeetingOperationLogsDTO log) {
        MeetingOperationLogsPO po = MeetingOperationLogsConvert.dtoToPo(log);
        meetingOperationLogsRepository.save(po);
    }
}