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
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.bean.json.OfficialDetailRequest;
import com.zhicheng.bean.json.UpFileRequest;
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

public class OfficialNoFinishDetails extends BaseActivity implements OfficialView {

    private RecyclerView mRecyclerView;
    private OfficialNoFinishDetailAdapter mAdapter;
    private OfficialPresenterImpl mOfficialDetail;
    private ViewGroup parentView;
    //处理
    private PopupWindow mPopupWindow;
    private DealAdapter mDealAdapter;
    private PhotoFilter filter;
    private ArrayList<String> mImagePath;
    private ArrayList<String> mShowPhoto;

    private TextView mBtn;
    private OfficialDetailResponse OfficialDetail;
    private String type;
    private AlertDialog dialog;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_selectimg);
        type = getIntent().getStringExtra("type");
        parentView = (ViewGroup) findViewById(android.R.id.content).getRootView();
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OfficialNoFinishDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mOfficialDetail = new OfficialPresenterImpl(this);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_clear));
        //处理
        mDealAdapter = new DealAdapter();
        mImagePath = new ArrayList<>();
        mShowPhoto = new ArrayList<>();
        PhotoPicker.init(new GlideImageLoader(), null);
        filter = PhotoFilter.build();
        filter.showGif(false);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                View deal_view = getLayoutInflater().inflate(R.layout.c_deal, parentView, false);
                if (item.getItemId() == R.id.action_deal) {
                    //处理事项
                    doThing(deal_view, "0");

                } else if (item.getItemId() == R.id.action_apply) {
                    //申请事项
                    View apply_view = getLayoutInflater().inflate(R.layout.c_apply, parentView, false);
                    doThing(apply_view, "4");
                }
                return true;
            }
        });
    }

    private void doThing(View pop_view, String type) {

        mBtn = (TextView) pop_view.findViewById(R.id.btnDeal);
        EditText mEdit = (EditText) pop_view.findViewById(R.id.suggestion);
        String guid = UUID.randomUUID().toString();
        mBtn.setOnClickListener(v -> {
            Gson gson = new Gson();
            UpFileRequest uf = new UpFileRequest();
            UpFileRequest.IqBean ufIB = new UpFileRequest.IqBean();
            UpFileRequest.IqBean.QueryBean ufIBQB = new UpFileRequest.IqBean.QueryBean();
            ufIBQB.setAttachmentGUID(guid);
            ufIB.setQuery(ufIBQB);
            ufIB.setNamespace("AttachmentUpdateRequest");
            uf.setIq(ufIB);
            String jFile = gson.toJson(uf);
            if (type.equals("0")) {
                if (null != mImagePath) {
                    if (mImagePath.size() != 0) {
                        dialog = new AlertDialog.Builder(this, R.style.dialog)
                                .setView(R.layout.z_loading_view)
                                .setCancelable(false)
                                .create();
                        dialog.show();
                        mOfficialDetail.upDeal(mImagePath, jFile, mEdit.getText().toString(), OfficialDetail, guid, type);
                        mBtn.setClickable(false);
                    } else {
                        Toast.makeText(UIUtils.getContext(), "请选择上传图片", Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (type.equals("4")) {
                dialog = new AlertDialog.Builder(this, R.style.dialog)
                        .setView(R.layout.z_loading_view)
                        .setCancelable(false)
                        .create();
                dialog.show();
                mOfficialDetail.upDeal(mImagePath, jFile, mEdit.getText().toString(), OfficialDetail, guid, type);
                mBtn.setClickable(false);
            }

        });
        RecyclerView dealRecyclerView = (RecyclerView) pop_view.findViewById(R.id.mRecycleView);
        dealRecyclerView.setLayoutManager(new GridLayoutManager(OfficialNoFinishDetails.this, 3));
        dealRecyclerView.setAdapter(mDealAdapter);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        if (null != mPopupWindow && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        } else {
            mPopupWindow = new PopupWindow(pop_view, width - width / 4, WindowManager.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            mPopupWindow.setOnDismissListener(() -> {
                AnimationUtils.darkBackgroundColor(getWindow(), 1f);
            });
            mPopupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
            AnimationUtils.darkBackgroundColor(getWindow(), 0.4f);
        }
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
                    .start(OfficialNoFinishDetails.this);
        });
    }

    @Override
    protected int getMenuID() {
        if (type.equals("nofinish")) {
            //return R.menu.official_detail;
            return R.menu.nofinish_action;
        } else {
            return super.getMenuID();
        }
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
    public void refreshData(Object result) {
        if (result instanceof OfficialDetailResponse) {
            if (((OfficialDetailResponse) result).getIq().getQuery().getErrorCode().equals("0")) {
                BaseApplication.log_say(TAG, ((OfficialDetailResponse) result).getIq().getQuery().getContent());
                this.OfficialDetail = (OfficialDetailResponse) result;
                for (int i = 0; i < ((OfficialDetailResponse) result).getIq().getQuery().getAttachments().size(); i++) {
                    mShowPhoto.add(URL.HOST_URL_SERVER_ZHICHENG + ((OfficialDetailResponse) result).getIq().getQuery().getAttachments().get(i).getHref());
                }
                mAdapter.setData((OfficialDetailResponse) result);
            } else {
                showMessage(((OfficialDetailResponse) result).getIq().getQuery().getErrorMessage());
            }

        } else if (result instanceof CommonResponse) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (((CommonResponse) result).getIq().getQuery().getErrorCode() == 0) {
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
                showMessage("操作成功");
                this.finish();
            } else {
                mBtn.setClickable(true);
                showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
            }
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
                        PermissionUtils.requestCameraPermission(OfficialNoFinishDetails.this);
                        PermissionUtils.requestStoragePermission(OfficialNoFinishDetails.this);
                        PhotoPicker.load()
                                .filter(filter) // 照片属性过滤
                                .gridColumns(4) // 照片列表显示列数
                                .showCamera(true)
                                .multi()
                                .maxPickSize(3) // 最大选择数
                                .selectedPaths(mImagePath) // 已选择的照片地址
                                .start(OfficialNoFinishDetails.this); // 从Fragment、Activity中启动
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
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}
