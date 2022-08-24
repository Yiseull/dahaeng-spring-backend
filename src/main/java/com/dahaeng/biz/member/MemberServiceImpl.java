package com.dahaeng.biz.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberDAO memberDAO;

    @Transactional
    @Override
    public void insertMember(MemberVO vo) {
        memberDAO.insertMember(vo);
    }

    @Transactional
    @Override
    public MemberVO findByEmail(String mail) {
        return memberDAO.findByEmail(mail);
    }
}

