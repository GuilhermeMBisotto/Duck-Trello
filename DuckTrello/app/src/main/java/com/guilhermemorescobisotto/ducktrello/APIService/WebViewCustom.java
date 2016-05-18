package com.guilhermemorescobisotto.ducktrello.APIService;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.guilhermemorescobisotto.ducktrello.Activities.MainActivityFragment;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.SharedPreferences;
import com.guilhermemorescobisotto.ducktrello.R;

/**
 * Created by guilhermemorescobisotto on 5/17/16.
 */
public class WebViewCustom extends LinearLayout {

    private static LinearLayout llWebContentWrapper;
    private static Context context;

    public WebViewCustom(Context context) {
        super(context);
    }

    public static void initWebView(final Context ctx, final android.webkit.WebView webView, LinearLayout linearLayout) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            android.webkit.WebView.setWebContentsDebuggingEnabled(true);
        }

        context = ctx;
        llWebContentWrapper = linearLayout;

        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setGeolocationEnabled(true);
        ws.setUseWideViewPort(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(false);
        ws.setSupportZoom(false);
        ws.setSupportMultipleWindows(true);
        ws.setBuiltInZoomControls(false);
        ws.setLoadWithOverviewMode(true);
        ws.setLoadsImagesAutomatically(true);
        ws.setAppCachePath(ctx.getApplicationContext().getCacheDir().getAbsolutePath());
        ws.setGeolocationDatabasePath(ctx.getFilesDir().getPath());
        ws.setAllowFileAccess(true);
        ws.setAppCacheEnabled(true);
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
                //TODO: loader start
                Essential.log("onPageStarted");
                if (url.contains("token/")) {
                    llWebContentWrapper.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                Essential.log("onPageFinished");
                //TODO: loader finish
                //TODO: set status to loaded
                //TODO: save cache

                if (url.contains("token/approve")) {
                    view.evaluateJavascript("document.getElementsByTagName(\"pre\")[0].textContent.trim()", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            Essential.log("key: " + value);
                            DataHolder.userToken = value;
                            SharedPreferences.ref().setUserToken(value);
                            MainActivityFragment.startHome(context);
                        }
                    });
                }
            }

            @Override
            public void onReceivedError(android.webkit.WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Essential.log("Error: " + error + " request: " + request);
            }

            @Override
            public void onReceivedError(android.webkit.WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Essential.log("Error: " + description + " failureUrl: " + failingUrl);
            }
        });
    }
}
