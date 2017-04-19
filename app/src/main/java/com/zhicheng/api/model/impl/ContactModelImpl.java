package com.zhicheng.api.model.impl;

import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.ContactService;
import com.zhicheng.api.model.ContactModel;
import com.zhicheng.bean.http.AddressBookResponse;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hp on 2017/3/11.
 */

public class ContactModelImpl implements ContactModel {
    @Override
    public void loadContacts(String lq, ApiCompleteListener listener) {
        ContactService mContactService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, ContactService.class);
        mContactService.loadContacts(lq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<AddressBookResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            listener.onFailed(null);
                            return;
                        }
                        listener.onFailed(new BaseResponse(404, e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<AddressBookResponse> mAddressBookResponseResponse) {
                        if (mAddressBookResponseResponse.isSuccessful()) {
                            listener.onComplected(mAddressBookResponseResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mAddressBookResponseResponse.code(), mAddressBookResponseResponse.message()));
                        }
                    }
                });
    }
}
