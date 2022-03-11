package com.dylan.service;

import com.dylan.model.Game;
import com.dylan.repository.GameRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameService {

    GameRepo gameRepo;
    public GameService(GameRepo gameRepo){

        this.gameRepo = gameRepo;
    }


    public List<Game> findAllGames(){


        return gameRepo.findAll();
    }

    public Game getGameById(int game_id){

        return gameRepo.findById(game_id);
    }

    public List<Game> getGameByGenre(String genre){

        return gameRepo.findAllByGenre(genre);
    }

    public List<Game> getGameByGenreRating(String genre){

        return gameRepo.getGamesByGenreRating(genre);
    }



    public List<Game> getUnownedGames(Integer user_id){

      return   gameRepo.getUnownedGames(user_id);
    }



    public Game addGame(Game game){


        List<Game> gameList = gameRepo.findAll();
        boolean uniqueTitle = true;
        for (Game value : gameList) {

            if (game.getTitle().equals(value.getTitle())) {

                uniqueTitle = false;
                break;

            }
        }

        if(uniqueTitle) {

            return gameRepo.save(game);


        }else{
            // TODO: 3/4/2022 add logging here
            System.out.println("Game Title not unique. Game not added.");
        }

        return null;
    }


    public void update(Game game) {

        gameRepo.save(game);
    }

    public void delete(Game game) {

        gameRepo.delete(game);
    }

//    public List<Game> getOwned(Integer user_id){
//       return gameRepo.getOwnedGames(user_id);
//
//    }


}
