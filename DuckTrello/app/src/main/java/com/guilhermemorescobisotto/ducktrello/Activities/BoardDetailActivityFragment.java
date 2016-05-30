package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.OnTaskCompleted;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Card;
import com.guilhermemorescobisotto.ducktrello.Models.TrelloList;
import com.guilhermemorescobisotto.ducktrello.R;
import com.guilhermemorescobisotto.ducktrello.Services.CardService;
import com.guilhermemorescobisotto.ducktrello.Services.ProjectDetailService;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoardDetailActivityFragment extends Fragment {

    private static ProgressBar boardDetailProgressBar;
    private AdapterBoardDetailListView adapterBoardDetailListView;
    private ArrayList<BoardDetailItem> boardDetailItemsList;
    private SwipeRefreshLayout boardDetailListRefresh;
    private Context context;
    private ListView lvBoardDetail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        this.boardDetailProgressBar = (ProgressBar) view.findViewById(R.id.cards_progressBar);

        this.boardDetailItemsList = new ArrayList<>();
        this.adapterBoardDetailListView = new AdapterBoardDetailListView(context, this.boardDetailItemsList);
        this.lvBoardDetail = (ListView) view.findViewById(R.id.cards_listView);
        this.lvBoardDetail.setAdapter(this.adapterBoardDetailListView);
//        this.lvBoardDetail.setOnItemClickListener(this.onItemClickListener);

        this.boardDetailListRefresh = (SwipeRefreshLayout) view.findViewById(R.id.cards_list_refresh);
        this.boardDetailListRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Essential.log("sb-- onRefresh");
                boardDetailItemsList.clear();
                initProjectDetailService();
                boardDetailListRefresh.setRefreshing(false);
            }
        });

        Essential.log("Board: " + DataHolder.getRef().currentBoard.name);

        this.initProjectDetailService();
	}

    private void initProjectDetailService() {

        this.boardDetailProgressBar.setVisibility(View.VISIBLE);

        ProjectDetailService.initService(DataHolder.getRef().currentBoard.id, new OnTaskCompleted() {
            @Override
            public void onTaskCompletedSuccess() {

                for (TrelloList list : DataHolder.getRef().currentBoard.getTrelloLists()) {
                    String listName = list.name;

                    for (Card card : list.getCards()) {
                        BoardDetailItem boardDetailItem = new BoardDetailItem(listName, card);
                        boardDetailItemsList.add(boardDetailItem);
                    }
                }

                Essential.log("Lists: " + DataHolder.getRef().currentBoard.getTrelloLists().size());
                Essential.log("Cards: " + DataHolder.getRef().currentBoard.getTrelloLists().get(0).getCards());


                Handler mainHandler = new Handler(context.getMainLooper());
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        adapterBoardDetailListView.notifyDataSetChanged();
                        boardDetailProgressBar.setVisibility(View.GONE);
                    }
                };
                mainHandler.post(runnable);
            }

            @Override
            public void onTaskCompletedFailure(String error) {
                Essential.log("initProjectDetailService Failure: " + error);
                boardDetailProgressBar.setVisibility(View.GONE);
            }
        });
    }

//    private void loadCards() {
//        this.boardDetailProgressBar.setVisibility(View.VISIBLE);
//
//        CardService.getCard(DataHolder.getRef().currentBoard.id, new APIServiceHandler() {
//            @Override
//            public void onSuccess(Object obj) {
//                Essential.log("Cards onSuccess");
//                List<Card> cards = new ArrayList<>();
//                cards.addAll(((ArrayList<Card>) obj));
//
//                if (DataHolder.getRef().cards == null) {
//                    DataHolder.getRef().cards = new ArrayList<>();
//                }
//                DataHolder.getRef().cards.addAll(cards);
//
//                setBoardInCards(cards);
//            }
//
//            @Override
//            public void onError(int errorCode, String errorMessage, Object err) {
//                Essential.log("Cards onError");
//                boardDetailProgressBar.setVisibility(View.GONE);
//            }
//        });
//    }

//    private void setBoardInCards(final List<Card> cards) {
//        for (Card card : cards) {
//            for (Board board : DataHolder.getRef().boards) {
//                if (board.id.equalsIgnoreCase(card.idBoard)) {
//                    card.setBoard(board);
//                }
//            }
//        }
//
//        Handler mainHandler = new Handler(context.getMainLooper());
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                loadMembersFromCard(cards);
//            }
//        };
//        mainHandler.postDelayed(runnable, 2000);
//    }

//    private void loadMembersFromCard(List<Card> cards) {
//        for (Card card : cards) {
//            CardService.getMemberFromCard(card);
//            Essential.log("Members: " + card.getMembersNameFromCard());
//
//            BoardDetailItem boardDetailItem = new BoardDetailItem(card);
//            boardDetailItemsList.add(boardDetailItem);
//        }
//
//        Handler mainHandler = new Handler(context.getMainLooper());
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                adapterBoardDetailListView.notifyDataSetChanged();
//                boardDetailProgressBar.setVisibility(View.GONE);
//            }
//        };
//        mainHandler.postDelayed(runnable, 2000);
//    }
}
