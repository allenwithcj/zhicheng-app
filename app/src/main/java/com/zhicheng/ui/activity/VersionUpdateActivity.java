package com.zhicheng.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zhicheng.R;
import com.zhicheng.ui.fragment.WebViewFragment;

public class VersionUpdateActivity extends BaseActivity {
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private WebViewFragment mWebViewFragment;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_version_update);
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (mWebViewFragment == null) {
            mWebViewFragment = WebViewFragment.newInstance("https://www.pgyer.com/T0GL", "在线更新");
            ft.add(R.id.main_content, mWebViewFragment);
        } else {
            ft.show(mWebViewFragment);
        }
        ft.commit();
    }

    @Override
    protected void initData() {

    }
}
