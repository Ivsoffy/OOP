package ru.nsu.ivchenko.models;

public enum Suit {
    Diamonds("Бубны"),
    Hearts("Червы"),
    Clubs("Трефы"),
    Spades("Пики");

    private final String type;

    Suit(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}