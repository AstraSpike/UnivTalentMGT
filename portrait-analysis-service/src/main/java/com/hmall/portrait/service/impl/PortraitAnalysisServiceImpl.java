package com.hmall.portrait.service.impl;

import com.hmall.common.domain.R;
import com.hmall.portrait.domain.dto.PortraitAnalysisDTO;
import com.hmall.portrait.domain.entity.PersonnelBasicInfo;
import com.hmall.portrait.domain.entity.PersonnelDetailInfo;
import com.hmall.portrait.mapper.PersonnelBasicInfoMapper;
import com.hmall.portrait.mapper.PersonnelDetailInfoMapper;
import com.hmall.portrait.service.PortraitAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PortraitAnalysisServiceImpl implements PortraitAnalysisService {

    @Resource
    private PersonnelBasicInfoMapper basicInfoMapper;

    @Resource
    private PersonnelDetailInfoMapper detailInfoMapper;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String ANALYSIS_API_URL = "http://127.0.0.1:5001/api/assessment/capability";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public R<PortraitAnalysisDTO> analyzePortrait(Long personnelId) {
        log.info("开始分析人员画像，人员ID: {}", personnelId);

        // 1. 获取人员基本信息
        log.debug("正在获取人员基本信息...");
        PersonnelBasicInfo basicInfo = basicInfoMapper.selectById(personnelId);
        if (basicInfo == null) {
            log.warn("未找到人员基本信息，人员ID: {}", personnelId);
            return R.error("人员不存在");
        }
        log.debug("成功获取人员基本信息: {}", basicInfo);

        // 2. 获取人员详细信息
        log.debug("正在获取人员详细信息...");
        PersonnelDetailInfo detailInfo = detailInfoMapper.selectById(personnelId);
        if (detailInfo == null) {
            log.warn("未找到人员详细信息，人员ID: {}", personnelId);
            return R.error("人员详细信息不存在");
        }
        log.debug("成功获取人员详细信息: {}", detailInfo);

        // 3. 构建分析请求数据
        log.info("开始构建画像分析请求数据...");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("staffId", personnelId.toString());

        Map<String, Object> features = new HashMap<>();
        
        // 教学特征
        Map<String, Object> teaching = new HashMap<>();
        teaching.put("courseCount", detailInfo.getCourseHours());
        teaching.put("studentEvaluation", detailInfo.getTeachingEvaluationScore());
        teaching.put("awards", countAwards(detailInfo.getTeachingAwards()));
        features.put("teaching", teaching);
        log.debug("教学特征数据: {}", teaching);

        // 科研特征
        Map<String, Object> research = new HashMap<>();
        research.put("publications", countPublications(detailInfo.getResearchPapers()));
        research.put("patents", countPatents(detailInfo.getPatents()));
        research.put("projectFunding", detailInfo.getProjectFunds());
        features.put("research", research);
        log.debug("科研特征数据: {}", research);

        // 管理特征
        Map<String, Object> management = new HashMap<>();
        management.put("teamSize", detailInfo.getManagementTeamSize());
        management.put("collaborations", 5); // 默认值，需要根据实际情况计算
        features.put("management", management);
        log.debug("管理特征数据: {}", management);

        // 创新特征
        Map<String, Object> innovation = new HashMap<>();
        innovation.put("innovativeProjects", countProjects(detailInfo.getResearchProjects()));
        innovation.put("awards", countAwards(detailInfo.getTeachingAwards()));
        features.put("innovation", innovation);
        log.debug("创新特征数据: {}", innovation);

        requestData.put("features", features);

        // 4. 调用分析API
        log.info("开始调用画像分析API，请求地址: {}", ANALYSIS_API_URL);
        try {
            // 打印请求数据
            log.debug("画像分析API请求数据: {}", objectMapper.writeValueAsString(requestData));
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestData, headers);
            
            log.info("正在发送HTTP请求到画像分析API...");
            
            // 使用exchange方法替代postForObject，以获取更多响应信息
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                ANALYSIS_API_URL,
                HttpMethod.POST,
                request,
                String.class
            );

            log.info("画像分析API调用成功，HTTP状态码: {}", responseEntity.getStatusCode());
            String responseBody = responseEntity.getBody();
            log.debug("画像分析API原始响应数据: {}", responseBody);

            // 解析响应数据
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
            log.debug("API响应原始数据: {}", responseMap);
            
            // 获取data部分
            @SuppressWarnings("unchecked")
            Map<String, Object> data = (Map<String, Object>) responseMap.get("data");
            if (data == null) {
                log.error("API响应中没有data字段");
                return R.error("画像分析失败：API响应格式错误");
            }
            
            // 获取capabilities部分
            @SuppressWarnings("unchecked")
            Map<String, Double> capabilities = (Map<String, Double>) data.get("capabilities");
            if (capabilities == null) {
                log.error("API响应中没有capabilities字段");
                return R.error("画像分析失败：API响应格式错误");
            }

            // 构建DTO对象
            PortraitAnalysisDTO analysisDTO = new PortraitAnalysisDTO();
            analysisDTO.setStaffId(personnelId.toString());
            
            // 计算总体评分（各能力维度的平均值）
            double overallScore = capabilities.values().stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);
            analysisDTO.setOverallScore(overallScore);
            
            // 设置维度评分
            analysisDTO.setDimensionScores(capabilities);
            
            // 生成能力标签
            Map<String, String> capabilityTags = new HashMap<>();
            capabilities.forEach((dimension, score) -> {
                String tag = generateCapabilityTag(dimension, score);
                capabilityTags.put(dimension, tag);
            });
            analysisDTO.setCapabilityTags(capabilityTags);
            
            // 生成发展建议
            Map<String, String> suggestions = new HashMap<>();
            capabilities.forEach((dimension, score) -> {
                String suggestion = generateDevelopmentSuggestion(dimension, score);
                suggestions.put(dimension, suggestion);
            });
            analysisDTO.setDevelopmentSuggestions(suggestions);
            
            analysisDTO.setAnalysisTimestamp(System.currentTimeMillis());

            // 打印处理后的结果
            log.info("画像分析结果 - 总体评分: {}", analysisDTO.getOverallScore());
            log.info("画像分析结果 - 维度评分: {}", analysisDTO.getDimensionScores());
            log.info("画像分析结果 - 能力标签: {}", analysisDTO.getCapabilityTags());
            log.info("画像分析结果 - 发展建议: {}", analysisDTO.getDevelopmentSuggestions());

            return R.ok(analysisDTO);
        } catch (Exception e) {
            log.error("调用画像分析API失败: {}", e.getMessage(), e);
            log.error("异常详细信息: ", e);
            return R.error("画像分析失败：" + e.getMessage());
        } finally {
            log.info("画像分析处理完成，人员ID: {}", personnelId);
        }
    }

    private int countAwards(String awards) {
        if (awards == null || awards.isEmpty()) {
            return 0;
        }
        return awards.split(",").length;
    }

    private int countPublications(String papers) {
        if (papers == null || papers.isEmpty()) {
            return 0;
        }
        return papers.split(",").length;
    }

    private int countPatents(String patents) {
        if (patents == null || patents.isEmpty()) {
            return 0;
        }
        return patents.split(",").length;
    }

    private int countProjects(String projects) {
        if (projects == null || projects.isEmpty()) {
            return 0;
        }
        return projects.split(",").length;
    }

    /**
     * 根据维度和分数生成能力标签
     */
    private String generateCapabilityTag(String dimension, double score) {
        String level;
        if (score >= 90) {
            level = "优秀的";
        } else if (score >= 80) {
            level = "良好的";
        } else if (score >= 70) {
            level = "中等的";
        } else if (score >= 60) {
            level = "基础的";
        } else {
            level = "待提升的";
        }

        switch (dimension) {
            case "teaching":
                return level + "教学能力";
            case "research":
                return level + "科研能力";
            case "management":
                return level + "管理能力";
            case "innovation":
                return level + "创新能力";
            default:
                return level + "综合能力";
        }
    }

    /**
     * 根据维度和分数生成发展建议
     */
    private String generateDevelopmentSuggestion(String dimension, double score) {
        if (score >= 90) {
            switch (dimension) {
                case "teaching":
                    return "建议担任教学带头人，指导其他教师提升教学水平";
                case "research":
                    return "建议主持更高级别的科研项目，带领团队开展创新研究";
                case "management":
                    return "建议承担更多管理职责，发挥领导才能";
                case "innovation":
                    return "建议牵头开展创新项目，推动学科发展";
                default:
                    return "继续保持优秀表现";
            }
        } else if (score >= 80) {
            switch (dimension) {
                case "teaching":
                    return "建议尝试开设新课程，拓展教学领域";
                case "research":
                    return "建议扩大研究范围，增加成果产出";
                case "management":
                    return "建议提升团队管理技能，优化管理流程";
                case "innovation":
                    return "建议加强创新思维，尝试新的研究方向";
                default:
                    return "继续努力，争取更大进步";
            }
        } else if (score >= 70) {
            switch (dimension) {
                case "teaching":
                    return "建议参加教学培训，提升教学技能";
                case "research":
                    return "建议加强科研合作，提升研究水平";
                case "management":
                    return "建议学习先进管理经验，提升管理效率";
                case "innovation":
                    return "建议加强创新意识，培养创新能力";
                default:
                    return "需要在各方面继续提升";
            }
        } else {
            switch (dimension) {
                case "teaching":
                    return "建议系统提升教学能力，参加专业培训";
                case "research":
                    return "建议加强科研基础，提升研究能力";
                case "management":
                    return "建议强化管理知识，提升管理水平";
                case "innovation":
                    return "建议培养创新思维，提升创新能力";
                default:
                    return "需要制定具体提升计划";
            }
        }
    }
} 