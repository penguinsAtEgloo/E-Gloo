package com.project.egloo.member.repository;

import com.project.egloo.member.domain.Member;
import com.project.egloo.member.dto.response.UserProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);

    Optional<Member> findByEmail(String email);

    Optional<UserProfileResponse> findMemberByEmail(String email);
}
