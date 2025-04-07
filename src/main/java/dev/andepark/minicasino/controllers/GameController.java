package dev.andepark.minicasino.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class GameController {

    public CasinoDatabase db; 

    public GameController() {
        db = new CasinoDatabase();
    }

    // get all games 
    @GetMapping("/games")
    public List<Map<String, Object>> getAllGames() {
        return db.getGames();
    }

    // get a game by its id 
    @GetMapping("/games/{id}")
    public Map<String, Object> getGameById(@PathVariable("id") int id) {
        return db.getGames().get(id);
    }
}
