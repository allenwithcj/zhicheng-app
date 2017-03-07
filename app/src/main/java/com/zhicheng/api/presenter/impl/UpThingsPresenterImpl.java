package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.MainModelImpl;
import com.zhicheng.api.presenter.UpThingsPresenter;
import com.zhicheng.api.view.UpThingsView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.List;


/**
 * Created by Donson on 2017/2/15.
 */

public class UpThingsPresenterImpl implements UpThingsPresenter,ApiCompleteListener{
    private UpThingsView mUpThingsView;
    private MainModelImpl mMainModelImpl;

    public UpThingsPresenterImpl(UpThingsView view){
        this.mUpThingsView = view;
        mMainModelImpl = new MainModelImpl();
    }

    @Override
    public void UpThings(int requestType,String GUID,String j,String jFile,List<String> imgs) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mUpThingsView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mMainModelImpl.UpThings(requestType,GUID,j,jFile,imgs,this);
    }

    @Override
    public void UpSimpleFile(String guid, List<String> imgs, String json) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mUpThingsView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mMainModelImpl.upSimpleFile(guid,imgs,json,this);
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof CommonResponse){
            mUpThingsView.UpThings(result);
        }
    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null){
            return;
        }
        mUpThingsView.showMessage(msg.getMsg());
    }
}
