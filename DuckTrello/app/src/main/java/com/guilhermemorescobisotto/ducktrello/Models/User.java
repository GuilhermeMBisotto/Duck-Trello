package com.guilhermemorescobisotto.ducktrello.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by guilhermemorescobisotto on 4/29/16.
 */
public class User extends AppModel {

    @SerializedName("username")
    public String username;

    @SerializedName("avatarHash")
    public String avatarHash;

    @SerializedName("bio")
    public String bio;

    @SerializedName("confirmed")
    public boolean confirmed;

    @SerializedName("fullName")
    public String fullName;

    @SerializedName("initials")
    public String initials;

    @SerializedName("memberType")
    public String memberType;

    @SerializedName("status")
    public String status;

    @SerializedName("url")
    public String url;

    @SerializedName("email")
    public String email;

    @SerializedName("gravatarHash")
    public String gravatarHash;

    @SerializedName("idBoards")
    public java.util.List<String> idBoards;

    @SerializedName("idOrganizations")
    public java.util.List<String> idOrganizations;


}
