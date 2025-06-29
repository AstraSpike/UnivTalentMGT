package com.hmall.training.service;

import com.hmall.training.domain.dto.PersonnelTrainingDTO;
import com.hmall.training.domain.vo.PersonnelTrainingAnalysisVO;

import java.util.List;

public interface PersonnelTrainingService {
    /**
     * 分析人员培训需求
     * @param personnelId 人员ID
     * @return 培训需求分析结果
     */
    PersonnelTrainingAnalysisVO analyzeTrainingNeeds(Integer personnelId);

    /**
     * 批量分析人员培训需求
     * @param personnelIds 人员ID列表
     * @return 培训需求分析结果列表
     */
    List<PersonnelTrainingAnalysisVO> batchAnalyzeTrainingNeeds(List<Integer> personnelIds);

    /**
     * 根据培训课程筛选人员
     * @param trainingCourse 培训课程
     * @return 适合参加培训的人员列表
     */
    List<PersonnelTrainingAnalysisVO> screenPersonnelByTrainingCourse(String trainingCourse);

    /**
     * 生成培训人员名单
     * @param trainingCourse 培训课程
     * @return 培训人员名单
     */
    List<PersonnelTrainingAnalysisVO> generateTrainingPersonnelList(String trainingCourse);

    /**
     * 保存人员培训信息
     * @param trainingDTO 人员培训信息
     */
    void savePersonnelTraining(PersonnelTrainingDTO trainingDTO);
}
