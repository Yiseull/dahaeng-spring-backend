package com.dahaeng.biz;

import java.util.List;

public interface NoteService {
    void insertNote(NoteVO vo);

    void updateNote(NoteVO vo);

    void deleteNote(NoteVO vo);

    NoteVO getNote(NoteVO vo);

    List<NoteVO> getNoteList();

}
