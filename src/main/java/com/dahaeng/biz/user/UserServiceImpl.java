package com.dahaeng.biz.user;

import com.dahaeng.biz.note.NoteVO;
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
    public UserVO findByEmailAndPassword(String email, String password) {
        return userDAO.findByEmailAndPassword(email, password);
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        userDAO.deleteUser(email);
    }

    @Transactional
    @Override
    public List<UserVO> findMember(String email) {
        return userDAO.findMember(email);
    }

    @Transactional
    @Override
    public void editNickname(String email, String nickname) {
        userDAO.editNickname(email, nickname);
    }

    @Transactional
    @Override
    public void editPassword(String email, String password) {
        userDAO.editPassword(email, password);
    }

    @Transactional
    @Override
    public List<UserVO> getMemberList(int noteId) {
        return userDAO.getMemberList(noteId);
    }
}