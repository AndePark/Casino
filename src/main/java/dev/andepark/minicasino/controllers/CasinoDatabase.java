package dev.andepark.minicasino.controllers;

import java.util.*;


public class CasinoDatabase {
    public List<Map<String, Object>> players = new ArrayList<>();
    public static final List<Map<String, Object>> games = new ArrayList<>();

    // CasinoDatabase constructor
    public CasinoDatabase() {
        Map<String, Object> player = new HashMap<>();
        games.add(Map.of("id", 0, "name", "Blackjack", "chanceOfWinning", 0.12, "multiplier", 1.0, "minBet", 3, "maxBet", 10));
        games.add(Map.of("id", 1, "name", "Roulette", "chanceOfWinning", 0.33, "multiplier", 3.0, "minBet", 3, "maxBet", 5));
        games.add(Map.of("id", 2, "name", "Poker", "chanceOfWinning", 0.23, "multiplier", 3.0, "minBet", 5, "maxBet", 10));
        games.add(Map.of("id", 3, "name", "Baccarat", "chanceOfWinning", 0.52, "multiplier", 2.0, "minBet", 1, "maxBet", 10));
        games.add(Map.of("id", 4, "name", "Craps", "chanceOfWinning", 0.42, "multiplier", 5.0, "minBet", 3, "maxBet", 10));
        games.add(Map.of("id", 5, "name", "Slots", "chanceOfWinning",0.22 , "multiplier", 1.0, "minBet", 5, "maxBet", 10));
        games.add(Map.of("id", 6, "name", "Plinko", "chanceOfWinning", 0.53, "multiplier", 4.0, "minBet", 1, "maxBet", 10));
        games.add(Map.of("id", 7, "name", "Pai Gow", "chanceOfWinning", 0.44, "multiplier", 3.0, "minBet", 1, "maxBet", 5));

        games.add(Map.of("id", 8, "name", "test9", "chanceOfWinning", 0.52, "multiplier", 3.0, "minBet", 1, "maxBet", 10));
        games.add(Map.of("id", 9, "name", "test10", "chanceOfWinning", 0.33, "multiplier", 3.0, "minBet", 5, "maxBet", 10));
        games.add(Map.of("id", 10, "name", "test11", "chanceOfWinning", 0.23, "multiplier", 3.0, "minBet", 3, "maxBet", 10));
        games.add(Map.of("id", 11, "name", "test12", "chanceOfWinning", 0.12, "multiplier", 3.0, "minBet", 1, "maxBet", 5));
        games.add(Map.of("id", 12, "name", "test13", "chanceOfWinning", 0.32, "multiplier", 3.0, "minBet", 5, "maxBet", 10));
        games.add(Map.of("id", 13, "name", "test14", "chanceOfWinning", 0.51, "multiplier", 3.0, "minBet", 1, "maxBet", 5));
        games.add(Map.of("id", 14, "name", "test15", "chanceOfWinning", 0.59, "multiplier", 3.0, "minBet", 3, "maxBet", 10));
        games.add(Map.of("id", 15, "name", "test16", "chanceOfWinning", 0.67, "multiplier", 3.0, "minBet", 1, "maxBet", 5));

        player.put("name", "test");
        player.put("username", "test");
        player.put("birthdate", "test");
        player.put("balance", 100.00);
        player.put("password", "test");

        players.add(player);
    }

    public Map<String, Object> getGameByName(String gamename) {
        for (Map<String, Object> game: games) {
            if (game.get("name").equals(gamename)) {
                return game;
            }
        }
        return null;
    }

    // returns a Map of player data or null if player not found
    public Map<String, Object> getPlayerByUsername(String username) {
        for (Map<String, Object> player : players) {
            if (player.get("username").equals(username)) {
                return player;
            }
        }
        return null; 
    }

    // updates player balance 
    public void updatePlayerBalance(String username, double newBalance) {
        Map<String, Object> player = getPlayerByUsername(username);
        if (player != null) {
            player.put("balance", newBalance); 
        }
    }

    public List<Map<String, Object>> getPlayers() {
        return players;
    }

    public List<Map<String, Object>> getGames() {
        return games;
    }
}
