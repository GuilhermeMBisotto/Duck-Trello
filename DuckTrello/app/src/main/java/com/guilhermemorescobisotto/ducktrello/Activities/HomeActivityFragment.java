package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.content.Intent;
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

import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.OnTaskCompleted;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.R;
import com.guilhermemorescobisotto.ducktrello.Services.HomeService;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

    private static ProgressBar homeProgressBar;
    private AdapterHomeListView adapterHomeListView;
    private ArrayList<HomeItem> homeItemList;
    private SwipeRefreshLayout homeListRefresh;
    private Context context;
    private ListView lvHome;
    private boolean clickIsAvailable = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        this.homeProgressBar = (ProgressBar) view.findViewById(R.id.home_progressBar);
        this.homeItemList = new ArrayList<>();

        this.adapterHomeListView = new AdapterHomeListView(context, this.homeItemList);
        this.lvHome = (ListView) view.findViewById(R.id.home_listView);
        this.lvHome.setAdapter(this.adapterHomeListView);
        this.lvHome.setOnItemClickListener(this.onItemClickListener);

        this.homeListRefresh = (SwipeRefreshLayout) view.findViewById(R.id.home_list_refresh);
        this.homeListRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Essential.log("sb-- onRefresh");
                homeItemList.clear();
                initHomeService();
                homeListRefresh.setRefreshing(false);
            }
        });

        this.initHomeService();
	}

    private ListView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (clickIsAvailable) {
                final HomeItem homeItem = adapterHomeListView.getItem(position);

                Essential.log("Board clicked: " + homeItem.getName());

                DataHolder.getRef().currentBoard = homeItem.getBoard();

                context.startActivity(new Intent(context, BoardDetailActivity.class));
            }
        }
    };

    private void initHomeService() {

        this.homeProgressBar.setVisibility(View.VISIBLE);
        this.clickIsAvailable = false;

        HomeService.initService(new OnTaskCompleted() {
            @Override
            public void onTaskCompletedSuccess() {

                ArrayList<Board> boards = new ArrayList<>(DataHolder.getRef().boards);

                for (Board board : boards) {
                    HomeItem homeItem = new HomeItem(board.name, board.closed, board);
                    homeItemList.add(homeItem);
                }

                Handler mainHandler = new Handler(context.getMainLooper());
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        adapterHomeListView.notifyDataSetChanged();
                    }
                };
                homeProgressBar.setVisibility(View.GONE);
                clickIsAvailable = true;
                mainHandler.post(runnable);
            }

            @Override
            public void onTaskCompletedFailure(String error) {
                Essential.log("initHomeService Failure: " + error);
                homeProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
