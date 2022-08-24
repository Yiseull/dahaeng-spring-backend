package com.dahaeng.biz.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDAO noteDAO;

    @Transactional
    @Override
    public void insertNote(NoteVO vo) {
        noteDAO.insertNote(vo);
    }

    @Transactional
    @Override
    public void updateNote(NoteVO vo) {
        noteDAO.updateNote(vo);
    }

    @Transactional
    @Override
    public void deleteNote(NoteVO vo) {
        noteDAO.deleteNote(vo);
    }

    @Transactional
    @Override
    public NoteVO getNote(NoteVO vo) {
        return noteDAO.getNote(vo);
    }

    @Transactional
    @Override
    public List<NoteVO> getNoteList(String email) {
        return noteDAO.getNoteList(email);
    }
}
