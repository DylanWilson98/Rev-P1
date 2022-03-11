package com.dylan.controller;


import com.dylan.model.Game;
import com.dylan.model.User;
import com.dylan.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {


    private GameService gameService;

    @Autowired
    public GameController(GameService gameService){

        this.gameService = gameService;
    }


    @GetMapping("games")
    public List<Game> getAllGames() {

        return gameService.getAllGames();
    }

    @GetMapping("game/{game_id}")
    public Game getGame(@PathVariable Integer game_id){

        return gameService.getGame(game_id);
    }

    @GetMapping("game/genre/{genre}")
    public  List<Game> getGamesByGenre(@PathVariable String genre){

        return gameService.getGamesByGenre(genre);

    }

    @GetMapping("game/best/{genre}")
    public  List<Game> getGamesByGenreRating(@PathVariable String genre){

        return gameService.getGamesByGenreRating(genre);
    }

    @GetMapping("user/game/unowned/{user_id}")
    public List getUnowned(@PathVariable Integer user_id) {

        return gameService.getUnOwned(user_id);
    }


    @GetMapping("user/game/owned/{user_id}")
    public List getOwned(@PathVariable Integer user_id) {

        return gameService.getOwned(user_id);
    }






    @PostMapping("addGame")
    public Game addGame(@RequestBody Game game){

        return gameService.addGame(game);
    }

    @DeleteMapping("deleteGame")
    public void deleteGame(@RequestBody Game game) {

        gameService.deleteGame(game);
    }

    @PostMapping("updateGame")
    public void updateGame(@RequestBody Game game){

        gameService.updateGame(game);
    }





}
