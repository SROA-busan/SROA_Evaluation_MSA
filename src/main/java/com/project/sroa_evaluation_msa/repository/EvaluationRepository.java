package com.project.sroa_evaluation_msa.repository;

import com.project.sroa_evaluation_msa.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {


    @Query(nativeQuery = true, value = "SELECT e.* FROM evaluation e WHERE e.schedule_num IN (SELECT s.schedule_num FROM schedule s WHERE s.engineer_num=?1);")
    List<Evaluation> findAllByEngineerNum(Long engineerNum);
}
