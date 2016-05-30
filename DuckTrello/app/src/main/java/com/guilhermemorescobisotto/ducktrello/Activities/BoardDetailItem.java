package com.guilhermemorescobisotto.ducktrello.Activities;

import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Card;

/**
 * Created by guilhermemorescobisotto on 5/24/16.
 */
public class BoardDetailItem {

    private Card card;
    private String listName;

    public BoardDetailItem(String listName, Card card) {
        this.listName = listName;
        this.card = card;
    }

    public String getListName(){
        return this.listName;
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
