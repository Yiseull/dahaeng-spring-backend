package com.dahaeng.biz.line;

import javax.persistence.*;


@Entity
@Table(name = "LINE")
public class LineVO {
    @Id
    private int lineId;

    private String contents;

    private int categoryId;

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getContents() {return contents;}

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "LineVO{" +
                "lineId=" + lineId +
                ", contents='" + contents + '\'' +
                ",  categoryId=" + categoryId +
                '}';
    }
}