package com.guilhermemorescobisotto.ducktrello.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.APIService.WebViewCustom;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.R;
import com.guilhermemorescobisotto.ducktrello.Services.BoardService;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {


    private TextView tv_home;
    private static ProgressBar mainProgressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.tv_home = (TextView) view.findViewById(R.id.tv_home);
        this.mainProgressBar = (ProgressBar) view.findViewById(R.id.main_progressBar);

        BoardService.getBoards(new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                HomeActivityFragment.this.tv_home.setText(obj.toString());
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                HomeActivityFragment.this.tv_home.setText(errorMessage);
            }
        });
	}
}
