package com.zhicheng.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.ui.adapter.OfficialDynamicAdapter;

/**
 * Created by Donson on 2017/1/15.
 */

public class OfficialWorkDynamic extends BaseActivity implements OfficialView,SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private OfficialPresenterImpl mOfficialPresenterImpl;
    private OfficialDynamicAdapter mOfficialDynamicAdapter;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official_basegrid);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        mOfficialDynamicAdapter = new OfficialDynamicAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mOfficialDynamicAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(mToolbar,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof OfficialWorkDynamicList){
            mOfficialDynamicAdapter.setAdapterData(((OfficialWorkDynamicList) result).getDetails());
        }
    }

    @Override
    public void addData(Object result) {

    }

    @Override
    public void onRefresh() {
//        mOfficialPresenterImpl.loadDynamic("dynamic");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("工作动态");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_span){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getMenuID() {
        return R.menu.menu_workspan;
    }
}
