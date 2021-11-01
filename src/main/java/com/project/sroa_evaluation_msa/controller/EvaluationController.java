package com.project.sroa_evaluation_msa.controller;

import com.project.sroa_evaluation_msa.dto.WriteEvaluation;
import com.project.sroa_evaluation_msa.model.Evaluation;
import com.project.sroa_evaluation_msa.model.Schedule;
import com.project.sroa_evaluation_msa.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@ResponseBody
public class EvaluationController {
    // status : 0-> 예약완료 , 1 -> 처리 완료, 2 -> 수령, 3 -> 수리 완료, 4 -> 반납예약완료, 5-> 평가 완료

    EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluation/customer/writeEvaluation")
    public boolean writeEvaluation(@RequestBody WriteEvaluation form) {

        Schedule schedule = evaluationService.updateChargeEmployeeScore(form.getScheduleNum(), form.getScore());
        schedule = evaluationService.updateSchedule(schedule);

        Evaluation evaluation = Evaluation.builder()
                .writeDate(Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
                .content(form.getContent())
                .score(form.getScore())
                .schedule(schedule)
                .build();

        return evaluationService.storeEvaluation(evaluation);
    }

    @GetMapping("/evaluation/engineer/askEvaluation/{engineerNum}")
    public List<Evaluation> askEvaluation(@PathVariable(value = "engineerNum") Long engineerNum) {
        return evaluationService.evaluationOfEngineer(engineerNum);
    }
}
