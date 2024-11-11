package ru.nsu.ivchenko.controllers;

import lombok.Getter;
import ru.nsu.ivchenko.models.Card;

import java.awt.*;
import java.util.LinkedList;

public class Diller {

    private  LinkedList<Card> hand = new LinkedList<>();
    private int bill = 0;
    private int open = 0;

    public void start(){
//        System.out.println("Start...");
//
//        LinkedList<Card> userCards = new LinkedList<>();
//        userCards.add(deck.getCard());
//        userCards.add(deck.getCard());
//
//        hand.add(deck.getCard());
//
//        Card card = deck.getCard();
//        hand.add(card);
//
//        System.out.println("Diller: " + card + " ???");
//
//        return  userCards;
    }

    public void setCard(Card card){
        hand.add(card);
    }

    public Card getHand(){
        return hand.get(0);
    }

    public LinkedList<Card> getOpenHand(){
        return hand;
    }

    public int getOpen(){
        return open;
    }

    public void setOpen(){
        open=1;
    }


    public int getBalance(){
        int res = 0;
        for(Card c : hand){
            res += c.getValue();
        }
        return res;
    }

    public void clearBalance(){
        hand.clear();
        open = 0;
    }

    public void win(){
        bill++;
    }

    public int getBill(){
        return bill;
    }
}
