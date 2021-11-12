package com.project.sroa_evaluation_msa.service;

import com.project.sroa_evaluation_msa.model.EngineerInfo;
import com.project.sroa_evaluation_msa.model.Evaluation;
import com.project.sroa_evaluation_msa.model.Schedule;
import com.project.sroa_evaluation_msa.model.UserInfo;

import java.util.List;


public interface EvaluationService {
    boolean storeEvaluation(Evaluation evaluation);

    Schedule updateSchedule(Schedule schedule);

    Schedule updateChargeEmployeeScore(Long scheduleNum, Integer score);

    List<Evaluation> evaluationOfEngineer(long engineerNum);

    UserInfo findUserById(String id);


    EngineerInfo findEngineerByUserNum(Long userNum);
}
