package com.dahaeng.controller;

import com.dahaeng.biz.member.MemberService;
import com.dahaeng.biz.member.MemberVO;
import com.dahaeng.biz.note.NoteVO;
import com.dahaeng.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/insertMember")
    public String insertMember(HttpServletRequest request,MemberVO vo) {
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
        NoteVO note = (NoteVO) session.getAttribute("note");

        vo.setEmail(user.getEmail());
        vo.setNoteId(note.getNoteId());
        memberService.insertMember(vo);

        return "getNoteList";
    }

    @RequestMapping("/deleteMember")
    public String deleteMember(HttpServletRequest request, MemberVO vo){
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
        NoteVO note = (NoteVO) session.getAttribute("note");

        vo.setEmail(user.getEmail());
        vo.setNoteId(note.getNoteId());
        memberService.deleteMember(vo);

        return "getNoteList";
    }

}
