package com.dahaeng.biz.line;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class LineDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertLine(LineVO vo) {
        entityManager.persist(vo);
    }

    public void updateLine(LineVO vo) {
        entityManager.merge(vo);
    }

    public void deleteLine(int lineId) {
        entityManager.remove(entityManager.find(LineVO.class, lineId));
    }

    public LineVO getLine(int lineId) {
        return (LineVO) entityManager.find((LineVO.class), lineId);
    }

    public List<LineVO> getLineList(int categoryId) {

        String jpql = "select l from LineVO l where l.categoryId = "+ categoryId;
        return entityManager.createQuery(jpql).getResultList();
    }
}