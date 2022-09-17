package com.dahaeng.biz.note;

import java.util.List;

public interface NoteService {
    void insertNote(NoteVO vo);

    void updateNote(NoteVO vo);

    void deleteNoteCompletely(int noteId);

    NoteVO getNote(int noteId);

    List<NoteVO> getNoteList(String email);

    int getNoteId();

}
