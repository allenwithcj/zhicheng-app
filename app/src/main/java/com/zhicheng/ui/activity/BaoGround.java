package com.zhicheng.ui.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zhicheng.R;
import com.zhicheng.ui.fragment.WebViewFragment;


/**
 * Created by Donson on 2017/1/3.
 */

public class BaoGround extends BaseActivity {

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_home_baoground);
        FragmentManager mFragmentManage = getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManage.beginTransaction();
        WebViewFragment mWebViewFragment = WebViewFragment.newInstance("http://10.0.2.2/helloworld/index.html","index");
        mWebViewFragment.isShowClose();
        ft.add(R.id.webView_content, mWebViewFragment);
        ft.commit();
    }

    @Override
    protected void initData() {

    }
}
