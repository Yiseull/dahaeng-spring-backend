package com.dahaeng.biz.category;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class CategoryVO {
    @Id
    private int categoryId;

    private String categoryName;

    private int noteId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ",  noteId=" + noteId +
                '}';
    }
}