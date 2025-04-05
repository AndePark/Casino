package dev.andepark.minicasino.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.andepark.minicasino.models.Bet;
import dev.andepark.minicasino.models.Game;
import dev.andepark.minicasino.models.Player;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/bets")
public class BetController {
    // private final Map<String, Player> players = new HashMap<>();
    // private final List<Game> games = Arrays.asList(
    //     new Game(1, "Roulette", 1, 2.0, 1.0, 100.0),
    //     new Game(2, "Blackjack", 2, 2.5, 5.0, 200.0)
    // );
    // private final List<Bet> betHistory = new ArrayList<>();


    @PostMapping("/place")
    public Map<String, Object> placeBet(@RequestBody Map<String, Object> betData) {
        String username = (String) betData.get("username");
        double betAmount = (double) betData.get("betAmount");

        // Get the player from the database (for simplicity, using a static list)
        Map<String, Object> player = CasinoDatabase.getPlayerByUsername(username);

        if (player == null) {
            return Map.of("success", false, "message", "Player not found");
        }

        double balance = (double) player.get("balance");
        if (balance < betAmount) {
            return Map.of("success", false, "message", "Insufficient balance");
        }

        
        // Generate a random result (50/50 win/loss)
        boolean isWin = new Random().nextBoolean();
        double newBalance;

        if (isWin) {
            // If win, double the bet
            newBalance = balance + betAmount;
            return Map.of("success", true, "message", "You won! Your balance is now: " + newBalance);
        } else {
            // If lose, subtract the bet
            newBalance = balance - betAmount;
            return Map.of("success", true, "message", "You lost. Your balance is now: " + newBalance);
        }
    }
    

    // @GetMapping("/{username}/summary")
    // public ResponseEntity<Map<String, Object>> getBetSummary(@PathVariable String username) {
    //     List<Bet> userBets = betHistory.stream().filter(b -> b.getUsername().equals(username)).toList();
    //     double totalBet = userBets.stream().mapToDouble(Bet::getBetAmount).sum();
    //     double totalWinnings = userBets.stream().mapToDouble(Bet::getWinnings).sum();

    //     Map<String, Object> summary = new HashMap<>();
    //     summary.put("totalBets", userBets.size());
    //     summary.put("totalBetAmount", totalBet);
    //     summary.put("totalWinnings", totalWinnings);

    //     return ResponseEntity.ok(summary);
    // }
}
