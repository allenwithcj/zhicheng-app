package com.zhicheng.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.NoticeResponse;
import com.zhicheng.bean.json.NewsDetailsRequest;
import com.zhicheng.bean.json.OfficialRequest;

import java.util.List;

/**
 * Created by Donson on 2017/1/22.
 */

public class CallTheCounActivity extends BaseActivity implements OfficialView
        ,SwipeRefreshLayout.OnRefreshListener,ApiCompleteListener{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OfficialPresenterImpl mOfficialPresenterImpl ;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NoticeAdapter mNoticeAdapter;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mNoticeAdapter = new NoticeAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mRecyclerView.setAdapter(mNoticeAdapter);
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
        if (result instanceof NoticeResponse){
            mNoticeAdapter.setDataList(((NoticeResponse) result).getIq().getQuery().getTable());
            int num = ((NoticeResponse) result).getIq().getQuery().getTotalNums();
            if (num != 0){
                mToolbar.setTitle("通知公告("+num+")");
            }else {
                mToolbar.setTitle("通知公告");
            }
        }
    }

    @Override
    public void addData(Object result) {

        if (result instanceof NoticeResponse){
            mNoticeAdapter.addDataList(((NoticeResponse) result).getIq().getQuery().getTable());
        }
    }


    @Override
    public void onRefresh() {
        OfficialRequest mOfficialRequest=new OfficialRequest();
        OfficialRequest.IqBean iq = new OfficialRequest.IqBean();
        OfficialRequest.IqBean.QueryBean query=new OfficialRequest.IqBean.QueryBean();
        query.setPage("1");
        query.setOrderBy("");
        query.setOrderType("");
        query.setPerPageNums("10");
        query.setRequestType("6");
        iq.setQuery(query);
        iq.setNamespace("ListRequest");
        mOfficialRequest.setIq(iq);
        Gson gson=new Gson();
        String g=gson.toJson(mOfficialRequest);
        mOfficialPresenterImpl.LoadNotice(g);
    }

    @Override
    public void onComplected(Object result) {

    }

    @Override
    public void onFailed(BaseResponse msg) {

    }

    private class NoticeAdapter extends RecyclerView.Adapter{
        private List<List<NoticeResponse.IqBean.QueryBean.TableBean.TableRowsBean>> data;
        private String [] tag={"标题:","送办人:","时间:","内容:"};

        public NoticeAdapter() {

        }

        public void addDataList(NoticeResponse.IqBean.QueryBean.TableBean data){
            int page=this.data.size();
            this.data.addAll(data.getTableRows());
            this.notifyItemRangeInserted(page,data.getTableRows().size());
        }

        public void setDataList(NoticeResponse.IqBean.QueryBean.TableBean data){
            this.data = data.getTableRows();
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(CallTheCounActivity.this).inflate(R.layout.activity_home_notice,parent,false);
            return new NoticeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof NoticeViewHolder){
                if (data!=null){
                    ((NoticeViewHolder) holder).notice_title.setText(tag[0]+data.get(position).get(4).getValue());
                    ((NoticeViewHolder) holder).notice_sendname.setText(tag[1]+data.get(position).get(5).getValue());
                    ((NoticeViewHolder) holder).notice_time.setText(tag[2]+data.get(position).get(6).getValue());
                    ((NoticeViewHolder) holder).notice_content.setText(tag[3]+data.get(position).get(1).getValue());
                    ((NoticeViewHolder) holder).noSuc.setOnClickListener(view -> {
                        //查询公告附件
                        NewsDetailsRequest newsDetailsRequest = new NewsDetailsRequest();
                        NewsDetailsRequest.IqBean iq = new NewsDetailsRequest.IqBean();
                        NewsDetailsRequest.IqBean.QueryBean qb = new NewsDetailsRequest.IqBean.QueryBean();
                        iq.setNamespace("NewsDetailsRequest");
                        qb.setId(data.get(position).get(0).getValue());
                        qb.setMsgId("");
                        qb.setRequestType("1");
                        iq.setQuery(qb);
                        newsDetailsRequest.setIq(iq);
                        Gson gson = new Gson();
                        mOfficialPresenterImpl.queryMewsDetail(gson.toJson(newsDetailsRequest));

                    });
                }
            }
        }

        @Override
        public int getItemCount() {
            if (data != null){
                return data.size();
            }
            return 0;
        }
    }

    private class NoticeViewHolder extends RecyclerView.ViewHolder{

        private TextView notice_title;
        private TextView notice_sendname;
        private TextView notice_time;
        private TextView notice_content;
        private LinearLayout noSuc;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            notice_title=(TextView)itemView.findViewById(R.id.notice_title);
            notice_sendname=(TextView)itemView.findViewById(R.id.notice_sendname);
            notice_time=(TextView)itemView.findViewById(R.id.notice_time);
            notice_content=(TextView)itemView.findViewById(R.id.notice_content);
            noSuc = (LinearLayout)itemView.findViewById(R.id.noSuc);
        }
    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mNoticeAdapter.getItemCount()) {
                onRefresh();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    }
}