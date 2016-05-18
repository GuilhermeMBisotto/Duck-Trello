package com.guilhermemorescobisotto.ducktrello.Helpers;

import android.content.Context;

import com.google.gson.Gson;
import com.guilhermemorescobisotto.ducktrello.Core;
import com.guilhermemorescobisotto.ducktrello.Models.User;

/**
 * Created by guilhermemorescobisotto on 4/28/16.
 */

public class SharedPreferences {

    private final String sharedKey = "key.ducktrello";
    private android.content.SharedPreferences sPref;
    private android.content.SharedPreferences.Editor sEdit;

    private final String setupRef = "setupRef";
    private final String lastUserRef = "lastUserRef";
    private final String tokenRef = "tokenRef";

    private static final SharedPreferences ref = new SharedPreferences();

    // Class
    protected SharedPreferences(){ this.sPref = Core.ref().getSharedPreferences(this.sharedKey, Context.MODE_PRIVATE); }
    public static SharedPreferences ref(){ return ref; }
    public void DestroyShared(){ this.sPref.edit().clear().commit(); }

    // Setup
    public void setSetupDone(){ this.sPref.edit().putBoolean(this.setupRef, true).apply(); }
    public boolean isSetupDone(){ return this.sPref.getBoolean(this.setupRef, false); }

    // Last Username
    public void setLastUser(User user){ this.sPref.edit().putString(this.lastUserRef, new Gson().toJson(user)).apply(); }
    public User getLastUser(){ return new Gson().fromJson(this.sPref.getString(this.lastUserRef, ""), User.class); }

    // User Token
    public void setUserToken(String token) { this.sPref.edit().putString(this.tokenRef, token).apply(); }
    public String getUserToken() { return this.sPref.getString(this.tokenRef, ""); }

}