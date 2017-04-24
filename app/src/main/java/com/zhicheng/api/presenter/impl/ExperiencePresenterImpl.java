package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.ExperienceModelImpl;
import com.zhicheng.api.model.impl.MainModelImpl;
import com.zhicheng.api.presenter.ExperiencePresenter;
import com.zhicheng.api.presenter.UpThingsPresenter;
import com.zhicheng.api.view.ExperienceView;
import com.zhicheng.api.view.UpThingsView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.ExperienceCommonResponse;
import com.zhicheng.bean.http.ExperienceDetailResponse;
import com.zhicheng.bean.http.ExperienceResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.List;


/**
 * Created by Donson on 2017/2/15.
 */

public class ExperiencePresenterImpl implements ExperiencePresenter, ApiCompleteListener {
    private ExperienceView mExperienceView;
    private ExperienceModelImpl mExperienceModelImpl;
    private int start;

    public ExperiencePresenterImpl(ExperienceView view) {
        this.mExperienceView = view;
        mExperienceModelImpl = new ExperienceModelImpl();
    }

    @Override
    public void UpThings(String GUID, String jFile, List<String> imgs) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mExperienceView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mExperienceModelImpl.UpThings(GUID, jFile, imgs, this);
    }

    @Override
    public void UpExperience(int requestType, String json) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mExperienceView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mExperienceModelImpl.upExperience(requestType, json, this);
    }

    @Override
    public void loadExp(String json,int start) {
        this.start = start;
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mExperienceView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mExperienceView.hideProgress();
        }
        mExperienceModelImpl.queryExp(json, this);
        mExperienceView.showProgress();
    }

    @Override
    public void loadExpDetails(String json) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mExperienceView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mExperienceModelImpl.queryExpDetail(json, this);
    }


    @Override
    public void onComplected(Object result) {
        if (result instanceof CommonResponse) {
            mExperienceView.upExpResponse(result);
        }
        if (result instanceof ExperienceCommonResponse) {
            mExperienceView.upExpResponse(result);
        }
        if(result instanceof ExperienceDetailResponse){
            mExperienceView.queryExpResponse(result);
        }
        if (result instanceof ExperienceResponse) {
            if (start == 1) {
                mExperienceView.queryExpResponse(result);
            } else{
                mExperienceView.loadExpResponse(result);
            }
            mExperienceView.hideProgress();
        }
    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null) {
            return;
        }
        mExperienceView.showMessage(msg.getMsg());
    }
}
