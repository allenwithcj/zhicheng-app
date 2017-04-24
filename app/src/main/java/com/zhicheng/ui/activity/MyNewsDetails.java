package com.zhicheng.ui.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.MyNewsPresenterImpl;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.MyNewsView;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.bean.json.FormSendDoRequest;
import com.zhicheng.bean.json.MyNewsRequest;
import com.zhicheng.bean.json.OfficialDetailRequest;
import com.zhicheng.bean.json.UpFileRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.common.URL;
import com.zhicheng.module.imageloader.GlideImageLoader;
import com.zhicheng.ui.adapter.OfficialNoFinishDetailAdapter;
import com.zhicheng.utils.common.AnimationUtils;
import com.zhicheng.utils.common.PermissionUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cc.dagger.photopicker.PhotoPicker;
import cc.dagger.photopicker.picker.PhotoFilter;


/**
 * Created by Donson on 2017/1/4.
 */

public class MyNewsDetails extends BaseActivity implements OfficialView,MyNewsView {

    private RecyclerView mRecyclerView;
    private OfficialNoFinishDetailAdapter mAdapter;
    private OfficialPresenterImpl mOfficialDetail;
    private MyNewsPresenterImpl mMyNewsPresenterImpl;
    private DealAdapter mDealAdapter;
    private PhotoFilter filter;
    private ArrayList<String> mImagePath;
    private ArrayList<String> mShowPhoto;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_selectimg);
        mMyNewsPresenterImpl = new MyNewsPresenterImpl(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OfficialNoFinishDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mOfficialDetail = new OfficialPresenterImpl(this);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_clear));
        mDealAdapter = new DealAdapter();
        mImagePath = new ArrayList<>();
        mShowPhoto = new ArrayList<>();
        PhotoPicker.init(new GlideImageLoader(), null);
        filter = PhotoFilter.build();
        filter.showGif(false);
    }


    @Override
    protected void initData() {
        OfficialDetailRequest odr = new OfficialDetailRequest();
        OfficialDetailRequest.IqBean odrIB = new OfficialDetailRequest.IqBean();
        OfficialDetailRequest.IqBean.QueryBean odrIBQB = new OfficialDetailRequest.IqBean.QueryBean();
        odrIB.setNamespace("CollaborationDetailsRequest");
        odrIB.setModel("0");
        odrIBQB.setId(getIntent().getStringExtra("detailId"));
        odrIBQB.setRequestType("0");
        odrIB.setQuery(odrIBQB);
        odr.setIq(odrIB);
        Gson gson = new Gson();
        String strEntity = gson.toJson(odr);
        mOfficialDetail.loadDetail(strEntity);
        mAdapter.setShowPhoto(position -> {
            PhotoPicker.preview()
                    .paths(mShowPhoto)
                    .currentItem(position)
                    .start(MyNewsDetails.this);
        });
    }

    @Override
    protected int getMenuID() {
        return super.getMenuID();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        mToolbar.setTitle("事项详情");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(UIUtils.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void readMyNewsResponse(Object result) {

    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof OfficialDetailResponse) {
            if (((OfficialDetailResponse) result).getIq().getQuery().getErrorCode().equals("0")) {
                BaseApplication.log_say(TAG, ((OfficialDetailResponse) result).getIq().getQuery().getContent());
                for (int i = 0; i < ((OfficialDetailResponse) result).getIq().getQuery().getAttachments().size(); i++) {
                    mShowPhoto.add(URL.HOST_URL_SERVER_ZHICHENG + ((OfficialDetailResponse) result).getIq().getQuery().getAttachments().get(i).getHref());
                }
                mAdapter.setData((OfficialDetailResponse) result);
                //传阅消息处理
                MyNewsRequest mFormSendDoRequest = new MyNewsRequest();
                MyNewsRequest.IqBean iq = new MyNewsRequest.IqBean();
                MyNewsRequest.IqBean.QueryBean qb = new MyNewsRequest.IqBean.QueryBean();
                iq.setNamespace("FormSendDoRequest");
                qb.setId(((OfficialDetailResponse) result).getIq().getQuery().getId());
                qb.setDealType(2);
                qb.setRequestType(5);
                qb.setIsTrace(0);
                qb.setSuggestion("");
                qb.setIsWait(0);
                qb.setIsReturnCurrentNode(0);
                iq.setQuery(qb);
                mFormSendDoRequest.setIq(iq);
                Gson gson = new Gson();
                mMyNewsPresenterImpl.readMyNews(gson.toJson(mFormSendDoRequest));
            } else {
                showMessage(((OfficialDetailResponse) result).getIq().getQuery().getErrorMessage());
            }

        }else if(result instanceof CommonResponse){

        }
    }

    @Override
    public void addData(Object result) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPicker.REQUEST_SELECTED) {
            if (resultCode == RESULT_OK) {
                List<String> mData = data.getStringArrayListExtra(PhotoPicker.EXTRA_RESULT);
                mImagePath = (ArrayList<String>) mData;
                mDealAdapter.addData(mData);
            }
        }
    }

    private class DealAdapter extends RecyclerView.Adapter {

        private List<String> mData;

        public DealAdapter() {
            mData = new ArrayList<>();
        }

        public void addData(List<String> d) {
            this.mData = d;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view, parent, false);
            return new DealViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof DealViewHolder) {
                if (position == mData.size()) {
                    Glide.with(holder.itemView.getContext())
                            .load(R.mipmap.icon_addpic_unfocused)
                            .into(((DealViewHolder) holder).mImageView);
                    ((DealViewHolder) holder).mImageView.setOnClickListener(v -> {
                        PermissionUtils.requestCameraPermission(MyNewsDetails.this);
                        PermissionUtils.requestStoragePermission(MyNewsDetails.this);
                        PhotoPicker.load()
                                .filter(filter) // 照片属性过滤
                                .gridColumns(4) // 照片列表显示列数
                                .showCamera(true)
                                .multi()
                                .maxPickSize(3) // 最大选择数
                                .selectedPaths(mImagePath) // 已选择的照片地址
                                .start(MyNewsDetails.this); // 从Fragment、Activity中启动
                    });
                } else {
                    Glide.with(holder.itemView.getContext())
                            .load("file://" + mData.get(position))
                            .into(((DealViewHolder) holder).mImageView);
                }
            }
        }

        @Override
        public int getItemCount() {
            return mData.size() + 1;
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
    protected void onDestroy() {
        super.onDestroy();
    }
}
