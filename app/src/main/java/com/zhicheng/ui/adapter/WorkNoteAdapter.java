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
import com.zhicheng.api.common.database.WorkNote;
import com.zhicheng.ui.activity.WorkNodeDetail;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Donson on 2017/1/19.
 */

public class WorkNoteAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM_BEGIN = 0;
    private static final int TYPE_ITEM_CONTENT = 1;
    private static final int TYPE_ITEM_END = 2;
    private LinkedList<WorkNote> mWorkNotes;

    public WorkNoteAdapter(){
        mWorkNotes = new LinkedList<WorkNote>();
    }

    public void addAllData(List<WorkNote> time){
        mWorkNotes.clear();
        this.mWorkNotes.addAll(time);
        this.notifyDataSetChanged();
    }

    public void insertData(List<WorkNote> wn){
        wn.clear();
        this.mWorkNotes.addAll(wn);
        this.notifyItemRangeInserted(getItemCount()-1,wn.size());
    }

    public void addData(WorkNote time){
        mWorkNotes.clear();
        this.mWorkNotes.addFirst(time);
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss", Locale.CHINESE);
            ((WorkNoteViewHolder) holder).mTextView.setText(sdf.format(mWorkNotes.get(position).getCreateTime()));
            ((WorkNoteViewHolder) holder).mMiniContent.setText(mWorkNotes.get(position).getContent());
            ((WorkNoteViewHolder) holder).mLayout.setOnClickListener(view -> {
                    Intent intent = new Intent(view.getContext(), WorkNodeDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mWorkNote",mWorkNotes.get(position));
                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return mWorkNotes.size();
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
        private LinearLayout mLayout;

        WorkNoteViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.NoteTime);
            mMiniContent = (TextView) itemView.findViewById(R.id.mini_content);
            mLayout = (LinearLayout) itemView.findViewById(R.id.layout);

        }
    }
}
