package com.guilhermemorescobisotto.ducktrello.Services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.guilhermemorescobisotto.ducktrello.APIService.APIService;
import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
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

    public static void logIn(final String user, final String pass, final APIServiceHandler callback) {
        final List<Pair<String, String>> params = new ArrayList<Pair<String, String>>() {{
            add(new Pair<>("username", user));
            add(new Pair<>("password", pass));
        }};

        APIService.POST(DuckConstants.API_LOGIN_URL, params, new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                User user = new Gson().fromJson((JsonElement) obj, User.class);
                SharedPreferences.ref().setLastUser(user);
                callback.onSuccess(user);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                callback.onError(errorCode, errorMessage, err);
            }
        });
    }
}
