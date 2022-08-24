package com.dahaeng.biz.user;

public interface UserService {
    void insertUser(UserVO vo);

    UserVO findByEmail(String mail);

    UserVO findByNickname(String nickname);

    UserVO findByEmailAndPassword(UserVO vo);

    void deleteUser(UserVO vo);

}