package dev.andepark.minicasino.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Game {
    private int id;
    private String name;
    private double chanceOfWinning;
    private double multiplier;
    private double maxBet;
    private double minBet;
}
