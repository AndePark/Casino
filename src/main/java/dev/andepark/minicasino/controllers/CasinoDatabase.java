package dev.andepark.minicasino.controllers;

import java.util.*;


public class CasinoDatabase {
    public List<Map<String, Object>> players = new ArrayList<>();
    public static final List<Map<String, Object>> games = new ArrayList<>();

    static {
        // Sample games
        games.add(Map.of("id", 1, "name", "Blackjack", "chanceOfWinning", 1.9, "multiplier", 1.0, "minBet", 1, "maxBet", 500));
        games.add(Map.of("id", 2, "name", "Roulette", "chanceOfWinning", 1.1, "multiplier", 3.0, "minBet", 10, "maxBet", 1000));
        games.add(Map.of("id", 3, "name", "Poker", "chanceOfWinning", 1.3, "multiplier", 3.0, "minBet", 20, "maxBet", 500));
        games.add(Map.of("id", 4, "name", "Baccarat", "chanceOfWinning", 0.5, "multiplier", 2.0, "minBet", 1, "maxBet", 20));
        games.add(Map.of("id", 5, "name", "Craps", "chanceOfWinning", 1.2, "multiplier", 5.0, "minBet", 1, "maxBet", 20));
        games.add(Map.of("id", 6, "name", "Slots", "chanceOfWinning",1.2 , "multiplier", 1.0, "minBet", 5, "maxBet", 100));
        games.add(Map.of("id", 7, "name", "Plinko", "chanceOfWinning", 2.0, "multiplier", 4.0, "minBet", 15, "maxBet", 1000));
        games.add(Map.of("id", 8, "name", "Pai Gow", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));

        games.add(Map.of("id", 9, "name", "test9", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 10, "name", "test10", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 11, "name", "test11", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 12, "name", "test12", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 13, "name", "test13", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 14, "name", "test14", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 15, "name", "test15", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 16, "name", "test16", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
    }
    //     players.add(Map.of("name", "test", "username", "test", "birthdate", "test", "balance", 100.00, "password", "test"));
    // }

    public CasinoDatabase() {
        Map<String, Object> player = new HashMap<>();
        games.add(Map.of("id", 1, "name", "Blackjack", "chanceOfWinning", 1.9, "multiplier", 1.0, "minBet", 1, "maxBet", 500));
        games.add(Map.of("id", 2, "name", "Roulette", "chanceOfWinning", 1.1, "multiplier", 3.0, "minBet", 10, "maxBet", 1000));
        games.add(Map.of("id", 3, "name", "Poker", "chanceOfWinning", 1.3, "multiplier", 3.0, "minBet", 20, "maxBet", 500));
        games.add(Map.of("id", 4, "name", "Baccarat", "chanceOfWinning", 0.5, "multiplier", 2.0, "minBet", 1, "maxBet", 20));
        games.add(Map.of("id", 5, "name", "Craps", "chanceOfWinning", 1.2, "multiplier", 5.0, "minBet", 1, "maxBet", 20));
        games.add(Map.of("id", 6, "name", "Slots", "chanceOfWinning",1.2 , "multiplier", 1.0, "minBet", 5, "maxBet", 100));
        games.add(Map.of("id", 7, "name", "Plinko", "chanceOfWinning", 2.0, "multiplier", 4.0, "minBet", 15, "maxBet", 1000));
        games.add(Map.of("id", 8, "name", "Pai Gow", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));

        games.add(Map.of("id", 9, "name", "test9", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 10, "name", "test10", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 11, "name", "test11", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 12, "name", "test12", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 13, "name", "test13", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 14, "name", "test14", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 15, "name", "test15", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));
        games.add(Map.of("id", 16, "name", "test16", "chanceOfWinning", 0.5, "multiplier", 3.0, "minBet", 10, "maxBet", 100));

        player.put("name", "test");
        player.put("username", "test");
        player.put("birthdate", "test");
        player.put("balance", 100.00);
        player.put("password", "test");

        players.add(player);
    }

    public Map<String, Object> getPlayerByUsername(String username) {
        for (Map<String, Object> player : players) {
            if (player.get("username").equals(username)) {
                return player;
            }
        }
        return null; // Player not found
    }

    public void updatePlayerBalance(String username, double newBalance) {
        Map<String, Object> player = getPlayerByUsername(username);
        if (player != null) {
            player.put("balance", newBalance); // Update the player's balance
        }
    }

    public List<Map<String, Object>> getPlayers() {
        return players;
    }

    // public List<Map<String, Object>> setPlayers() {
    //     return players;
    // }

    // public List<Map<String, Object>> getGames() {
    //     return games;
    // }


}
