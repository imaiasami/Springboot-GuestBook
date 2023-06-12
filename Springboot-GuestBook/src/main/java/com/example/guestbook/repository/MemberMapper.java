package com.example.guestbook.repository;

import com.example.guestbook.model.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    // 회원가입
    void addMember(Member member);

    // 아이디로 회원정보 검색
    Member findMember(String member_id);

}
