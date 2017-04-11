package com.zhicheng.ui.activity;


import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.common.Constant;
import com.zhicheng.utils.SystemBarTintManager;
import com.zhicheng.utils.common.SPUtils;
import com.zhicheng.utils.common.UIUtils;

import roboguice.activity.RoboActionBarActivity;

/**
 * Created by Donson on 2016/12/29.
 */

public abstract class BaseActivity extends RoboActionBarActivity {
    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        ((BaseApplication) UIUtils.getContext()).addActivity(this);
        initSystemBar(activity);
        init();
    }


    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    private void init() {
        initEvents();
        initData();

    }

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

    /**
     * 绑定数据
     */
    protected abstract void initData();

    /**
     * 获取Toolbar
     *
     * @return
     */
    public Toolbar getToolbar() {
        return mToolbar;
    }

    private void initSystemBar(BaseActivity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setTranslucentStatus(activity, true);
            }
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            //使用颜色资源
            tintManager.setStatusBarTintResource(getStatusColor());
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @TargetApi(19)
    protected void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 状态栏颜色
     * 子类可以复写此方法来改变状态栏颜色
     *
     * @return ID
     */
    protected int getStatusColor() {
        if (SPUtils.getPrefBoolean(Constant.THEME_MODEL, false)) {
            return R.color.colorPrimaryDarkNight;
        } else {
            return R.color.colorPrimaryDark;
        }
    }

    /**
     * 菜单按钮初始化
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getMenuID(), menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * 是否初始化状态栏
     * 默认true
     *
     * @return
     */
    protected boolean isInitSystemBar() {
        return true;
    }

    /**
     * 是否显示菜单
     *
     * @return
     */
    protected boolean isShowMenu() {
        return true;
    }

    /**
     * 默认Toolbar不带menu
     * 复写此方法设置menu
     *
     * @return ID
     */
    protected int getMenuID() {
        return R.menu.menu_empty;
    }

    /**
     * 退出时将activity移出栈
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((BaseApplication) UIUtils.getContext()).removeActivity(this);
    }
}
