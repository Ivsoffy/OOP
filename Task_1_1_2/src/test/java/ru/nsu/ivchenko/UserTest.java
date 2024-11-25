package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.ivchenko.controllers.User;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.CardType;
import ru.nsu.ivchenko.models.Suit;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testAddCardAndBalance() {
        user.setCard(new Card("5", Suit.Clubs));
        user.setCard(new Card(CardType.King, Suit.Spades));
        assertEquals(15, user.getBalance());
    }

    @Test
    void testAceHandling() {
        user.setCard(new Card(CardType.Ace, Suit.Hearts));
        assertEquals(11, user.getBalance());
        user.setCard(new Card("10", Suit.Clubs));
        assertEquals(21, user.getBalance());
    }

    @Test
    void testClearBalance() {
        user.setCard(new Card("7", Suit.Hearts));
        user.clearBalance();
        assertEquals(0, user.getBalance());
        assertTrue(user.getHand().isEmpty());
    }

    @Test
    void testWin() {
        assertEquals(0, user.getBill());
        user.win();
        assertEquals(1, user.getBill());
    }
}
