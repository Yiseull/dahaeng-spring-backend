package com.dahaeng.biz.user;

import java.util.List;

public interface UserService {
    void insertUser(UserVO vo);

    UserVO findByEmail(String mail);

    UserVO findByNickname(String nickname);

    UserVO findByEmailAndPassword(UserVO vo);

    void deleteUser(UserVO vo);

    List<UserVO> findMember(String email);

    UserVO updateUser(UserVO vo, String type);
}