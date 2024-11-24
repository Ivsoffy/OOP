package ru.nsu.ivchenko.controllers;

import ru.nsu.ivchenko.models.Card;

import java.util.LinkedList;

public class Dealer {

    private final LinkedList<Card> hand = new LinkedList<>();
    private int bill = 0;
    private int open = 0;

    public void setCard(Card card) {
        hand.add(card);
    }

    public Card getHand() {
        return hand.get(0);
    }

    public LinkedList<Card> getOpenHand() {
        return hand;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen() {
        open = 1;
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
        open = 0;
    }

    public void win() {
        bill++;
    }

    public int getBill() {
        return bill;
    }
}
