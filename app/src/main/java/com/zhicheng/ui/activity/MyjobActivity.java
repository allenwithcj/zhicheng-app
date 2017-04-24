package com.zhicheng.ui.activity;

import android.widget.TextView;

import com.zhicheng.R;

/**
 * Created by Donson on 2017/4/19.
 */

public class MyjobActivity extends BaseActivity {
    private TextView ceshi;
    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_myjob_ceshi);
        ceshi= (TextView) findViewById(R.id.ceshi);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);

    }

    @Override
    protected void initData() {

    }
}
