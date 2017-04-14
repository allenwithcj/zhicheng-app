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

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.presenter.impl.OfficialBaseGridDetailPresenterImpl;
import com.zhicheng.api.presenter.impl.OfficialBaseGridUpdatePresenterImpl;
import com.zhicheng.api.view.OfficialBaseGridDetailView;
import com.zhicheng.api.view.OfficialBaseGridUpdateView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.OfficialBaseGridDetailResponse;
import com.zhicheng.bean.json.OfficialQueryDetailRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.ui.adapter.OfficialBaseGridDeatilAdapter;

/**
 * Created by Donson on 2017/1/14.
 */

public class OfficialBaseGridDetail extends BaseActivity implements OfficialBaseGridDetailView, OfficialBaseGridUpdateView {
    private RecyclerView mRecyclerView;
    private OfficialBaseGridUpdatePresenterImpl mOfficialBaseGridUpdatePresenterImpl;
    private OfficialBaseGridDetailPresenterImpl mOfficialBaseGridDetailPresenterImpl;
    private OfficialBaseGridDeatilAdapter mAdapter;
    private String ID;
    private String USERID;
    private AlertDialog dialog;
    private DatabaseHelper mData;
    private TextView title_name;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            OfficialBaseGridDeatilAdapter.ItemViewHolder holder = (OfficialBaseGridDeatilAdapter.ItemViewHolder) mRecyclerView
                    .getChildViewHolder(mRecyclerView.getChildAt(0));
            if (intent.getAction().equals("com.grid.type")) {
                String value = intent.getStringExtra("value");
                String type = intent.getStringExtra("type");
                if (type.equals(Constant.TYPE_RELATION)) {
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
                }else if (type.equals(Constant.TYPE_REMARK2)) {
                    holder.grid_base_add_lessor_remark2.setText(value);
                } else if (type.equals(Constant.TYPE_HOBBY)) {
                    holder.grid_base_add_hobby.setText(value);
                }
            }
        }
    };

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official_basegrid_add_detail);
        mData = new DatabaseHelper();
        title_name = (TextView) findViewById(R.id.title_name);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOfficialBaseGridUpdatePresenterImpl = new OfficialBaseGridUpdatePresenterImpl(this);
        mAdapter = new OfficialBaseGridDeatilAdapter(mOfficialBaseGridUpdatePresenterImpl);
        ID = getIntent().getStringExtra("ID");
        mRecyclerView.setAdapter(mAdapter);
        mOfficialBaseGridDetailPresenterImpl = new OfficialBaseGridDetailPresenterImpl(this);
        IntentFilter mFilter = new IntentFilter("com.grid.type");
        registerReceiver(receiver, mFilter);
        title_name.setText(getResources().getString(R.string.grid_base_detail_title));
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {
        OfficialQueryDetailRequest odr = new OfficialQueryDetailRequest();
        OfficialQueryDetailRequest.IqBean irb = new OfficialQueryDetailRequest.IqBean();
        irb.setNamespace("PersonMsgMaRequest");
        OfficialQueryDetailRequest.IqBean.QueryBean qb = new OfficialQueryDetailRequest.IqBean.QueryBean();
        qb.setType("5");
        qb.setID(ID);
        irb.setQuery(qb);
        odr.setIq(irb);
        Gson gson = new Gson();
        mOfficialBaseGridDetailPresenterImpl.loadDetail(gson.toJson(odr));
    }

    @Override
    protected int getMenuID() {
        USERID = getIntent().getStringExtra("USERID");
        if (mData.getLocalConfig().getUserPost().equals("镇街受理员")) {
            return R.menu.official_grid_update;
        } else if (mData.getLocalConfig().getUserPost().equals("镇街单位业务员") ||
                mData.getLocalConfig().getUserPost().equals("镇街值班长")
                || mData.getLocalConfig().getUserPost().equals("镇街指挥长")
                || mData.getLocalConfig().getUserPost().equals("镇街领导")
                || mData.getLocalConfig().getUserPost().equals("办公人员")) {
            return super.getMenuID();

        } else if (mData.getLocalConfig().getUserPost().equals("网格长")
                || mData.getLocalConfig().getUserPost().equals("网格员")) {
            if (mData.getLocalConfig().getUserId().equals(USERID)) {
                return R.menu.official_grid_update;
            } else {
                return super.getMenuID();
            }
        }
        return super.getMenuID();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_update) {
            dialog = new AlertDialog.Builder(this, R.style.dialog)
                    .setView(R.layout.z_loading_view)
                    .setCancelable(false)
                    .create();
            dialog.show();
            mAdapter.update(ID, dialog);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(String msg) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void updateDateResponse(Object result) {
        if (result instanceof CommonResponse) {
            if (((CommonResponse) result).getIq().getQuery().getErrorCode() == 0) {
                showMessage("修改成功");
                this.finish();
            } else {
                showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
            }
        }

    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof OfficialBaseGridDetailResponse) {
            if (((OfficialBaseGridDetailResponse) result).getIq().getQuery().getErrorCode() == 0) {
                mAdapter.setData((OfficialBaseGridDetailResponse) result);
            } else {
                showMessage(((OfficialBaseGridDetailResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public void addData(Object result) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
