package com.dahaeng.biz.line;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

        String jpql = "select l from LineVO l where l.categoryId = '"+ categoryId + "'order by l.lineindex";
        return entityManager.createQuery(jpql).getResultList();
    }

    //인덱스에 1 더해서 자리 만들기
    public void plusindex(int categoryId, int lineindex) {
        String jpql;
        jpql = "UPDATE LineVO l SET l.lineindex = l.lineindex + 1 where l.categoryId=?1 and l.lineindex > ?2";
        entityManager.createQuery(jpql)
                .setParameter(1, categoryId)
                .setParameter(2, lineindex)
                .executeUpdate();
    }
}