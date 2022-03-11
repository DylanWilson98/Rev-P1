package com.dylan.repository;

import com.dylan.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface GameRepo extends JpaRepository<Game, Integer> {
        //get all
    @Query("from Game")
    List<Game> findAll();

    //get by id
    @Query("from Game where game_id = :game_id")
    Game findById(@Param("game_id") int game_id);



    //get by genre
    @Query("from Game where genre = :genre")
    List<Game> findAllByGenre(@Param("genre") String genre);



//get unOwned games
    @Query(value = " select game_id, title, genre, rating from game_store g" +
            " where game_id in" +
            " (select gs.game_id from game_store gs except " +
            "(select uc.game_id from user_collection uc where uc.user_id = ?1)) ", nativeQuery = true)
    public List<Game> getUnownedGames( Integer user_id);

//get games by genre over rating of 7
    @Query(value = "select * from game_store where rating > 7 and  genre = ?", nativeQuery = true)
    public List<Game> getGamesByGenreRating(@Param("genre") String genre );

// TODO: 3/11/2022  fix

//    //get user games
//    @Query(value = "select uc.game_id, gs.title from user_collection as uc" +
//            "inner join " +
//            "game_store gs on gs.game_id = uc.game_id where uc.user_id = ?", nativeQuery = true)
//    public List<Game> getOwnedGames(@Param("uc.user_id") int user_id);








}
