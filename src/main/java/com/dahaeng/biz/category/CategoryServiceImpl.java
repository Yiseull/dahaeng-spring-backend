package com.dahaeng.biz.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    @Override
    public void insertCategory(CategoryVO vo) {
        categoryDAO.insertCategory(vo);
    }

    @Transactional
    @Override
    public void updateCategory(CategoryVO vo) {
        categoryDAO.updateCategory(vo);
    }

    @Transactional
    @Override
    public void deleteCategory(int categoryId) {
        categoryDAO.deleteCategory(categoryId);
    }

    @Transactional
    @Override
    public CategoryVO getCategory(int categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    @Transactional
    @Override
    public List<CategoryVO> getCategoryList(int noteId) {
        return categoryDAO.getCategoryList(noteId);
    }
}