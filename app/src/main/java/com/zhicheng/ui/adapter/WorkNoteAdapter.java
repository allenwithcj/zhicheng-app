package com.zhicheng.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhicheng.R;
import com.zhicheng.api.common.database.WorkNote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Donson on 2017/1/19.
 *
 */

public class WorkNoteAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM_BEGIN = 0;
    private static final int TYPE_ITEM_CONTENT = 1;
    private static final int TYPE_ITEM_END = 2;

    private LinkedList<WorkNote> NormalContent;
    private ImageAdapter mImageAdapter;

    public WorkNoteAdapter(){
        NormalContent = new LinkedList<WorkNote>();
        mImageAdapter = new ImageAdapter();
    }

    public void addAllData(List<WorkNote> time){
        NormalContent.clear();
        this.NormalContent.addAll(time);
        this.notifyDataSetChanged();
    }

    public void insertData(List<WorkNote> wn){
        this.NormalContent.addAll(wn);
        this.notifyItemRangeInserted(getItemCount()-1,wn.size());
    }

    public void addData(WorkNote time){
        this.NormalContent.addFirst(time);
        this.notifyItemInserted(0);
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss", Locale.CHINESE);
            String str = simpleDateFormat.format(NormalContent.get(position).getCreateTime());
            ((WorkNoteViewHolder) holder).mTextView.setText(str);
            ((WorkNoteViewHolder) holder).mMiniContent.setText(NormalContent.get(position).getContent());
            //图片显示,可判断如果图片数量为0，就设置mRecycler为GONE
            //((WorkNoteViewHolder) holder).mRecyclerView.setVisibility(View.GONE);
            ((WorkNoteViewHolder) holder).mRecyclerView.setAdapter(mImageAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return NormalContent.size();
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

    private static class WorkNoteViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private TextView mMiniContent;
        private RecyclerView mRecyclerView;

        WorkNoteViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.NoteTime);
            mMiniContent = (TextView) itemView.findViewById(R.id.mini_content);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.imgs);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        }
    }
    //图片adapter
    private class ImageAdapter extends RecyclerView.Adapter{

        private List<String> mImagePath;

        ImageAdapter(){
            mImagePath = new ArrayList<>();
        }
        //设置数据集
        public void setImagePath(List<String> data){
            this.mImagePath = data;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view,parent,false));
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ImageViewHolder){
                Glide.with(holder.itemView.getContext())
                        .load("url")//设置数据
                        .thumbnail((float) 0.3)
                        .into(((ImageViewHolder) holder).mImageView);
            }
        }

        @Override
        public int getItemCount() {
            return mImagePath.size();
        }

        private class ImageViewHolder extends RecyclerView.ViewHolder {

            private ImageView mImageView;

            ImageViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.img_content);
            }
        }
    }
}
