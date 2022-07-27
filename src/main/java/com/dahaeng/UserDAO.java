package com.dahaeng;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class)
    public void insertUser(UserVO vo) {
        entityManager.persist(vo);
    }

    public UserVO getUser(UserVO vo) {
        return (UserVO) entityManager.find((UserVO.class), vo.getEmail());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(UserVO vo) {
        entityManager.remove(vo);
    }

}
