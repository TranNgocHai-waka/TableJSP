package poly.dao;

import poly.model.User;
import poly.utils.JpaUtils;

import javax.persistence.*;
import java.util.List;

public class UserDao {
    public void insert(User user) {
        EntityManager em = JpaUtils.getEntityManager();

        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch(Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        }finally {
            em.close();
        }
    }

    public void update(User user) {
        EntityManager em = JpaUtils.getEntityManager();

        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch(Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        }finally {
            em.close();
        }
    }

    public void delete(String userId) {
        EntityManager em = JpaUtils.getEntityManager();

        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            User user = em.find(User.class, userId);
            if(user != null) {
                em.remove(user);
            } else {
                throw new Exception("User can not found");
            }
            trans.commit();
        } catch(Exception e) {
            e.printStackTrace();
            trans.rollback();
        }finally {
            em.close();
        }
    }

    public User findById(String userId) {
        EntityManager em = JpaUtils.getEntityManager();
        User user = em.find(User.class, userId);
        return user;
    }

    public List<User> findAll() {
        EntityManager em = JpaUtils.getEntityManager();
        Query query = em.createNamedQuery("User.findAll");

        return query.getResultList();
    }

    //phan trang
    public List<User> findAll(int page, int pageSize) {
        EntityManager em = JpaUtils.getEntityManager();
        Query query = em.createNamedQuery("User.findAll");
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public User checkLogin(String userId, String password) {
        EntityManager em = JpaUtils.getEntityManager();
        String jqpl = "select u from User u where u.userId = :userId and u.password= :password";
        TypedQuery<User> query = em.createNamedQuery(jqpl, User.class);
        query.setParameter("userId",userId);//"  " ten tron gtham so truy van sql
        query.setParameter("password",password);
        return  query.getSingleResult();
    }

    public List<User> findByFullName(String fullname) {
        EntityManager em = JpaUtils.getEntityManager();
        String jqpl = "select u from User u where u.fullname like :fullname";
        TypedQuery<User> query = em.createNamedQuery(jqpl, User.class);
        query.setParameter("fullname", fullname);
        return query.getResultList();
    }

    public int count() {
        EntityManager em = JpaUtils.getEntityManager();
        String jqpl = "select count(u) from User u ";
        Query query = em.createNamedQuery(jqpl);
        return ((Long)query.getSingleResult()).intValue(); //epp ve kieu long, roi chuyen sang kieu int
    }
}

