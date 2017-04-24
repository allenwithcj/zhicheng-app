package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialBaseGridPresenterImpl;
import com.zhicheng.api.view.OfficialBaseGridView;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.CaseGridResponse;
import com.zhicheng.bean.json.CaseGridRequest;

import java.util.List;

/**
 * Created by lwl on 2017/4/11.
 */

public class GridNameActivity extends BaseActivity implements OfficialBaseGridView ,SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyGridAdapter mAdapter;
    private OfficialBaseGridPresenterImpl mOfficialBaseGridPresenterImpl;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_grid_name);
        mOfficialBaseGridPresenterImpl = new OfficialBaseGridPresenterImpl(this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyGridAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("所属网格");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
        if(result instanceof CaseGridResponse){
            if(((CaseGridResponse) result).getIq().getQuery().getErrorCode() == 0){
                mAdapter.setDateList(((CaseGridResponse) result).getIq().getQuery().getItems());
            }else{
                showMessage(((CaseGridResponse) result).getIq().getQuery().getErrorMessage());
            }
        }

    }

    @Override
    public void onRefresh() {
        CaseGridRequest mCaseGridRequest = new CaseGridRequest();
        CaseGridRequest.IqBean iqb = new CaseGridRequest.IqBean();
        CaseGridRequest.IqBean.QueryBean qb = new CaseGridRequest.IqBean.QueryBean();
        iqb.setNamespace("CaseGridRequest");
        qb.setFlag("2");
        iqb.setQuery(qb);
        mCaseGridRequest.setIq(iqb);
        Gson gson = new Gson();
        mOfficialBaseGridPresenterImpl.loadOfficialBaseGridNames(gson.toJson(mCaseGridRequest));
    }

    private class MyGridAdapter extends RecyclerView.Adapter{
        private List<CaseGridResponse.IqBean.QueryBean.ItemsBean> items;
        public void setDateList(List<CaseGridResponse.IqBean.QueryBean.ItemsBean> items) {
            this.items = items;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view,parent,false);
            return new MyGridViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyGridViewHolder){
                if(items !=null){
                    ((MyGridViewHolder) holder).text_view.setText(items.get(position).getThirdname());
                    ((MyGridViewHolder) holder).text_view.setOnClickListener(view -> {
                        Intent intent = new Intent();
                        intent.setAction("com.grid.gridNo");
                        intent.putExtra("value", items.get(position).getThirdname());
                        sendBroadcast(intent);
                        finish();
                    });
                }
            }
        }

        @Override
        public int getItemCount() {
            return items == null ? 0:items.size();
        }


    }
    class MyGridViewHolder extends RecyclerView.ViewHolder{
        private TextView text_view;

        public MyGridViewHolder(View itemView) {
            super(itemView);
            text_view = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
