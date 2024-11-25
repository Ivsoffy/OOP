package ru.nsu.ivchenko.models;

/**
 * Class that contains information about just one card.
 */
public class Card {

    private int value;
    private String name;
    private Suit suit;

    /**
     * Constructor for Jack, Queen, King, Ace.
     *
     * @param name - card name
     * @param suit - card suit
     */
    public Card(CardType name, Suit suit) {
        this.name = name.name();
        this.suit = suit;
        this.value = name.getValue();
    }

    /**
     * Constructor for numbers.
     *
     * @param name - card name
     * @param suit - card suit
     */
    public Card(String name, Suit suit) {
        this.name = name;
        this.suit = suit;
        this.value = Integer.parseInt(name);
    }

    /**
     * Constructor for Ace with different values.
     *
     * @param name  - card name
     * @param suit  - card suit
     * @param value - card value (depends on card type)
     */
    public Card(CardType name, Suit suit, int value) {
        this.name = name.name();
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return suit + " " + name + " (" + value + ")";
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Suit getSuit() {
        return suit;
    }
}
