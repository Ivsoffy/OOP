package ru.nsu.ivchenko.models;

/**
 * Class that contains different card types (numbers, Jack, Queen, King, Ace).
 */
public enum CardType {
    Jack("Валет", 10),
    Queen("Дама", 10),
    Ace("Туз", 11),
    King("Король", 10);


    private final String type;
    private final int value;

    /**
     * Constructor for CardType.
     *
     * @param type  - Jack, Queen, King, Ace
     * @param value - 10 or 11 (card value)
     */
    CardType(String type, int value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Getter for value.
     *
     * @return value - card value
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter for type.
     *
     * @return type - card type
     */
    public String getType() {
        return type;
    }
}
