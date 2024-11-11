package ru.nsu.ivchenko;

import java.util.InputMismatchException;
import java.util.Scanner;
import ru.nsu.ivchenko.controllers.Diller;
import ru.nsu.ivchenko.controllers.User;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.DeckOfCards;

import java.util.LinkedList;


public class Blackjack {

  private static final DeckOfCards deck = new DeckOfCards();
  private static final Diller diller = new Diller();
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
    if (diller.getOpen() == 0) {
      System.out.println("    Карты дилера: [" + diller.getHand() + ", <закрытая карта>]");
    } else {
      System.out.println(
          "    Карты дилера: " + diller.getOpenHand() + " --> " + diller.getBalance());
    }

  }

  public static void start() {

    diller.setCard(deck.getCard());
    diller.setCard(deck.getCard());
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

    System.out.println("Ход дилера\nДилер открывает закрытую карту " + diller.getOpenHand().get(1));
    diller.setOpen();
    printHands();
    while (diller.getBalance() <= 17) {
      Card card = deck.getCard();
      diller.setCard(card);
      System.out.println("Дилер открывает карту " + card);
      printHands();
    }

    sleeping();
    if (user.getBalance() == 21 || (
        (user.getBalance() > diller.getBalance() || diller.getBalance() > 21)
            && user.getBalance() < 21)) {
      user.win();
      System.out.print("Вы выиграли раунд! Счет " + user.getBill() + ":" + diller.getBill());
    } else {
      diller.win();
      System.out.print("Вы проиграли! Счет " + user.getBill() + ":" + diller.getBill());

    }
    if (user.getBill() > diller.getBill()) {
      System.out.println(" в вашу пользу.");
    } else if (user.getBill() < diller.getBill()) {
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
      diller.clearBalance();
      sleeping();
    }
  }
}
