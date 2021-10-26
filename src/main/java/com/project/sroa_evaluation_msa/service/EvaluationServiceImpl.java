package com.project.sroa_evaluation_msa.service;

import com.project.sroa_evaluation_msa.model.EngineerInfo;
import com.project.sroa_evaluation_msa.model.Evaluation;
import com.project.sroa_evaluation_msa.model.Schedule;
import com.project.sroa_evaluation_msa.repository.EngineerInfoRepository;
import com.project.sroa_evaluation_msa.repository.EvaluationRepository;
import com.project.sroa_evaluation_msa.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    ScheduleRepository scheduleRepository;
    EvaluationRepository evaluationRepository;
    EngineerInfoRepository engineerInfoRepository;

    @Autowired
    public EvaluationServiceImpl(ScheduleRepository scheduleRepository,
                                 EvaluationRepository evaluationRepository,
                                 EngineerInfoRepository engineerInfoRepository) {
        this.scheduleRepository = scheduleRepository;
        this.evaluationRepository = evaluationRepository;
        this.engineerInfoRepository = engineerInfoRepository;
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        scheduleRepository.updateStatus(schedule.getScheduleNum(), 4);
        System.out.println("평가 작성 : 일정 상태 변경(평가작성 완료)");
        schedule.setStatus(4);
        return schedule;
    }

    @Override
    public Schedule updateChargeEmployeeScore(Long scheduleNum, Integer score) {
        Schedule schedule = scheduleRepository.findByScheduleNum(scheduleNum);
        // 기사의 평균 평점 수정
        long totalScore = 0;
        List<Evaluation> list = evaluationRepository.findAllByEngineerNum(schedule.getEngineerInfo().getEngineerNum());

        for (Evaluation e : list) {
            totalScore += e.getScore();
        }

        totalScore += score;
        Integer avgScore = Math.toIntExact(totalScore / (list.size() + 1));
        engineerInfoRepository.updateEngineerScore(schedule.getEngineerInfo().getEngineerNum(), avgScore);
        System.out.println("평가 작성 : 엔지니어 평균 평점 갱신");
        return schedule;
    }

    @Override
    public boolean storeEvaluation(Evaluation evaluation) {
        evaluationRepository.save(evaluation);
        System.out.println("평가 작성 : 평가 저장");
        return true;
    }

    @Override
    public List<Evaluation> evaluationOfEngineer(long engineerNum) {
        EngineerInfo engineerInfo = engineerInfoRepository.findByEngineerNum(engineerNum);

        List<Evaluation> list = evaluationRepository.findAllByEngineerNum(engineerInfo.getEngineerNum());
        System.out.println("평가 조회 : 평가 완료된 일정에 대해 해당 엔지니어 일정 조회");

        return list;
    }
}
