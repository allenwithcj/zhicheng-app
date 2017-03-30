package com.zhicheng.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhicheng.R;
import com.zhicheng.bean.http.PersonalLogMaResponse;
import com.zhicheng.ui.activity.WorkNodeDetail;
import com.zhicheng.utils.common.UIUtils;

import java.util.ArrayList;
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

    public WorkNoteAdapter() {
        prelogsBeen = new ArrayList<>();
    }

    public void addAllData(List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogsBeen) {
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
        if (viewType == TYPE_ITEM_BEGIN) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_worknete_timelines_begin, parent, false);
            return new WorkNoteViewHolder(view);
        } else if (viewType == TYPE_ITEM_CONTENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_worknete_timelines, parent, false);
            return new WorkNoteViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_worknete_timelines_end, parent, false);
            return new WorkNoteViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WorkNoteViewHolder) {
            String sendTime = prelogsBeen.get(position).getCd01();
            ((WorkNoteViewHolder) holder).mTextView.setText(sendTime.substring(0, sendTime.length() - 2));
            ((WorkNoteViewHolder) holder).mSender.setText(prelogsBeen.get(position).getCd03());
            ((WorkNoteViewHolder) holder).mMiniContent.setText(prelogsBeen.get(position).getCd02());
            if (prelogsBeen.get(position).getCd04() != null) {
                if(prelogsBeen.get(position).getCd04().size() != 0){
                    ((WorkNoteViewHolder) holder).mImagesConts
                            .setText(prelogsBeen.get(position).getCd04().size()+"张图片 >");
                }
            }
            ((WorkNoteViewHolder) holder).mItemLayout.setOnClickListener(view -> {
                Intent intent = new Intent(holder.itemView.getContext(), WorkNodeDetail.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("prelogsBeen",prelogsBeen.get(position));
                intent.putExtras(bundle);
                UIUtils.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        if (prelogsBeen != null) {
            return prelogsBeen.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM_BEGIN;
        } else if (position == getItemCount() - 1) {
            return TYPE_ITEM_END;
        } else {
            return TYPE_ITEM_CONTENT;
        }
    }

    private class WorkNoteViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private TextView mSender;
        private TextView mMiniContent;
        private TextView mImagesConts;
        private LinearLayout mItemLayout;

        WorkNoteViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.NoteTime);
            mSender = (TextView) itemView.findViewById(R.id.sender);
            mMiniContent = (TextView) itemView.findViewById(R.id.mini_content);
            mImagesConts = (TextView) itemView.findViewById(R.id.imagesCount);
            mItemLayout = (LinearLayout) itemView.findViewById(R.id.item_layout);
        }
    }
}
