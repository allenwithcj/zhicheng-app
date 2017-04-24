package com.zhicheng.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.ExperiencePresenterImpl;
import com.zhicheng.api.view.ExperienceView;
import com.zhicheng.bean.http.ExperienceDetailResponse;
import com.zhicheng.bean.http.ExperienceResponse;
import com.zhicheng.bean.json.ExperienceRequest;
import com.zhicheng.common.URL;
import com.zhicheng.utils.common.UIUtils;

import java.util.ArrayList;
import java.util.List;

import cc.dagger.photopicker.PhotoPicker;

import static com.zhicheng.R.id.imgs;

public class ExperienceExchangeDetailActivity extends BaseActivity implements ExperienceView {
    private RecyclerView mRecyclerView;
    private TextView title_name;
    private LinearLayoutManager mLinearLayoutManager;
    private MyExpAdapter mAdapter;
    private ExperiencePresenterImpl mExperiencePresenterImpl;
    private String ID;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_experience_exchange_detail);
        mExperiencePresenterImpl = new ExperiencePresenterImpl(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mAdapter = new MyExpAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText(getResources().getString(R.string.Experiment));
    }

    @Override
    protected void initData() {
        String strEntity = createObj();
        mExperiencePresenterImpl.loadExpDetails(strEntity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        return super.onCreateOptionsMenu(menu);
    }

    private String createObj() {
        ID = getIntent().getStringExtra("ID");
        ExperienceRequest mExperienceRequest = new ExperienceRequest();
        ExperienceRequest.IqBean iqb = new ExperienceRequest.IqBean();
        ExperienceRequest.IqBean.QueryBean qb = new ExperienceRequest.IqBean.QueryBean();
        iqb.setNamespace("ExperienceRequest");
        qb.setID(ID);
        qb.setType("2");
        iqb.setQuery(qb);
        mExperienceRequest.setIq(iqb);
        Gson gson = new Gson();
        return gson.toJson(mExperienceRequest);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void queryExpResponse(Object result) {
        if(result instanceof ExperienceDetailResponse){
            if(((ExperienceDetailResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                mAdapter.setDataList(((ExperienceDetailResponse) result).getIq().getQuery().getPreMsg());
            }else{
                showMessage(((ExperienceDetailResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public void loadExpResponse(Object result) {

    }

    @Override
    public void upExpResponse(Object result) {

    }

    class MyExpAdapter extends RecyclerView.Adapter{
        private String[] str = {"发布时间:","发布人:","部门:"};
        private ExperienceDetailResponse.IqBean.QueryBean.PreMsgBean preMsgBean;

        public void setDataList(ExperienceDetailResponse.IqBean.QueryBean.PreMsgBean prelogcon) {
            this.preMsgBean = prelogcon;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_experience_detail,parent,false);
            return new MyExpDetailViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyExpDetailViewHolder){
                String sendeTime = preMsgBean.getPOSTED_TIME();
                ((MyExpDetailViewHolder) holder).exp_title.setText(preMsgBean.getTITLE_NAME());
                if(sendeTime != null && sendeTime.length()>2){
                    ((MyExpDetailViewHolder) holder).exp_time.setText(str[0]+sendeTime.substring(0,sendeTime.length()-2));
                }
                ((MyExpDetailViewHolder) holder).exp_sender.setText(str[1]+preMsgBean.getPOSTED_BY());
                ((MyExpDetailViewHolder) holder).exp_dept.setText(str[2]+preMsgBean.getDEPARTMENT());
                ((MyExpDetailViewHolder) holder).exp_content.setText(preMsgBean.getCONTENT());
                ((MyExpDetailViewHolder) holder).mRecyclerView.setAdapter(new ImageRecyclerAdapter(preMsgBean.getANNEX()));
            }
        }

        @Override
        public int getItemCount() {
            return preMsgBean == null ? 0:1;
        }
    }

    class MyExpDetailViewHolder extends RecyclerView.ViewHolder{
        private TextView exp_time;
        private TextView exp_title;
        private TextView exp_content;
        private TextView exp_sender;
        private TextView exp_dept;
        private RecyclerView mRecyclerView;

        public MyExpDetailViewHolder(View itemView) {
            super(itemView);

            exp_time = (TextView) itemView.findViewById(R.id.exp_time);
            exp_content = (TextView) itemView.findViewById(R.id.exp_content);
            exp_sender = (TextView) itemView.findViewById(R.id.exp_sender);
            exp_title = (TextView) itemView.findViewById(R.id.exp_title);
            exp_dept = (TextView) itemView.findViewById(R.id.exp_dept);
            mRecyclerView = (RecyclerView)itemView.findViewById(R.id.mRecycleView);
            GridLayoutManager mGridLayoutManager = new GridLayoutManager(itemView.getContext(), 3);
            mRecyclerView.setLayoutManager(mGridLayoutManager);

        }
    }

    private class ImageRecyclerAdapter extends RecyclerView.Adapter {
        private List<ExperienceDetailResponse.IqBean.QueryBean.PreMsgBean.ANNEXBean> annex;

        public ImageRecyclerAdapter(List<ExperienceDetailResponse.IqBean.QueryBean.PreMsgBean.ANNEXBean> annex) {
            this.annex = annex;
            this.notifyDataSetChanged();

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view, parent, false);
            return new imgViewHolder(root);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof imgViewHolder) {
                Glide.with(holder.itemView.getContext())
                        .load(URL.HOST_URL_SERVER_ZHICHENG + annex.get(position).getHref())
                        .placeholder(R.drawable.glide_loading)
                        .error(R.drawable.glide_failed)
                        .into(((imgViewHolder) holder).mImageView);
                ArrayList<String> paths = new ArrayList<String>();
                for (int i = 0; i < annex.size(); i++) {
                    paths.add(URL.HOST_URL_SERVER_ZHICHENG + annex.get(i).getHref());
                }
                ((imgViewHolder) holder).mImageView.setOnClickListener(view -> {
                    PhotoPicker.preview()
                            .paths(paths)
                            .currentItem(position)
                            .start((Activity) holder.itemView.getContext());
                });
            }
        }

        @Override
        public int getItemCount() {

            return annex == null ? 0: annex.size();
        }

        class imgViewHolder extends RecyclerView.ViewHolder {

            private ImageView mImageView;

            imgViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.img_content);
            }
        }
    }
}
