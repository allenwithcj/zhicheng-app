package com.zhicheng.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.zhicheng.R;
import com.zhicheng.api.presenter.OfficialBaseGridAddPresenter;
import com.zhicheng.api.presenter.impl.OfficialBaseGridAddPresenterImpl;
import com.zhicheng.api.view.OfficialBaseGridAddView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.JudgementLocationResponse;
import com.zhicheng.bean.http.PersonMsgMaResponse;
import com.zhicheng.common.Constant;
import com.zhicheng.ui.adapter.OfficialBaseGridAddAdapter;
import com.zhicheng.utils.BDLocationInit;
import com.zhicheng.utils.common.AnimationUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2017/3/1.
 */

public class OfficialBaseGridAdd extends BaseActivity implements OfficialBaseGridAddView {

    private RecyclerView mRecyclerView;
    private OfficialBaseGridAddAdapter mAdapter;
    private OfficialBaseGridAddPresenter mOfficialBaseGridAddPresenter;
    private AlertDialog dialog;
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;
    private String latitude,longitude,address;
    private PersonMsgMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean mPrelogsBean;
    private String hzId;
    private TextView title_name;

    public interface sendLocation {
        void onSendLocation(Map<String, String> maps);
    }

    private sendLocation mSendLocation;

    public void setSendLocation(sendLocation s) {
        if (this.mSendLocation == null) {
            this.mSendLocation = s;
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            OfficialBaseGridAddAdapter.ItemViewHolder holder = (OfficialBaseGridAddAdapter.ItemViewHolder) mRecyclerView
                    .getChildViewHolder(mRecyclerView.getChildAt(0));
            if (intent.getAction().equals("com.grid.type")) {
                String type = intent.getStringExtra("type");
                String value = intent.getStringExtra("value");
                if (type.equals(Constant.TYPE_RELATION)) {
                    if(value.equals("本人或户主")){
                        holder.grid_base_huzu_name.setText("");
                        holder.grid_base_huzu_name.setBackgroundColor(getResources().getColor(R.color.gray_text));
                        holder.grid_base_huzu_name.setClickable(false);
                    }else{
                        holder.grid_base_huzu_name.setBackgroundColor(getResources().getColor(R.color.white));
                        holder.grid_base_huzu_name.setClickable(true);
                    }
                    holder.grid_base_add_relation.setText(value);
                }else if (type.equals(Constant.TYPE_SEX)) {
                    holder.grid_base_add_sex.setText(value);
                } else if (type.equals(Constant.TYPE_POLICATIAL)) {
                    holder.grid_base_add_policatial.setText(value);
                } else if (type.equals(Constant.TYPE_DEGREE)) {
                    holder.grid_base_add_degree.setText(value);
                } else if (type.equals(Constant.TYPE_MARRIED)) {
                    holder.grid_base_add_married.setText(value);
                } else if (type.equals(Constant.TYPE_CLASSIFICATION)) {
                    holder.grid_base_add_rkfl.setText(value);
                } else if (type.equals(Constant.TYPE_REMARK1)) {
                    holder.grid_base_add_remark1.setText(value);
                } else if (type.equals(Constant.TYPE_REMARK2)) {
                    holder.grid_base_add_remark2.setText(value);
                } else if (type.equals(Constant.TYPE_HOBBY)) {
                    holder.grid_base_add_hobby.setText(value);
                }
            }else if(intent.getAction().equals("com.grid.huzu")){
                mPrelogsBean = intent.getParcelableExtra("value");
                hzId = mPrelogsBean.getID();
                if(intent.getBooleanExtra("type",true)){
                    holder.grid_base_huzu_name.setText(mPrelogsBean.getHZNAME());
                }else{
                    holder.grid_base_huzu_name.setText(mPrelogsBean.getNAME());
                }

            }
        }
    };

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official_basegrid_add);
        mLocationClient = new LocationClient(getApplicationContext());
        BDLocationInit.initLocation(mLocationClient);
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        title_name = (TextView)findViewById(R.id.title_name);
        //开启定位
        openGps();
        mOfficialBaseGridAddPresenter = new OfficialBaseGridAddPresenterImpl(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mAdapter = new OfficialBaseGridAddAdapter(mOfficialBaseGridAddPresenter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction("com.grid.type");
        mFilter.addAction("com.grid.huzu");
        registerReceiver(receiver, mFilter);
        title_name.setText(getResources().getString(R.string.grid_base_add_title));
        mAdapter.setWindow(getWindow());
        setSendLocation(maps -> {
            latitude = maps.get("latitude");
            longitude = maps.get("longitude");
            address = maps.get("address");

        });
    }


    @Override
    protected void initData() {

    }

    @Override
    protected int getMenuID() {
        return R.menu.official_grid_submit;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_submit:
                dialog = new AlertDialog.Builder(this, R.style.dialog)
                        .setView(R.layout.z_loading_view)
                        .setCancelable(false)
                        .create();
                dialog.show();
                //先判断是否处于所属网格
                mAdapter.submit(dialog,hzId,latitude,longitude,address);
                //mOfficialBaseGridAddPresenter.judgmentLocation(latitude,longitude);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openGps() {
        mLocationClient.start();
        mLocationClient.requestLocation();
    }

    @Override
    public void showMessage(String msg) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addDateResponse(Object result) {
        if (result instanceof CommonResponse) {
            if (((CommonResponse) result).getIq().getQuery().getErrorCode() == 0) {
                showMessage("新增成功");
                this.finish();
            } else {
                showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
            }
        }else if(result instanceof JudgementLocationResponse){
            if (((JudgementLocationResponse) result).isSuccess()) {
                //  mAdapter.submit(dialog,hzId,latitude,longitude,address);

            } else {

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Map<String, String> map = new HashMap<>();
            map.put("latitude", String.valueOf(bdLocation.getLatitude()));
            map.put("longitude", String.valueOf(bdLocation.getLongitude()));
            map.put("address", bdLocation.getAddrStr());
            map.put("desc", bdLocation.getLocationDescribe());

            mSendLocation.onSendLocation(map);
            mLocationClient.stop();
        }
    }
}
