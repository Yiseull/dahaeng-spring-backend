package com.dahaeng.biz.member;

public interface MemberService {
    void insertMember(MemberVO vo);

    void deleteMember(MemberVO vo);

    MemberVO findByEmail(String mail);
}