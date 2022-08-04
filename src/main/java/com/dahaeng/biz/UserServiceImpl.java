package com.dahaeng.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void insertUser(UserVO vo) {
        userDAO.insertUser(vo);
    }

    @Transactional(readOnly = true)
    public UserVO findByEmail(UserVO vo) {
        return userDAO.findByEmail(vo);
    }

    @Transactional(readOnly = true)
    public UserVO findByEmailAndPassword(UserVO vo) {
        return userDAO.findByEmailAndPassword(vo);
    }

    @Transactional
    public void deleteUser(UserVO vo) {
        userDAO.deleteUser(vo);
    }
}