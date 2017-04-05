package com.zhicheng.ui.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.bean.json.UpFileRequest;
import com.zhicheng.module.imageloader.GlideImageLoader;
import com.zhicheng.ui.adapter.OfficialDynamicAdapter;
import com.zhicheng.utils.BDLocationInit;
import com.zhicheng.utils.common.AnimationUtils;
import com.zhicheng.utils.common.PermissionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cc.dagger.photopicker.PhotoPicker;
import cc.dagger.photopicker.picker.PhotoFilter;

/**
 * Created by Donson on 2017/1/15.
 */

public class OfficialWorkDynamic extends BaseActivity implements OfficialView,SwipeRefreshLayout.OnRefreshListener{

    private int start;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private OfficialPresenterImpl mOfficialPresenterImpl;
    private OfficialDynamicAdapter mOfficialDynamicAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    //新增动态
    private ViewGroup parentView;
    private PopupWindow mPopupWindow;
    private TextView mBtn;
    private DealAdapter mDealAdapter;
    private PhotoFilter filter;
    private ArrayList<String> mImagePath;
    private AlertDialog dialog;
    //定位
    //定位信息
    private LocationClient mLocationClient = null;
    private mLocationListener mLocation;
    private String mLocationSite;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official_basegrid);
        parentView = (ViewGroup) findViewById(android.R.id.content).getRootView();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        mOfficialDynamicAdapter = new OfficialDynamicAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mOfficialDynamicAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);

        //处理
        mDealAdapter = new DealAdapter();
        mImagePath = new ArrayList<>();
        PhotoPicker.init(new GlideImageLoader(),null);
        filter = PhotoFilter.build();
        filter.showGif(false);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void showMessage(String msg) {
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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
        if (result instanceof OfficialWorkDynamicList){
            if (((OfficialWorkDynamicList) result).getIq().getQuery().getErrorCode().equals("0")) {
                mOfficialDynamicAdapter.setDataList(((OfficialWorkDynamicList) result).getIq().getQuery().getPrelogcon().getPrelogs());
            }else{
                showMessage(((OfficialWorkDynamicList) result).getIq().getQuery().getErrorMessage());
            }
        }else if(result instanceof CommonResponse){
            if (((CommonResponse) result).getIq().getQuery().getErrorCode() == 0){
                mImagePath.clear();
                showMessage("发送动态成功");
                if (mPopupWindow != null && mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
                fresh();
            }else {
                mBtn.setText("处理");
                mBtn.setClickable(true);
                showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    private void onLoadMore(){
        String strEntity = createObj(start,"2");
        mOfficialPresenterImpl.loadDynamic(strEntity,start);
        start++;
    }

    @Override
    public void addData(Object result) {
        if (result instanceof OfficialWorkDynamicList){
            if (((OfficialWorkDynamicList) result).getIq().getQuery().getErrorCode().equals("0")) {
                mOfficialDynamicAdapter.addDataList(((OfficialWorkDynamicList) result).getIq().getQuery().getPrelogcon().getPrelogs());
            }else{
                showMessage(((OfficialWorkDynamicList) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public void onRefresh() {
        fresh();
    }

    private void fresh() {
        start = 1;
        String strEntity = createObj(start,"2");
        mOfficialPresenterImpl.loadDynamic(strEntity,start);
        start++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("工作动态");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_span){
            //开始定位
            mLocationClient = new LocationClient(this);
            BDLocationInit.getInstance().initLocation(mLocationClient);
            mLocation = new mLocationListener();
            mLocationClient.registerLocationListener(mLocation);
            if(!mLocationClient.isStarted()){
                mLocationClient.start();
            }else{
                mLocationClient.requestLocation();
            }
            //发送动态，弹出POPView形式
            //mOfficialPresenterImpl.upDynamic();已经写好了上传动态
            View pop_view = getLayoutInflater().inflate(R.layout.c_deal,parentView,false);
            mBtn = (TextView) pop_view.findViewById(R.id.btnDeal);
            EditText mEdit = (EditText) pop_view.findViewById(R.id.suggestion);
            String guid = UUID.randomUUID().toString();
            mBtn.setOnClickListener(view -> {
                dialog = new AlertDialog.Builder(this,R.style.dialog)
                        .setView(R.layout.z_loading_view)
                        .setCancelable(false)
                        .create();
                dialog.show();
                Gson gson = new Gson();
                UpFileRequest uf = new UpFileRequest();
                UpFileRequest.IqBean ufIB = new UpFileRequest.IqBean();
                UpFileRequest.IqBean.QueryBean ufIBQB = new UpFileRequest.IqBean.QueryBean();
                ufIBQB.setAttachmentGUID(guid);
                ufIB.setQuery(ufIBQB);
                ufIB.setNamespace("AttachmentUpdateRequest");
                uf.setIq(ufIB);
                String jFile = gson.toJson(uf);
                if (null != mImagePath){
                    if(mImagePath.size() != 0){
                        mOfficialPresenterImpl.upDynamic(jFile,mImagePath,mEdit.getText().toString(),mLocationSite,guid);
                        mBtn.setText("处理中...");
                        mBtn.setClickable(false);
                    }else {
                        dialog.dismiss();
                        Toast.makeText(this,"请选择上传图片",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            RecyclerView dealRecyclerView = (RecyclerView) pop_view.findViewById(R.id.mRecycleView);
            dealRecyclerView.setLayoutManager(new GridLayoutManager(OfficialWorkDynamic.this,3));
            dealRecyclerView.setAdapter(mDealAdapter);
            int width = getWindowManager().getDefaultDisplay().getWidth();
            if (null != mPopupWindow){
                mPopupWindow.dismiss();
            }else {
                mPopupWindow = new PopupWindow(pop_view,width - width/4, WindowManager.LayoutParams.WRAP_CONTENT,true);
                mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                mPopupWindow.setOnDismissListener(() -> {
                    AnimationUtils.darkBackgroundColor(getWindow(),1f);
                });
            }
            mPopupWindow.showAtLocation(parentView, Gravity.CENTER,0,0);
            AnimationUtils.darkBackgroundColor(getWindow(),0.4f);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getMenuID() {
        return R.menu.menu_workspan;
    }

    //创建请求json
    private String createObj(int page,String type){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        map1.put("namespace","PersonalDynamicRequest");
        if (type.equals("2")){
            map2.put("type",type);
            map2.put("page",String.valueOf(page));
        }else {
            map2.put("type",type);
            map2.put("id","");//GUID唯一id
            map2.put("cont","");//内容
            map2.put("attguid","");//附件guid
            map2.put("location","");//位置
        }
        map1.put("query",map2);
        map.put("iq",map1);
        return gson.toJson(map);
    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mOfficialDynamicAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    }

    private class DealAdapter extends RecyclerView.Adapter{

        private List<String> mData;

        public DealAdapter(){
            mData = new ArrayList<>();
        }

        public void addData(List<String> d){
            this.mData = d;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view,parent,false);
            return new DealViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof DealViewHolder){
                if (position == mData.size()){
                    Glide.with(holder.itemView.getContext())
                            .load(R.mipmap.icon_addpic_unfocused)
                            .into(((DealViewHolder) holder).mImageView);
                    ((DealViewHolder) holder).mImageView.setOnClickListener(v -> {
                        PermissionUtils.requestCameraPermission(OfficialWorkDynamic.this);
                        PermissionUtils.requestStoragePermission(OfficialWorkDynamic.this);
                        PhotoPicker.load()
                                .filter(filter) // 照片属性过滤
                                .gridColumns(4) // 照片列表显示列数
                                .showCamera(true)
                                .multi()
                                .maxPickSize(9) // 最大选择数
                                .selectedPaths(mImagePath) // 已选择的照片地址
                                .start(OfficialWorkDynamic.this); // 从Fragment、Activity中启动
                    });
                }else {
                    Glide.with(holder.itemView.getContext())
                            .load("file://" + mData.get(position))
                            .into(((DealViewHolder) holder).mImageView);
                }
            }
        }

        @Override
        public int getItemCount() {
            return mData.size()+1;
        }
    }

    private class DealViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public DealViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img_content);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPicker.REQUEST_SELECTED){
            if (resultCode == RESULT_OK){
                List<String> mData = data.getStringArrayListExtra(PhotoPicker.EXTRA_RESULT);
                mImagePath = (ArrayList<String>) mData;
                mDealAdapter.addData(mData);
            }
        }
    }



    private class mLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            mLocationSite = bdLocation.getAddrStr();
            if (mLocationSite == null ||mLocationSite.equals("")){
                mLocationSite = "地点未知";
            }
            mLocationClient.stop();
        }
    }

    @Override
    protected void onDestroy() {
        if(mLocationClient != null){
            if (mLocationClient.isStarted()){
                mLocationClient.stop();
            }
        }
        super.onDestroy();
    }
}
