package com.hmall.team.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmall.common.domain.PageDTO;
import com.hmall.common.exception.BadRequestException;
import com.hmall.team.domain.dto.TeamAnalysisResult;
import com.hmall.team.domain.dto.TeamRequirementDTO;
import com.hmall.team.domain.entity.TeamAnalysis;
import com.hmall.team.domain.entity.TeamRequirement;
import com.hmall.team.mapper.TeamAnalysisMapper;
import com.hmall.team.mapper.TeamRequirementMapper;
import com.hmall.team.service.TeamAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 班子分析服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamAnalysisServiceImpl implements TeamAnalysisService {

    private final TeamAnalysisMapper teamAnalysisMapper;
    private final TeamRequirementMapper teamRequirementMapper;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${algorithm.service.url:http://localhost:5000}")
    private String algorithmServiceUrl;

    @Override
    @Transactional
    public TeamAnalysisResult analyzeTeam(TeamRequirementDTO requirementDTO) {
        log.info("开始班子分析，需求信息: {}", requirementDTO);

        try {
            // 1. 保存需求信息
            TeamRequirement requirement = new TeamRequirement();
            requirement.setPositionRequirements(objectMapper.writeValueAsString(requirementDTO.getPositions()));
            requirement.setTeamSize(requirementDTO.getTeamSize());
            requirement.setUserId(1); // TODO: 从上下文获取当前用户ID
            requirement.setCreateTime(LocalDateTime.now());
            requirement.setUpdateTime(LocalDateTime.now());
            teamRequirementMapper.insert(requirement);

            // 2. 创建分析记录
            TeamAnalysis analysis = new TeamAnalysis();
            analysis.setRequirementId(requirement.getId());
            analysis.setStatus(0); // 分析进行中
            analysis.setDescription("分析进行中");
            analysis.setCreateTime(LocalDateTime.now());
            analysis.setUpdateTime(LocalDateTime.now());
            teamAnalysisMapper.insert(analysis);

            // 3. 构建算法服务请求数据
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> teamRequirements = new HashMap<>();
            teamRequirements.put("positions", requirementDTO.getPositions());
            teamRequirements.put("otherConditions", requirementDTO.getOtherConditions());
            requestBody.put("teamRequirements", teamRequirements);

            // 4. 调用算法服务
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(algorithmServiceUrl, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                // 更新分析状态
                analysis.setStatus(1); // 分析完成
                analysis.setDescription("分析完成");
                analysis.setRecommendedStaffIds(response.getBody());
                analysis.setUpdateTime(LocalDateTime.now());
                teamAnalysisMapper.updateById(analysis);

                // 构建返回结果
                TeamAnalysisResult result = new TeamAnalysisResult();
                result.setAnalysisId(analysis.getId());
                result.setStatus(analysis.getStatus());
                result.setDescription(analysis.getDescription());
                result.setTimestamp(System.currentTimeMillis());
                
                return result;
            } else {
                throw new BadRequestException("算法服务返回异常：" + response.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            log.error("JSON处理异常", e);
            throw new BadRequestException("需求数据处理失败：" + e.getMessage());
        } catch (Exception e) {
            log.error("班子分析失败", e);
            throw new RuntimeException("班子分析失败：" + e.getMessage());
        }
    }

    @Override
    public PageDTO<TeamAnalysisResult> getAnalysisHistory(Integer page, Integer pageSize) {
        // TODO: 实现分页查询逻辑
        PageDTO<TeamAnalysisResult> pageDTO = new PageDTO<>();
        pageDTO.setTotal(0L);
        pageDTO.setList(Collections.emptyList());
        return pageDTO;
    }

    @Override
    public TeamAnalysisResult getAnalysisDetail(Long id) {
        TeamAnalysis analysis = teamAnalysisMapper.selectById(id);
        if (analysis == null) {
            throw new BadRequestException("分析记录不存在");
        }

        TeamAnalysisResult result = new TeamAnalysisResult();
        result.setAnalysisId(analysis.getId());
        result.setStatus(analysis.getStatus());
        result.setDescription(analysis.getDescription());
        result.setTimestamp(analysis.getCreateTime().toInstant(java.time.ZoneOffset.UTC).toEpochMilli());

        return result;
    }
} 