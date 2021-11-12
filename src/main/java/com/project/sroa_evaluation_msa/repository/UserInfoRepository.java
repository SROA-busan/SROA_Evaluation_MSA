package com.project.sroa_evaluation_msa.repository;

import com.project.sroa_evaluation_msa.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query(nativeQuery = true, value = "SELECT u.* FROM user_info u WHERE u.id like ?1")
    UserInfo findById(String id);

}
