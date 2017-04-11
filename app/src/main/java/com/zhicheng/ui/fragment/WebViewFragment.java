package com.zhicheng.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zhicheng.R;

/**
 * Created by Donson on 2017/1/14.
 */

public class WebViewFragment extends BaseFragment implements View.OnClickListener {

    private WebView mWebView;
    private ImageButton btnBack;
    private ImageButton btnClose;
    private ImageButton btnMore;
    private TextView title;
    private boolean showClose = false;
    private String mUrl;

    public static WebViewFragment newInstance(String url, String title) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle b = new Bundle();
        b.putString("url", url);
        b.putString("title", title);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.webview_content, container, false);
    }

    @Override
    protected void initEvents() {
        mUrl = getArguments().getString("url");
        mWebView = (WebView) mRootView.findViewById(R.id.mWebView);
        btnBack = (ImageButton) mRootView.findViewById(R.id.webView_back);
        btnClose = (ImageButton) mRootView.findViewById(R.id.webView_close);
        btnMore = (ImageButton) mRootView.findViewById(R.id.webView_more);
        title = (TextView) mRootView.findViewById(R.id.webView_title);
        btnBack.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnMore.setOnClickListener(this);
        initWebView();
        mWebView.clearCache(true);
        mWebView.loadUrl(mUrl);
        title.setText(getArguments().getString("title"));
    }

    @Override
    protected void initData(boolean isSavedNull) {

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
        if (showClose) {
            btnClose.setVisibility(View.GONE);
        }
    }

    public void isShowClose() {
        showClose = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.webView_back:
                if (getFragmentManager().getBackStackEntryCount() == 0) {
                    getActivity().finish();
                } else {
                    getFragmentManager().popBackStack();
                }
                break;
            case R.id.webView_close:
                getActivity().finish();
                break;
            case R.id.webView_more:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri content_url = Uri.parse(mUrl);
                intent.setData(content_url);
                startActivity(intent);
                break;
        }
    }

    public class JsInterface {
        @JavascriptInterface
        public void toastMessage(String msg) {
            Snackbar.make(mWebView, msg, Snackbar.LENGTH_SHORT).show();
        }

        @SuppressWarnings("ResourceType")
        @JavascriptInterface
        public void nextPage(String url, String name) {
            FragmentManager mFragmentManage = getFragmentManager();
            FragmentTransaction ft = mFragmentManage.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.setCustomAnimations(
                    R.animator.fragment_slide_right_in, R.animator.fragment_slide_left_out,
                    R.animator.fragment_slide_left_in, R.animator.fragment_slide_right_out);
            ft.hide(WebViewFragment.this);
            ft.add(R.id.webView_content, WebViewFragment.newInstance(url, name));
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mWebView.getWindowToken(), 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWebView.stopLoading();
    }
}
