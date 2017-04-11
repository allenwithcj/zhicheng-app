package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.ContactModelImpl;
import com.zhicheng.api.presenter.ContactPresenter;
import com.zhicheng.api.view.ContactView;
import com.zhicheng.bean.http.AddressBookResponse;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by hp on 2017/3/11.
 */

public class ContactPresenterImpl implements ContactPresenter, ApiCompleteListener {
    private int start;
    private ContactModelImpl contactModelImpl;
    private ContactView contactView;

    public ContactPresenterImpl(ContactView contactView) {
        this.contactView = contactView;
        contactModelImpl = new ContactModelImpl();
    }

    @Override
    public void loadContacts(String s, int start) {
        this.start = start;
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            contactView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            contactView.hideProgress();
        }
        contactView.showProgress();
        contactModelImpl.loadContacts(s, this);
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof AddressBookResponse) {
            if (start == 1) {
                contactView.refreshData(result);
            } else {
                contactView.addDate(result);
            }
        }
        contactView.hideProgress();
    }

    @Override
    public void onFailed(BaseResponse msg) {
        contactView.hideProgress();
        if (msg == null) {
            return;
        }
        contactView.showMessage(msg.getMsg());
    }
}
