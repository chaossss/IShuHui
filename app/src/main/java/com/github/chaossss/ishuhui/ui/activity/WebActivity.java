package com.github.chaossss.ishuhui.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.chaossss.ishuhui.R;

/**
 * Created by chaos on 2016/1/4.
 */
public class WebActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "extra_url";
    public static final String EXTRA_TITLE = "title";

    private WebView mWebView;

    private Context mContext;
    private String mUrl, mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mContext = this;
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        Log.i("url", "url----->" + mUrl);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new MyWebClient());

        mWebView.loadUrl(mUrl);
    }

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    }
                    else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) mWebView.destroy();
    }

    @Override protected void onPause() {
        if (mWebView != null) mWebView.onPause();
        super.onPause();
    }

    @Override protected void onResume() {
        super.onResume();
        if (mWebView != null) mWebView.onResume();
    }

    private class ChromeClient extends WebChromeClient {
        @Override public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    private class MyWebClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }
}
