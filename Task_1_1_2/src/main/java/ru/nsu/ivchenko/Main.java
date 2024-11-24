package ru.nsu.ivchenko;

import ru.nsu.ivchenko.controllers.Dealer;
import ru.nsu.ivchenko.controllers.User;
import ru.nsu.ivchenko.models.Blackjack;
import ru.nsu.ivchenko.models.DeckOfCards;

public class Main {

    public static void main(String[] args) {

        DeckOfCards deck = new DeckOfCards();
        Dealer dealer = new Dealer();
        User user = new User();

        Blackjack blackjack = new Blackjack(deck, dealer, user);
        blackjack.run();
    }
}
