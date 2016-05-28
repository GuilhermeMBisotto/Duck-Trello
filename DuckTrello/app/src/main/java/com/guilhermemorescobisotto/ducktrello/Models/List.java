package com.guilhermemorescobisotto.ducktrello.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by guilhermemorescobisotto on 4/29/16.
 */
public class List extends AppModel {

    //region Attributes
    @SerializedName("name")
    public String name;

    @SerializedName("closed")
    public boolean closed;

    @SerializedName("idBoard")
    public String idBoard;

    @SerializedName("pos")
    public int pos;

    @SerializedName("subscribed")
    public boolean subscribed;

    private java.util.List<Card> cards;
    //endregion

    public void setCards(java.util.List<Card> cards) {
        if (this.cards == null) {
            this.cards = new ArrayList<>();
        }
        this.cards.addAll(cards);
    }
}
