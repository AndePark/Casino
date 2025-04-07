package dev.andepark.minicasino;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import dev.andepark.minicasino.controllers.CasinoDatabase;

// import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

@SpringBootTest
 class CasinoDatabaseTest {

    // Unit tests for CasinoDatabase class 
    
    private CasinoDatabase casinoDatabase;

    // Initialize a new CasinoDatabase before each test 
    @BeforeEach
    public void setup() {
        casinoDatabase = new CasinoDatabase();
    }

    //Test to check getGameByName() method fetches correct game when given a valid game name
    @Test
    public void testGetGameByName() {
        // Test a known game
        Map<String, Object> game = casinoDatabase.getGameByName("Blackjack");
        assertNotNull(game);
        assertEquals("Blackjack", game.get("name"));

        // Test invalid game
        Map<String, Object> nonExistentGame = casinoDatabase.getGameByName("NonExistentGame");
        assertNull(nonExistentGame);
    }

    // Test to check getPlayerByUsername() method correctly fetches a player by their username
    @Test
    public void testGetPlayerByUsername() {
        Map<String, Object> player = casinoDatabase.getPlayerByUsername("test");
        assertNotNull(player);
        assertEquals("test", player.get("username"));

        // Test invalid player
        Map<String, Object> nonExistentPlayer = casinoDatabase.getPlayerByUsername("nonExistentPlayer");
        assertNull(nonExistentPlayer);
    }

    // Test to check updatePlayerBalance() method properly updates a player's balance
    @Test
    public void testUpdatePlayerBalance() {
        Map<String, Object> player = casinoDatabase.getPlayerByUsername("test");
        double initialBalance = (Double) player.get("balance");

        // Update the player's balance
        casinoDatabase.updatePlayerBalance("test", initialBalance + 50.0);

        // Fetch updated player data and verify balance
        Map<String, Object> updatedPlayer = casinoDatabase.getPlayerByUsername("test");
        assertNotNull(updatedPlayer);
        assertEquals(initialBalance + 50.0, updatedPlayer.get("balance"));
    }

    // Test to check getPlayers() method returns the correct list of players
    @Test
    public void testGetPlayers() {
        assertEquals(1, casinoDatabase.getPlayers().size());  
    }

    // Test to check getGames() method returns a non-empty list of games
    @Test
    public void testGetGames() {
        assertEquals(16, casinoDatabase.getGames().size());  // 16 static game entries
    }
}
