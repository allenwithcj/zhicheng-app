package com.zhicheng.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhicheng.R;

/**
 * Created by Donson on 2017/1/15.
 */

public class ContactFragment extends BaseFragment {

    public static ContactFragment newInstance(){
        ContactFragment fragment = new ContactFragment();

        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_main_contact,container,false);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }
}
