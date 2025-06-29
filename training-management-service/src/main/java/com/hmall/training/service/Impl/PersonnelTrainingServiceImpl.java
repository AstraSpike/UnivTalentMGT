package com.hmall.training.service.Impl;


import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.hmall.training.domain.dto.PersonnelTrainingDTO;
import com.hmall.training.domain.po.PersonnelBasicInfoPO;
import com.hmall.training.domain.po.PersonnelTrainingNeedsPO;
import com.hmall.training.domain.vo.PersonnelTrainingAnalysisVO;
import com.hmall.training.repository.PersonnelBasicInfoRepository;
import com.hmall.training.repository.PersonnelTrainingNeedsRepository;
import com.hmall.training.service.PersonnelTrainingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonnelTrainingServiceImpl implements PersonnelTrainingService {
    @Autowired
    private PersonnelBasicInfoRepository personnelBasicInfoRepository;

    @Autowired
    private PersonnelTrainingNeedsRepository trainingNeedsRepository;

    @Override
    public PersonnelTrainingAnalysisVO analyzeTrainingNeeds(Integer personnelId) {
        // 查询人员基本信息
        Optional<PersonnelBasicInfoPO> basicInfoOptional = personnelBasicInfoRepository.findById(personnelId);
        if (basicInfoOptional.isEmpty()) {
            return null;
        }
        PersonnelBasicInfoPO basicInfo = basicInfoOptional.get();

        // 查询人员培训需求
        Optional<PersonnelTrainingNeedsPO> trainingNeedsOptional = trainingNeedsRepository.findByPersonnelId(personnelId);

        // 调用机器学习模型进行能力分析和课程推荐
        PersonnelTrainingAnalysisVO analysisVO = new PersonnelTrainingAnalysisVO();
        BeanUtils.copyProperties(basicInfo, analysisVO);

        // 手动设置 personnelId
        analysisVO.setPersonnelId(personnelId);

        if (trainingNeedsOptional.isPresent()) {
            PersonnelTrainingNeedsPO trainingNeeds = trainingNeedsOptional.get();
            analysisVO.setAbilityShortcomings(trainingNeeds.getAbilityShortcomings());
            analysisVO.setRecommendedCourses(trainingNeeds.getRecommendedCourses());
        } else {
            // 如果没有培训需求记录，使用模型预测
            String[] abilityShortcomings = predictAbilityShortcomings(basicInfo);
            String[] recommendedCourses = recommendTrainingCourses(abilityShortcomings);

            analysisVO.setAbilityShortcomings(String.join("，", abilityShortcomings));
            analysisVO.setRecommendedCourses(String.join("，", recommendedCourses));

            // 保存预测结果
            PersonnelTrainingNeedsPO newTrainingNeeds = new PersonnelTrainingNeedsPO();
            newTrainingNeeds.setPersonnelId(personnelId);
            newTrainingNeeds.setAbilityShortcomings(analysisVO.getAbilityShortcomings());
            newTrainingNeeds.setRecommendedCourses(analysisVO.getRecommendedCourses());
            trainingNeedsRepository.save(newTrainingNeeds);
        }

        // 设置培训优先级
        analysisVO.setTrainingPriority(calculateTrainingPriority(analysisVO));

        return analysisVO;
    }

    @Override
    public List<PersonnelTrainingAnalysisVO> batchAnalyzeTrainingNeeds(List<Integer> personnelIds) {
        if (CollectionUtils.isEmpty(personnelIds)) {
            return Collections.emptyList();
        }

        return personnelIds.stream()
                .map(this::analyzeTrainingNeeds)  // 修正：使用双冒号方法引用
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonnelTrainingAnalysisVO> screenPersonnelByTrainingCourse(String trainingCourse) {
        // 查询所有人员的培训需求
        List<PersonnelTrainingNeedsPO> allTrainingNeeds = trainingNeedsRepository.findAll();

        // 筛选出需要该培训课程的人员ID
        List<Integer> matchedPersonnelIds = allTrainingNeeds.stream()
                .filter(needs -> needs.getRecommendedCourses() != null && needs.getRecommendedCourses().contains(trainingCourse))
                .map(PersonnelTrainingNeedsPO::getPersonnelId)
                .collect(Collectors.toList());

        // 批量分析这些人员的培训需求
        return batchAnalyzeTrainingNeeds(matchedPersonnelIds);
    }

    @Override
    public List<PersonnelTrainingAnalysisVO> generateTrainingPersonnelList(String trainingCourse) {
        // 筛选人员
        List<PersonnelTrainingAnalysisVO> screenedPersonnel = screenPersonnelByTrainingCourse(trainingCourse);

        // 按培训优先级排序
        return screenedPersonnel.stream()
                .sorted(Comparator.comparingInt(PersonnelTrainingAnalysisVO::getTrainingPriority).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void savePersonnelTraining(PersonnelTrainingDTO trainingDTO) {
        // 保存或更新人员培训信息
        Optional<PersonnelTrainingNeedsPO> trainingNeedsOptional = trainingNeedsRepository.findByPersonnelId(trainingDTO.getPersonnelId());

        PersonnelTrainingNeedsPO trainingNeeds;
        if (trainingNeedsOptional.isPresent()) {
            trainingNeeds = trainingNeedsOptional.get();
            // 更新推荐课程
            String originalCourses = trainingNeeds.getRecommendedCourses();
            if (!originalCourses.contains(trainingDTO.getTrainingCourse())) {
                trainingNeeds.setRecommendedCourses(originalCourses + "，" + trainingDTO.getTrainingCourse());
            }
        } else {
            trainingNeeds = new PersonnelTrainingNeedsPO();
            trainingNeeds.setPersonnelId(trainingDTO.getPersonnelId());
            trainingNeeds.setRecommendedCourses(trainingDTO.getTrainingCourse());
            // 这里可以调用模型预测能力短板
            trainingNeeds.setAbilityShortcomings("需要提升" + trainingDTO.getTrainingCourse().replace("培训课程", ""));
        }

        trainingNeedsRepository.save(trainingNeeds);
    }

    /**
     * 预测人员能力短板（简化版，实际应调用机器学习模型）
     */
    private String[] predictAbilityShortcomings(PersonnelBasicInfoPO basicInfo) {
        // 这里是简化的预测逻辑，实际应用中应该调用机器学习模型
        List<String> shortcomings = new ArrayList<>();

        // 根据职位预测可能的能力短板
        if ("教师".equals(basicInfo.getPosition())) {
            if (basicInfo.getEntryTime().after(new Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000))) {
                // 入职不到两年的教师可能缺乏教学经验
                shortcomings.add("教学经验不足");
            }
            if ("本科".equals(basicInfo.getDegree())) {
                // 本科学历可能需要提升学术水平
                shortcomings.add("学术水平有待提升");
            }
        } else if ("辅导员".equals(basicInfo.getPosition())) {
            if ("群众".equals(basicInfo.getPoliticalStatus())) {
                // 群众身份的辅导员可能需要加强思想政治教育能力
                shortcomings.add("思想政治教育能力不足");
            }
        }

        // 如果没有发现特定短板，添加一个通用短板
        if (shortcomings.isEmpty()) {
            shortcomings.add("团队协作能力需要提升");
        }

        return shortcomings.toArray(new String[0]);
    }

    /**
     * 推荐培训课程（简化版，实际应调用机器学习模型）
     */
    private String[] recommendTrainingCourses(String[] abilityShortcomings) {
        // 这里是简化的推荐逻辑，实际应用中应该调用机器学习模型
        Map<String, String> courseMapping = new HashMap<>();
        courseMapping.put("教学经验不足", "教学方法与技巧培训课程");
        courseMapping.put("学术水平有待提升", "学术研究方法培训课程");
        courseMapping.put("思想政治教育能力不足", "学生思想政治工作培训课程");
        courseMapping.put("团队协作能力需要提升", "团队建设与沟通技巧培训课程");

        return Arrays.stream(abilityShortcomings)
                .map(courseMapping::get)
                .filter(Objects::nonNull)
                .toArray(String[]::new);
    }

    /**
     * 计算培训优先级（简化版）
     */
    private Integer calculateTrainingPriority(PersonnelTrainingAnalysisVO analysisVO) {
        // 根据能力短板数量和职位重要性计算优先级
        int priority = 1;

        // 能力短板越多，优先级越高
        if (analysisVO.getAbilityShortcomings() != null) {
            priority += analysisVO.getAbilityShortcomings().split("，").length;
        }

        // 某些职位优先级更高
        if ("教授".equals(analysisVO.getPosition()) || "系主任".equals(analysisVO.getPosition())) {
            priority += 2;
        }

        return Math.min(priority, 5); // 最大优先级为5
    }
}
