package ru.nsu.ivchenko.controllers;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import ru.nsu.ivchenko.models.Card;
import ru.nsu.ivchenko.models.CardType;

public class User {

    private final LinkedList<Card> hand = new LinkedList<>();
    private int bill = 0;

    /**
     * User's step.
     *
     * @return id - the user's decision (whether to take the card)
     */
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

    /**
     * Set card in hand and update it value if it is Ace.
     *
     * @param card - just card
     */
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

    /**
     * Getter for hand.
     *
     * @return hand - all cards in hand
     */
    public LinkedList<Card> getHand() {
        return hand;
    }

    /**
     * Getter for balance.
     *
     * @return res - the amount of the cards
     */
    public int getBalance() {
        int res = 0;
        for (Card c : hand) {
            res += c.getValue();
        }
        return res;
    }

    /**
     * Clear all cards in hand.
     */
    public void clearBalance() {
        hand.clear();
    }

    /**
     * Adds a victoru counter.
     */
    public void win() {
        bill++;
    }

    /**
     * Getter for the bill.
     *
     * @return bill - victory counter
     */
    public int getBill() {
        return bill;
    }
}
