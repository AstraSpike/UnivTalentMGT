package com.hmall.personnel.service;

import com.hmall.personnel.domain.dto.TagsDTO;
import java.util.List;

public interface ITagService {
    /**
     * 保存标签并更新缓存
     * @param dto 标签实体
     */
    void saveTag(TagsDTO dto);

    /**
     * 获取所有标签（优先从缓存读取）
     * @return 标签列表
     */
    List<TagsDTO> getAllTags();
}