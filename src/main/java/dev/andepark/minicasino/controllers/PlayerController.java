package dev.andepark.minicasino.controllers;

import java.time.LocalDate;
// import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    // private final Map<String, Player> players = new HashMap<>();
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

        System.out.println(body);

        // Get the player from the database (for simplicity, using a static list)
        Map<String, Object> player = db.getPlayerByUsername(username);
        
        System.out.println(player);

        if (player == null) {
            return Map.of("success", false, "message", "Player not found");
        }

        double balance = (Double) player.get("balance");
        if (balance < betAmount) {
            return Map.of("success", false, "message", "Insufficient balance");
        }

        
        // Generate a random result (50/50 win/loss)
        boolean isWin = new Random().nextBoolean();
        // double newBalance;

        System.out.println("000");
        System.out.println(player.get("balance"));
        System.out.println(player);
        if (isWin) {
            // If win, double the bet
            player.put("balance", (Double) player.get("balance") + betAmount);
            return Map.of("success", true, "message", "You won!", "balance", player.get("balance"));
        } else {
            // If lose, subtract the bet
            if (!player.containsKey("balance")) {
                player.put("balance", 2.00);
                System.out.println("1");
            }
            System.out.println("2");
            player.put("balance", (Double) player.get("balance") - betAmount);
            return Map.of("success", true, "message", "You lost", "balance", player.get("balance"));
        }


    }

    // @GetMapping("/{user}")
    // public Map<String, Object> getUserName(@RequestBody Map<String, String> body) {
    //     String username = body.get("username");
    //     Map<String, Object> player = CasinoDatabase.getPlayerByUsername(username);
    //     return player; 
    // }

    // @PostMapping("/signup")
    // public Map<String, Object> signup(@RequestBody Map<String, String> body) {
    //     boolean success = playerService.registerPlayer(
    //         body.get("username"),
    //         body.get("password"),
    //         LocalDate.parse(body.get("birthdate"))
    //     );
    //     return Map.of("success", success, "message", success ? "Signup successful" : "Signup failed");
    // }

    // @GetMapping("/{username}/balance")
    // public ResponseEntity<Double> getBalance(@PathVariable String username) {
    //     Player player = players.get(username);
    //     if (player == null) {
    //         return ResponseEntity.badRequest().body(0.0);
    //     }
    //     return ResponseEntity.ok(player.getBalance());
    // }

    // @PostMapping("/{username}/deposit") 
    // public ResponseEntity<String> deposit(@PathVariable String username, @RequestParam double amount) {
    //     Player player = players.get(username);
    //     if (player == null) {
    //         return ResponseEntity.badRequest().body("Player not found");
    //     }
    //     player.setBalance(player.getBalance() + amount);
    //     return ResponseEntity.ok("Deposit successful. New balance: " + player.getBalance());
    // }
}
