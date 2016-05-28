package com.guilhermemorescobisotto.ducktrello.Activities;

import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Card;

/**
 * Created by guilhermemorescobisotto on 5/24/16.
 */
public class CardItem {

    private Card card;

    public CardItem(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public boolean isClosed() {
        return card.closed;
    }

    public String getName() {
        return card.name;
    }
}
