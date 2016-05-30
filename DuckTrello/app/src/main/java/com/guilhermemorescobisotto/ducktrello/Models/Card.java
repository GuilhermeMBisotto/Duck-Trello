package com.guilhermemorescobisotto.ducktrello.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Card extends AppModel {

    //region Attributes
    @SerializedName("closed")
    public boolean closed;

    @SerializedName("name")
    public String name;

    @SerializedName("desc")
    public String desc;

    @SerializedName("idBoard")
    public String idBoard;

    @SerializedName("idList")
    public String idList;

    @SerializedName("dateLastActivity")
    public String dateLastActivity;

    @SerializedName("due")
    public String due;

    @SerializedName("idMembers")
    public List<String> idMembers;

    @SerializedName("badges")
    public Badges badges;

    @SerializedName("idChecklists")
    public List<String> idChecklists;

    private Board board;

    private List<Member> memberList;
    //endregion

    public void setMembers(List<Member> members) {
        if (this.memberList == null) {
            this.memberList = new ArrayList<>();
        } else {
            this.memberList.clear();
        }
        this.memberList.addAll(members);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    public String getMembersNameFromCard() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < this.memberList.size(); i++) {
            if (i == 0) {
                string.append(this.memberList.get(i).username);
            } else if (i == (this.memberList.size()-1)) {
                string.append(" e ").append(this.memberList.get(i).username);
            } else {
                string.append(", ").append(this.memberList.get(i).username);
            }
        }

        return string.toString();
    }
}