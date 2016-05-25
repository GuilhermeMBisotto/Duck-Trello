package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.APIService.WebViewCustom;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.R;
import com.guilhermemorescobisotto.ducktrello.Services.BoardService;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

    private static ProgressBar homeProgressBar;
    private AdapterHomeListView adapterHomeListView;
    private ArrayList<BoardItem> homeItemList;
    private SwipeRefreshLayout homeListRefresh;
    private Context context;
    private ListView lvHome;


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
                loadBoards();
                homeListRefresh.setRefreshing(false);
            }
        });

        this.loadBoards();
	}

    private ListView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final BoardItem boardItem = adapterHomeListView.getItem(position);

            Essential.log("Board clicked: " + boardItem.getName());
        }
    };

    private void loadBoards() {
        homeProgressBar.setVisibility(View.VISIBLE);

        BoardService.getBoards(new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                Essential.log("Boards onSuccess");
                List<Board> boards = new ArrayList<>();
                boards.addAll(((ArrayList<Board>) obj));

                for (Board board : boards) {
                    BoardItem boardItem = new BoardItem(board.name, board.closed, board);
                    homeItemList.add(boardItem);
                }

                Handler mainHandler = new Handler(context.getMainLooper());

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        adapterHomeListView.notifyDataSetChanged();
                    }
                };
                mainHandler.post(runnable);

                homeProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                Essential.log("Boards onError");
                homeProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
