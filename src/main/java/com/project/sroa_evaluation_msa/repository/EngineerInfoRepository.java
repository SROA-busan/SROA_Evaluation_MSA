package com.project.sroa_evaluation_msa.repository;

import com.project.sroa_evaluation_msa.model.EngineerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface EngineerInfoRepository extends JpaRepository<EngineerInfo, Long> {


    EngineerInfo findByEngineerNum(long engineerNum);


    @Transactional
    @Modifying
    @Query("UPDATE EngineerInfo e SET e.avgScore=?2 WHERE e.engineerNum=?1")
    void updateEngineerScore(long engineerNum, Integer avgScore);


    @Query(nativeQuery = true, value = "SELECT e.* FROM engineer_info e WHERE e.user_num=?1 ")
    EngineerInfo findByUserNum(Long userNum);
}
