package com.dahaeng.biz.note;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "NOTE")
public class NoteVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties(allowSetters = true)
    private int noteId;
    private String noteName;

    private String setDate;

    private String noteDescription;

    private int noteColor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastUpdate;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    public String getSetDate() {
        return setDate;
    }

    public void setSetDate(String setDate) {
        this.setDate = setDate;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public int getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(int noteColor) {
        this.noteColor = noteColor;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "NoteVO{" +
                "noteId=" + noteId +
                ", noteName='" + noteName + '\'' +
                ", setDate='" + setDate + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                ", noteColor=" + noteColor +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}