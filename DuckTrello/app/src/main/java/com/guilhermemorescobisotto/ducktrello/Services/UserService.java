package com.guilhermemorescobisotto.ducktrello.Services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.guilhermemorescobisotto.ducktrello.APIService.APIService;
import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.SharedPreferences;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kotlin.Pair;

/**
 * Created by guilhermemorescobisotto on 4/29/16.
 */
public class UserService {


    public static void getUser(final APIServiceHandler callback) {
        final List<Pair<String, String>> params = new ArrayList<Pair<String, String>>() {{
            add(new Pair<>("key", DuckConstants.APP_KEY));
        }};

        APIService.GET(DuckConstants.API_TOKEN_MEMBER.replace("{token}", SharedPreferences.ref().getUserToken().toString().replaceAll("\"", "")), params, new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                User user = new Gson().fromJson((String)obj, User.class);
                callback.onSuccess(user);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                callback.onError(errorCode, errorMessage, err);
            }
        });
    }

    public static void logout() {
        SharedPreferences.ref().DestroyShared();
        DataHolder.clearAll();
    }
}
