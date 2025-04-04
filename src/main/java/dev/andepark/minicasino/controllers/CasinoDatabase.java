package dev.andepark.minicasino.controllers;

import java.util.*;

public class CasinoDatabase {
    public static final List<Map<String, Object>> players = new ArrayList<>();
    public static final List<Map<String, Object>> games = new ArrayList<>();

    static {
        // Sample games
        games.add(Map.of("id", 1, "name", "Blackjack", "chanceOfWinning", 2, "multiplier", 1.0, "minBet", 5, "maxBet", 500));
        games.add(Map.of("id", 2, "name", "Roulette", "chanceOfWinning", 3, "multiplier", 3.0, "minBet", 10, "maxBet", 1000));
    }
}
