package com.guilhermemorescobisotto.ducktrello.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilhermemorescobisotto on 4/29/16.
 */
public class TrelloList extends AppModel {

    //region Attributes
    @SerializedName("name")
    public String name;

    @SerializedName("closed")
    public boolean closed;

    @SerializedName("idBoard")
    public String idBoard;

    @SerializedName("subscribed")
    public boolean subscribed;

    private List<Card> cards;
    //endregion

    public void setCards(List<Card> cards) {
        if (this.cards == null) {
            this.cards = new ArrayList<>();
        } else {
            this.cards.clear();
        }
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        if (this.cards == null) {
            this.cards = new ArrayList<>();
        }
        return cards;
    }
}
