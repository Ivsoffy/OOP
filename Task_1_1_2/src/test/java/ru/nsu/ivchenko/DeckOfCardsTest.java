package ru.nsu.ivchenko;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.DeckOfCards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeckOfCardsTest {

    private DeckOfCards deck;

    @BeforeEach
    void setUp() {
        deck = new DeckOfCards();
    }

    @Test
    void testDeckInitialization() {
        assertEquals(52, deck.getCards().size());
    }

    @Test
    void testGetCardRemovesFromDeck() {
        Card card = deck.getCard();
        assertNotNull(card);
        assertEquals(51, deck.getCards().size());
        assertFalse(deck.getCards().contains(card));
    }

    @Test
    void testUniqueCardsInDeck() {
        Set<Card> dealtCards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            Card card = deck.getCard();
            assertFalse(dealtCards.contains(card));
            dealtCards.add(card);
        }
        assertEquals(52, dealtCards.size());
    }

    @Test
    void testGetCardFromEmptyDeckThrowsException() {
        for (int i = 0; i <= 52; i++) {
            deck.getCard();
        }
        assertEquals(51, deck.getCards().size());
    }


}
