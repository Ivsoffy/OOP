package ru.nsu.ivchenko;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.nsu.ivchenko.controllers.Dealer;
import ru.nsu.ivchenko.controllers.User;
import ru.nsu.ivchenko.models.Blackjack;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.DeckOfCards;
import ru.nsu.ivchenko.models.Suit;

class BlackjackTest {

    private Blackjack blackjack;
    private DeckOfCards deckMock;
    private Dealer dealerMock;
    private User userMock;

    @BeforeEach
    void setUp() {
        // Используем моки для зависимостей
        deckMock = Mockito.mock(DeckOfCards.class);
        dealerMock = Mockito.mock(Dealer.class);
        userMock = Mockito.mock(User.class);

        // Создаем тестовый экземпляр класса Blackjack с моком зависимостей
        blackjack = new Blackjack(deckMock, dealerMock, userMock);

        Card card1 = new Card("10", Suit.Hearts );
        Card card2 = new Card("6", Suit.Spades);

        LinkedList<Card> hand = new LinkedList<>();
        hand.add(card1);
        hand.add(card2);
        when(dealerMock.getOpenHand())
            .thenReturn(hand);
    }

    @Test
    void testStartGame() {
        // Мокаем поведение раздачи карт
        Card card1 = new Card("10", Suit.Hearts );
        Card card2 = new Card("6", Suit.Spades);
        Card card3 = new Card("8", Suit.Diamonds);
        Card card4 = new Card("4", Suit.Clubs);

        when(deckMock.getCard())
            .thenReturn(card1, card2, card3, card4);

        when(userMock.step()).thenReturn(0);

        when(dealerMock.getBalance())
            .thenReturn(18);

        // Старт игры
        blackjack.start();

        // Проверяем, что карты раздаются правильно
        verify(dealerMock, times(2)).setCard(any(Card.class));
        verify(userMock, times(2)).setCard(any(Card.class));
    }




    @Test
    void testSleeping() {
        long startTime = System.currentTimeMillis();
        blackjack.sleeping();
        long elapsedTime = System.currentTimeMillis() - startTime;

        // Проверяем, что задержка составляет около 1500 мс
        assertTrue(elapsedTime >= 1500, "Sleeping delay should be at least 1500 ms.");
    }

    @Test
    void testPrintHands() {
        when(userMock.getHand()).thenReturn(new LinkedList<>(List.of(
            new Card("10", Suit.Hearts),
            new Card("7", Suit.Spades)
        )));
        when(userMock.getBalance()).thenReturn(17);

        when(dealerMock.getOpenHand()).thenReturn(new LinkedList<>(List.of(
            new Card("8", Suit.Diamonds),
            new Card("6", Suit.Clubs)
        )));
        when(dealerMock.getBalance()).thenReturn(14);
        when(dealerMock.getOpen()).thenReturn(1);

        blackjack.printHands();
        // Проверяем, что метод завершился корректно
        assertDoesNotThrow(() -> blackjack.printHands());
    }

    @Test
    void testFullGameCycle() {
        // Мокаем поведение раздачи карт
        when(deckMock.getCard())
            .thenReturn(
                new Card("10", Suit.Hearts), new Card("6", Suit.Spades),  // Карты дилера
                new Card("8", Suit.Diamonds), new Card("4", Suit.Clubs)   // Карты игрока
            );

        when(userMock.step()).thenReturn(1, 0); // Игрок берет одну карту и останавливается
        when(userMock.getBalance()).thenReturn(20); // Финальный баланс игрока
        when(dealerMock.getBalance()).thenReturn(18); // Финальный баланс дилера

        blackjack.start();

        // Проверяем результат игры
        verify(userMock).win();
        verify(dealerMock, never()).win();
    }
}
