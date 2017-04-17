package com.zhicheng.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.alarm.LocationUpReciver;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.service.HuZuService;
import com.zhicheng.api.presenter.impl.HuZuPresenterImpl;
import com.zhicheng.api.presenter.impl.OfficialBaseGridQueryPresenterImpl;
import com.zhicheng.api.view.HuZuView;
import com.zhicheng.api.view.OfficialBaseGridQueryView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.OfficialQueyResponse;
import com.zhicheng.bean.http.PersonMsgMaResponse;
import com.zhicheng.bean.http.PersonMsgResponse;
import com.zhicheng.bean.json.OfficialQueryRequest;
import com.zhicheng.bean.json.PersonMsgMaRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.common.URL;
import com.zhicheng.utils.BDLocationInit;
import com.zhicheng.utils.common.NotificationUtils;
import com.zhicheng.utils.common.PermissionUtils;
import com.zhicheng.utils.common.UIUtils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by Donson on 2017/1/5.
 */

public class OfficialBaseGrid extends BaseActivity implements OfficialBaseGridQueryView, SwipeRefreshLayout.OnRefreshListener {
    private int start;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OfficialBaseGridQueryPresenterImpl mOfficialBaseGridQueryPresenterImpl;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private OfficialBaseGridAdapter mAdapter;
    private MenuItem item;
    private Drawable icon;
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;
    private DatabaseHelper mData;
    private TextView title_name;
    public static OfficialBaseGrid instance;
    private Button ad_search;


    public static OfficialBaseGrid getInstance(){
        if(instance == null){
            instance = new OfficialBaseGrid();
        }
        return instance;
    }

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official_basegrid);
        instance = this;
        mData = new DatabaseHelper();
        title_name = (TextView) findViewById(R.id.title_name);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        ad_search = (Button)findViewById(R.id.ad_search);
        mLocationClient = new LocationClient(this);
        BDLocationInit.getInstance().initLocation(mLocationClient);
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mOfficialBaseGridQueryPresenterImpl = new OfficialBaseGridQueryPresenterImpl(this);
        mAdapter = new OfficialBaseGridAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
        title_name.setText(getResources().getString(R.string.grid_base_title));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLocationClient.isStarted()) {
                    mLocationDialog();
                } else {
                    finish();
                }
            }
        });

        ad_search.setOnClickListener(view -> {
            UIUtils.startActivity(new Intent(this,BaseGridSearchActivity.class));
        });
    }

    @Override
    protected void initData() {
        PermissionUtils.requestLocationPermission(this);
        if (mData.getLocalConfig() != null) {
            if (mData.getLocalConfig().getUserPost().equals("网格长")
                    || mData.getLocalConfig().getUserPost().equals("网格员")) {
                mLocationDialog();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
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
        if (result instanceof OfficialQueyResponse) {
            if (((OfficialQueyResponse) result).getIq().getQuery().getErrorCode().equals("0")) {
                getHuzus("1",((OfficialQueyResponse) result).getIq().getQuery()
                        .getPreMsgcon().getPreMsgs());
            } else {
                showMessage(((OfficialQueyResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    private void getHuzus(String s, List<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> preMsgs) {
        Gson gson = new Gson();
        PersonMsgMaRequest mPersonMsgMaRequest = new PersonMsgMaRequest();
        PersonMsgMaRequest.IqBean iqb = new PersonMsgMaRequest.IqBean();
        iqb.setNamespace("PersonMsgMaRequest");
        PersonMsgMaRequest.IqBean.QueryBean qyb = new PersonMsgMaRequest.IqBean.QueryBean();
        qyb.setType("6");
        qyb.setRow("1000");
        qyb.setPage("1");
        iqb.setQuery(qyb);
        mPersonMsgMaRequest.setIq(iqb);
        Observable.just(gson.toJson(mPersonMsgMaRequest))
                .flatMap(new Func1<String, Observable<Response<PersonMsgMaResponse>>>() {
                    @Override
                    public Observable<Response<PersonMsgMaResponse>> call(String s) {
                        HuZuService mHuZuService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, HuZuService.class);

                        return mHuZuService.queryHuzu(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<PersonMsgMaResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            Toast.makeText(getApplication(), "", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Response<PersonMsgMaResponse> mPersonMsgMaResponse) {
                        if (mPersonMsgMaResponse.isSuccessful()) {
                            if (mPersonMsgMaResponse.body() != null) {
                                List<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> ps = new ArrayList<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean>();
                                for (OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean mp:preMsgs){
                                    OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean mPreMsgsBean = new OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean();
                                    mPreMsgsBean.setNAME(mp.getNAME());
                                    mPreMsgsBean.setID(mp.getID());
                                    mPreMsgsBean.setDOMICILE(mp.getDOMICILE());
                                    mPreMsgsBean.setREPORTUSER(mp.getREPORTUSER());
                                    mPreMsgsBean.setHUZU(mp.getHUZU());
                                    for(PersonMsgMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean plg : mPersonMsgMaResponse.body().getIq().getQuery().getPrelogcon().getPrelogs()){
                                        if(plg.getID().equals(mp.getHUZU())){
                                            mPreMsgsBean.setHUZU(plg.getNAME());
                                        }
                                    }
                                    ps.add(mPreMsgsBean);
                                }
                                if(s.equals("1")){
                                    mAdapter.setDataList(ps);
                                }else{
                                    mAdapter.addDataList(ps);
                                }
                            }
                        } else {
                            Toast.makeText(getApplication(), mPersonMsgMaResponse.message(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void addData(Object result) {
        if (result instanceof OfficialQueyResponse) {
            if (((OfficialQueyResponse) result).getIq().getQuery().getErrorCode().equals("0")) {
                getHuzus("2",((OfficialQueyResponse) result).getIq().getQuery()
                        .getPreMsgcon().getPreMsgs());
            } else {
                showMessage(((OfficialQueyResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        if (mData.getLocalConfig() != null) {
            if (mData.getLocalConfig().getUserPost().equals("网格长")
                    || mData.getLocalConfig().getUserPost().equals("网格员")) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.official_grid, menu);
                item = menu.findItem(R.id.action_location);
                if (mLocationClient.isStarted()) {
                    icon = getResources().getDrawable(R.drawable.ic_location_on_black_24dp);
                } else {
                    icon = getResources().getDrawable(R.drawable.ic_location_on_white_24dp);
                }
                item.setIcon(icon);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            if(mLocationClient.isStarted()){
                Intent intent = new Intent();
                intent.setClass(this, OfficialBaseGridAdd.class);
                startActivity(intent);
            }else{
                Toast.makeText(OfficialBaseGrid.this,getResources().getString(R.string.grid_base_location_alert),Toast.LENGTH_SHORT).show();
            }
        } else if (item.getItemId() == R.id.action_location) {
            mLocationDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void mLocationDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_grid_location_switch, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        TextView mLocation_title = (TextView) view.findViewById(R.id.mLocation_title);
        TextView mLocation_message = (TextView) view.findViewById(R.id.mLocation_message);
        builder.setCancelable(false);
        Intent intent = new Intent(OfficialBaseGrid.this, LocationUpReciver.class);
        intent.setAction(Constant.ALARM_ACTION);
        PendingIntent mPendingIntent = PendingIntent.getBroadcast(OfficialBaseGrid.this, 0, intent, 0);
        AlarmManager mArm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (mLocationClient.isStarted()) {
            mLocation_title.setText(getResources().getString(R.string.grid_location_close));
            mLocation_title.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, getResources().getDrawable(R.drawable.ic_location_on_black_24dp), null);
            mLocation_message.setText(getResources().getString(R.string.grid_location_close_message));
            builder.setPositiveButton("关闭上传", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    if (mLocationClient.isStarted()) {
                        mLocationClient.stop();
                    }
                    icon = getResources().getDrawable(R.drawable.ic_location_on_white_24dp);
                    item.setIcon(icon);
                    if (mArm != null) {
                        mArm.cancel(mPendingIntent);
                        mPendingIntent.cancel();
                        new NotificationUtils(OfficialBaseGrid.this, 1).clear();
                    }
                    finish();
                }
            }).setNegativeButton("取消", null).show();
        } else {
            mLocation_title.setText(getResources().getString(R.string.grid_location_open));
            mLocation_title.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, getResources().getDrawable(R.drawable.ic_location_on_white_24dp), null);
            mLocation_message.setText(getResources().getString(R.string.grid_location_message));
            builder.setPositiveButton("开启上传", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //开启定位
                    openGps();
                    icon = getResources().getDrawable(R.drawable.ic_location_on_black_24dp);
                    item.setIcon(icon);
                    mArm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime(), Constant.LOCATION_UP_TIME, mPendingIntent);
                }
            }).setNegativeButton("取消", null).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onLoadMore() {
        String strEntity = createObj(start);
        mOfficialBaseGridQueryPresenterImpl.query(strEntity, start);
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
        private String[] tag = {"姓名:", "户主:", "户籍地址:"};
        private ItemViewHolder itemHolder;

        public OfficialBaseGridAdapter() {

        }

        public void addDataList(List<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data) {
            int page = data.size();
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
            this.itemHolder = (ItemViewHolder) holder;
            if (holder instanceof ItemViewHolder) {
                ((ItemViewHolder) holder).grid_base_add_name.setText(tag[0] + data.get(position).getNAME());
                ((ItemViewHolder) holder).grid_base_add_huzu.setText(tag[1] + data.get(position).getHUZU());
                ((ItemViewHolder) holder).grid_base_add_brithplace.setText(tag[2] + data.get(position).getDOMICILE());

                ((ItemViewHolder) holder).Suc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UIUtils.getContext(), OfficialBaseGridDetail.class);
                        intent.putExtra("ID", data.get(position).getID());
                        intent.putExtra("USERID", data.get(position).getREPORTUSER());
                        UIUtils.startActivity(intent);
                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return data == null ? 0:data.size();
        }

        private class ItemViewHolder extends RecyclerView.ViewHolder {
            private TextView grid_base_add_name;
            private TextView grid_base_add_huzu;
            private TextView grid_base_add_brithplace;
            private RelativeLayout Suc;

            public ItemViewHolder(View itemView) {
                super(itemView);
                grid_base_add_name = (TextView) itemView.findViewById(R.id.grid_base_add_name);
                grid_base_add_huzu = (TextView) itemView.findViewById(R.id.grid_base_add_huzu);
                grid_base_add_brithplace = (TextView) itemView.findViewById(R.id.grid_base_add_brithplace);
                Suc = (RelativeLayout) itemView.findViewById(R.id.Suc);
            }
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

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Constant.LATITUDE = String.valueOf(bdLocation.getLatitude());
            Constant.LONGITUDE = String.valueOf(bdLocation.getLongitude());
            Constant.ADDRESS = bdLocation.getAddrStr();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mLocationClient.isStarted()) {
                mLocationDialog();
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void openGps(){
        mLocationClient.start();
        mLocationClient.requestLocation();
    }

}
