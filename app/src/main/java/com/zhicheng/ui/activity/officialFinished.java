package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.bean.json.OfficialRequest;
import com.zhicheng.utils.common.DensityUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Donson on 2017/2/22.
 */

public class officialFinished extends BaseActivity implements OfficialView,SwipeRefreshLayout.OnRefreshListener{

    private int start;


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OfficialPresenterImpl mOfficialPresenterImpl;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
    private FinishAdapter mFinishAdapter;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mOfficialPresenterImpl=new OfficialPresenterImpl(this);
        mRecyclerView=(RecyclerView) findViewById(R.id.mRecycleView);
        mFinishAdapter=new FinishAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mFinishAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        start = 1;
        String strEntity = createObj(start);
        mOfficialPresenterImpl.loadNoFinish(strEntity,start);
        start++;
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(mToolbar,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof OfficialResponse){
            mFinishAdapter.setDataList(((OfficialResponse) result).getIq().getQuery().getTable());
            mToolbar.setTitle("已办事项");
        }
    }

    public void onLoadMore(){
        String strEntity = createObj(start);
        mOfficialPresenterImpl.loadNoFinish(strEntity,start);
        start++;
    }

    @Override
    public void addData(Object result) {
        if (result instanceof OfficialResponse){
            mFinishAdapter.addDataList(((OfficialResponse) result).getIq().getQuery().getTable());
        }
    }

    private String createObj(int page){
        Gson gson = new Gson();
        OfficialRequest officialRequest = new OfficialRequest();
        OfficialRequest.IqBean iqb = new OfficialRequest.IqBean();
        OfficialRequest.IqBean.QueryBean iqbQB = new OfficialRequest.IqBean.QueryBean();
        iqbQB.setRequestType("1");
        iqbQB.setPage(String.valueOf(page));
        iqbQB.setPerPageNums("10");
        iqbQB.setOrderBy("");
        iqbQB.setOrderType("");
        iqb.setQuery(iqbQB);
        iqb.setModel("0");
        iqb.setNamespace("ListRequest");
        officialRequest.setIq(iqb);
        return gson.toJson(officialRequest);
    }

    private class FinishAdapter extends RecyclerView.Adapter{

//        private OfficialResponse.IqBean.QueryBean.TableBean data;
        private List<List<OfficialResponse.IqBean.QueryBean.TableBean.TableRowsBean>> data;

        private String[] tag={"编号:","描述:","已完成:"};

        public FinishAdapter(){

        }

        public void addDataList(OfficialResponse.IqBean.QueryBean.TableBean data){
            int page = this.data.size();
            this.data.addAll(data.getTableRows());
            this.notifyItemRangeInserted(page,data.getTableRows().size());
        }

        public void setDataList(OfficialResponse.IqBean.QueryBean.TableBean data){
            this.data = data.getTableRows();
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(officialFinished.this).inflate(R.layout.activity_home_finish,parent,false);
            return new FinishViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof FinishViewHolder){
//                Glide.with(officialFinished.this)
//                        .load("http://219.131.197.178:9904/"+data.get(position).get(5).getValue())
//                        .thumbnail((float) 0.7)
//                        .into(((FinishViewHolder)holder).photo);
                ((FinishViewHolder) holder).NumberId.setText(tag[0]+data.get(position).get(1).getValue());
                ((FinishViewHolder) holder).desc.setText(tag[1]+data.get(position).get(4).getValue());
                ((FinishViewHolder) holder).deal.setText(tag[2]+data.get(position).get(3).getValue());
                ((FinishViewHolder) holder).Suc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(UIUtils.getContext(),OfficialFinishDetail.class);
                        intent.putExtra("detailId",data.get(position).get(0).getValue());
                        UIUtils.startActivity(intent);
                    }
                });

            }
        }


        @Override
        public int getItemCount() {
            if (data != null){
                return data.size();
            }
            return 0;
        }

        private class FinishViewHolder extends RecyclerView.ViewHolder{
            private RelativeLayout Suc;
//            private ImageView photo;
            private TextView NumberId;
            private TextView desc;
            private TextView deal;

            public FinishViewHolder(View itemView) {
                super(itemView);
                Suc = (RelativeLayout) itemView.findViewById(R.id.Suc);
//                photo = (ImageView) itemView.findViewById(R.id.photo);
                NumberId = (TextView) itemView.findViewById(R.id.NumberId);
                desc = (TextView) itemView.findViewById(R.id.desc);
                deal = (TextView) itemView.findViewById(R.id.deal);
            }
        }
    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mFinishAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    }
}
