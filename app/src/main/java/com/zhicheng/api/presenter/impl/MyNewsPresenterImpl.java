package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.ExperienceModelImpl;
import com.zhicheng.api.model.impl.MyNewsModelImpl;
import com.zhicheng.api.presenter.ExperiencePresenter;
import com.zhicheng.api.presenter.MyNewsPresenter;
import com.zhicheng.api.view.ExperienceView;
import com.zhicheng.api.view.MyNewsView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.ExperienceCommonResponse;
import com.zhicheng.bean.http.ExperienceDetailResponse;
import com.zhicheng.bean.http.ExperienceResponse;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.List;


/**
 * Created by Donson on 2017/2/15.
 */

public class MyNewsPresenterImpl implements MyNewsPresenter, ApiCompleteListener {
    private MyNewsView myNewsView;
    private MyNewsModelImpl mMyNewsModelImpl;

    public MyNewsPresenterImpl(MyNewsView view) {
        this.myNewsView = view;
        mMyNewsModelImpl = new MyNewsModelImpl();
    }


    @Override
    public void onComplected(Object result) {
        if (result instanceof CommonResponse) {
            myNewsView.readMyNewsResponse(result);
        }

    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null) {
            return;
        }
        myNewsView.showMessage(msg.getMsg());
    }

    @Override
    public void readMyNews(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            myNewsView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mMyNewsModelImpl.readMyNews(s,this);
    }
}
