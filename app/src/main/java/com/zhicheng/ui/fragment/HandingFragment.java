package com.zhicheng.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.bean.json.OfficialRequest;
import com.zhicheng.ui.activity.OfficialFinishDetail;
import com.zhicheng.utils.common.UIUtils;

import java.util.List;

/**
 * Created by hp on 2017/3/17.
 * 在办
 */
public class HandingFragment extends BaseFragment implements OfficialView, SwipeRefreshLayout.OnRefreshListener {

    private int start;


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OfficialPresenterImpl mOfficialPresenterImpl;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
    private FinishAdapter mFinishAdapter;


    public static HandingFragment newInstance() {
        HandingFragment fragment = new HandingFragment();
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_main_official_handing, container, false);
    }


    @Override
    protected void initData(boolean isSavedNull) {
        onRefresh();
    }

    @Override
    protected void initEvents() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        mFinishAdapter = new FinishAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mFinishAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        start = 1;
        String strEntity = createObj(start);
        mOfficialPresenterImpl.loadNoFinish(strEntity, start);
        start++;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
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
        if (result instanceof OfficialResponse) {
            mFinishAdapter.setDataList(((OfficialResponse) result).getIq().getQuery().getTable());
        }
    }

    public void onLoadMore() {
        String strEntity = createObj(start);
        mOfficialPresenterImpl.loadNoFinish(strEntity, start);
        start++;
    }

    @Override
    public void addData(Object result) {
        if (result instanceof OfficialResponse) {
            mFinishAdapter.addDataList(((OfficialResponse) result).getIq().getQuery().getTable());
        }
    }

    private String createObj(int page) {
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

    private class FinishAdapter extends RecyclerView.Adapter {

        private List<List<OfficialResponse.IqBean.QueryBean.TableBean.TableRowsBean>> data;

        private String[] tag = {"编号:", "描述:", "办理时间:"};

        public FinishAdapter() {

        }

        public void addDataList(OfficialResponse.IqBean.QueryBean.TableBean data) {
            int page = this.data.size();
            this.data.addAll(data.getTableRows());
            this.notifyItemRangeInserted(page, data.getTableRows().size());
        }

        public void setDataList(OfficialResponse.IqBean.QueryBean.TableBean data) {
            this.data = data.getTableRows();
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_finish, parent, false);
            return new FinishViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof FinishViewHolder) {
                ((FinishViewHolder) holder).NumberId.setText(tag[0] + data.get(position).get(1).getValue());
                ((FinishViewHolder) holder).desc.setText(tag[1] + data.get(position).get(4).getValue());
                ((FinishViewHolder) holder).deal.setText(tag[2] + data.get(position).get(3).getValue());
                ((FinishViewHolder) holder).Suc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UIUtils.getContext(), OfficialFinishDetail.class);
                        intent.putExtra("detailId", data.get(position).get(0).getValue());
                        UIUtils.startActivity(intent);
                    }
                });

            }
        }


        @Override
        public int getItemCount() {
            if (data != null) {
                return data.size();
            }
            return 0;
        }

        private class FinishViewHolder extends RecyclerView.ViewHolder {
            private RelativeLayout Suc;
            //private ImageView photo;
            private TextView NumberId;
            private TextView desc;
            private TextView deal;

            public FinishViewHolder(View itemView) {
                super(itemView);
                Suc = (RelativeLayout) itemView.findViewById(R.id.Suc);
                //photo = (ImageView) itemView.findViewById(R.id.photo);
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