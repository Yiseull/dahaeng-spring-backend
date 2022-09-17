package com.dahaeng.controller;

import com.dahaeng.biz.member.MemberService;
import com.dahaeng.biz.member.MemberVO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/insert")
    public ResponseEntity<JSONObject> insert(@RequestBody MemberVO vo) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        try {
            memberService.insertMember(vo);
            obj.put("result", "SUCCESS");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result", "FAIL");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<JSONObject> delete(@RequestBody MemberVO vo){
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        try {
            memberService.deleteMember(vo);
            obj.put("result", "SUCCESS");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result", "FAIL");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }


}