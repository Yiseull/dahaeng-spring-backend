package com.dahaeng.biz.note;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NoteDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertNote(NoteVO vo) {
        entityManager.persist(vo);
    }

    public void updateNote(NoteVO vo) {
        entityManager.merge(vo);
    }

    public void deleteNote(NoteVO vo) {
        entityManager.remove(entityManager.find(NoteVO.class, vo.getNoteId()));
    }

    public NoteVO getNote(NoteVO vo) {
        return (NoteVO) entityManager.find((NoteVO.class), vo.getNoteId());
    }

    public List<NoteVO> getNoteList(String email) {

        String jpql = "select n from NoteVO n where n.noteId In (select m.noteId from MemberVO m where m.email = '"+email+"')";
        return entityManager.createQuery(jpql).getResultList();
    }
}
