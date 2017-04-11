package com.zhicheng.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhicheng.R;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by Donson on 2017/1/14.
 */

public class ExperienceActivity extends BaseActivity {

    private WebView mWebView;
    private String mUrl;

    public static void actionStart(String url) {
        Intent intent = new Intent(UIUtils.getContext(), ExperienceActivity.class);
        intent.putExtra("url", url);
        UIUtils.startActivity(intent);
    }

    @Override
    protected void initEvents() {
        setContentView(R.layout.experience_webview_content);
        mUrl = getIntent().getStringExtra("url");
        mWebView = (WebView) findViewById(R.id.mWebView);
        initWebView();
        mWebView.clearCache(true);
        mWebView.loadUrl(mUrl);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {

    }

    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JsInterface(), "JsBridge");
        //启用缓存
        mWebView.getSettings().setAppCacheEnabled(true);
        //启用缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.setWebChromeClient(new WebChromeClient() {
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle(UIUtils.getContext().getResources().getString(R.string.Experiment));
        return super.onCreateOptionsMenu(menu);
    }

    public class JsInterface {
        @JavascriptInterface
        public void toastMessage(String msg) {
            Snackbar.make(mWebView, msg, Snackbar.LENGTH_SHORT).show();
        }

        @SuppressWarnings("ResourceType")
        @JavascriptInterface
        public void nextPage(String url, String name) {

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mWebView.getWindowToken(), 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWebView.stopLoading();
    }
}
