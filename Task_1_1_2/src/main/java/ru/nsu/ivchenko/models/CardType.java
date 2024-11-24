package ru.nsu.ivchenko.models;

import lombok.Getter;

public enum CardType {
    Jack("Валет", 10),
    Queen("Дама", 10),
    Ace("Туз", 11),
    King("Король", 10);


    private final String type;
    private final int value;

    CardType(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
