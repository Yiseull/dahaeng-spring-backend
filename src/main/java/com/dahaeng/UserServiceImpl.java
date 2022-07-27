package com.dahaeng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    public void insertUser(UserVO vo) {
        userDAO.insertUser(vo);
    }

    public UserVO getUser(UserVO vo) {
        return userDAO.getUser(vo);
    }

    public void deleteUser(UserVO vo) {
        userDAO.deleteUser(vo);
    }
}
