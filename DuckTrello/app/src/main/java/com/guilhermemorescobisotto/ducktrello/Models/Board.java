package com.guilhermemorescobisotto.ducktrello.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    //endregion


    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("Id: ").append(this.id);
        string.append(" Name: ").append(this.name);
        string.append(" Closed: ").append(this.closed);
        string.append("\n\n");

        return string.toString();
    }
}
