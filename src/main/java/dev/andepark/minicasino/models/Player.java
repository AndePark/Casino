package dev.andepark.minicasino.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String name;
    private String username;
    private String birthdate;
    private double balance;
}
