package com.dahaeng.biz.line;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "LINE")
public class LineVO {
    @Id
    private int lineId;

    private String text;

    private String type;

    private String color;

    private String bgcolor;

    private String font;

    private int categoryId;

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getText() {return text;}

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {return type;}

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {return color;}

    public void setColor(String color) {
        this.color = color;
    }

    public String getBgcolor() {return bgcolor;}

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getFont() {return font;}

    public void setFont(String font) {
        this.font = font;
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
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", bgcolor='" + bgcolor + '\'' +
                ", font='" + font + '\'' +
                ",  categoryId=" + categoryId +
                '}';
    }
}