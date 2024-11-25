package ru.nsu.ivchenko.models;


public class Card {

    private int value;
    private String name;
    private Suit suit;

    /**
     * Constructor for Jack, Queen, King, Ace.
     *
     * @param name
     * @param suit
     */
    public Card(CardType name, Suit suit) {
        this.name = name.name();
        this.suit = suit;
        this.value = name.getValue();
    }

    /**
     * Constructor for numbers.
     *
     * @param name
     * @param suit
     */
    public Card(String name, Suit suit) {
        this.name = name;
        this.suit = suit;
        this.value = Integer.parseInt(name);
    }

    /**
     * Constructor for Ace with different values.
     *
     * @param name
     * @param suit
     * @param value
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
