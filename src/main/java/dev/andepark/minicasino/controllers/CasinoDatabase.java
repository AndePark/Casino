package dev.andepark.minicasino.controllers;

import java.util.*;


public class CasinoDatabase {
    public List<Map<String, Object>> players = new ArrayList<>();
    public List<Map<String, Object>> games = new ArrayList<>();

    // CasinoDatabase constructor
    public CasinoDatabase() {
        Map<String, Object> gameOne = new HashMap<>();

        gameOne.put("id", 0);
        gameOne.put("name", "Blackjack");
        gameOne.put("chanceOfWinning", 0.52);
        gameOne.put("multiplier", 1.0);
        gameOne.put("minBet", 3);
        gameOne.put("maxBet", 10);
        games.add(gameOne);

        
        Map<String, Object> gameTwo = new HashMap<>();
        gameTwo.put("id", 1);
        gameTwo.put("name", "Roulette");
        gameTwo.put("chanceOfWinning", 0.33);
        gameTwo.put("multiplier", 3.0);
        gameTwo.put("minBet", 3);
        gameTwo.put("maxBet", 5);
        games.add(gameTwo);

        Map<String, Object> gameThree = new HashMap<>();
        gameThree.put("id", 2);
        gameThree.put("name", "Poker");
        gameThree.put("chanceOfWinning", 0.03);
        gameThree.put("multiplier", 3.0);
        gameThree.put("minBet", 5);
        gameThree.put("maxBet", 10);
        games.add(gameThree);

        Map<String, Object> gameFour = new HashMap<>();
        gameFour.put("id", 3);
        gameFour.put("name", "Baccarat");
        gameFour.put("chanceOfWinning", 0.52);
        gameFour.put("multiplier", 2.0);
        gameFour.put("minBet", 1);
        gameFour.put("maxBet", 10);
        games.add(gameFour);

        Map<String, Object> gameFive = new HashMap<>();
        gameFive.put("id", 4);
        gameFive.put("name", "Craps");
        gameFive.put("chanceOfWinning", 0.42);
        gameFive.put("multiplier", 5.0);
        gameFive.put("minBet", 3);
        gameFive.put("maxBet", 10);
        games.add(gameFive);

        Map<String, Object> gameSix = new HashMap<>();
        gameSix.put("id", 5);
        gameSix.put("name", "Slots");
        gameSix.put("chanceOfWinning", 0.22);
        gameSix.put("multiplier", 1.0);
        gameSix.put("minBet", 5);
        gameSix.put("maxBet", 10);
        games.add(gameSix);

        Map<String, Object> gameSeven = new HashMap<>();
        gameSeven.put("id", 6);
        gameSeven.put("name", "Plinko");
        gameSeven.put("chanceOfWinning", 0.53);
        gameSeven.put("multiplier", 4.0);
        gameSeven.put("minBet", 1);
        gameSeven.put("maxBet", 10);
        games.add(gameSeven);

        Map<String, Object> gameEight = new HashMap<>();
        gameEight.put("id", 7);
        gameEight.put("name", "Pai Gow");
        gameEight.put("chanceOfWinning", 0.44);
        gameEight.put("multiplier", 3.0);
        gameEight.put("minBet", 1);
        gameEight.put("maxBet", 5);
        games.add(gameEight);

        Map<String, Object> gameNine = new HashMap<>();
        gameNine.put("id", 8);
        gameNine.put("name", "Keno");
        gameNine.put("chanceOfWinning", 0.52);
        gameNine.put("multiplier", 3.0);
        gameNine.put("minBet", 1);
        gameNine.put("maxBet", 10);
        games.add(gameNine);

        Map<String, Object> gameTen = new HashMap<>();
        gameTen.put("id", 9);
        gameTen.put("name", "Sic Bo");
        gameTen.put("chanceOfWinning", 0.33);
        gameTen.put("multiplier", 3.0);
        gameTen.put("minBet", 5);
        gameTen.put("maxBet", 10);
        games.add(gameTen);

        Map<String, Object> gameEleven = new HashMap<>();
        gameEleven.put("id", 10);
        gameEleven.put("name", "Video Poker");
        gameEleven.put("chanceOfWinning", 0.23);
        gameEleven.put("multiplier", 3.0);
        gameEleven.put("minBet", 3);
        gameEleven.put("maxBet", 10);
        games.add(gameEleven);

        Map<String, Object> gameTwelve = new HashMap<>();
        gameTwelve.put("id", 11);
        gameTwelve.put("name", "Three Card Poker");
        gameTwelve.put("chanceOfWinning", 0.12);
        gameTwelve.put("multiplier", 3.0);
        gameTwelve.put("minBet", 1);
        gameTwelve.put("maxBet", 5);
        games.add(gameTwelve);

        Map<String, Object> gameThirteen = new HashMap<>();
        gameThirteen.put("id", 12);
        gameThirteen.put("name", "Wheel of Fortune");
        gameThirteen.put("chanceOfWinning", 0.32);
        gameThirteen.put("multiplier", 3.0);
        gameThirteen.put("minBet", 5);
        gameThirteen.put("maxBet", 10);
        games.add(gameThirteen);

        Map<String, Object> gameFourteen = new HashMap<>();
        gameFourteen.put("id", 13);
        gameFourteen.put("name", "Bingo");
        gameFourteen.put("chanceOfWinning", 0.51);
        gameFourteen.put("multiplier", 3.0);
        gameFourteen.put("minBet", 1);
        gameFourteen.put("maxBet", 5);
        games.add(gameFourteen);

        Map<String, Object> gameFifteen = new HashMap<>();
        gameFifteen.put("id", 14);
        gameFifteen.put("name", "Red Dog");
        gameFifteen.put("chanceOfWinning", 0.59);
        gameFifteen.put("multiplier", 3.0);
        gameFifteen.put("minBet", 3);
        gameFifteen.put("maxBet", 10);
        games.add(gameFifteen);

        Map<String, Object> gameSixteen = new HashMap<>();
        gameSixteen.put("id", 15);
        gameSixteen.put("name", "Rummy");
        gameSixteen.put("chanceOfWinning", 0.67);
        gameSixteen.put("multiplier", 3.0);
        gameSixteen.put("minBet", 1);
        gameSixteen.put("maxBet", 5);
        games.add(gameSixteen);

        Map<String, Object> player = new HashMap<>();
        player.put("name", "test");
        player.put("username", "test");
        player.put("birthdate", "test");
        player.put("balance", 100.00);
        player.put("password", "test");

        players.add(player);
    }

    // get a game by its nae 
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
            // System.out.println(newBalance);
        }
    }

    public List<Map<String, Object>> getPlayers() {
        return players;
    }

    public List<Map<String, Object>> getGames() {
        return games;
    }

}
