package com.zhicheng.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.alarm.LocationUpReciver;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.Extend.model.BaseModelImpl;
import com.zhicheng.api.common.database.LocalConfig;
import com.zhicheng.api.presenter.impl.OfficialBaseGridQueryPresenterImpl;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.Extend.response.PersonMsgMaResponse;
import com.zhicheng.bean.json.PersonQueryRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.utils.BDLocationInit;
import com.zhicheng.utils.ClearEditText;
import com.zhicheng.utils.common.NotificationUtils;
import com.zhicheng.utils.common.PermissionUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Donson on 2017/1/5.
 *
 */

public class OfficialBaseGrid extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    //网络请求主导-------------------------------------
//    private OfficialBaseGridQueryPresenterImpl mOfficialBaseGridQueryPresenterImpl;
    private BaseModelImpl mBaseModelImpl;
    //网络请求主导-------------------------------------
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private OfficialBaseGridAdapter mAdapter;
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;
    //本地配置
    private LocalConfig mLocalConfig;
    //Toolbar标题栏
    private TextView title_name;
    public static OfficialBaseGrid instance;
    private Button ad_search;
    private ClearEditText mClearEditText;
//    private AlertDialog dialog;
    private PersonQueryRequest.IqBean.QueryBean mQb;
    private TextView search_count;
    //搜索页数
    private int page = 1;
    //网格页数
    private int start = 1;
    private boolean isTopsearch = false;
    //没有数据时候的view
    private TextView mNoDataSearchView;


    public static OfficialBaseGrid getInstance(){
        if(instance == null){
            instance = new OfficialBaseGrid();
        }
        return instance;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.grid.search")) {
                isTopsearch = true;
                page = 1;
                mQb = (PersonQueryRequest.IqBean.QueryBean) intent.getSerializableExtra("value");
                mAdapter.clearData();
//              personQueryRequest(page,mQb,false);
            }
        }


    };



    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official_basegrid);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.grid.search");
        registerReceiver(receiver, intentFilter);
        instance = this;
        //本地配置
        mLocalConfig = BaseApplication.getLocalConfig();
        //Toolbar标题栏
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText(getResources().getString(R.string.grid_base_title));
        //搜索栏
        mClearEditText = (ClearEditText)findViewById(R.id.mClearEditText);
        //刷新布局
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        ad_search = (Button)findViewById(R.id.ad_search);
        //百度地图-----------------------
        mLocationClient = new LocationClient(getApplicationContext());
        BDLocationInit.initLocation(mLocationClient);
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //百度地图-----------------------
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        //顶部搜索记录SearchViewCount
        search_count = (TextView)findViewById(R.id.search_count);
        //网络请求Presenter
//        mOfficialBaseGridQueryPresenterImpl = new OfficialBaseGridQueryPresenterImpl(this);
        mBaseModelImpl = BaseModelImpl.getBaseModelImpl();
        //本页网格适配器------------------------------------------
        mAdapter = new OfficialBaseGridAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //列表----------------------------------------------------
        mNoDataSearchView = (TextView) findViewById(R.id.noData);
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
        //右上角新增按钮
        ad_search.setOnClickListener(view -> {
            mNoDataSearchView.setVisibility(View.GONE);
            UIUtils.startActivity(new Intent(this,BaseGridSearchActivity.class));
        });
        //搜索框
        mClearEditText.setImeOptions(EditorInfo.IME_ACTION_SEND);
        mClearEditText.setImeActionLabel(getResources().getString(R.string.search), EditorInfo.IME_ACTION_SEND);
        mClearEditText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mClearEditText.setSingleLine(false);
        mClearEditText.setMaxLines(5);
        mClearEditText.setHorizontallyScrolling(false);
        mClearEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mClearEditText.getWindowToken(), 0);
//                dialog = new AlertDialog.Builder(this, R.style.dialog)
//                        .setView(R.layout.z_loading_view)
//                        .setCancelable(false)
//                        .create();
//                dialog.show();

                if (mClearEditText.getText().toString().isEmpty()) {
                    showMessage(getResources().getString(R.string.hint_search));
                } else {
                    mNoDataSearchView.setVisibility(View.GONE);
                    page = 1;
                    queryGrid(page,false);
                    page ++;
                }
                return true;
            }
            return false;
        });

        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().isEmpty()){
                    search_count.setVisibility(View.GONE);
                    mNoDataSearchView.setVisibility(View.GONE);
                    isQuery = false;
                    onRefresh();
                }
            }
        });
    }

    private void queryGrid(int page,boolean isAdd) {
        isQuery=true;
        PersonQueryRequest.IqBean.QueryBean query = new PersonQueryRequest.IqBean.QueryBean();
        query.setPkey(mClearEditText.getText().toString());
        query.setBegintime("");
        query.setEndtime("");
        query.setGrid("");
        query.setType("");
        query.setUserid("");
        query.setPmastkey("");
        BaseApplication.log_say("------------------------1111",isQuery+"");
        personQueryRequest(page,query,isAdd);
    }

    @Override
    protected void initData() {
        PermissionUtils.requestLocationPermission(this);
        if (mLocalConfig != null) {
            if (mLocalConfig.getUserPost().equals("网格长")
                    || mLocalConfig.getUserPost().equals("网格员")) {
                mLocationDialog();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    public void showMessage(String msg) {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        page = 1;
        start = 1;
        if(isTopsearch){
            personQueryRequest(page,mQb,false);
            page++;
        }else{
            if (search_count.getVisibility() == View.GONE){
                personMsgMaRequest("3",start,"","",false);
                start++;
            }else{
                queryGrid(page,false);
                page++;
            }

        }
    }

    public void onLoadMore() {
//        String strEntity = createObj(start);
//        mOfficialBaseGridQueryPresenterImpl.query(strEntity, start);
        if(isTopsearch){
            personQueryRequest(page,mQb,true);
            page++;
        }else{
            if (search_count.getVisibility() == View.GONE){
                personMsgMaRequest("3",start,"","",true);
                start++;
            }else{
                queryGrid(page,true);
                page++;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
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
                Toast.makeText(UIUtils.getContext(),getResources().getString(R.string.grid_base_location_alert),Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getMenuID() {
        if (mLocalConfig != null) {
            if (mLocalConfig.getUserPost().equals("网格长")
                    || mLocalConfig.getUserPost().equals("网格员")) {
                return R.menu.official_grid;
            }
        }
        return super.getMenuID();
    }

    /**
     *  请求创建函数
     *
     *  CreateObj PersonMsgMaRequest
     *  CreateObjQuery PersonQueryRequest
     *  superiorSearch PersonQueryRequest
     *
     *
     *
     *
     *  Gson gson = new Gson();
     *  OfficialQueryRequest ofq = new OfficialQueryRequest();
     *  OfficialQueryRequest.IqBean iqb = new OfficialQueryRequest.IqBean();
     *  iqb.setNamespace("PersonMsgMaRequest");
     *  OfficialQueryRequest.IqBean.QueryBean qyb = new OfficialQueryRequest.IqBean.QueryBean();
     *  qyb.setType("3");
     *  qyb.setPage(String.valueOf(page));
     *  iqb.setQuery(qyb);
     *  ofq.setIq(iqb);
     *
     *
     *
     *  PersonQueryRequest mPersonQueryRequest = new PersonQueryRequest();
     *  PersonQueryRequest.IqBean iqb = new PersonQueryRequest.IqBean();
     *  PersonQueryRequest.IqBean.QueryBean qb = new PersonQueryRequest.IqBean.QueryBean();
     *  iqb.setNamespace("PersonQueryRequest");
     *  qb.setPkey(mClearEditText.getText().toString());
     *  qb.setBegintime("");
     *  qb.setEndtime("");
     *  qb.setUserid("");
     *  qb.setGrid("");
     *  qb.setPagenum(page);
     *  iqb.setQuery(qb);
     *  mPersonQueryRequest.setIq(iqb);
     *  Gson gson = new Gson();
     *
     *
     */
    private boolean isHuZhu = true;
    private boolean isQuery = false;
    private PersonMsgMaResponse mPersonMsgMaResponse;
    private void personMsgMaRequest(String type,int page,String row,String ID,boolean isAdd) {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
        Map<String,String> query = new HashMap<>();
        query.put("type",type);
        query.put("page",String.valueOf(page));
        query.put("row",row);
        query.put("ID",ID);
        mBaseModelImpl.ServiceConnect(BaseModelImpl.parseRequest("PersonMsgMaRequest", query), PersonMsgMaResponse.class, new ApiCompleteListener() {
            @Override
            public void onComplected(Object result) {
                mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
                if (result instanceof PersonMsgMaResponse){
                    if (!isAdd){
                        if (isHuZhu){
                            isHuZhu = false;
                            mPersonMsgMaResponse = (PersonMsgMaResponse) result;
                            personMsgMaRequest("6",1,"1000","",isAdd);
                        }else {
                            BaseApplication.log_say("-------------->","11111111");
                            isHuZhu = true;
                            if (!isQuery){
                                BaseApplication.log_say("-------------->","22222222");
                                //遍历
                                for (PersonMsgMaResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean p : mPersonMsgMaResponse.getIq().getQuery().getPreMsgcon().getPreMsgs()){
                                    for (PersonMsgMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean m : ((PersonMsgMaResponse) result).getIq().getQuery().getPrelogcon().getPrelogs()){
                                        if (p.getHUZU().equals(m.getID())){
                                            p.setHUZU(m.getNAME());
                                        }
                                    }
                                }
                                //标题栏显示总体条数
                                int num = mPersonMsgMaResponse.getIq().getQuery().getPreMsgcon().getAllnum();
                                if(isTopsearch){
                                    if(num != 0){
                                        title_name.setText(getResources().getString(R.string.grid_base_title)+"(" + num + ")");
                                        String str = "共搜索网格基础数据<font color=#af3428>" + num + "条</font>";
                                        search_count.setVisibility(View.VISIBLE);
                                        search_count.setText(Html.fromHtml(str));
                                    }else{
                                        title_name.setText(getResources().getString(R.string.grid_base_title)+"(0)");
                                        showMessage("未搜索到数据");
                                    }
                                }else{
                                    if(num != 0){
                                        title_name.setText(getResources().getString(R.string.grid_base_title)+"(" + num + ")");
                                    }else{
                                        title_name.setText(getResources().getString(R.string.grid_base_title)+"(0)");
                                    }
                                }

                                //----------------------
                                mAdapter.setDataList(mPersonMsgMaResponse.getIq().getQuery().getPreMsgcon().getPreMsgs());
                            }else {
                                BaseApplication.log_say("-------------->","33333333");
                                //query 遍历
                                for (PersonMsgMaResponse.IqBean.QueryBean.PersonlistconBean.PrelogsBean p : mPersonMsgMaResponse.getIq().getQuery().getPersonlistcon().getPrelogs()){
                                    for (PersonMsgMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean m : ((PersonMsgMaResponse) result).getIq().getQuery().getPrelogcon().getPrelogs()){
                                        if (p.getHUZU().equals(m.getID())){
                                            p.setHUZU(m.getNAME());
                                        }
                                    }
                                }
                                //----------------------
//                                if (dialog != null && dialog.isShowing()) {
//                                    dialog.dismiss();
//                                }
                                int num1 = mPersonMsgMaResponse.getIq().getQuery().getPersonlistcon().getAllnum();
                                if(num1 != 0) {
                                    title_name.setText(getResources().getString(R.string.grid_base_title)+"(" + num1 + ")");
                                    String str = "共搜索网格基础数据<font color=#af3428>" + num1 + "条</font>";
                                    search_count.setVisibility(View.VISIBLE);
                                    search_count.setText(Html.fromHtml(str));
                                }else {
                                    title_name.setText(getResources().getString(R.string.grid_base_title)+"(0)");
                                    showMessage("未搜索到数据");
                                }
                                mAdapter.setDataListType2(mPersonMsgMaResponse.getIq().getQuery().getPersonlistcon().getPrelogs());
                            }
                        }
                    }else {
                        if (isHuZhu){
                            isHuZhu = false;
                            mPersonMsgMaResponse = (PersonMsgMaResponse) result;
                            personMsgMaRequest("6",1,"1000","",true);
                        }else {
                            isHuZhu = true;
                            BaseApplication.log_say("-------------->","444444");
                            if (!isQuery){
                                BaseApplication.log_say("-------------->","5555555");
                                    for (PersonMsgMaResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean p : mPersonMsgMaResponse.getIq().getQuery().getPreMsgcon().getPreMsgs()){
                                        for (PersonMsgMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean m : ((PersonMsgMaResponse) result).getIq().getQuery().getPrelogcon().getPrelogs()){
                                            if (p.getHUZU().equals(m.getID())){
                                                p.setHUZU(m.getNAME());
                                            }
                                        }
                                    }

                                mAdapter.addDataList(mPersonMsgMaResponse.getIq().getQuery().getPreMsgcon().getPreMsgs());
                            }else {
                                BaseApplication.log_say("-------------->","666666");
                                for (PersonMsgMaResponse.IqBean.QueryBean.PersonlistconBean.PrelogsBean p : mPersonMsgMaResponse.getIq().getQuery().getPersonlistcon().getPrelogs()){
                                    for (PersonMsgMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean m : ((PersonMsgMaResponse) result).getIq().getQuery().getPrelogcon().getPrelogs()){
                                        if (p.getHUZU().equals(m.getID())){
                                            p.setHUZU(m.getNAME());
                                        }
                                    }
                                }
                                mAdapter.addDataListType2(mPersonMsgMaResponse.getIq().getQuery().getPersonlistcon().getPrelogs());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailed(BaseResponse msg) {
                mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
                showMessage("返回数据出错");
            }
        });
    }

    private void personQueryRequest(int page,Object obj,boolean isAdd){

        /**
         * pkey : xxxx
         * userid : 宋莹
         * grid : 皇家湾社区第一网格
         * begintime : 2017-3-30 14:44:50
         * endtime : 2017-4-5 10:13:26
         * pageNum : 1
         */

        if (obj instanceof PersonQueryRequest.IqBean.QueryBean){
            mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
            Map<String,Object> query = new HashMap<>();
            query.put("pkey",((PersonQueryRequest.IqBean.QueryBean) obj).getPkey());
            query.put("userid",((PersonQueryRequest.IqBean.QueryBean) obj).getUserid());
            query.put("grid",((PersonQueryRequest.IqBean.QueryBean) obj).getGrid());
            query.put("begintime",((PersonQueryRequest.IqBean.QueryBean) obj).getBegintime());
            query.put("endtime",((PersonQueryRequest.IqBean.QueryBean) obj).getEndtime());
            query.put("pageNum",page);
            query.put("type",((PersonQueryRequest.IqBean.QueryBean) obj).getType());
            query.put("pmastkey",((PersonQueryRequest.IqBean.QueryBean) obj).getPmastkey());

            mBaseModelImpl.ServiceConnect(BaseModelImpl.parseRequest("PersonQueryRequest", query), PersonMsgMaResponse.class, new ApiCompleteListener() {
                @Override
                public void onComplected(Object result) {
                    mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
                    if (result instanceof PersonMsgMaResponse){
                        if(((PersonMsgMaResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                            if (((PersonMsgMaResponse) result).getIq().getQuery().getPersonlistcon().getPrelogs().size() != 0){
                                int num2 = ((PersonMsgMaResponse) result).getIq().getQuery().getPersonlistcon().getAllnum();
                                isHuZhu = false;
                                mPersonMsgMaResponse = (PersonMsgMaResponse) result;
                                isQuery = true;
                                personMsgMaRequest("6",1,"1000","",isAdd);
                                if(num2 != 0) {
                                    title_name.setText(getResources().getString(R.string.grid_base_title)+"(" + num2 + ")");
                                    String str = "共搜索网格基础数据<font color=#af3428>" + num2 + "条</font>";
                                    search_count.setVisibility(View.VISIBLE);
                                    search_count.setText(Html.fromHtml(str));
                                }else {
                                    title_name.setText(getResources().getString(R.string.grid_base_title)+"(0)");
                                    showMessage("未搜索到数据");
                                }

                            }else {
                                if(!isAdd){
                                    mAdapter.clearData();
                                    search_count.setVisibility(View.GONE);
                                    mNoDataSearchView.setVisibility(View.VISIBLE);
                                }
                                title_name.setText(getResources().getString(R.string.grid_base_title)+"(0)");
                                BaseApplication.log_say("---------------->",isQuery+"");
                                showMessage("没有更多数据");
                            }
                        }else{
                            showMessage(((PersonMsgMaResponse) result).getIq().getQuery().getErrorMessage());
                        }

                    }
                }

                @Override
                public void onFailed(BaseResponse msg) {
                    mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
                    showMessage("返回数据出错");
                }
            });
        }else {
            showMessage("数据错误");
        }
    }

    /**
     *
     * 请求地理位置录入Dialog
     *
     */

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
                    if (mArm != null) {
                        mArm.cancel(mPendingIntent);
                        mPendingIntent.cancel();
                        new NotificationUtils(OfficialBaseGrid.this, 1).clear();
                    }
                    finish();
                }
            }).setNegativeButton("取消",null).show();
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
                    mArm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime(), Constant.LOCATION_UP_TIME, mPendingIntent);
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).show();
        }
    }

    /**
     * Adapter
     *
     * 表格列表适配器
     *
     */

    class OfficialBaseGridAdapter extends RecyclerView.Adapter {

        /**
         * private List<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data;
         *
         *  public void addDataList(List<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data) {
         *  int page = data.size();
         *  this.data.addAll(data);
         *  this.notifyItemRangeInserted(page, data.size());
         *  }
         *
         *
         *  public void setDataList(List<OfficialQueyResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data) {
         *  this.data = data;
         *  this.notifyDataSetChanged();
         *  }
         *
         *
         */

        private boolean isSearch = false;
        private List<PersonMsgMaResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data;
        private List<PersonMsgMaResponse.IqBean.QueryBean.PersonlistconBean.PrelogsBean> dataType2;
        private String[] tag = {"姓名:", "户主:", "户籍地址:"};

        public OfficialBaseGridAdapter() {
            data = new ArrayList<>();
            dataType2 = new ArrayList<>();
        }

        public void addDataList(List<PersonMsgMaResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data) {
            isSearch = false;
            int page = this.data.size();
//            BaseApplication.log_say("----------->","page:"+page);
            this.data.addAll(data);
            this.notifyItemRangeInserted(page, data.size());
//            this.notifyDataSetChanged();
        }

        public void setDataList(List<PersonMsgMaResponse.IqBean.QueryBean.PreMsgconBean.PreMsgsBean> data) {
            isSearch = false;
//            BaseApplication.log_say("----------->","page:"+page);
            this.data = data;
            this.notifyDataSetChanged();
        }

        public void clearData(){
            this.data.clear();
            this.dataType2.clear();
            this.notifyDataSetChanged();
        }

        public void addDataListType2(List<PersonMsgMaResponse.IqBean.QueryBean.PersonlistconBean.PrelogsBean> data) {
            isSearch = true;
            int page = dataType2.size();
            this.dataType2.addAll(data);
            this.notifyItemRangeInserted(page, data.size());
        }

        public void setDataListType2(List<PersonMsgMaResponse.IqBean.QueryBean.PersonlistconBean.PrelogsBean> data) {
            isSearch = true;
            this.dataType2 = data;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(OfficialBaseGrid.this).inflate(R.layout.i_grid_base_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ItemViewHolder) {
                if (!isSearch){
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
                }else {
                    ((ItemViewHolder) holder).grid_base_add_name.setText(tag[0] + dataType2.get(position).getNAME());
                    ((ItemViewHolder) holder).grid_base_add_huzu.setText(tag[1] + dataType2.get(position).getHUZU());
                    ((ItemViewHolder) holder).grid_base_add_brithplace.setText(tag[2] + dataType2.get(position).getDOMICILE());

                    ((ItemViewHolder) holder).Suc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UIUtils.getContext(), OfficialBaseGridDetail.class);
                            intent.putExtra("ID", dataType2.get(position).getID());
                            intent.putExtra("USERID", dataType2.get(position).getREPORTUSER());
                            UIUtils.startActivity(intent);
                        }
                    });
                }

            }
        }

        @Override
        public int getItemCount() {
            if (!isSearch){
                return data == null ? 0:data.size();
            }else {
                return dataType2 == null ? 0:dataType2.size();
            }
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
    /**
     *
     * 上拉加载更多
     *
     * RecyclerViewScrollDetector
     *
     */
    public class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && !mSwipeRefreshLayout.isRefreshing() && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    }

    /**
     * 地点监听
     *
     * 地点位置监听
     *
     */

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Constant.LATITUDE = String.valueOf(bdLocation.getLatitude());
            Constant.LONGITUDE = String.valueOf(bdLocation.getLongitude());
            Constant.ADDRESS = bdLocation.getAddrStr();
        }
    }

    /**
     * 点击事件拦截
     *
     * @param keyCode keyCode
     * @param event event
     * @return boolean
     */
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

    /**
     * 百度地图
     *
     * 开启监听，请地理位置
     */

    public void openGps(){
        if (mLocationClient != null){
            mLocationClient.start();
            mLocationClient.requestLocation();
        }else {
            Snackbar.make(mToolbar,"地点出错",Snackbar.LENGTH_SHORT).show();
        }
    }


}
