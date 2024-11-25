package ru.nsu.ivchenko.controllers;

import java.util.LinkedList;
import ru.nsu.ivchenko.models.Card;

/**
 * Class that simulates playing for a dealer.
 */
public class Dealer {

    private final LinkedList<Card> hand = new LinkedList<>();
    private int bill = 0;
    private int open = 0;

    /**
     * Adds card in hand.
     *
     * @param card - just card
     */
    public void setCard(Card card) {
        hand.add(card);
    }

    /**
     * Getter for hand.
     *
     * @return first card in hand (for the game in closed)
     */
    public Card getHand() {
        return hand.get(0);
    }

    /**
     * Getter for hand.
     *
     * @return hand - all dealer's cards
     */
    public LinkedList<Card> getOpenHand() {
        return hand;
    }

    /**
     * Getter for open.
     *
     * @return open
     */
    public int getOpen() {
        return open;
    }

    /**
     * Setter for open - makes it so that dealer plays in open mode.
     */
    public void setOpen() {
        open = 1;
    }

    /**
     * Count Balance.
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
     * Clear dealer hand and makes close mode for dealer's hand.
     */
    public void clearBalance() {
        hand.clear();
        open = 0;
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
