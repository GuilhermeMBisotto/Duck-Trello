package com.guilhermemorescobisotto.ducktrello.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by guilhermemorescobisotto on 5/28/16.
 */
public class Member extends AppModel {

    //region Attributes
    @SerializedName("fullName")
    public String fullName;

    @SerializedName("username")
    public String username;
    //endregion
}
