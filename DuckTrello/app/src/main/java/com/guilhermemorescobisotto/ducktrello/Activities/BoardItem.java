package com.guilhermemorescobisotto.ducktrello.Activities;

import com.guilhermemorescobisotto.ducktrello.Models.Board;

/**
 * Created by guilhermemorescobisotto on 5/24/16.
 */
public class BoardItem {

    private String name;
    private boolean closed;
    private Board board;

    public BoardItem(String name, boolean closed, Board board) {
        this.name = name;
        this.closed = closed;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isClosed() {
        return closed;
    }

    public String getName() {
        return name;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setName(String name) {
        this.name = name;
    }
}
