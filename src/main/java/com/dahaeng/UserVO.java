public class UserVO {
    private int id;
    private String email;
    private String nickname;
    private int notificationCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getNotificationCheck() {
        return notificationCheck;
    }

    public void setNotificationCheck(int notificationCheck) {
        this.notificationCheck = notificationCheck;
    }

}
