package com.hmall.personnel.util.convert;

import com.hmall.personnel.domain.dto.TagsDTO;
import com.hmall.personnel.domain.po.TagsPO;
import com.hmall.personnel.domain.vo.TagsVO;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class TagsConvert {

    public static TagsDTO poToDto(TagsPO po) {
        if (po == null) {
            return null;
        }
        TagsDTO dto = new TagsDTO();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }

    // 添加：VO 转 DTO
    // 在 TagsConvert 类中添加 parseDate 方法
    private static Date parseDate(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dateStr);
    }
    public static TagsDTO voToDto(TagsVO vo) {
        if (vo == null) {
            return null;
        }
        TagsDTO dto = new TagsDTO();
        BeanUtils.copyProperties(vo, dto);

        // 解析日期字符串
        try {
            if (vo.getCreateTime() != null) {
                dto.setCreateTime(parseDate(vo.getCreateTime()));
            }
            if (vo.getUpdateTime() != null) {
                dto.setUpdateTime(parseDate(vo.getUpdateTime()));
            }
        } catch (Exception e) {
            throw new RuntimeException("日期格式解析错误", e);
        }

        return dto;
    }

    // 添加：DTO 列表转 VO 列表
    public static List<TagsVO> dtoListToVoList(List<TagsDTO> dtos) {
        return dtos.stream()
                .map(TagsConvert::dtoToVo)
                .collect(Collectors.toList());
    }

    public static TagsPO dtoToPo(TagsDTO dto) {
        if (dto == null) {
            return null;
        }
        TagsPO po = new TagsPO();
        BeanUtils.copyProperties(dto, po);
        return po;
    }

    public static TagsVO dtoToVo(TagsDTO dto) {
        if (dto == null) {
            return null;
        }
        TagsVO vo = new TagsVO();
        BeanUtils.copyProperties(dto, vo);

        // 格式化日期
        if (dto.getCreateTime() != null) {
            vo.setCreateTime(formatDate(dto.getCreateTime()));
        }

        if (dto.getUpdateTime() != null) {
            vo.setUpdateTime(formatDate(dto.getUpdateTime()));
        }

        return vo;
    }

    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static List<TagsDTO> poListToDtoList(List<TagsPO> pos) {
        return pos.stream()
                .map(TagsConvert::poToDto)
                .collect(Collectors.toList());
    }
}