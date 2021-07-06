package com.project.egloo.repository;

import com.eatit.main.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRespository extends JpaRepository<Member,Long>{
}
