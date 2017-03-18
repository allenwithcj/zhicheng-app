package com.zhicheng.ui.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zhicheng.R;
import com.zhicheng.bean.http.PersonalLogMaResponse;
import com.zhicheng.common.URL;

import java.util.List;


/**
 * Created by Donson on 2017/1/19.
 *
 */

public class WorkNoteAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM_BEGIN = 0;
    private static final int TYPE_ITEM_CONTENT = 1;
    private static final int TYPE_ITEM_END = 2;

    List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogsBeen;
    private ImageAdapter mImageAdapter;

    public WorkNoteAdapter(){
        mImageAdapter = new ImageAdapter();
    }

    public void addAllData(List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogsBeen){
        this.prelogsBeen = prelogsBeen;
        this.notifyDataSetChanged();
    }

    public void addDataList(List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogsBeen) {
        int page = prelogsBeen.size();
        this.prelogsBeen.addAll(prelogsBeen);
        this.notifyItemRangeInserted(page, prelogsBeen.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_ITEM_BEGIN){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_worknete_timelines_end,parent,false);
            return new WorkNoteViewHolder(view);
        }else if (viewType == TYPE_ITEM_CONTENT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_worknete_timelines,parent,false);
            return new WorkNoteViewHolder(view);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_worknete_timelines_begin,parent,false);
            return new WorkNoteViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WorkNoteViewHolder){
            String sendTime = prelogsBeen.get(position).getCd01();
            ((WorkNoteViewHolder) holder).mTextView.setText(sendTime.substring(0,sendTime.length()-2));
            ((WorkNoteViewHolder) holder).mMiniContent.setText(prelogsBeen.get(position).getCd02());
            if(prelogsBeen.get(position).getCd04() != null){
                mImageAdapter.setImagePath(prelogsBeen.get(position).getCd04());
            }
        }
    }

    @Override
    public int getItemCount() {
        if(prelogsBeen != null){
            return prelogsBeen.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_ITEM_BEGIN;
        }else if (position == getItemCount()-1){
            return TYPE_ITEM_END;
        }else {
            return TYPE_ITEM_CONTENT;
        }
    }

    private class WorkNoteViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private TextView mMiniContent;
        private RecyclerView mRecyclerView;

        WorkNoteViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.NoteTime);
            mMiniContent = (TextView) itemView.findViewById(R.id.mini_content);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.imgs);
            mRecyclerView.setLayoutManager(new LinearLayoutManager
                    (itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            mRecyclerView.setAdapter(mImageAdapter);
        }
    }
    //图片adapter
    private class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

        private List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean.Cd04Bean> mImagePath;

        //设置数据集
        public void setImagePath(List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean.Cd04Bean> data){
            this.mImagePath = data;
            this.notifyDataSetChanged();
        }

        @Override
        public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view,parent,false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onViewRecycled(ImageViewHolder holder) {
            super.onViewRecycled(holder);
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            String mURL = URL.HOST_URL_SERVER_ZHICHENG + mImagePath.get(position).getHref();
            holder.mImageView.setTag(mURL);
            SimpleTarget target = new SimpleTarget<Bitmap>() {

                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if(mURL.equals(holder.mImageView.getTag())){
                        holder.mImageView.setImageBitmap(resource);
                    }
                }
            };

            Glide.with(holder.itemView.getContext())
                    .load(mURL)//设置数据
                    .asBitmap()
                    .placeholder(R.drawable.glide_loading)
                    .error(R.drawable.glide_failed)
                    .thumbnail((float) 0.3)
                    .into(target);



        }


        @Override
        public int getItemCount() {
            if(mImagePath != null){
                return mImagePath.size();
            }
            return 0;
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {

            private ImageView mImageView;

            public ImageViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.img_content);
            }
        }
    }
}
