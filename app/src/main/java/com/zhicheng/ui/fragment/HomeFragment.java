package com.zhicheng.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.MainPresenterImpl;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.MainView;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.bean.json.OfficialRequest;
import com.zhicheng.ui.adapter.HomeFragmentAdapter;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Donson on 2017/1/2.
 */

public class HomeFragment extends BaseFragment implements MainView,OfficialView,
        SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MainPresenterImpl mMainPresenterImpl;
    private HomeFragmentAdapter mHomeAdapter;
    //private Main parentActivity;

    private OfficialPresenterImpl mOfficialPresenterImpl;
    private int start;

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        fresh();
        super.onResume();
    }

    //查询待办事项个数
    private void fresh() {
        start = 1;
        String strEntity = createObj(start);
        mOfficialPresenterImpl.loadNoFinish(strEntity,start);
        start++;
    }

    private String createObj(int page){
        Gson gson = new Gson();
        OfficialRequest officialRequest = new OfficialRequest();
        OfficialRequest.IqBean iqb = new OfficialRequest.IqBean();
        OfficialRequest.IqBean.QueryBean iqbQB = new OfficialRequest.IqBean.QueryBean();
        iqbQB.setRequestType("0");
        iqbQB.setPage(String.valueOf(page));
        iqbQB.setPerPageNums("10");
        iqbQB.setOrderBy("");
        iqbQB.setOrderType("");
        iqbQB.setIsReadJian("0");
        iqb.setQuery(iqbQB);
        iqb.setModel("0");
        iqb.setNamespace("ListRequest");
        officialRequest.setIq(iqb);
        return gson.toJson(officialRequest);
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.recycle_content,container,false);
    }

    @Override
    protected void initEvents() {
        mMainPresenterImpl = new MainPresenterImpl(this);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter = new HomeFragmentAdapter();
        mRecyclerView.setAdapter(mHomeAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    protected void initData(boolean isSavedNull) {

    }

    @Override
    public void onRefresh() {
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("namespace","NewsCaseTotalRequest");
        map1.put("query",new HashMap<>());
        map.put("iq",map1);
        String json = gson.toJson(map);
        BaseApplication.log_say(TAG,json);
        mMainPresenterImpl.loadMain(json);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
//        if (parentActivity == null){
//            parentActivity = (Main) getActivity();
//        }
//        parentActivity.isTouch(false);
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof CommonResponse){
            if(((CommonResponse) result).getIq().getQuery().getErrorCode() == 0){
                mHomeAdapter.setAdapterData((CommonResponse) result);
//                Intent intent = new Intent();
//                intent.setAction("com.news.count.action");
//                intent.putExtra("news",String.valueOf(((CommonResponse) result).getIq().getQuery().getData().getDaiBanTotal()));
//                UIUtils.getContext().sendBroadcast(intent);
            }else{
                showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
            }
//            parentActivity.isTouch(true);
        }else if (result instanceof OfficialResponse){
            if(((OfficialResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                int nofinish_count = ((OfficialResponse) result).getIq().getQuery().getTotalNums();
                mHomeAdapter.setCountDate(nofinish_count);
            }
        }
    }

    @Override
    public void addData(Object result) {

    }


}
