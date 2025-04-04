package dev.andepark.minicasino.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.andepark.minicasino.models.Bet;
import dev.andepark.minicasino.models.Game;
import dev.andepark.minicasino.models.Player;

@RestController
@RequestMapping("/api/bets")
public class BetController {
    private final Map<String, Player> players = new HashMap<>();
    private final List<Game> games = Arrays.asList(
        new Game(1, "Roulette", 1, 2.0, 1.0, 100.0),
        new Game(2, "Blackjack", 2, 2.5, 5.0, 200.0)
    );
    private final List<Bet> betHistory = new ArrayList<>();


    @PostMapping("/place")
    public ResponseEntity<String> placeBet(@RequestBody Bet bet) {
        Player player = players.get(bet.getUsername());
        if (player == null) {
            return ResponseEntity.badRequest().body("Player not found");
        }

        Game game = games.stream().filter(g -> g.getId() == bet.getGameId()).findFirst().orElse(null);
        if (game == null) {
            return ResponseEntity.badRequest().body("Invalid game ID");
        }

        if (bet.getBetAmount() < game.getMinBet() || bet.getBetAmount() > game.getMaxBet()) {
            return ResponseEntity.badRequest().body("Bet amount out of range");
        }

        if (player.getBalance() < bet.getBetAmount()) {
            return ResponseEntity.badRequest().body("Insufficent balance");
        }

        boolean win = Math.random() < game.getChanceOfWinning();
        double winnings = win ? bet.getBetAmount() * game.getMultiplier() : 0;
        player.setBalance(player.getBalance() - bet.getBetAmount() + winnings);
        bet.setWin(win);
        bet.setWinnings(winnings);
        betHistory.add(bet);

        return ResponseEntity.ok(win ? "You won! Winnings: " + winnings : "You lost! Try again");
    }

    @GetMapping("/{username}/summary")
    public ResponseEntity<Map<String, Object>> getBetSummary(@PathVariable String username) {
        List<Bet> userBets = betHistory.stream().filter(b -> b.getUsername().equals(username)).toList();
        double totalBet = userBets.stream().mapToDouble(Bet::getBetAmount).sum();
        double totalWinnings = userBets.stream().mapToDouble(Bet::getWinnings).sum();

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalBets", userBets.size());
        summary.put("totalBetAmount", totalBet);
        summary.put("totalWinnings", totalWinnings);

        return ResponseEntity.ok(summary);
    }
}
