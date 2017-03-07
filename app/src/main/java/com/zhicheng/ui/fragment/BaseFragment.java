package com.zhicheng.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhicheng.BaseApplication;


/**
 * Created by Donson on 2016/12/30.
 */

public abstract class BaseFragment extends Fragment {
    protected final String TAG = getClass().getName();
    protected View mRootView;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden){
                ft.hide(this);
            }else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN,isHidden());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initRootView(inflater,container,savedInstanceState);
        initEvents();
        initData(savedInstanceState == null);
        BaseApplication.log_say("sss","onCreateView");
        return mRootView;
    }

    /**
     * 初始化根布局
     *
     * @return View 视图
     */
    protected abstract void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化监听事件等
     */
    protected abstract void initEvents();

    /**
     * 加载数据
     * @param isSavedNull
     */
    protected abstract void initData(boolean isSavedNull);
}
