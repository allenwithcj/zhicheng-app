package com.zhicheng.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.alarm.LocationUpReciver;
import com.zhicheng.api.presenter.impl.OfficialBaseGridQueryPresenterImpl;
import com.zhicheng.api.view.OfficialBaseGridQueryView;
import com.zhicheng.bean.http.OfficialQueyResponse;
import com.zhicheng.bean.json.OfficialQueryRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.utils.common.UIUtils;

import java.util.List;


/**
 * Created by Donson on 2017/1/5.
 */

public class OfficialBaseGrid extends BaseActivity implements OfficialBaseGridQueryView,SwipeRefreshLayout.OnRefreshListener {
    private int start;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OfficialBaseGridQueryPresenterImpl mOfficialBaseGridQueryPresenterImpl;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private OfficialBaseGridAdapter mAdapter;
    private MenuItem item;
    private Drawable icon;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official_basegrid);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mOfficialBaseGridQueryPresenterImpl = new OfficialBaseGridQueryPresenterImpl(this);
        mAdapter = new OfficialBaseGridAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Constant.ISLOCATION){
                    mLocationDialog();
                }else{
                    finish();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(mToolbar, msg, Snackbar.LENGTH_SHORT).show();
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
        if (result instanceof OfficialQueyResponse){
            mAdapter.setDataList(((OfficialQueyResponse) result).getIq().getQuery()
            .getPreMsgcon().getPreMsgs());
            mToolbar.setTitle("网格基础数据");
        }
        mLocationDialog();

    }

    @Override
    public void addData(Object result) {
        if (result instanceof OfficialQueyResponse) {
            mAdapter.addDataList(((OfficialQueyResponse) result).getIq().getQuery()
                    .getPreMsgcon().getPreMsgs());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.official_grid, menu);
        item = menu.findItem(R.id.action_location);
        if(Constant.ISLOCATION){
            icon = getResources().getDrawable(R.drawable.ic_location_on_red_24dp);
        }else{
            icon = getResources().getDrawable(R.drawable.ic_location_on_black_24dp);
        }
        item.setIcon(icon);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent();
            intent.setClass(this, OfficialBaseGridAdd.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.action_location){
            mLocationDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void mLocationDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_grid_location_switch,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        TextView mLocation_title = (TextView) view.findViewById(R.id.mLocation_title);
        TextView mLocation_message = (TextView) view.findViewById(R.id.mLocation_message);
        builder.setCancelable(false);
        Intent intent = new Intent(OfficialBaseGrid.this,LocationUpReciver.class);
        intent.setAction(Constant.ALARM_ACTION);
        PendingIntent mPendingIntent = PendingIntent.getBroadcast(OfficialBaseGrid.this,0,intent,0);
        AlarmManager mArm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        if(Constant.ISLOCATION){
            mLocation_title.setText(getResources().getString(R.string.grid_location_close));
            mLocation_title.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,null,getResources().getDrawable(R.drawable.ic_location_on_red_24dp),null);
            mLocation_message.setText(getResources().getString(R.string.grid_location_close_message));
            builder.setNegativeButton("关闭上传", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Constant.ISLOCATION = false;
                    icon = getResources().getDrawable(R.drawable.ic_location_on_black_24dp);
                    item.setIcon(icon);
                    if(mArm != null){
                        mArm.cancel(mPendingIntent);
                        mPendingIntent.cancel();
                    }
                }
            });
            builder.show();
        }else{
            mLocation_title.setText(getResources().getString(R.string.grid_location_open));
            mLocation_title.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,null,getResources().getDrawable(R.drawable.ic_location_on_black_24dp),null);
            mLocation_message.setText(getResources().getString(R.string.grid_location_message));
            builder.setPositiveButton("开启上传", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    icon = getResources().getDrawable(R.drawable.ic_location_on_red_24dp);
                    item.setIcon(icon);
                    Constant.ISLOCATION = true;
                    mArm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime(), Constant.LOCATION_UP_TIME, mPendingIntent);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                }
            });
            builder.show();
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onLoadMore(){
        String strEntity = createObj(start);
        mOfficialBaseGridQueryPresenterImpl.query(strEntity,start);
        start++;
    }

    @Override
    public void onRefresh() {
        start = 1;
        String strEntity = createObj(start);
        mOfficialBaseGridQueryPresenterImpl.query(strEntity, start);
        start++;
    }

    private String createObj(int page) {
        Gson gson = new Gson();
        OfficialQueryRequest ofq = new OfficialQueryRequest();
        OfficialQueryRequest.IqBean iqb = new OfficialQueryRequest.IqBean();
        iqb.setNamespace("PersonMsgMaRequest");
        OfficialQueryRequest.IqBean.QueryBean qyb = new OfficialQueryRequest.IqBean.QueryBean();
        qyb.setType("3");
        qyb.setPage(String.valueOf(page));
        iqb.setQuery(qyb);
        ofq.setIq(iqb);
        return gson.toJson(ofq);
    }

    class OfficialBaseGridAdapter extends RecyclerView.Adapter {
        private List<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data;
        private String[] tag = {"姓名:", "户籍地址:"};

        public OfficialBaseGridAdapter() {

        }

        public void addDataList(List<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data) {
            int page = this.data.size();
            this.data.addAll(data);
            this.notifyItemRangeInserted(page, data.size());
        }

        public void setDataList(List<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data) {
            this.data = data;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(OfficialBaseGrid.this).inflate(R.layout.i_grid_base_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof ItemViewHolder){
                ((ItemViewHolder) holder).grid_base_add_name.setText(tag[0]+data.get(position).getName());
                ((ItemViewHolder) holder).grid_base_add_brithplace.setText(tag[1]+data.get(position).getDOMICILE());
                ((ItemViewHolder) holder).Suc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(UIUtils.getContext(),OfficialBaseGridDetail.class);
                        intent.putExtra("ID",data.get(position).getID());
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

        private class ItemViewHolder extends RecyclerView.ViewHolder {
            private TextView grid_base_add_name;
            private TextView grid_base_add_brithplace;
            private RelativeLayout Suc;

            public ItemViewHolder(View itemView) {
                super(itemView);
                grid_base_add_name = (TextView) itemView.findViewById(R.id.grid_base_add_name);
                grid_base_add_brithplace = (TextView) itemView.findViewById(R.id.grid_base_add_brithplace);
                Suc = (RelativeLayout) itemView.findViewById(R.id.Suc);
            }
        }
    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(Constant.ISLOCATION){
                mLocationDialog();
            }else{
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
