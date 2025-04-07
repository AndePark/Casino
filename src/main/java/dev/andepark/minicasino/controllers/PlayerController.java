package dev.andepark.minicasino.controllers;

import java.time.LocalDate;
import java.util.*;


import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/players")
public class PlayerController {
    public CasinoDatabase db; 

    public PlayerController() {
        db = new CasinoDatabase();
    }


    // signup new player 
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
            if (player.get("username").equals(username)) return Map.of("success", false, "message", "Username Taken");
        }

        Map<String, Object> newPlayer = new HashMap<>();
        newPlayer.put("username", username);
        newPlayer.put("password", password);
        newPlayer.put("balance", 100.0);
        db.players.add(newPlayer);
        return Map.of("success", true, "message", "Signup successful", "player", newPlayer);
    }

    // login player 
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

    // place a bet 
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

        // alert is used if there is insufficient balance to place the bet, otherwise display 
        // the message associated with the unsuccessful placement of bet (ie. min/max bet not selected)
        double balance = (Double) player.get("balance");
        if (balance < betAmount) {
            return Map.of("success", false, "message", "Insufficient balance", "type", false);
        }

        int minBet = (int) game.get("minBet");
        int maxBet = (int) game.get("maxBet");

        if (betAmount < minBet || betAmount > maxBet) {
            return Map.of("success", false, "message", "Place Valid Min/Max Bet", "type", true);
        }
        
        double chanceWin = (Double) game.get("chanceOfWinning");
        double multiplier = (Double) game.get("multiplier");
        boolean isWin = Math.random() < chanceWin;
        
        if (isWin) {
            // If win, new balance incremented by betAmount * multiplier
            db.updatePlayerBalance(username, balance + (betAmount * multiplier));
            return Map.of("success", true, "message", String.format("You Won %.2f€!", (betAmount * multiplier)), "balance", player.get("balance"));
        } else {
            // If lose, new balance decremented by betAmount 
            db.updatePlayerBalance(username, balance - betAmount);
            return Map.of("success", true, "message", String.format("You Lost %.2f€!", (betAmount)), "balance", player.get("balance"));
        }
    }

    // deposit to player's balance
    @PostMapping("/deposit")
    public Map<String, Object> deposit(@RequestBody Map<String, String> body) {
        String username = (String) body.get("username");
        String depositStr = (String) body.get("deposit");

        double deposit = 0.00; 
        Map<String, Object> player = db.getPlayerByUsername(username);
        System.out.println(player);

        // check that deposit a double and not another type like a string 
        try {
            deposit = Double.parseDouble(depositStr);
        } catch (NumberFormatException e) {
            return Map.of("success", false, "message", "Invalid Deposit Amount","balance", player.get("balance"));
        }


        double balance = (Double) player.get("balance");

        if (deposit > 0.00) {
            // add deposit to player balance if deposit > 0 
            db.updatePlayerBalance(username, balance + deposit);
            return Map.of("success", true, "message", "Deposit Successful", "balance", player.get("balance"));
        } else {
            // otherwise return invalid deposit message 
            return Map.of("success", false, "message", "Invalid Deposit Amount","balance", player.get("balance"));
        }
    }

    // get players balance 
    @GetMapping("/balance/{username}") 
    public Map<String, Object> getPlayerBalance(@PathVariable("username") String username) {
        Map<String, Object> player = db.getPlayerByUsername(username);
        return Map.of("success", true, "message", "Balance Retrieved", "balance", player.get("balance"));
    }
}
