package com.hmall.personnel.controller;

import com.hmall.personnel.domain.dto.MeetingOperationLogsDTO;
import com.hmall.personnel.domain.dto.PersonnelBasicInfoDTO;
import com.hmall.personnel.domain.dto.PersonnelDetailInfoDTO;
import com.hmall.personnel.domain.dto.TagsDTO;
import com.hmall.personnel.domain.vo.MeetingOperationLogsVO;
import com.hmall.personnel.domain.vo.PersonnelBasicInfoVO;
import com.hmall.personnel.domain.vo.PersonnelDetailInfoVO;
import com.hmall.personnel.domain.vo.TagsVO;
import com.hmall.personnel.service.IMeetingService;
import com.hmall.personnel.service.IPersonnelService;
import com.hmall.personnel.service.ITagService;
import com.hmall.personnel.service.Impl.MeetingServiceImpl;
import com.hmall.personnel.service.Impl.PersonnelServiceImpl;
import com.hmall.personnel.service.Impl.TagServiceImpl;
import com.hmall.personnel.util.convert.MeetingOperationLogsConvert;
import com.hmall.personnel.util.convert.PersonnelConvert;
import com.hmall.personnel.util.convert.TagsConvert;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人员看板API接口
 * 提供人员基本信息、标签和会议日志的CRUD操作
 */
@RestController
@RequestMapping("/personnel")
public class PersonnelController {

    @Autowired
    private IPersonnelService personnelService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private IMeetingService meetingService;

    // ==================== Personnel 接口 ====================
    /**
     * 获取所有人员基本信息列表
     * @return 人员基本信息VO列表
     */
    @ApiOperation("获取所有人员基本信息")
    @GetMapping("/basic")
    public List<PersonnelBasicInfoVO> getAllPersonnelBasicInfo() {
        // 调用服务获取DTO列表
        List<PersonnelBasicInfoDTO> dtos = personnelService.getAllPersonnelBasicInfo();
        // DTO -> VO
        return PersonnelConvert.basicDtoListToVoList(dtos);
    }

    /**
     * 根据人员ID获取详细信息
     * @param personnelId 人员ID
     * @return 人员详细信息VO
     */
    @ApiOperation("根据ID获取人员详细信息")
    @ApiImplicitParam(name = "personnelId", value = "人员ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/detail/{personnelId}")
    public PersonnelDetailInfoVO getPersonnelDetailInfo(@PathVariable Long personnelId) {
        // 调用服务获取DTO
        PersonnelDetailInfoDTO dto = personnelService.getPersonnelDetailInfoById(personnelId);
        // DTO -> VO
        return PersonnelConvert.dtoToVo(dto);
    }

    // ==================== Tag 接口 ====================

    /**
     * 创建或更新标签
     * @param vo 标签VO对象
     */
    @ApiOperation("创建或更新标签")
    @ApiImplicitParam(name = "vo", value = "标签信息", required = true, dataType = "TagVO", paramType = "body")
    @PostMapping("/tags")
    public void saveTag(@RequestBody TagsVO vo) {
        // VO -> DTO
        TagsDTO dto = TagsConvert.voToDto(vo);
        // 调用服务保存
        tagService.saveTag(dto);
    }

    /**
     * 获取所有标签列表
     * @return 标签VO列表
     */
    @ApiOperation("获取所有标签列表")
    @GetMapping("/tags")
    public List<TagsVO> getAllTags() {
        // 调用服务获取DTO列表
        List<TagsDTO> dtos = tagService.getAllTags();
        // DTO -> VO
        return TagsConvert.dtoListToVoList(dtos);
    }

    /**
     * 保存系统生成的标签
     * @param tags 标签DTO列表
     */
    @ApiOperation("保存系统生成的标签")
    @ApiImplicitParam(name = "tags", value = "系统生成的标签列表", required = true, dataType = "List<TagsDTO>", paramType = "body")
    @PostMapping("/tags/system")
    public void saveSystemGeneratedTags(@RequestBody List<TagsDTO> tags) {
        personnelService.saveSystemGeneratedTags(tags);
    }

    /**
     * 保存用户自定义的标签
     * @param dto 标签DTO对象
     */
    @ApiOperation("保存用户自定义的标签")
    @ApiImplicitParam(name = "dto", value = "用户自定义标签信息", required = true, dataType = "TagsDTO", paramType = "body")
    @PostMapping("/tags/user-defined")
    public void saveUserDefinedTag(@RequestBody TagsDTO dto) {
        personnelService.saveUserDefinedTag(dto);
    }

    // ==================== Meeting 接口 ====================
    /**
     * 记录会议操作日志
     * @param operation 操作描述
     */
    @ApiOperation("记录会议操作日志")
    @ApiImplicitParam(name = "operation", value = "操作描述", required = true, dataType = "String", paramType = "body")
    @PostMapping("/meetings/log")
    public void recordMeetingOperation(@RequestBody String operation) {
        // 直接调用服务记录操作
        meetingService.recordMeetingOperation(operation);
    }

    /**
     * 获取所有会议操作日志列表
     * @return 会议操作日志VO列表
     */
    @ApiOperation("获取所有会议操作日志列表")
    @GetMapping("/meetings/logs")
    public List<MeetingOperationLogsVO> getAllMeetingLogs() {
        // 调用服务获取DTO列表
        List<MeetingOperationLogsDTO> dtos = meetingService.getAllMeetingLogs();
        // DTO -> VO
        return MeetingOperationLogsConvert.dtoListToVoList(dtos);
    }

    /**
     * 大屏展示：获取当前展示人员的全部基础信息、标签信息、详细资料信息
     * @param personnelId 人员ID
     * @return 人员基础信息VO
     */
    @ApiOperation("大屏展示：获取当前展示人员的全部信息")
    @ApiImplicitParam(name = "personnelId", value = "人员ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/screen/{personnelId}")
    public PersonnelBasicInfoVO getPersonnelInfoForScreen(@PathVariable Long personnelId) {
        PersonnelBasicInfoDTO basicInfoDto = personnelService.getAllPersonnelBasicInfo().stream()
                .filter(info -> info.getId().equals(personnelId))
                .findFirst()
                .orElse(null);
        if (basicInfoDto == null) {
            return null;
        }
        PersonnelBasicInfoVO basicInfoVo = PersonnelConvert.dtoToVo(basicInfoDto);
        basicInfoVo.setTags(TagsConvert.dtoListToVoList(tagService.getAllTags()));
        basicInfoVo.setDetailInfo(PersonnelConvert.dtoToVo(personnelService.getPersonnelDetailInfoById(personnelId)));
        return basicInfoVo;
    }

    /**
     * 记录会议操作日志
     * @param log 会议操作日志DTO
     */
    @ApiOperation("记录会议操作日志")
    @ApiImplicitParam(name = "log", value = "会议操作日志信息", required = true, dataType = "MeetingOperationLogsDTO", paramType = "body")
    @PostMapping("/meetings/logs/record")
    public void recordMeetingOperationLog(@RequestBody MeetingOperationLogsDTO log) {
        personnelService.recordMeetingOperationLog(log);
    }
}