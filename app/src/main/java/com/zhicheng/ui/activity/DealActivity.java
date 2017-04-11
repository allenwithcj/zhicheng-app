package com.zhicheng.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.UpThingsPresenterImpl;
import com.zhicheng.api.view.MainView;
import com.zhicheng.api.view.UpThingsView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.json.UpFileRequest;
import com.zhicheng.module.imageloader.GlideImageLoader;
import com.zhicheng.utils.common.PermissionUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cc.dagger.photopicker.PhotoPicker;
import cc.dagger.photopicker.picker.PhotoFilter;

/**
 * Created by Donson on 2017/2/28.
 */

public class DealActivity extends BaseActivity implements UpThingsView {

    private RecyclerView mRecyclerView;
    private DealAdapter mDealAdapter;
    private UpThingsPresenterImpl mUpThingsPresenter;
    private PhotoFilter filter;
    private ArrayList<String> mImagePath;
    private ArrayList<String> mData;
    //基础组件
    private TextView mBtn;
    private EditText mEdit;
    private String guid;
    private AlertDialog dialog;

    @Override
    protected void initEvents() {
        setContentView(R.layout.c_deal);
        mBtn = (TextView) findViewById(R.id.btnDeal);
        mEdit = (EditText) findViewById(R.id.suggestion);
        guid = UUID.randomUUID().toString();
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mDealAdapter = new DealAdapter();
        mRecyclerView.setAdapter(mDealAdapter);
        mUpThingsPresenter = new UpThingsPresenterImpl(this);
        PhotoPicker.init(new GlideImageLoader(), null);
        filter = PhotoFilter.build();
        filter.showGif(false);
    }

    @Override
    protected void initData() {
        mBtn.setText("保存并返回");
        mImagePath = new ArrayList<>();
        mData = new ArrayList<>();
        mBtn.setOnClickListener(v -> {
            mData.clear();
            mData.add(guid);
            mData.add(mEdit.getText().toString());
            Gson gson = new Gson();
            UpFileRequest uf = new UpFileRequest();
            UpFileRequest.IqBean ufIB = new UpFileRequest.IqBean();
            UpFileRequest.IqBean.QueryBean ufIBQB = new UpFileRequest.IqBean.QueryBean();
            ufIBQB.setAttachmentGUID(guid);
            ufIB.setQuery(ufIBQB);
            ufIB.setNamespace("AttachmentUpdateRequest");
            uf.setIq(ufIB);
            String jFile = gson.toJson(uf);
            if (null != mImagePath) {
                dialog = new AlertDialog.Builder(this, R.style.dialog)
                        .setView(R.layout.z_loading_view)
                        .setCancelable(false)
                        .create();
                dialog.show();
                mUpThingsPresenter.UpSimpleFile(guid, mImagePath, jFile);
                mData.addAll(mImagePath);
                mBtn.setClickable(false);
            } else {
                Snackbar.make(mToolbar, "请选择上传图片", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        mBtn.setClickable(true);
        Toast.makeText(UIUtils.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void UpThings(Object result) {
        if (result instanceof CommonResponse) {
            if (((CommonResponse) result).getIq().getQuery().getErrorCode() == 0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                mBtn.setClickable(true);
                Intent intent = new Intent();
                intent.putStringArrayListExtra("mData", mData);
                this.setResult(RESULT_OK, intent);
                this.finish();
            } else {
                mBtn.setClickable(false);
                showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mData.size() < 4) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            Intent intent = new Intent();
            intent.putStringArrayListExtra("mData", null);
            setResult(RESULT_OK, intent);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
                        PermissionUtils.requestCameraPermission(DealActivity.this);
                        PermissionUtils.requestStoragePermission(DealActivity.this);
                        PhotoPicker.load()
                                .filter(filter) // 照片属性过滤
                                .gridColumns(4) // 照片列表显示列数
                                .showCamera(true)
                                .multi()
                                .maxPickSize(3) // 最大选择数
                                .selectedPaths(mImagePath) // 已选择的照片地址
                                .start(DealActivity.this); // 从Fragment、Activity中启动
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
}
