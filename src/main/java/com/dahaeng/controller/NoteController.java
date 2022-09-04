package com.dahaeng.controller;

import com.dahaeng.biz.note.NoteService;
import com.dahaeng.biz.note.NoteVO;
import com.dahaeng.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody NoteVO vo) {
        ResponseEntity<String> entity = null;

        try {
            noteService.insertNote(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody NoteVO vo) {
        ResponseEntity<String> entity = null;

        try {
            noteService.updateNote(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }


//    @DeleteMapping("/delete")
//    public String delete(@RequestBody NoteVO vo){
//        ResponseEntity<String> entity = null;
//
//        return entity;
//    }

    @DeleteMapping("/deleteCompletely")
    public ResponseEntity<String> deleteCompletely(@RequestBody Map<String, Object> param) {
        ResponseEntity<String> entity = null;
        int noteId = (int) param.get("noteId");

        try {
            noteService.deleteNoteCompletely(noteId);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @GetMapping("/get")
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

    @GetMapping("/list")
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