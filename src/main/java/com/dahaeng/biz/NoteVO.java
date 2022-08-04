package com.dahaeng.biz;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "NOTE")
public class NoteVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteId;
    private String noteName;
    private LocalDateTime lastUpdate;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "NoteVO{" +
                "noteId=" + noteId +
                ", noteName='" + noteName + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
