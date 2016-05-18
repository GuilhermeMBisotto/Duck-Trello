package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.APIService.WebViewCustom;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.SharedPreferences;
import com.guilhermemorescobisotto.ducktrello.R;
import com.guilhermemorescobisotto.ducktrello.Services.BoardService;

import java.util.logging.Handler;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private LinearLayout llWebContentWrapper;
    private WebView webView;
    private static ProgressBar mainProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.llWebContentWrapper = (LinearLayout) view.findViewById(R.id.llWebContent_wrapper);
        this.webView = (WebView) view.findViewById(R.id.webContent);
        this.mainProgressBar = (ProgressBar) view.findViewById(R.id.main_progressBar);
        ((Button) view.findViewById(R.id.login_btnLogin)).setOnClickListener(onLoginAPIClickListener);

        if (!SharedPreferences.ref().getUserToken().isEmpty()) {
            mainProgressBar.setVisibility(View.VISIBLE);

            android.os.Handler mainHandler = new android.os.Handler(getContext().getMainLooper());

            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    mainProgressBar.setVisibility(View.GONE);
                    getContext().startActivity(new Intent(getContext(), HomeActivity.class));
                }
            };
            mainHandler.postDelayed(runnable, 3000);
        }
	}

    private Button.OnClickListener onLoginAPIClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            llWebContentWrapper.setVisibility(View.VISIBLE);
            String url = DuckConstants.API_AUTHORIZE + "?key=" + DuckConstants.APP_KEY + "&name=Duck%20Trello&expiration=never&response_type=token&scope=read,write";
            WebViewCustom.initWebView(MainActivityFragment.this.getContext(), MainActivityFragment.this.webView, MainActivityFragment.this.llWebContentWrapper);
            MainActivityFragment.this.webView.loadUrl("about:blank");

            MainActivityFragment.this.webView.loadUrl(url);
        }
    };

    public static void startHome(final Context context) {

        mainProgressBar.setVisibility(View.VISIBLE);

        android.os.Handler mainHandler = new android.os.Handler(context.getMainLooper());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!SharedPreferences.ref().getUserToken().isEmpty()) {
                    mainProgressBar.setVisibility(View.GONE);
                    context.startActivity(new Intent(context, HomeActivity.class));
                }
            }
        };
        mainHandler.postDelayed(runnable, 3000);
    }
}
