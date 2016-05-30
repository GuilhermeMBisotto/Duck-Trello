package com.guilhermemorescobisotto.ducktrello.Services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guilhermemorescobisotto.ducktrello.APIService.APIService;
import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.OnTaskCompleted;
import com.guilhermemorescobisotto.ducktrello.Helpers.SharedPreferences;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Member;
import com.guilhermemorescobisotto.ducktrello.Models.TrelloList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kotlin.Pair;

/**
 * Created by guilhermemorescobisotto on 5/17/16.
 */
public class TrelloListService {

    public static void getLists(final String boardId, final APIServiceHandler callback) {
        final List<Pair<String, String>> params = new ArrayList<Pair<String, String>>() {{
            add(new Pair<>("key", DuckConstants.APP_KEY));
            add(new Pair<>("token", SharedPreferences.ref().getUserToken().toString()));
        }};

        APIService.GET(DuckConstants.API_GET_LIST.replace("{boardId}", boardId), params, new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                ArrayList<TrelloList> trelloLists = new ArrayList<>();
                trelloLists.addAll(new Gson().<Collection<? extends TrelloList>>fromJson((String) obj, new TypeToken<List<TrelloList>>() {
                }.getType()));

                callback.onSuccess(trelloLists);

            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                callback.onError(errorCode, errorMessage, err);
            }
        });
    }
}
