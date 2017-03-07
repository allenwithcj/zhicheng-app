package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.SearchModel;
import com.zhicheng.api.model.impl.SearchModelImpl;
import com.zhicheng.api.presenter.SearchPresenter;
import com.zhicheng.api.view.SearchView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by Donson on 2017/1/17.
 */

public class SearchPresenterImpl implements SearchPresenter,ApiCompleteListener {

    private SearchModelImpl mSearchModel;
    private SearchView mSearchView;

    public SearchPresenterImpl(SearchView mSearchView){
        this.mSearchView = mSearchView;
        mSearchModel = new SearchModelImpl();
    }

    @Override
    public void getSearchResult(String request) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mSearchView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mSearchModel.SearchDataList(request,this);
    }

    @Override
    public void SearchBaoClassify(String p1) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mSearchView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mSearchModel.SearchBaoClassifyList(p1,this);
    }

    @Override
    public void cancelSearch() {
        mSearchModel.cancelSearch();
    }

    @Override
    public void onComplected(Object result) {
        mSearchView.updateView(result);
    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null){
            return;
        }
        mSearchView.showMessage(msg.getMsg());
    }
}
