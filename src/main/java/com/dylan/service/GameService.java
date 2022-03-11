package com.dylan.service;


import com.dylan.model.Game;
import com.dylan.model.User;
import com.dylan.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {


    @Autowired
    GameRepo gameRepo;

    public GameService(GameRepo gameRepo) {

        this.gameRepo = gameRepo;
    }

   // User user = new User();


    public List<Game> getAllGames(){

        return gameRepo.getAll();  }


    public List<Game> getGamesByGenre(String genre){

        return gameRepo.getGamesByGenre(genre);}


    public List<Game> getGamesByGenreRating(String genre){

        return gameRepo.getGamesByGenreRating(genre);

    }





    public Game getGame(Integer game_id) {  return gameRepo.getGameById(game_id);}




    public Game addGame(Game game){

        List<Game> gameList = gameRepo.getAll();
        boolean uniqueTitle = true;
        for (Game value : gameList) {

            if (game.getTitle().equals(value.getTitle())) {

                uniqueTitle = false;
                break;

            }
        }

        if(uniqueTitle) {

            return gameRepo.add(game);


        }else{
            // TODO: 3/4/2022 add logging here
            System.out.println("Game Title not unique. Game not added.");
        }

        return null;
    }

public void deleteGame(Game game){
        gameRepo.deleteGame( game);
}


public void updateGame(Game game) {
//       int newRating = game.getRating();
//        List<Game> gameList = gameRepo.getAll();
//        int oldRating = gameList.get(game.getGame_id()-1).getRating();
//
//        newRating = (newRating+oldRating)/2;
//        game.setRating(newRating);

        gameRepo.updateGame(game);

}



public List getUnOwned(Integer user_id) {

        return gameRepo.getUnownedGames(user_id);


}


    public List getOwned(Integer user_id) {

        return gameRepo.getOwnedGames(user_id);


    }



}
