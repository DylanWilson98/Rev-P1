package com.dylan.repository;

import com.dylan.model.Game;
import com.dylan.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.ietf.jgss.GSSManager;
import org.springframework.stereotype.Component;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class GameRepo {


    


    public List<Game> getAll() {
        List<Game> gameList = null;

        try {
            Session session = HibernateUtil.getSession();

            String query = "from Game";
            Query<Game> q = session.createQuery(query, Game.class);

            gameList = q.getResultList();

        }catch (IOException e){e.printStackTrace();}


        return gameList;
    }








//    public Game getGame(Game g) {
//
//        Session session = null;
//        try {
//            session = HibernateUtil.getSession();
//            return session.get(Game.class, g.getGame_id());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public Game getGameById(int game_id){
        Game game = null;
        try {
            Session session = HibernateUtil.getSession();


            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Game> cq = cb.createQuery(Game.class);
            Root<Game> root = cq.from(Game.class);
            Predicate p = cb.equal(root.get("game_id"), game_id);

            cq.select(root).where(p);

            game = session.createQuery(cq).getSingleResult();



        } catch (IOException e) {
            e.printStackTrace();
        }


        return game;

    }




    public void updateGame(Game game) {

        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.update(game);
            transaction.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public Game add(Game game){

        try {
            Session s = HibernateUtil.getSession();

            Transaction tx = s.beginTransaction();
            s.save(game);
            tx.commit();

        } catch (IOException| HibernateException e) {
            e.printStackTrace();
        }

        return game;
    }


    // TODO: 3/4/2022 add logic here or in service to reset autoincrment so that update rating logic works 
    public void deleteGame(Game game){

        try {
            Session s = HibernateUtil.getSession();
            Transaction tx = s.beginTransaction();
            s.delete(game);

            tx.commit();

        } catch (IOException| HibernateException e) {
            e.printStackTrace();
        }

    }

    public List<Game> getUnownedGames(Integer user_id){
            List<Game> query = null;
        try {
            Session session = HibernateUtil.getSession();

                //returns games not owned by player of given id
            Query<Game> q = session.createNativeQuery(
                    "select g.game_id, title, rating from game_store g " +
                    "left join" +
                    " user_collection c on g.game_id = c.game_id" +
                            " where user_id  is null or user_id !=" + user_id );

                query = q.getResultList();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }




    public List<Game> getOwnedGames(Integer user_id){
        List<Game> query = null;
        try {
            Session session = HibernateUtil.getSession();


           // returns games owned by given user
            Query<Game> q = session.createNativeQuery("select uc.game_id, gs.title from user_collection as uc" +
                    " inner join" +
                    " game_store gs on uc.game_id = gs.game_id where uc.user_id = " + user_id);

            query = q.getResultList();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }


    public List<Game> getGamesByGenre(String genre){

        List<Game> query = null;

        try {
            Session session = HibernateUtil.getSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Game> cq = cb.createQuery(Game.class);
            Root<Game> root = cq.from(Game.class);
            Predicate p = cb.equal(root.get("genre"), genre);

            cq.select(root).where(p);

            query = session.createQuery(cq).getResultList();



        }catch (IOException e) {
            e.printStackTrace();
        }


        return query;
    }


    public List<Game> getGamesByGenreRating(String genre){

        List<Game> query = null;

        try {
            Session session = HibernateUtil.getSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Game> cq = cb.createQuery(Game.class);
            Root<Game> root = cq.from(Game.class);
            Predicate p = cb.equal(root.get("genre"), genre);

            cq.multiselect(root.get("game_id"), root.get("genre"), root.get("title"), root.get("rating")).
                    where(cb.greaterThan(root.get("rating"), 0)).having(p);




            query = session.createQuery(cq).getResultList();







        }catch (IOException e) {
            e.printStackTrace();
        }


        return query;
    }


}




//returns game_id of games not owned by a given user
//            Query<Game> q2 = session.createNativeQuery("select game_id from game_store gs " +
//                    "except" +
//                    "(select uc.game_id from user_collection uc where uc.user_id = " + user_id + ")");
//
//                query = q2.getResultList();


        //resets id to number after last id in table. Doesn't work since deleting an id in middle would cause problems.
   // s.createNativeQuery("declare @max int select @max=ISNULL(max([game_id]-1),0)" +
//                     " from game_store; " +
//                     "DBCC CHECKIDENT ('game_store', RESEED, @max )").executeUpdate();




//    public List<Game> getAll() {
//
//        try {
//            Session session = HibernateUtil.getSession();
//            List<Game> gameList;
//            gameList = session.createCriteria(Game.class).list();
//
//            return gameList;
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//
//    }
