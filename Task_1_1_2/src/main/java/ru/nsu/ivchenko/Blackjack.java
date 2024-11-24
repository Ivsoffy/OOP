package ru.nsu.ivchenko;

import ru.nsu.ivchenko.controllers.Dealer;
import ru.nsu.ivchenko.controllers.User;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.DeckOfCards;


public class Blackjack {

    private static final DeckOfCards deck = new DeckOfCards();
    private static final Dealer DEALER = new Dealer();
    private static final User user = new User();
    private static int round = 0;

    public static void sleeping() {
        try {
            Thread.sleep(1500); // Задержка на 1.5 секунды
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }
    }

    public static void printHands() {
        System.out.println("    Ваши карты: " + user.getHand() + " --> " + user.getBalance());
        if (DEALER.getOpen() == 0) {
            System.out.println("    Карты дилера: [" + DEALER.getHand() + ", <закрытая карта>]");
        } else {
            System.out.println(
                "    Карты дилера: " + DEALER.getOpenHand() + " --> " + DEALER.getBalance());
        }

    }

    public static void start() {

        DEALER.setCard(deck.getCard());
        DEALER.setCard(deck.getCard());
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
                System.out.println("Вы открыли карту " + card.toString());
                printHands();
            }
        }
        sleeping();

        System.out.println(
            "Ход дилера\nДилер открывает закрытую карту " + DEALER.getOpenHand().get(1));
        DEALER.setOpen();
        printHands();
        while (DEALER.getBalance() <= 17) {
            Card card = deck.getCard();
            DEALER.setCard(card);
            System.out.println("Дилер открывает карту " + card);
            printHands();
        }

        sleeping();
        if (user.getBalance() == 21 || (
            (user.getBalance() > DEALER.getBalance() || DEALER.getBalance() > 21)
                && user.getBalance() < 21)) {
            user.win();
            System.out.print("Вы выиграли раунд! Счет " + user.getBill() + ":" + DEALER.getBill());
        } else {
            DEALER.win();
            System.out.print("Вы проиграли! Счет " + user.getBill() + ":" + DEALER.getBill());

        }
        if (user.getBill() > DEALER.getBill()) {
            System.out.println(" в вашу пользу.");
        } else if (user.getBill() < DEALER.getBill()) {
            System.out.println(" в пользу диллера.");
        } else {
            System.out.println(".");
        }
    }

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Блэкджек!");
        while (true) {
            System.out.println("---------------------------");
            System.out.println("Раунд " + ++round);
            start();
            user.clearBalance();
            DEALER.clearBalance();
            sleeping();
        }
    }
}
