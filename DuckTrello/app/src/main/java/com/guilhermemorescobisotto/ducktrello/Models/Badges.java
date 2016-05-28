package com.guilhermemorescobisotto.ducktrello.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by guilhermemorescobisotto on 5/28/16.
 */
public class Badges {

    //region Attributes
    @SerializedName("checkItems")
    public int checkItems;

    @SerializedName("checkItemsChecked")
    public int checkItemsChecked;

    @SerializedName("comments")
    public int comments;

    @SerializedName("attachments")
    public int attachments;
    //endregion

}
