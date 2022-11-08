package com.dahaeng.biz.user;

import java.util.List;

public interface UserService {
    void insertUser(UserVO vo);

    UserVO findByEmail(String mail);

    UserVO findByNickname(String nickname);

    UserVO findByEmailAndPassword(String email, String password);

    void deleteUser(String email);

    List<UserVO> findMember(String email);

    void editNickname(String email, String nickname);

    void editPassword(String email, String password);

    List<UserVO> getMemberList(int noteId);
}