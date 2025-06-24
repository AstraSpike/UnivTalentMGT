// personnel-management-service/src/main/java/com/hmall/personnel/convert/MeetingOperationLogsConvert.java
package com.hmall.personnel.util.convert;

import com.hmall.personnel.domain.dto.MeetingOperationLogsDTO;
import com.hmall.personnel.domain.po.MeetingOperationLogsPO;
import com.hmall.personnel.domain.vo.MeetingOperationLogsVO;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingOperationLogsConvert {

    public static MeetingOperationLogsDTO poToDto(MeetingOperationLogsPO po) {
        if (po == null) {
            return null;
        }
        MeetingOperationLogsDTO dto = new MeetingOperationLogsDTO();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }

    public static MeetingOperationLogsPO dtoToPo(MeetingOperationLogsDTO dto) {
        if (dto == null) {
            return null;
        }
        MeetingOperationLogsPO po = new MeetingOperationLogsPO();
        BeanUtils.copyProperties(dto, po);
        return po;
    }

    public static MeetingOperationLogsVO dtoToVo(MeetingOperationLogsDTO dto) {
        if (dto == null) {
            return null;
        }
        MeetingOperationLogsVO vo = new MeetingOperationLogsVO();
        BeanUtils.copyProperties(dto, vo);

        // 格式化日期
        if (dto.getOperationTime() != null) {
            vo.setOperationTime(formatDate(dto.getOperationTime()));
        }

        return vo;
    }

    // 批量转换
    public static List<MeetingOperationLogsDTO> poListToDtoList(List<MeetingOperationLogsPO> poList) {
        return poList.stream()
                .map(MeetingOperationLogsConvert::poToDto)
                .collect(Collectors.toList());
    }

    public static List<MeetingOperationLogsVO> dtoListToVoList(List<MeetingOperationLogsDTO> dtoList) {
        return dtoList.stream()
                .map(MeetingOperationLogsConvert::dtoToVo)
                .collect(Collectors.toList());
    }

    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}