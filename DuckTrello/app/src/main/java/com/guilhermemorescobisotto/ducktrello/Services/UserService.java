package com.guilhermemorescobisotto.ducktrello.Services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.guilhermemorescobisotto.ducktrello.APIService.APIService;
import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.SharedPreferences;
import com.guilhermemorescobisotto.ducktrello.Models.User;

import java.util.ArrayList;
import java.util.List;

import kotlin.Pair;

/**
 * Created by guilhermemorescobisotto on 4/29/16.
 */
public class UserService {

    public static void logout() {
        SharedPreferences.ref().DestroyShared();
        DataHolder.clearAll();
    }
}
