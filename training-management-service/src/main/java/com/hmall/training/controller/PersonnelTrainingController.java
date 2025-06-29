package com.hmall.training.controller;

import com.hmall.common.result.Result;
import com.hmall.training.domain.dto.PersonnelTrainingDTO;
import com.hmall.training.domain.vo.PersonnelTrainingAnalysisVO;
import com.hmall.training.service.PersonnelTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/training")
public class PersonnelTrainingController {
    @Autowired
    private PersonnelTrainingService trainingService;

    /**
     * 分析单个人员培训需求
     */
    @GetMapping("/analyses/{personnelId}")
    public Result<PersonnelTrainingAnalysisVO> analyzeTrainingNeeds(
            @PathVariable @Valid Integer personnelId) {
        PersonnelTrainingAnalysisVO result = trainingService.analyzeTrainingNeeds(personnelId);
        return Result.ok(result);
    }

    /**
     * 批量分析人员培训需求 - GET方式
     */
    @GetMapping("/analyses/batch")
    public Result<List<PersonnelTrainingAnalysisVO>> batchAnalyzeTrainingNeedsByGet(
            @RequestParam List<Integer> personnelIds) {
        List<PersonnelTrainingAnalysisVO> result = trainingService.batchAnalyzeTrainingNeeds(personnelIds);
        return Result.ok(result);
    }

    /**
     * 根据培训课程筛选人员
     */
    @GetMapping("/screened-personnel")
    public Result<List<PersonnelTrainingAnalysisVO>> screenPersonnelByTrainingCourse(
            @RequestParam String trainingCourse) {
        List<PersonnelTrainingAnalysisVO> result = trainingService.screenPersonnelByTrainingCourse(trainingCourse);
        return Result.ok(result);
    }

    /**
     * 生成培训人员名单
     */
    @GetMapping("/training-list")
    public Result<List<PersonnelTrainingAnalysisVO>> generateTrainingPersonnelList(
            @RequestParam String trainingCourse) {
        List<PersonnelTrainingAnalysisVO> result = trainingService.generateTrainingPersonnelList(trainingCourse);
        return Result.ok(result);
    }

    /**
     * 保存人员培训信息
     */
    @PostMapping("/records")
    public Result<Void> savePersonnelTraining(@RequestBody @Valid PersonnelTrainingDTO trainingDTO) {
        trainingService.savePersonnelTraining(trainingDTO);
        return Result.ok();
    }
}
