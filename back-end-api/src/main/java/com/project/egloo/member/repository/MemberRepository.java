package com.project.egloo.member.repository;

import com.project.egloo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{
    Optional<Member> findByUserIdAndPassword(String userId, String password);
    Optional<Member> findByUserId(String userId);
}
