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
    public UserVO findByEmail(String mail) {
        return userDAO.findByEmail(mail);
    }

    @Transactional(readOnly = true)
    public UserVO findByNickname(String nickname) {
        return userDAO.findByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public UserVO findByEmailAndPassword(UserVO vo) {
        return userDAO.findByEmailAndPassword(vo);
    }

    @Transactional(readOnly = true)
    public void deleteUser(UserVO vo) {
        userDAO.deleteUser(vo);
    }

    @Transactional(readOnly = true)
    public UserVO editUser(UserVO vo) {
        return userDAO.editUser(vo);
    }
}