package com.guilhermemorescobisotto.ducktrello.APIService;


import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

/**
 * Created by guilhermemorescobisotto on 4/28/16.
 */
public class APIServiceResult {

    @SerializedName("isSuccess")
    public boolean isSuccess;

    @SerializedName("data")
    public JsonElement data;

    public String responseString;

    @SerializedName("errorCode")
    public int errorCode;

    @SerializedName("errorMessage")
    public String errorMessage;
}
