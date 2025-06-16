package com.hmall.personnel.util.convert;

import com.hmall.personnel.domain.dto.PersonnelBasicInfoDTO;
import com.hmall.personnel.domain.dto.PersonnelDetailInfoDTO;
import com.hmall.personnel.domain.po.PersonnelBasicInfoPO;
import com.hmall.personnel.domain.po.PersonnelDetailInfoPO;
import com.hmall.personnel.domain.vo.PersonnelBasicInfoVO;
import com.hmall.personnel.domain.vo.PersonnelDetailInfoVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonnelConvert {

    // ==================== PersonnelBasicInfo 转换 ====================
    public static PersonnelBasicInfoDTO poToDto(PersonnelBasicInfoPO po) {
        if (po == null) {
            return null;
        }
        PersonnelBasicInfoDTO dto = new PersonnelBasicInfoDTO();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }

    public static PersonnelBasicInfoPO dtoToPo(PersonnelBasicInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        PersonnelBasicInfoPO po = new PersonnelBasicInfoPO();
        BeanUtils.copyProperties(dto, po);
        return po;
    }

    public static PersonnelBasicInfoVO dtoToVo(PersonnelBasicInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        PersonnelBasicInfoVO vo = new PersonnelBasicInfoVO();
        BeanUtils.copyProperties(dto, vo);

        // 格式化日期
        if (dto.getEntryTime() != null) {
            vo.setEntryTime(formatDate(dto.getEntryTime()));
        }

        // 转换关联对象
        if (dto.getTags() != null) {
            vo.setTags(dto.getTags().stream()
                    .map(TagsConvert::dtoToVo)
                    .collect(Collectors.toList()));
        }

        if (dto.getDetailInfo() != null) {
            vo.setDetailInfo(dtoToVo(dto.getDetailInfo()));
        }

        return vo;
    }

    // ==================== PersonnelDetailInfo 转换 ====================
    public static PersonnelDetailInfoDTO poToDto(PersonnelDetailInfoPO po) {
        if (po == null) {
            return null;
        }
        PersonnelDetailInfoDTO dto = new PersonnelDetailInfoDTO();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }

    public static PersonnelDetailInfoPO dtoToPo(PersonnelDetailInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        PersonnelDetailInfoPO po = new PersonnelDetailInfoPO();
        BeanUtils.copyProperties(dto, po);
        return po;
    }

    public static PersonnelDetailInfoVO dtoToVo(PersonnelDetailInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        PersonnelDetailInfoVO vo = new PersonnelDetailInfoVO();
        BeanUtils.copyProperties(dto, vo);

        // 格式化日期
        if (dto.getPublicationTime() != null) {
            vo.setPublicationTime(formatDate(dto.getPublicationTime()));
        }

        if (dto.getPatentAuthorizationTime() != null) {
            vo.setPatentAuthorizationTime(formatDate(dto.getPatentAuthorizationTime()));
        }

        if (dto.getCreateTime() != null) {
            vo.setCreateTime(formatDate(dto.getCreateTime()));
        }

        if (dto.getUpdateTime() != null) {
            vo.setUpdateTime(formatDate(dto.getUpdateTime()));
        }

        return vo;
    }

    // ==================== 集合转换 ====================
    public static List<PersonnelBasicInfoVO> basicDtoListToVoList(List<PersonnelBasicInfoDTO> dtos) {
        return convertList(dtos, PersonnelConvert::dtoToVo);
    }

    public static List<PersonnelDetailInfoVO> detailDtoListToVoList(List<PersonnelDetailInfoDTO> dtos) {
        return convertList(dtos, PersonnelConvert::dtoToVo);
    }
    public static <T, R> List<R> convertList(List<T> source, Converter<T, R> converter) {
        if (source == null || source.isEmpty()) {
            return new ArrayList<>();
        }
        return source.stream()
                .filter(Objects::nonNull)
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    // 日期格式化工具方法
    private static String formatDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    // 函数式接口用于泛型转换
    @FunctionalInterface
    public interface Converter<T, R> {
        R convert(T source);
    }
}