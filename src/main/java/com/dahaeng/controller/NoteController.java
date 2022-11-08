package com.dahaeng.controller;

import com.dahaeng.biz.category.CategoryService;
import com.dahaeng.biz.category.CategoryVO;
import com.dahaeng.biz.member.MemberService;
import com.dahaeng.biz.member.MemberVO;
import com.dahaeng.biz.note.NoteService;
import com.dahaeng.biz.note.NoteVO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin(origins = "*")
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/insert")
    public ResponseEntity<JSONObject> insert(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        NoteVO noteVO = new NoteVO();
        CategoryVO categoryVO = new CategoryVO();
        MemberVO memberVO = new MemberVO();
        JSONObject obj = new JSONObject();

        noteVO.setNoteName((String) param.get("noteName"));
        noteVO.setStartDate((String) param.get("startDate"));
        noteVO.setEndDate((String) param.get("endDate"));
        noteVO.setNoteDescription((String) param.get("noteDescription"));
        noteVO.setNoteColor((int)param.get("noteColor"));

        try {
            noteService.insertNote(noteVO);
            int noteId = noteService.getNoteId();
            System.out.println(noteId);
            categoryVO.setCategoryName("카테고리");
            categoryVO.setNoteId(noteId);
            categoryService.insertCategory(categoryVO);
            memberVO.setEmail((String) param.get("email"));
            memberVO.setNoteId(noteId);
            memberService.insertMember(memberVO);
            obj.put("result","SUCCESS");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result","FAIL");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @PostMapping("/update")
    public ResponseEntity<JSONObject> update(@RequestBody NoteVO vo) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        try {
            noteService.updateNote(vo);
            obj.put("result","SUCCESS");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result","FAIL");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }


//    @DeleteMapping("/delete")
//    public String delete(@RequestBody NoteVO vo){
//        ResponseEntity<String> entity = null;
//
//        return entity;
//    }

    @PostMapping("/deleteCompletely")
    public ResponseEntity<JSONObject> deleteCompletely(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();
        int noteId = (int) param.get("noteId");

        try {
            noteService.deleteNoteCompletely(noteId);
            obj.put("result","SUCCESS");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result","FAIL");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/get")
    public ResponseEntity<NoteVO> get(@RequestBody Map<String, Object> param) {
        ResponseEntity<NoteVO> entity = null;
        int noteId = (int) param.get("noteId");

        try {
            entity = new ResponseEntity<NoteVO>(noteService.getNote(noteId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/list")
    public ResponseEntity<List<NoteVO>> list(@RequestBody Map<String, Object> param) {
        ResponseEntity<List<NoteVO>> entity = null;
        String email = (String) param.get("email");

        try {
            entity = new ResponseEntity<>(
                    noteService.getNoteList(email), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}