package com.dahaeng.biz;

public interface UserService {
    public void insertUser(UserVO vo);

    public UserVO findByEmail(UserVO vo);

    public UserVO findByEmailAndPassword(UserVO vo);

    public void deleteUser(UserVO vo);

}
