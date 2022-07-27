package com.dahaeng;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class UserVO {
    @Id
    private String email;
    private String password;
    private String nickname;
    private String id_token;
    private int notificationCheck;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public int getNotificationCheck() {
        return notificationCheck;
    }

    public void setNotificationCheck(int notificationCheck) {
        this.notificationCheck = notificationCheck;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", id_token='" + id_token + '\'' +
                ", notificationCheck=" + notificationCheck +
                '}';
    }
}
