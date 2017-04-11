package com.zhicheng.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.AnnoucementDetailsResponse;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.json.NewsDetailsRequest;
import com.zhicheng.common.URL;

import java.util.List;

/**
 * Created by Donson on 2017/1/22.
 */

public class CallTheCounDetailActivity extends BaseActivity implements OfficialView
        , ApiCompleteListener {

    private OfficialPresenterImpl mOfficialPresenterImpl;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NoticeAdapter mNoticeAdapter;
    private String mID;
    private AttrAdapter attrAdapter;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_call_the_count_detail);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mNoticeAdapter = new NoticeAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mNoticeAdapter);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);

    }

    @Override
    protected void initData() {
        mID = getIntent().getStringExtra("id");
        NewsDetailsRequest newsDetailsRequest = new NewsDetailsRequest();
        NewsDetailsRequest.IqBean iq = new NewsDetailsRequest.IqBean();
        NewsDetailsRequest.IqBean.QueryBean qb = new NewsDetailsRequest.IqBean.QueryBean();
        iq.setNamespace("NewsDetailsRequest");
        qb.setId(mID);
        qb.setMsgId("");
        qb.setRequestType("6");
        iq.setQuery(qb);
        newsDetailsRequest.setIq(iq);
        Gson gson = new Gson();
        mOfficialPresenterImpl.queryMewsDetail(gson.toJson(newsDetailsRequest));
    }


    @Override
    public void showMessage(String msg) {
        Snackbar.make(mToolbar, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    @Override
    public void refreshData(Object result) {
        if (result instanceof AnnoucementDetailsResponse) {
            mNoticeAdapter.setDataList(((AnnoucementDetailsResponse) result).getIq().getQuery());
            mToolbar.setTitle("通知公告详情");
        }
    }

    @Override
    public void addData(Object result) {

    }


    @Override
    public void onComplected(Object result) {

    }

    @Override
    public void onFailed(BaseResponse msg) {

    }

    private class NoticeAdapter extends RecyclerView.Adapter {
        private AnnoucementDetailsResponse.IqBean.QueryBean data;
        private String[] tag = {"标题:", "送办人:", "时间:", "内容:", "附件:"};

        public NoticeAdapter() {
            attrAdapter = new AttrAdapter();
        }

        public void setDataList(AnnoucementDetailsResponse.IqBean.QueryBean data) {
            this.data = data;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CallTheCounDetailActivity.this).inflate(R.layout.activity_home_notice_detail, parent, false);
            return new NoticeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof NoticeViewHolder) {
                if (data != null) {
                    ((NoticeViewHolder) holder).notice_title.setText(tag[0] + data.getTitle());
                    ((NoticeViewHolder) holder).notice_sendname.setText(tag[1] + data.getSendUser());
                    ((NoticeViewHolder) holder).notice_time.setText(tag[2] + data.getSendTime());
                    ((NoticeViewHolder) holder).notice_content.setText(Html.fromHtml(tag[3] + data.getContent()));
                    if (data.getAttachments() != null) {
                        if (data.getAttachments().size() != 0) {
                            ((NoticeViewHolder) holder).notice_attr.setText(tag[4]);
                            attrAdapter.setDateList(data.getAttachments());
                            attrAdapter.notifyDataSetChanged();
                        } else {
                            ((NoticeViewHolder) holder).notice_attr.setText(tag[4] + "无");
                        }
                    }

                }
            }
        }

        @Override
        public int getItemCount() {

            return 1;
        }
    }

    private class NoticeViewHolder extends RecyclerView.ViewHolder {

        private TextView notice_title;
        private TextView notice_sendname;
        private TextView notice_time;
        private TextView notice_content;
        private TextView notice_attr;
        private RecyclerView mRecyclerView;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            notice_title = (TextView) itemView.findViewById(R.id.notice_title);
            notice_sendname = (TextView) itemView.findViewById(R.id.notice_sendname);
            notice_time = (TextView) itemView.findViewById(R.id.notice_time);
            notice_content = (TextView) itemView.findViewById(R.id.notice_content);
            notice_attr = (TextView) itemView.findViewById(R.id.notice_attr);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.mRecycleView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            mRecyclerView.setAdapter(attrAdapter);
        }
    }

    class AttrAdapter extends RecyclerView.Adapter<AttrAdapter.AttrViewHolder> {
        private List<AnnoucementDetailsResponse.IqBean.QueryBean.AttachmentsBean> attachments;

        public void setDateList(List<AnnoucementDetailsResponse.IqBean.QueryBean.AttachmentsBean> attachments) {
            this.attachments = attachments;
        }

        @Override
        public AttrAdapter.AttrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_home_notice_attrment, parent, false);
            return new AttrViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AttrViewHolder holder, int position) {
            String html = "<a href='" + URL.HOST_URL_SERVER_ZHICHENG + attachments.get(position).getHref()
                    + "'>" + attachments.get(position).getName() + "</a>";
            CharSequence charSequence = Html.fromHtml(html);
            holder.attrmentName.setText(charSequence);
            holder.attrmentName.setMovementMethod(LinkMovementMethod.getInstance());
        }

        @Override
        public int getItemCount() {
            if (attachments != null) {
                return attachments.size();
            }
            return 0;
        }

        public class AttrViewHolder extends RecyclerView.ViewHolder {

            private TextView attrmentName;

            public AttrViewHolder(View itemView) {
                super(itemView);
                attrmentName = (TextView) itemView.findViewById(R.id.attrmentName);
            }
        }


    }


}