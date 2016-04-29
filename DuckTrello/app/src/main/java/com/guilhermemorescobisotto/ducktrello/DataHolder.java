package com.guilhermemorescobisotto.ducktrello;

import java.util.List;

/**
 * Created by guilhermemorescobisotto on 4/28/16.
 */
public class DataHolder {

//    public User currentUser;

    private static DataHolder holder = new DataHolder();
    public static DataHolder getRef() { return holder; }

    private DataHolder(){}

    public static void clearAll(){
        holder = new DataHolder();
    }
}
