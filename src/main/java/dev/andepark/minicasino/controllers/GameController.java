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
    // private final List<Game> games = Arrays.asList(
    //     new Game(1, "Roulette", 1, 2.0, 1.0, 100.0),
    //     new Game(2, "Blackjack", 2, 2.5, 5.0, 200.0)
    // );

    @GetMapping("/games")
    public List<Map<String, Object>> getAllGames() {
        return CasinoDatabase.games;
    }

    @GetMapping("/games/{id}")
    public Map<String, Object> getGameById(@PathVariable("id") int id) {
        return CasinoDatabase.games.get(id);
    }

}
