package com.dahaeng.controller;

import com.dahaeng.biz.NoteService;
import com.dahaeng.biz.NoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @RequestMapping("/insertNote")
    public String insertNote(NoteVO vo) {
        noteService.insertNote(vo);
        return "getNoteList";
    }

    @RequestMapping("/updateNote")
    public String updateNote(@ModelAttribute("note") NoteVO vo) {
        noteService.updateNote(vo);
        return "/getNoteList";
    }

    @RequestMapping("/deleteNote")
    public String deleteNote(NoteVO vo) {
        noteService.deleteNote(vo);
        return "/getNoteList";
    }

    @RequestMapping("/getNote")
    public String getNote(NoteVO vo, Model model) {
        model.addAttribute("note", noteService.getNote(vo)); // Model 정보 저장
        return "getNote.jsp";  // View 이름 리턴
    }

    @RequestMapping("/getNoteList")
    public String getNoteList(Model model) {
        // Model 정보 저장
        model.addAttribute("noteList", noteService.getNoteList());
        return "getNoteList.jsp";  // View 이름 리턴
    }
}
