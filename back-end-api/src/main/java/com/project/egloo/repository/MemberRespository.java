package com.project.egloo.repository;

import com.project.egloo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRespository extends JpaRepository<Member,Long>{
    Member findByUserIdAndPassword(String userId, String password);
    Member findByUserId(String userId);
}
