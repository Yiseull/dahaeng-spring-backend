package com.dahaeng.biz.member;

public interface MemberService {
    public void insertMember(MemberVO vo);

    public MemberVO findByEmail(String mail);




}