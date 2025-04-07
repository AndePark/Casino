package dev.andepark.minicasino.controllers;

import java.time.LocalDate;
// import java.time.Period;
import java.util.HashMap;
import java.util.Map;


import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/players")
public class PlayerController {
    public CasinoDatabase db; 

    public PlayerController() {
        db = new CasinoDatabase();
    }


    @PostMapping("/signup")
    public Map<String, Object> signup(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String repeatPassword = body.get("repeatPassword");
        LocalDate birthdate = LocalDate.parse(body.get("birthdate"));

        if (!password.equals(repeatPassword)) return Map.of("success", false, "message", "Passwords do not match");
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$"))
            return Map.of("success", false, "message", "Password too weak");
        if (birthdate.isAfter(LocalDate.now().minusYears(18)))
            return Map.of("success", false, "message", "Must be over 18");


        for (Map<String, Object> player : db.getPlayers()) {
            if (player.get("username").equals(username)) return Map.of("success", false, "message", "Username taken");
        }

        Map<String, Object> newPlayer = new HashMap<>();
        newPlayer.put("username", username);
        newPlayer.put("password", password);
        newPlayer.put("balance", 100.0);
        db.players.add(newPlayer);

        return Map.of("success", true, "message", "Signup successful", "player", newPlayer);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        for (Map<String, Object> player : db.players) {
            if (player.get("username").equals(username) && player.get("password").equals(password)) {
                return Map.of("success", true, "message", "Login successful", "player", player);
            }
        }
        return Map.of("success", false, "message", "Invalid credentials");
    }

    @PostMapping("/place")
    public Map<String, Object> placeBet(@RequestBody Map<String, Object> body) {
        String username = (String) body.get("username");
        double betAmount = ((Number) body.get("betAmount")).doubleValue();
        String gamename = (String) body.get("gamename");

        Map<String, Object> player = db.getPlayerByUsername(username);
        Map<String, Object> game = db.getGameByName(gamename);


        if (player == null) {
            return Map.of("success", false, "message", "Player not found");
        }

        double balance = (Double) player.get("balance");
        if (balance < betAmount) {
            return Map.of("success", false, "message", "Insufficient balance");
        }

        int minBet = (int) game.get("minBet");
        int maxBet = (int) game.get("maxBet");

        if (betAmount < minBet || betAmount > maxBet) {
            return Map.of("success", false, "message", "Place Valid Min/Max Bet");
        }
        
        double chanceWin = (Double) game.get("chanceOfWinning");
        double multiplier = (Double) game.get("multiplier");
        boolean isWin = Math.random() < chanceWin;
        
        if (isWin) {
            // If win, new balance incremented by betAmount * multiplier
            player.put("balance", (Double) player.get("balance") + (betAmount * multiplier));
            return Map.of("success", true, "message", String.format("You Won $%.2f!", (betAmount * multiplier)), "balance", player.get("balance"));
        } else {
            // If lose, new balance decremented by betAmount 
            player.put("balance", (Double) player.get("balance") - betAmount);
            return Map.of("success", true, "message", String.format("You Lost $%.2f!", (betAmount)), "balance", player.get("balance"));
        }
    }
}
