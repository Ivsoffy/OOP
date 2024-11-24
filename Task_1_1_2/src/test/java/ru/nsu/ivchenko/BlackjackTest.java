package ru.nsu.ivchenko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.ivchenko.controllers.Dealer;
import ru.nsu.ivchenko.controllers.User;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.CardType;
import ru.nsu.ivchenko.models.DeckOfCards;
import ru.nsu.ivchenko.models.Suit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BlackjackTest {

    private DeckOfCards deckMock;
    private Dealer dealer;
    private User user;

    @BeforeEach
    void setUp() {
        deckMock = mock(DeckOfCards.class);
        dealer = new Dealer();
        user = new User();
    }

    @Test
    void testGameStart() {
        when(deckMock.getCard())
            .thenReturn(new Card("7", Suit.Hearts))
            .thenReturn(new Card(CardType.King, Suit.Diamonds))
            .thenReturn(new Card("2", Suit.Clubs))
            .thenReturn(new Card("9", Suit.Spades));

        dealer.setCard(deckMock.getCard());
        dealer.setCard(deckMock.getCard());
        user.setCard(deckMock.getCard());
        user.setCard(deckMock.getCard());

        assertEquals(17, dealer.getBalance());
        assertEquals(11, user.getBalance());
    }

    @Test
    void testDealerPlaysCorrectly() {
        when(deckMock.getCard())
            .thenReturn(new Card("7", Suit.Hearts))
            .thenReturn(new Card("4", Suit.Diamonds));

        dealer.setCard(deckMock.getCard());
        dealer.setCard(deckMock.getCard());

        while (dealer.getBalance() <= 17) {
            dealer.setCard(deckMock.getCard());
        }

        assertTrue(dealer.getBalance() > 17);
    }
}
