package com.guilhermemorescobisotto.ducktrello.Services;


import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.OnTaskCompleted;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Card;
import com.guilhermemorescobisotto.ducktrello.Models.Member;
import com.guilhermemorescobisotto.ducktrello.Models.Members;
import com.guilhermemorescobisotto.ducktrello.Models.TrelloList;
import com.guilhermemorescobisotto.ducktrello.Models.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by guilhermemorescobisotto on 5/17/16.
 */
public class ProjectDetailService {

    public static void initService(final String boardId, final OnTaskCompleted callback) {

        TrelloListService.getLists(boardId, new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                DataHolder.getRef().currentBoard.setList(((ArrayList<TrelloList>) obj));

                getCards(callback);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                callback.onTaskCompletedFailure(errorMessage);
            }
        });
    }

    private static void getCards(final OnTaskCompleted callback) {

        final int[] cont = new int[1];
        cont[0] = 0;

        for (int i = 0; i < DataHolder.getRef().currentBoard.getTrelloLists().size(); i++) {
            final int index = i;
            CardService.getCardsFromList(DataHolder.getRef().currentBoard.getTrelloLists().get(i).id, new APIServiceHandler() {
                @Override
                public void onSuccess(Object obj) {
                    cont[0] = cont[0] + 1;

                    DataHolder.getRef().currentBoard.getTrelloLists().get(index).setCards(((ArrayList<Card>) obj));

                    for (Card card : DataHolder.getRef().currentBoard.getTrelloLists().get(index).getCards()) {
                        CardService.getMemberFromCard(card);
                    }

                    if (cont[0] == (DataHolder.getRef().currentBoard.getTrelloLists().size())) {
                        callback.onTaskCompletedSuccess();
                    }
                }

                @Override
                public void onError(int errorCode, String errorMessage, Object err) {
                    Essential.log("Cards onError");
                    callback.onTaskCompletedFailure(errorMessage);
                }
            });
        }
    }
}
