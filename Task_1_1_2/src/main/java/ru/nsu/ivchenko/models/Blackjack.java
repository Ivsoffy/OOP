package ru.nsu.ivchenko.models;

import ru.nsu.ivchenko.controllers.Dealer;
import ru.nsu.ivchenko.controllers.User;

/**
 * Class where the main action of the game is realised.
 */
public class Blackjack {

    private final DeckOfCards deck;
    private final Dealer dealer;
    private final User user;
    private static int round = 0;

    /**
     * Makes a 1.5-second delay.
     */
    public void sleeping() {
        try {
            Thread.sleep(1500); // Задержка на 1.5 секунды
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }
    }

    /**
     * Prints dealer and user hands.
     */
    public void printHands() {
        System.out.println("    Ваши карты: " + user.getHand() + " --> " + user.getBalance());
        if (dealer.getOpen() == 0) {
            System.out.println("    Карты дилера: [" + dealer.getHand() + ", <закрытая карта>]");
        } else {
            System.out.println(
                "    Карты дилера: " + dealer.getOpenHand() + " --> " + dealer.getBalance());
        }

    }

    /**
     * Game realisation.
     */
    public void start() {

        dealer.setCard(deck.getCard());
        dealer.setCard(deck.getCard());
        user.setCard(deck.getCard());
        user.setCard(deck.getCard());

        System.out.println("Дилер раздал карты");
        printHands();

        int res = 1;
        System.out.println("Ваш ход\n"
            + "-------\n");

        while (user.getBalance() < 21 && res != 0) {
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться.");
            res = user.step();

            if (res == 1) {
                Card card = deck.getCard();
                user.setCard(card);
                System.out.println("Вы открыли карту " + card);
                printHands();
            }
        }
        sleeping();

        System.out.println(
            "Ход дилера\nДилер открывает закрытую карту " + dealer.getOpenHand().get(1));
        dealer.setOpen();
        printHands();
        while (dealer.getBalance() <= 17) {
            Card card = deck.getCard();
            dealer.setCard(card);
            System.out.println("Дилер открывает карту " + card);
            printHands();
        }

        sleeping();
        if (user.getBalance() == 21 || (
            (user.getBalance() > dealer.getBalance() || dealer.getBalance() > 21)
                && user.getBalance() < 21)) {
            user.win();
            System.out.print("Вы выиграли раунд! Счет " + user.getBill() + ":" + dealer.getBill());
        } else {
            dealer.win();
            System.out.print("Вы проиграли! Счет " + user.getBill() + ":" + dealer.getBill());

        }
        if (user.getBill() > dealer.getBill()) {
            System.out.println(" в вашу пользу.");
        } else if (user.getBill() < dealer.getBill()) {
            System.out.println(" в пользу диллера.");
        } else {
            System.out.println(".");
        }
    }

    /**
     * Constructor.
     *
     * @param deck   - deck of cards
     * @param dealer - dealer
     * @param user   - user
     */
    public Blackjack(DeckOfCards deck, Dealer dealer, User user) {
        this.deck = deck;
        this.dealer = dealer;
        this.user = user;
    }

    /**
     * Game in cycle.
     */
    public void run() {
        System.out.println("Добро пожаловать в Блэкджек!");
        while (true) {
            System.out.println("---------------------------");
            System.out.println("Раунд " + ++round);
            start();
            user.clearBalance();
            dealer.clearBalance();
            sleeping();
        }
    }
}
