package com.dahaeng.biz.note;

import java.util.List;

public interface NoteService {
    void insertNote(NoteVO vo);

    void updateNote(NoteVO vo);

    void deleteNoteCompletely(NoteVO vo);

    NoteVO getNote(NoteVO vo);

    List<NoteVO> getNoteList(String email);

}
