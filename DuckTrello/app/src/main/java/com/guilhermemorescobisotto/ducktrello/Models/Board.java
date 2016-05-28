package com.guilhermemorescobisotto.ducktrello.Models;

import com.google.gson.annotations.SerializedName;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranzi on 19/05/16.
 */
public class Board extends AppModel {

    //region Attributes
    @SerializedName("name")
    public String name;

    @SerializedName("desc")
    public String desc;

    @SerializedName("closed")
    public boolean closed;

    @SerializedName("memberships")
    public List<Members> membersList;

    private List<Member> members;

    private List<com.guilhermemorescobisotto.ducktrello.Models.List> lists;

    private String userType;
    //endregion

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setMembers(List<Member> members) {
        if (this.members == null) {
            this.members = new ArrayList<>();
        }
        this.members.addAll(members);
        Essential.log("setMembers: " + getMembersNameFromBoard());
    }

    public void setList(List<com.guilhermemorescobisotto.ducktrello.Models.List> list) {
        if (this.lists == null) {
            this.lists = new ArrayList<>();
        }
        this.lists.addAll(list);
    }

    public String getUserType() {
        return this.userType;
    }

    public List<Member> getMembers() {
        if (this.members == null) {
            this.members = new ArrayList<>();
        }
        return this.members;
    }


    public List<Members> getActiveMembers () {
        ArrayList<Members> activeMembers = new ArrayList<>();

        for (Members member : this.membersList) {
            if (!member.deactivated) {
                activeMembers.add(member);
            }
        }
        return activeMembers;
    }

    public String getMembersNameFromBoard() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < this.members.size(); i++) {
            if (i == 0) {
                string.append(this.members.get(i).username);
            } else if (i == (this.members.size()-1)) {
                string.append(" e ").append(this.members.get(i).username);
            } else {
                string.append(", ").append(this.members.get(i).username);
            }
        }

        return string.toString();
    }

    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("Id: ").append(this.id);
        string.append(" Name: ").append(this.name);
        string.append(" Closed: ").append(this.closed);
        string.append("\n\n");

        return string.toString();
    }
}
