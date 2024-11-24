package ru.nsu.ivchenko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.ivchenko.controllers.Dealer;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.CardType;
import ru.nsu.ivchenko.models.Suit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void testAddCardAndBalance() {
        dealer.setCard(new Card("2", Suit.Hearts));
        dealer.setCard(new Card(CardType.Queen, Suit.Diamonds));
        assertEquals(12, dealer.getBalance());
    }

    @Test
    void testSetAndGetOpen() {
        assertEquals(0, dealer.getOpen());
        dealer.setOpen();
        assertEquals(1, dealer.getOpen());
    }

    @Test
    void testClearBalance() {
        dealer.setCard(new Card(CardType.Jack, Suit.Spades));
        dealer.clearBalance();
        assertEquals(0, dealer.getBalance());
        assertTrue(dealer.getOpenHand().isEmpty());
    }

    @Test
    void testWin() {
        assertEquals(0, dealer.getBill());
        dealer.win();
        assertEquals(1, dealer.getBill());
    }
}
