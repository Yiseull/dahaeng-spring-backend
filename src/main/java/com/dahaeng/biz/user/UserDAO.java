package com.dahaeng.biz.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertUser(UserVO vo) {
        entityManager.persist(vo);
    }

    public UserVO findByEmailAndPassword(UserVO vo) {
        String jpql = "SELECT u FROM UserVO u WHERE u.email = ?1 and u.password = ?2";
        TypedQuery<UserVO> query = entityManager.createQuery(jpql, UserVO.class)
                .setParameter(1, vo.getEmail())
                .setParameter(2, vo.getPassword());
        UserVO user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

    public UserVO findByEmail(String mail) {
        String jpql = "SELECT u FROM UserVO u WHERE u.email = ?1";
        TypedQuery<UserVO> query = entityManager.createQuery(jpql, UserVO.class)
                .setParameter(1, mail);
        UserVO user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public UserVO findByNickname(String nickname) {
        String jpql = "SELECT u FROM UserVO u WHERE u.nickname = ?1";
        TypedQuery<UserVO> query = entityManager.createQuery(jpql, UserVO.class)
                .setParameter(1, nickname);
        UserVO user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    //public UserVO findByEmail1(UserVO vo) {
    //    return (UserVO) entityManager.find((UserVO.class), vo.getEmail());
    //}

    public void deleteUser(UserVO vo) {
        entityManager.remove(entityManager.find(UserVO.class, vo.getEmail()));
    }

}