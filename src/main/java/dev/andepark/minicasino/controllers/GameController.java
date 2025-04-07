package dev.andepark.minicasino.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import dev.andepark.minicasino.models.Game;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class GameController {

    public CasinoDatabase db; 

    public GameController() {
        db = new CasinoDatabase();
    }

    @GetMapping("/games")
    public List<Map<String, Object>> getAllGames() {
        return db.getGames();
    }

    @GetMapping("/games/{id}")
    public Map<String, Object> getGameById(@PathVariable("id") int id) {
        return db.getGames().get(id);
    }
}
