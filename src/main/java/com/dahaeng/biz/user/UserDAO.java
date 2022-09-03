package com.dahaeng.biz.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertUser(UserVO vo) {
        entityManager.persist(vo);
    }

    public UserVO findByEmailAndPassword(String email, String password) {
        String jpql = "SELECT u FROM UserVO u WHERE u.email = ?1 and u.password = ?2";
        TypedQuery<UserVO> query = entityManager.createQuery(jpql, UserVO.class)
                .setParameter(1, email)
                .setParameter(2, password);
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

    public void deleteUser(String email) {
        entityManager.remove(entityManager.find(UserVO.class, email));
    }

    public List<UserVO> findMember(String email) {

        String jpql = "select u from UserVO u where u.email Like '%"+email+"%'";
        return entityManager.createQuery(jpql).getResultList();
    }

    public void editNickname(String email, String nickname) {
        String jpql;
        jpql = "UPDATE UserVO u SET u.nickname=?1 WHERE u.email = ?2";
        entityManager.createQuery(jpql)
                .setParameter(1, nickname)
                .setParameter(2, email)
                .executeUpdate();
    }

    public void editPassword(String email, String password) {
        String jpql;
        jpql = "UPDATE UserVO u SET u.password=?1 WHERE u.email = ?2";
        entityManager.createQuery(jpql)
                .setParameter(1, password)
                .setParameter(2, email)
                .executeUpdate();
    }
}