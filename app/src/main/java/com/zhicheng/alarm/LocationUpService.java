package com.zhicheng.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.presenter.impl.LocationPresenterImpl;
import com.zhicheng.api.view.LocationView;
import com.zhicheng.bean.json.LocationUploadRequest;
import com.zhicheng.common.Constant;

/**
 * Created by hp on 2017/3/7.
 */

public class LocationUpService extends Service implements LocationView {
    private LocationPresenterImpl locationPresenterImpl;
    private DatabaseHelper mDate;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mDate = new DatabaseHelper();
        locationPresenterImpl = new LocationPresenterImpl(this);
        LocationUploadRequest upLocationRequest = new LocationUploadRequest();
        LocationUploadRequest.IqBean iq = new LocationUploadRequest.IqBean();
        LocationUploadRequest.IqBean.QueryBean qb = new LocationUploadRequest.IqBean.QueryBean();
        iq.setNamespace("LocationUploadRequest");
        qb.setUser(mDate.getLocalConfig().getUserName());
        qb.setLongitude(Constant.LONGITUDE);
        qb.setLatitude(Constant.LATITUDE);
        iq.setQuery(qb);
        upLocationRequest.setIq(iq);
        Gson gson = new Gson();
        locationPresenterImpl.upLocation(gson.toJson(upLocationRequest));
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void refreshData(Object result) {

    }
}
