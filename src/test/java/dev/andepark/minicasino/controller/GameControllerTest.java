package dev.andepark.minicasino.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.andepark.minicasino.controllers.GameController;

import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    // Unit tests for endpoints in GameController class 
    private GameController gameController;

    // Initialize a new GameController before each test 
    @BeforeEach
    public void setup() {
        gameController = new GameController();
    }

    // Test that getAllGames returns a non-empty list of games
    @Test
    public void testGetAllGames() {
        List<Map<String, Object>> games = gameController.getAllGames();
        assertNotNull(games);
        assertEquals(16, games.size()); // 16 static game entries 

        // First game checked to ensure it contains all appropriate values
        Map<String, Object> game = games.get(0);
        assertTrue(game.containsKey("id"), "Each game should have an id");
        assertTrue(game.containsKey("name"), "Each game should have a name");
        assertTrue(game.containsKey("chanceOfWinning"), "Each game should have a 'chanceOfWinning'");
        assertTrue(game.containsKey("multiplier"), "Each game should have a 'multiplier'");
        assertTrue(game.containsKey("minBet"), "Each game should have a minBet");
        assertTrue(game.containsKey("maxBet"), "Each game should have a maxBet");
    }

    // Test that getGameById returns the correct game associated with the id in the path
    @Test
    public void testGetGameById_ValidId() {
        List<Map<String, Object>> games = gameController.getAllGames();

        // Grab the first game from getAllGames
        Map<String, Object> firstGame = games.get(0);
        Map<String, Object> gameById = gameController.getGameById(0);

        assertEquals(firstGame, gameById, "Game returned by ID should match the one in the list");
    }

    // Test that getGameById fails when id is invalid 
    @Test
    public void testGetGameById_InvalidId() {
        int invalidIndex = 100;

        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameController.getGameById(invalidIndex);
        }, "Should throw IndexOutOfBoundsException for invalid game ID");
    }
}
