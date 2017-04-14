package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
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
import com.zhicheng.utils.common.UIUtils;

import java.util.List;

public class OfficialSended extends BaseActivity implements OfficialView,
        SwipeRefreshLayout.OnRefreshListener {

    private int start;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OfficialPresenterImpl mOfficialPresenterImpl;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
    private SendedAdapter mSendedAdapter;
    private TextView title_name;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_official_sended);
        title_name = (TextView) findViewById(R.id.title_name);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mSendedAdapter = new SendedAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mSendedAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
        title_name.setText(getResources().getString(R.string.baoliao_title));
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        return super.onCreateOptionsMenu(menu);
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

    @Override
    public void showMessage(String msg) {
        Toast.makeText(UIUtils.getContext(), msg, Toast.LENGTH_SHORT).show();
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
            if (((OfficialResponse) result).getIq().getQuery().getErrorCode().equals("0")) {
                mSendedAdapter.setDataList(((OfficialResponse) result).getIq().getQuery().getTable());
            } else {
                showMessage(((OfficialResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }


    @Override
    public void addData(Object result) {
        if (result instanceof OfficialResponse) {
            if (((OfficialResponse) result).getIq().getQuery().getErrorCode().equals("0")) {
                mSendedAdapter.addDataList(((OfficialResponse) result).getIq().getQuery().getTable());
            } else {
                showMessage(((OfficialResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    private String createObj(int page) {
        Gson gson = new Gson();
        OfficialRequest officialRequest = new OfficialRequest();
        OfficialRequest.IqBean iqb = new OfficialRequest.IqBean();
        OfficialRequest.IqBean.QueryBean iqbQB = new OfficialRequest.IqBean.QueryBean();
        iqbQB.setRequestType("4");
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

    private class SendedAdapter extends RecyclerView.Adapter {

        private List<List<OfficialResponse.IqBean.QueryBean.TableBean.TableRowsBean>> data;

        private String[] tag = {"编号:", "描述:", "发送时间:"};

        public SendedAdapter() {

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
            View view = LayoutInflater.from(OfficialSended.this).inflate(R.layout.activity_home_finish, parent, false);
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
                        Intent intent = new Intent(UIUtils.getContext(), OfficialSendDetails.class);
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

    public class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mSendedAdapter.getItemCount()) {
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
