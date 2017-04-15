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
import com.zhicheng.ui.activity.OfficialNoFinishDetails;
import com.zhicheng.utils.common.UIUtils;

import java.util.List;

/**
 * Created by Donson on 2017/1/15.
 */

public class MyNewsFragment extends BaseFragment implements OfficialView, SwipeRefreshLayout.OnRefreshListener {
    private int start;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OfficialPresenterImpl mOfficialPresenterImpl;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NoFinishAdapter mNoFinishAdapter;
    private TextView title_name;

    public static MyNewsFragment newInstance() {
        MyNewsFragment fragment = new MyNewsFragment();
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_my_news, container, false);
    }

    @Override
    protected void initEvents() {
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        title_name = (TextView) mRootView.findViewById(R.id.title_name);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        mNoFinishAdapter = new NoFinishAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mRecyclerView.setAdapter(mNoFinishAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        title_name.setText(getResources().getString(R.string.news));
    }


    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(UIUtils.getContext(), msg, Toast.LENGTH_SHORT).show();
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
    protected void initData(boolean isSavedNull) {

    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof OfficialResponse) {
            mNoFinishAdapter.setDataList(((OfficialResponse) result).getIq().getQuery().getTable());
        }
    }

    @Override
    public void addData(Object result) {
        if (result instanceof OfficialResponse) {
            mNoFinishAdapter.addDataList(((OfficialResponse) result).getIq().getQuery().getTable());
        }
    }

    public void onLoadMore() {
        String strEntity = createObj(start);
        mOfficialPresenterImpl.loadNoFinish(strEntity, start);
        start++;
    }

    @Override
    public void onRefresh() {
        start = 1;
        String strEntity = createObj(start);
        mOfficialPresenterImpl.loadNoFinish(strEntity, start);
        start++;
    }


    private String createObj(int page) {
        Gson gson = new Gson();
        OfficialRequest officialRequest = new OfficialRequest();
        OfficialRequest.IqBean iqb = new OfficialRequest.IqBean();
        OfficialRequest.IqBean.QueryBean iqbQB = new OfficialRequest.IqBean.QueryBean();
        iqbQB.setRequestType("0");
        iqbQB.setPage(String.valueOf(page));
        iqbQB.setPerPageNums("10");
        iqbQB.setOrderBy("");
        iqbQB.setOrderType("");
        iqbQB.setIsReadJian("1");
        iqb.setQuery(iqbQB);
        iqb.setModel("0");
        iqb.setNamespace("ListRequest");
        officialRequest.setIq(iqb);
        return gson.toJson(officialRequest);
    }


    private class NoFinishAdapter extends RecyclerView.Adapter {

        private List<List<OfficialResponse.IqBean.QueryBean.TableBean.TableRowsBean>> data;
        private String[] tag = {"编号:", "描述:", "发起时间:"};

        public NoFinishAdapter() {

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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_nofinsh, parent, false);
            return new noFinishViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof noFinishViewHolder) {
                if (data != null) {
                    ((noFinishViewHolder) holder).NumberId.setText(tag[0] + data.get(position).get(1).getValue());
                    ((noFinishViewHolder) holder).desc.setText(tag[1] + data.get(position).get(4).getValue());
                    ((noFinishViewHolder) holder).deal.setText(tag[2] + data.get(position).get(3).getValue());
                    ((noFinishViewHolder) holder).noSuc.setOnClickListener(v -> {
                        Intent intent = new Intent(UIUtils.getContext(), OfficialNoFinishDetails.class);
                        intent.putExtra("detailId", data.get(position).get(0).getValue());
                        intent.putExtra("type", "news");
                        UIUtils.startActivity(intent);
                    });
                }
            }
        }

        @Override
        public int getItemCount() {
            if (data != null) {
                return data.size();
            }
            return 0;
        }

        private class noFinishViewHolder extends RecyclerView.ViewHolder {

            private RelativeLayout noSuc;
            private TextView NumberId;
            private TextView desc;
            private TextView deal;

            public noFinishViewHolder(View itemView) {
                super(itemView);
                noSuc = (RelativeLayout) itemView.findViewById(R.id.noSuc);
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
            if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                    lastVisibleItem + 1 == mNoFinishAdapter.getItemCount()) {
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
