package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Card;
import com.guilhermemorescobisotto.ducktrello.R;
import com.guilhermemorescobisotto.ducktrello.Services.BoardService;
import com.guilhermemorescobisotto.ducktrello.Services.CardService;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class CardsActivityFragment extends Fragment {

    private static ProgressBar cardsProgressBar;
    private AdapterCardsListView adapterCardsListView;
    private ArrayList<CardItem> cardItemsList;
    private SwipeRefreshLayout cardsListRefresh;
    private Context context;
    private ListView lvCard;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cards, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        this.cardsProgressBar = (ProgressBar) view.findViewById(R.id.cards_progressBar);

        this.cardItemsList = new ArrayList<>();
        this.adapterCardsListView = new AdapterCardsListView(context, this.cardItemsList);
        this.lvCard = (ListView) view.findViewById(R.id.cards_listView);
        this.lvCard.setAdapter(this.adapterCardsListView);
//        this.lvCard.setOnItemClickListener(this.onItemClickListener);

        this.cardsListRefresh = (SwipeRefreshLayout) view.findViewById(R.id.cards_list_refresh);
        this.cardsListRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Essential.log("sb-- onRefresh");
                cardItemsList.clear();
                loadCards();
                cardsListRefresh.setRefreshing(false);
            }
        });

        Essential.log("Board: " + DataHolder.getRef().currentBoard.name);

        this.loadCards();
	}

    private void loadCards() {
        this.cardsProgressBar.setVisibility(View.VISIBLE);

        CardService.getCard(DataHolder.getRef().currentBoard.id, new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                Essential.log("Cards onSuccess");
                List<Card> cards = new ArrayList<>();
                cards.addAll(((ArrayList<Card>) obj));

                if (DataHolder.getRef().cards == null) {
                    DataHolder.getRef().cards = new ArrayList<>();
                }
                DataHolder.getRef().cards.addAll(cards);

                setBoardInCards(cards);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                Essential.log("Cards onError");
                cardsProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setBoardInCards(final List<Card> cards) {
        for (Card card : cards) {
            for (Board board : DataHolder.getRef().boards) {
                if (board.id.equalsIgnoreCase(card.idBoard)) {
                    card.setBoard(board);
                }
            }
        }

        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loadMembersFromCard(cards);
            }
        };
        mainHandler.postDelayed(runnable, 2000);
    }

    private void loadMembersFromCard(List<Card> cards) {
        for (Card card : cards) {
            CardService.getMemberFromCard(card);
            Essential.log("Members: " + card.getMembersNameFromCard());

            CardItem cardItem = new CardItem(card);
            cardItemsList.add(cardItem);
        }

        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                adapterCardsListView.notifyDataSetChanged();
                cardsProgressBar.setVisibility(View.GONE);
            }
        };
        mainHandler.postDelayed(runnable, 2000);
    }
}
