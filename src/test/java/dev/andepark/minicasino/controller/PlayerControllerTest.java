package dev.andepark.minicasino.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.andepark.minicasino.controllers.PlayerController;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {

    // Unit tests for endpoints in PlayerController class 

    private PlayerController playerController;

     // Initialize a new PlayerController before each test
    @BeforeEach
    void setUp() {
        playerController = new PlayerController();
    }

    // Test successful signup with valid inputs 
    @Test
    void testSignupSuccess() {
        Map<String, String> request = new HashMap<>();
        request.put("username", "newuser");
        request.put("password", "pass1!");
        request.put("repeatPassword", "pass1!");
        request.put("birthdate", LocalDate.now().minusYears(20).toString());

        Map<String, Object> response = playerController.signup(request);

        assertTrue((Boolean) response.get("success"));
        assertEquals("Signup successful", response.get("message"));
        assertNotNull(response.get("player"));
    }

    // Test signup fails when password != repeatPassword 
    @Test
    void testSignupPasswordMismatch() {
        Map<String, String> request = Map.of(
                "username", "user1",
                "password", "pass1!",
                "repeatPassword", "pass2!",
                "birthdate", LocalDate.now().minusYears(20).toString()
        );

        Map<String, Object> response = playerController.signup(request);

        assertFalse((Boolean) response.get("success"));
        assertEquals("Passwords do not match", response.get("message"));
    }

    // Test signup fails when password does not meet strength requirements
    @Test
    void testSignupWeakPassword() {
        Map<String, String> request = Map.of(
                "username", "user1",
                "password", "abc",
                "repeatPassword", "abc",
                "birthdate", LocalDate.now().minusYears(20).toString()
        );

        Map<String, Object> response = playerController.signup(request);

        assertFalse((Boolean) response.get("success"));
        assertEquals("Password too weak", response.get("message"));
    }

    // Test signup fails if user is under 18 years of age 
    @Test
    void testSignupUnderage() {
        Map<String, String> request = Map.of(
                "username", "user1",
                "password", "Pass1!",
                "repeatPassword", "Pass1!",
                "birthdate", LocalDate.now().minusYears(16).toString()
        );

        Map<String, Object> response = playerController.signup(request);

        assertFalse((Boolean) response.get("success"));
        assertEquals("Must be over 18", response.get("message"));
    }


    // Test login succeeds after a successful signup
    @Test
    void testLoginSuccess() {
        testSignupSuccess(); // First sign up user
        Map<String, String> request = Map.of(
                "username", "newuser",
                "password", "pass1!"
        );

        Map<String, Object> response = playerController.login(request);

        assertTrue((Boolean) response.get("success"));
        assertEquals("Login successful", response.get("message"));
    }

    // Test login fails for non-existent user
    @Test
    void testLoginFail() {
        Map<String, String> request = Map.of(
                "username", "notExist",
                "password", "pass1!"
        );

        Map<String, Object> response = playerController.login(request);

        assertFalse((Boolean) response.get("success"));
        assertEquals("Invalid credentials", response.get("message"));
    }

    // Test placing a valid bet results in win or loss, balance updated accordingly     
    @Test
    void testPlaceBetSuccess() {
        testSignupSuccess();
        Map<String, Object> body = new HashMap<>();
        body.put("username", "newuser");
        body.put("betAmount", 5.0);
        body.put("gamename", "Slots");

        Map<String, Object> result = playerController.placeBet(body);

        assertTrue(result.containsKey("success"));
        assertTrue(result.containsKey("message"));
        assertTrue(result.containsKey("balance"));
    }

    // Test placing a bet with insufficient balance 
    @Test
    void testPlaceBetInsufficientBalance() {
        testSignupSuccess();
        Map<String, Object> body = Map.of(
                "username", "newuser",
                "betAmount", 9999.0,
                "gamename", "Slots"
        );

        Map<String, Object> result = playerController.placeBet(body);
        assertFalse((Boolean) result.get("success"));
        assertEquals("Insufficient balance", result.get("message"));
    }

    // Test placing a bet that does not meet min/max bet requirement 
    @Test
    void testPlaceBetInvalidRange() {
        testSignupSuccess();
        Map<String, Object> body = Map.of(
                "username", "newuser",
                "betAmount", 0.5,
                "gamename", "Slots"
        );

        Map<String, Object> result = playerController.placeBet(body);
        assertFalse((Boolean) result.get("success"));
        assertEquals("Place Valid Min/Max Bet", result.get("message"));
    }

    // Test successful deposit increases the player's balance
    @Test
    void testDepositSuccess() {
        testSignupSuccess();
        Map<String, String> body = Map.of(
                "username", "newuser",
                "deposit", "50"
        );

        Map<String, Object> result = playerController.deposit(body);
        assertTrue((Boolean) result.get("success"));
        assertEquals("Deposit Successful", result.get("message"));
    }

    // Test deposit fails if the input is not a valid number
    @Test
    void testDepositFailInvalidAmount() {
        testSignupSuccess();
        Map<String, String> body = Map.of(
                "username", "newuser",
                "deposit", "abc"
        );

        Map<String, Object> result = playerController.deposit(body);
        assertFalse((Boolean) result.get("success"));
        assertEquals("Invalid Deposit Amount", result.get("message"));
    }

    // Test deposit fails if the value is negative or zero
    @Test
    void testDepositFailZeroOrNegative() {
        testSignupSuccess();
        Map<String, String> body = Map.of(
                "username", "newuser",
                "deposit", "-5.00"
        );

        Map<String, Object> result = playerController.deposit(body);
        assertFalse((Boolean) result.get("success"));
        assertEquals("Invalid Deposit Amount", result.get("message"));
    }

    // Test fetching a player's balance, returns the correct data
    @Test
    void testGetBalance() {
        testSignupSuccess();

        Map<String, Object> result = playerController.getPlayerBalance("newuser");
        assertTrue((Boolean) result.get("success"));
        assertEquals("Balance Retrieved", result.get("message"));
        assertNotNull(result.get("balance"));
    }
}
