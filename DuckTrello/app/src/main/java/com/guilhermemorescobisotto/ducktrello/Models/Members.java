package com.guilhermemorescobisotto.ducktrello.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by guilhermemorescobisotto on 5/28/16.
 */
public class Members extends AppModel {

    //region Attributes
    @SerializedName("idMember")
    public String idMember;

    @SerializedName("memberType")
    public String memberType;

    @SerializedName("unconfirmed")
    public boolean unconfirmed;

    @SerializedName("deactivated")
    public boolean deactivated;
    //endregion

}
