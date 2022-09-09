package com.dahaeng.biz.category;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertCategory(CategoryVO vo) {

        System.out.println("categorydao");
        entityManager.persist(vo);
        System.out.println("categorydao end");
    }

    public void updateCategory(CategoryVO vo) {
        entityManager.merge(vo);
    }

    public void deleteCategory(int categoryId) {
        entityManager.remove(entityManager.find(CategoryVO.class, categoryId));
    }

    public CategoryVO getCategory(int categoryId) {
        return (CategoryVO) entityManager.find((CategoryVO.class), categoryId);
    }

    public List<CategoryVO> getCategoryList(int noteId) {

        String jpql = "select c from CategoryVO c where c.noteId = "+ noteId;
        return entityManager.createQuery(jpql).getResultList();
    }
}