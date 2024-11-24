package ru.nsu.ivchenko.models;

import lombok.Data;
import java.util.*;


public class DeckOfCards {

    private final List<Card> cards = new LinkedList<>();

    public DeckOfCards() {
        shuffle();
    }

    public void shuffle(){
        for (Suit suit : Suit.values()) {

            for (CardType cardType : CardType.values()) {
                cards.add(new Card(cardType, suit));
            }

            for (int i = 2; i < 11; i++) {
                cards.add(new Card(String.valueOf(i), suit));
            }
        }
    }

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

    public List<Card> getCards() {
        return cards;
    }
}
