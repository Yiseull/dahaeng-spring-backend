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
    public void deleteNoteCompletely(int noteId) {
        noteDAO.deleteNoteCompletely(noteId);
    }

    @Transactional
    @Override
    public NoteVO getNote(int noteId) {
        return noteDAO.getNote(noteId);
    }

    @Transactional
    @Override
    public List<NoteVO> getNoteList(String email) {
        return noteDAO.getNoteList(email);
    }

    @Transactional
    @Override
    public int getNoteId() {
        return noteDAO.getNoteId();
    }
}
