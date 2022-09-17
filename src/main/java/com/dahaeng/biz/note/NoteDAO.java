package com.dahaeng.biz.note;

import com.dahaeng.biz.member.MemberVO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public void deleteNoteCompletely(int noteId) {
        entityManager.remove(entityManager.find(NoteVO.class, noteId));
    }

    public NoteVO getNote(int noteId) {
        return (NoteVO) entityManager.find((NoteVO.class), noteId);
    }

    public List<NoteVO> getNoteList(String email) {

        String jpql = "select n from NoteVO n where n.noteId In (select m.noteId from MemberVO m where m.email = '"+email+"')";
        return entityManager.createQuery(jpql).getResultList();
    }

    public int getNoteId() {
        String jpql = "SELECT max(n.noteId) FROM NoteVO n";
        return (int) entityManager.createQuery(jpql).getSingleResult();
    }
}
