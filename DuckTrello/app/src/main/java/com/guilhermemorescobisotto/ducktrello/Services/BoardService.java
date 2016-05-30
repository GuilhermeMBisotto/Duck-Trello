package com.guilhermemorescobisotto.ducktrello.Services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.guilhermemorescobisotto.ducktrello.APIService.APIService;
import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.SharedPreferences;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Member;
import com.guilhermemorescobisotto.ducktrello.Models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kotlin.Pair;

/**
 * Created by guilhermemorescobisotto on 5/17/16.
 */
public class BoardService {

    public static void getBoards(final APIServiceHandler callback) {
        final List<Pair<String, String>> params = new ArrayList<Pair<String, String>>() {{
            add(new Pair<>("key", DuckConstants.APP_KEY));
            add(new Pair<>("token", SharedPreferences.ref().getUserToken().toString()));
        }};

        APIService.GET(DuckConstants.API_BOARD + "/all", params, new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                ArrayList<Board> boards = new ArrayList<>();
                boards.addAll(new Gson().<Collection<? extends Board>>fromJson((String) obj, new TypeToken<List<Board>>() {
                }.getType()));

                callback.onSuccess(boards);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                callback.onError(errorCode, errorMessage, err);
            }
        });
    }

    public static void getMemberFromBoard(String boardId, final APIServiceHandler callback) {
        final List<Pair<String, String>> params = new ArrayList<Pair<String, String>>() {{
            add(new Pair<>("key", DuckConstants.APP_KEY));
            add(new Pair<>("token", SharedPreferences.ref().getUserToken().toString()));
        }};

        APIService.GET(DuckConstants.API_BOARD_MEMBERS.replace("{boardId}", boardId), params, new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                ArrayList<Member> members = new ArrayList<>();
                members.addAll(new Gson().<Collection<? extends Member>>fromJson((String) obj, new TypeToken<List<Member>>() {
                }.getType()));

                callback.onSuccess(members);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                callback.onError(errorCode, errorMessage, err);
            }
        });
    }
}
