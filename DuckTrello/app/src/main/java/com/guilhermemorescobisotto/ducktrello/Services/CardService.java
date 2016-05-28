package com.guilhermemorescobisotto.ducktrello.Services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guilhermemorescobisotto.ducktrello.APIService.APIService;
import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.SharedPreferences;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Card;
import com.guilhermemorescobisotto.ducktrello.Models.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kotlin.Pair;

/**
 * Created by guilhermemorescobisotto on 5/17/16.
 */
public class CardService {

    public static void getCard(String boardId, final APIServiceHandler callback) {
        final List<Pair<String, String>> params = new ArrayList<Pair<String, String>>() {{
            add(new Pair<>("key", DuckConstants.APP_KEY));
            add(new Pair<>("token", SharedPreferences.ref().getUserToken().toString().replaceAll("\"", "")));
        }};

        APIService.GET(DuckConstants.API_BOARD_CARDS.replace("{boardId}", boardId), params, new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                ArrayList<Card> cards = new ArrayList<>();
                cards.addAll(new Gson().<Collection<? extends Card>>fromJson((String) obj, new TypeToken<List<Card>>() {
                }.getType()));

                callback.onSuccess(cards);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                callback.onError(errorCode, errorMessage, err);
            }
        });
    }

    public static void getMemberFromCard(Card card) {
        ArrayList<Member> members = new ArrayList<>();

        if (card.getBoard() != null) {
            for (Member memberBoard : card.getBoard().getMembers()) {
                for (String memberCard : card.idMembers) {
                    if (memberBoard.id.equalsIgnoreCase(memberCard)) {
                        members.add(memberBoard);
                    }
                }
            }
        }

        card.setMembers(members);
    }
}
