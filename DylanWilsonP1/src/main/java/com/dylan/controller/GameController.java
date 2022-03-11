package com.dylan.controller;

import com.dylan.model.Game;
import com.dylan.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

  GameService gameService;

  @Autowired
    public GameController(GameService gameService){

      this.gameService = gameService;

  }

  @GetMapping("games")
    public List<Game> getGames(){

      return gameService.findAllGames();
  }

  @GetMapping("game/{game_id}")
  public Game getGameById(@PathVariable int game_id){

    return gameService.getGameById(game_id);
  }


@GetMapping("game/genre/{genre}")
public List<Game> getGameByGenre(@PathVariable String genre){

    return gameService.getGameByGenre(genre);
}

  @GetMapping("game/best/{genre}")
  public List<Game> getGamesByGenreRating(@PathVariable String genre){

    return gameService.getGameByGenreRating(genre);
  }

  @GetMapping("usertest/game/unowned/{user_id}")
  public List<Game> getUnowned(@PathVariable Integer user_id){

    return gameService.getUnownedGames(user_id);
  }

//  @GetMapping("user/game/owned/{user_id}")
//  public List getOwned(@PathVariable Integer user_id) {
//    return gameService.getOwned(user_id);


//}

  @PostMapping("addGame")
  public Game addGame(@RequestBody Game game){

    return gameService.addGame(game);
  }

  @PostMapping("updateGame")
  public void updateGame(@RequestBody Game game){
    gameService.update(game);

  }

  @DeleteMapping("deleteGame")

  public void deleteGame(@RequestBody Game game) {

    gameService.delete(game);
  }







}
