package com.project.egloo.member.mapper;

import com.project.egloo.member.domain.Member;
import com.project.egloo.member.dto.response.MemberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDTO toDTO(Member member);
}
