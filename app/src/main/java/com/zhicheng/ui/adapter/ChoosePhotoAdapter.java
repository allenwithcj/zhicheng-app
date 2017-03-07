package com.zhicheng.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhicheng.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Donson on 2017/2/14.
 */

public class ChoosePhotoAdapter extends RecyclerView.Adapter{

    private List<String> imagePath;

    public ChoosePhotoAdapter(){
        imagePath = new ArrayList<String>();
    }

    public void addData(List<String> imgs){
        this.imagePath = imgs;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view,parent,false);
        return new ChoosePhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ChoosePhotoViewHolder){
            Glide.with(holder.itemView.getContext())
                    .load("file://" + imagePath.get(position))
                    .into(((ChoosePhotoViewHolder) holder).mImg);
        }
    }

    @Override
    public int getItemCount() {
        return imagePath.size();
    }

    private class ChoosePhotoViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImg;

        public ChoosePhotoViewHolder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.img_content);
        }
    }

}
