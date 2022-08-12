package com.dahaeng.biz;

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

    public void deleteNote(NoteVO vo) {
        entityManager.remove(entityManager.find(NoteVO.class, vo.getNoteId()));
    }

    public NoteVO getNote(NoteVO vo) {
        return (NoteVO) entityManager.find((NoteVO.class), vo.getNoteId());
    }

    public List<NoteVO> getNoteList() {
        String jpql = "select n from NoteVO n";
        return entityManager.createQuery(jpql).getResultList();
    }
}
