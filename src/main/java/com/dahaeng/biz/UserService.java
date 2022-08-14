package com.dahaeng.biz;

public interface UserService {
    public void insertUser(UserVO vo);

    public UserVO findByEmail(String mail);

    public UserVO findByNickname(String nickname);

    public UserVO findByEmailAndPassword(UserVO vo);

    public void deleteUser(UserVO vo);

}