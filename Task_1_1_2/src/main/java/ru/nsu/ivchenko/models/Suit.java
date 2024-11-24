package ru.nsu.ivchenko.models;

import lombok.Getter;

@Getter
public enum Suit {
    Diamonds("Бубны"),
    Hearts("Червы"),
    Clubs("Трефы"),
    Spades("Пики");

    private final String type;

    Suit(String type) {
        this.type = type;
    }

}
