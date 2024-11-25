package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.CardType;
import ru.nsu.ivchenko.models.Suit;

class CardTest {

    @Test
    void testCardCreation() {
        Card card = new Card(CardType.Ace, Suit.Hearts);
        assertEquals("Ace", card.getName());
        assertEquals(11, card.getValue());
        assertEquals(Suit.Hearts, card.getSuit());
    }

    @Test
    void testCardToString() {
        Card card = new Card(CardType.King, Suit.Diamonds);
        assertEquals("Diamonds King (10)", card.toString());
    }
}
