package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.ExperiencePresenterImpl;
import com.zhicheng.api.view.ExperienceView;
import com.zhicheng.bean.http.ExperienceResponse;
import com.zhicheng.bean.json.ExperienceRequest;
import com.zhicheng.utils.common.UIUtils;

import org.w3c.dom.Text;

import java.util.List;

public class ExperienceExchangeLsitActivity extends BaseActivity implements ExperienceView,SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private TextView title_name;
    private LinearLayoutManager mLinearLayoutManager;
    private MyExpAdapter mAdapter;
    private int start;
    private ExperiencePresenterImpl mExperiencePresenterImpl;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_experience_exchange_lsit);
        mExperiencePresenterImpl = new ExperiencePresenterImpl(this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mAdapter = new MyExpAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText(getResources().getString(R.string.Experiment));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        start = 1;
        String strEntity = createObj(start);
        mExperiencePresenterImpl.loadExp(strEntity, start);
        start++;

    }

    private String createObj(int page) {
        ExperienceRequest mExperienceRequest = new ExperienceRequest();
        ExperienceRequest.IqBean iqb = new ExperienceRequest.IqBean();
        ExperienceRequest.IqBean.QueryBean qb = new ExperienceRequest.IqBean.QueryBean();
        iqb.setNamespace("ExperienceRequest");
        qb.setPage(page);
        qb.setRow(10);
        qb.setType("1");
        iqb.setQuery(qb);
        mExperienceRequest.setIq(iqb);
        Gson gson = new Gson();
        return gson.toJson(mExperienceRequest);
    }


    public void onLoadMore() {
        String strEntity = createObj(start);
        mExperiencePresenterImpl.loadExp(strEntity, start);
        start++;
    }

    @Override
    protected int getMenuID() {
        return R.menu.experience_add;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            UIUtils.startActivity(new Intent(this,ExperienceExchangeAddActivity.class));
        }
        return super.onOptionsItemSelected(item);
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
    public void queryExpResponse(Object result) {
        if(result instanceof ExperienceResponse){
            if(((ExperienceResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                mAdapter.setDataList(((ExperienceResponse) result).getIq().getQuery().getPrelogcon().getPrelogs());
            }else{
                showMessage(((ExperienceResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public void loadExpResponse(Object result) {
        if(result instanceof ExperienceResponse){
            if(((ExperienceResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                mAdapter.addDataList(((ExperienceResponse) result).getIq().getQuery().getPrelogcon().getPrelogs());
            }else{
                showMessage(((ExperienceResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public void upExpResponse(Object result) {

    }

    class MyExpAdapter extends RecyclerView.Adapter{
        private String[] str = {"标题:","内容:","发布人:"};
        private List<ExperienceResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogcon;

        public void setDataList(List<ExperienceResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogcon) {
            this.prelogcon = prelogcon;
            notifyDataSetChanged();
        }

        public void addDataList(List<ExperienceResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogs) {
            int page = prelogs.size();
            this.prelogcon.addAll(prelogs);
            this.notifyItemRangeInserted(page, prelogs.size());
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_experience_list_item,parent,false);
            return new MyExpViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyExpViewHolder){
                ((MyExpViewHolder) holder).exp_title.setText(str[0]+prelogcon.get(position).getTITLE_NAME());
                ((MyExpViewHolder) holder).exp_content.setText(str[1]+prelogcon.get(position).getCONTENT());
                ((MyExpViewHolder) holder).exp_sender.setText(str[2]+prelogcon.get(position).getPOSTED_BY());
                ((MyExpViewHolder) holder).exp_timer.setText(prelogcon.get(position).getPOSTED_TIME().substring(0,16));

                holder.itemView.setOnClickListener(view -> {
                    Intent intent = new Intent(holder.itemView.getContext(),ExperienceExchangeDetailActivity.class);
                    intent.putExtra("ID",prelogcon.get(position).getID());
                    UIUtils.startActivity(intent);
                });
            }
        }


        @Override
        public int getItemCount() {
            return prelogcon == null ? 0: prelogcon.size();
        }
    }

    class MyExpViewHolder extends  RecyclerView.ViewHolder{
        private TextView exp_title;
        private TextView exp_content;
        private TextView exp_sender;
        private TextView exp_timer;

        public MyExpViewHolder(View itemView) {
            super(itemView);

            exp_title = (TextView) itemView.findViewById(R.id.exp_title);
            exp_content = (TextView) itemView.findViewById(R.id.exp_content);
            exp_sender = (TextView) itemView.findViewById(R.id.exp_sender);
            exp_timer = (TextView) itemView.findViewById(R.id.exp_timer);

        }
    }
    public class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
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
