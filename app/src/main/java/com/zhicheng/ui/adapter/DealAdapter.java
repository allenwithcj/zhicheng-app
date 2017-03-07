package com.zhicheng.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhicheng.R;
import com.zhicheng.utils.common.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import cc.dagger.photopicker.PhotoPicker;
import cc.dagger.photopicker.picker.PhotoFilter;

/**
 * Created by Donson on 2017/2/28.
 */

public class DealAdapter extends RecyclerView.Adapter {

    private List<String> mData;
    private Activity activity;
    private PhotoFilter filter;

    public DealAdapter(Activity activity,PhotoFilter filter){
        mData = new ArrayList<>();
        this.activity = activity;
        this.filter = filter;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DealViewHolder){
            if (position == mData.size()){
                Glide.with(holder.itemView.getContext())
                        .load(R.mipmap.icon_addpic_unfocused)
                        .into(((DealViewHolder) holder).mImageView);
                ((DealViewHolder) holder).mImageView.setOnClickListener(v -> {
                    PermissionUtils.requestCameraPermission(activity);
                    PermissionUtils.requestStoragePermission(activity);
                    PhotoPicker.load()
                            .filter(filter) // 照片属性过滤
                            .gridColumns(4) // 照片列表显示列数
                            .showCamera(true)
                            .multi()
                            .maxPickSize(3) // 最大选择数
                            .selectedPaths(new ArrayList<>()) // 已选择的照片地址
                            .start(activity); // 从Fragment、Activity中启动
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
        return 0;
    }

    private class DealViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public DealViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img_content);
        }
    }
}
