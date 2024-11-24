package ru.nsu.ivchenko.controllers;

import lombok.Data;
import lombok.Getter;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.CardType;
import ru.nsu.ivchenko.models.DeckOfCards;
import ru.nsu.ivchenko.models.Suit;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class User {

    private final LinkedList<Card> hand = new LinkedList<>();
    private int bill = 0;

    public int step() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int id = scanner.nextInt();
                if (id == 0 || id == 1) {
                    return id;
                } else {
                    throw new InputMismatchException("Err");
                }
            } catch (Exception e) {
                System.out.println("Введена неверная инструкция. Попробуйте еще раз.");
            }
        }
    }

    public void setCard(Card card) {
        if (card.getName().equals(CardType.Ace.name())) {
            Card ace;
            if (getBalance() <= 10) {
                ace = new Card(CardType.Ace, card.getSuit(), 11);
            } else {
                ace = new Card(CardType.Ace, card.getSuit(), 1);
            }
            hand.add(ace);
        } else {
            hand.add(card);
        }
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public int getBalance() {
        int res = 0;
        for (Card c : hand) {
            res += c.getValue();
        }
        return res;
    }

    public void clearBalance() {
        hand.clear();
    }


    public void win() {
        bill++;
    }

    public int getBill() {
        return bill;
    }
}
