package ru.nsu.ivchenko.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

    private final List<Card> cards = new LinkedList<>();

    /**
     * Constructor for DeckOfCards
     */
    public DeckOfCards() {
        shuffle();
    }

    /**
     * Make deck of cards (52 cards)
     */
    public void shuffle() {
        for (Suit suit : Suit.values()) {

            for (CardType cardType : CardType.values()) {
                cards.add(new Card(cardType, suit));
            }

            for (int i = 2; i < 11; i++) {
                cards.add(new Card(String.valueOf(i), suit));
            }
        }
    }

    /**
     * Function to get random card and remove it from deck
     *
     * @return new_card - random card
     */
    public Card getCard() {
        if (cards.isEmpty()) {
            shuffle();
        }
        Random random = new Random();
        int randomNumber = random.nextInt(cards.size());
        Card new_card = cards.get(randomNumber);
        cards.remove(randomNumber);
        return new_card;
    }

    /**
     * Getter for cards
     *
     * @return cards - all cards in deck
     */
    public List<Card> getCards() {
        return cards;
    }
}
