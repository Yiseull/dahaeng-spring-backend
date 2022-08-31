package com.dahaeng.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public void insertUser(UserVO vo) {
        userDAO.insertUser(vo);
    }

    @Transactional
    @Override
    public UserVO findByEmail(String mail) {
        return userDAO.findByEmail(mail);
    }

    @Transactional
    @Override
    public UserVO findByNickname(String nickname) {
        return userDAO.findByNickname(nickname);
    }

    @Transactional
    @Override
    public UserVO findByEmailAndPassword(UserVO vo) {
        return userDAO.findByEmailAndPassword(vo);
    }

    @Transactional
    @Override
    public void deleteUser(UserVO vo) {
        userDAO.deleteUser(vo);
    }

    @Transactional
    @Override
    public List<UserVO> findMember(String email) {
        return userDAO.findMember(email);
    }

    @Transactional
    @Override
    public UserVO updateUser(UserVO vo, String type) {
        return userDAO.updateUser(vo, type);
    }
}