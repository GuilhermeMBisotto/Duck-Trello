package com.guilhermemorescobisotto.ducktrello.Services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.guilhermemorescobisotto.ducktrello.APIService.APIService;
import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.SharedPreferences;
import com.guilhermemorescobisotto.ducktrello.Models.User;

import java.util.ArrayList;
import java.util.List;

import kotlin.Pair;

/**
 * Created by guilhermemorescobisotto on 5/17/16.
 */
public class BoardService {

    public static void getBoards(final APIServiceHandler callback) {
        final List<Pair<String, String>> params = new ArrayList<Pair<String, String>>() {{
            add(new Pair<>("key", DuckConstants.APP_KEY));
            add(new Pair<>("token", SharedPreferences.ref().getUserToken().toString().replaceAll("\"", "")));
        }};

        APIService.GET(DuckConstants.API_BOARD + "/all", params, new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                callback.onSuccess(obj);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                callback.onError(errorCode, errorMessage, err);
            }
        });
    }
}
