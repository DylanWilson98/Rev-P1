package com.dylan.repository;

import com.dylan.model.User;
import com.dylan.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;
import java.util.Queue;


@Component
public class UserRepo {






    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            Session session = HibernateUtil.getSession();

            String query = "from User";
            Query<User> q = session.createQuery(query, User.class);

             users = q.getResultList();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;


    }





    public User getByUsername(String username) {
        User user = null;
        try {
            Session session = HibernateUtil.getSession();


            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            Predicate p = cb.equal(root.get("username"), username);

            cq.select(root).where(p);

            user = session.createQuery(cq).getSingleResult();



        } catch (IOException e) {
            e.printStackTrace();
        }


        return user;
    }

    public User getUserById(int user_id) {


        User user = null;
        try {
            Session session = HibernateUtil.getSession();


            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            Predicate p = cb.equal(root.get("user_id"), user_id);

            cq.select(root).where(p);

            user = session.createQuery(cq).getSingleResult();



        } catch (IOException e) {
            e.printStackTrace();
        }


        return user;
    }




    public User add(User user) {

        try {
            Session s = HibernateUtil.getSession();

            Transaction tx = s.beginTransaction();
            s.save(user);
            tx.commit();

        } catch (IOException| HibernateException e) {
            e.printStackTrace();
        }

        return user;
    }

    //return the updated users?????
    public void update(User user){

        try {
            Session s = HibernateUtil.getSession();
            Transaction tx = s.beginTransaction();
            s.update(user);
            tx.commit();




        } catch (IOException| HibernateException e) {
            e.printStackTrace();
        }

    }

    public void deleteUser(User user){

        try {
            Session s = HibernateUtil.getSession();
            Transaction tx = s.beginTransaction();
            s.delete(user);

//            s.createNativeQuery("declare @max int select @max=ISNULL(max([user_id]-1),0)" +
//                    " from users; " +
//                    "DBCC CHECKIDENT ('users', RESEED, @max )").executeUpdate();
//
//




            tx.commit();




        } catch (IOException| HibernateException e) {
            e.printStackTrace();
        }

    }


//        public void addGame(int user_id, int game_id) {
//        User user = getUserById(user_id);
//        GameRepo gameRepo = new GameRepo();
//                user.getUserGames().add(gameRepo.getGameById(game_id));
//            try {
//                Session s = HibernateUtil.getSession();
////                Transaction tx = s.beginTransaction();
////                s.update(user);
////                tx.commit();
//
//
//
//
//
//
//            } catch (IOException| HibernateException e) {
//                e.printStackTrace();
//            }
//
//
//        }


}

//    public List<User> getAllUsers() {
//        List<User> userList = null;
//
//        try {
//            Session session = HibernateUtil.getSession();
//            userList = session.createCriteria(User.class).list();
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return userList;
//    }
