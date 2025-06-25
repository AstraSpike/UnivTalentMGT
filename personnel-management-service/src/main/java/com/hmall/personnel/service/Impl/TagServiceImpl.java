package com.hmall.personnel.service.Impl;

import com.hmall.personnel.domain.dto.TagsDTO;
import com.hmall.personnel.domain.po.TagsPO;
import com.hmall.personnel.repository.TagsRepository;
import com.hmall.personnel.service.ITagService;
import com.hmall.personnel.util.convert.TagsConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagsRepository tagRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveTag(TagsDTO tag) {
        // DTO -> PO
        TagsPO po = TagsConvert.dtoToPo(tag);

        // 保存到数据库
        tagRepository.save(po);

        // 更新 Redis 缓存（存 DTO 而非 PO）
        redisTemplate.opsForValue().set("tag:" + po.getId(), tag);

        // 清除全量缓存
        redisTemplate.delete("all_tags");
    }

    @Override
    public List<TagsDTO> getAllTags() {
        // 先从 Redis 缓存中获取
        List<TagsDTO> tags = (List<TagsDTO>) redisTemplate.opsForValue().get("all_tags");

        if (tags == null) {
            // 从数据库获取
            List<TagsPO> pos = tagRepository.findAll();

            // PO -> DTO
            tags = TagsConvert.poListToDtoList(pos);

            // 更新 Redis 缓存
            redisTemplate.opsForValue().set("all_tags", tags, 1, java.util.concurrent.TimeUnit.HOURS);
        }

        return tags;
    }
}
