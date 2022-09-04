package com.dahaeng.controller;

import com.dahaeng.biz.member.MemberService;
import com.dahaeng.biz.member.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody MemberVO vo) {
        ResponseEntity<String> entity = null;

        try {
            memberService.insertMember(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody MemberVO vo){
        ResponseEntity<String> entity = null;

        try {
            memberService.deleteMember(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }


}
