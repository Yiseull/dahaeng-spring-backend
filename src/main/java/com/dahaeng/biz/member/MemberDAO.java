package com.dahaeng.biz.member;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
public class MemberDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertMember(MemberVO vo) {
        entityManager.persist(vo);
    }

    public void deleteMember(MemberVO vo) {
        Query query =  entityManager.createQuery("DELETE FROM MemberVO m WHERE m.email = ?1 and m.noteId = ?2");
        query.setParameter(1,vo.getEmail());
        query.setParameter(2,vo.getNoteId());
        query.executeUpdate();
    }

    public MemberVO findByEmail(String mail) {
        String jpql = "SELECT m FROM MemberVO m WHERE m.email = ?1";
        TypedQuery<MemberVO> query = entityManager.createQuery(jpql, MemberVO.class)
                .setParameter(1, mail);
        MemberVO member = null;
        try {
            member = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return member;
    }





}