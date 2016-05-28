package com.guilhermemorescobisotto.ducktrello;

import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Card;
import com.guilhermemorescobisotto.ducktrello.Models.User;

import java.util.List;

/**
 * Created by guilhermemorescobisotto on 4/28/16.
 */
public class DataHolder {

    public User currentUser;
    public Board currentBoard;

    public List<Board> boards;
    public List<Card> cards;

    private static DataHolder holder = new DataHolder();
    public static DataHolder getRef() { return holder; }
    public static String userToken;

    private DataHolder(){}

    public static void clearAll(){
        holder = new DataHolder();
    }
}
