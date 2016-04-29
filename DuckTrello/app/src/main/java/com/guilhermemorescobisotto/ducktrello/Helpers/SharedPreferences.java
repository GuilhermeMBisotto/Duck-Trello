package com.guilhermemorescobisotto.ducktrello.Helpers;

import android.content.Context;

import com.guilhermemorescobisotto.ducktrello.Core;

/**
 * Created by guilhermemorescobisotto on 4/28/16.
 */

public class SharedPreferences {

    private final String sharedKey = "key.ducktrello";
    private android.content.SharedPreferences sPref;
    private android.content.SharedPreferences.Editor sEdit;

    private final String setupRef = "setupRef";
    private final String lastUserRef = "lastUserRef";

    private static final SharedPreferences ref = new SharedPreferences();

    // Class
    protected SharedPreferences(){ this.sPref = Core.ref().getSharedPreferences(this.sharedKey, Context.MODE_PRIVATE); }
    public static SharedPreferences ref(){ return ref; }
    public void DestroyShared(){ this.sPref.edit().clear().commit(); }

    // Setup
    public void setSetupDone(){ this.sPref.edit().putBoolean(this.setupRef, true).apply(); }
    public boolean isSetupDone(){ return this.sPref.getBoolean(this.setupRef, false); }

    // Last Username
    public void setLastUser(String username){ this.sPref.edit().putString(this.lastUserRef, username).apply(); }
    public String getLastUser(){ return this.sPref.getString(this.lastUserRef, ""); }
}