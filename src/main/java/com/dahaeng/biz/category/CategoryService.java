package com.dahaeng.biz.category;

import java.util.List;

public interface CategoryService {
    void insertCategory(CategoryVO vo);

    void updateCategory(CategoryVO vo);

    void deleteCategory(int categoryId);

    CategoryVO getCategory(int categoryId);

    List<CategoryVO> getCategoryList(int noteId);

    int getCategoryId();

}