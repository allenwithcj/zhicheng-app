package com.zhicheng.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhicheng.R;
import com.zhicheng.ui.activity.ContactActivity;
import com.zhicheng.ui.activity.OfficialSended;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by Donson on 2017/2/8.
 */

public class InfoAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_MIDDLE = 1;
    private static final int TYPE_LAST = 2;

    public interface ButtonClick{
        void onButtonClick();
    }


    private ButtonClick mButtonClick;

    public void setButtonClick(ButtonClick mButtonClick){
        if (this.mButtonClick == null){
            this.mButtonClick = mButtonClick;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEAD){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.c_card_text_view,parent,false);
            return new InfoHeadViewHolder(view);
        }else if (viewType == TYPE_MIDDLE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.c_card_text_view_middle,parent,false);
            return new InfoMiddleViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.c_card_text_view_last,parent,false);
            return new InfoLastViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InfoHeadViewHolder){
            if (position == 0){
                ((InfoHeadViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_main_news));
                ((InfoHeadViewHolder) holder).mTextViewHead.setText("通讯录");
                ((InfoHeadViewHolder) holder).mTextViewHead.setOnClickListener(view -> {
                    UIUtils.startActivity(new Intent(UIUtils.getContext(), ContactActivity.class));
                });
            }else{
                ((InfoHeadViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_main_night_mode));
                ((InfoHeadViewHolder) holder).mTextViewHead.setText("夜间模式");
            }
        }else if (holder instanceof InfoMiddleViewHolder){
            if(position == 1){
                ((InfoMiddleViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_main_bao_notice));
                ((InfoMiddleViewHolder) holder).mTextViewMiddle.setText("我的爆料");
                ((InfoMiddleViewHolder) holder).mTextViewMiddle.setOnClickListener(view -> {
                    UIUtils.startActivity(new Intent(UIUtils.getContext(), OfficialSended.class));
                });
            }else{
                ((InfoMiddleViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_main_bao_notice));
                ((InfoMiddleViewHolder) holder).mTextViewMiddle.setText("在线更新");
                ((InfoMiddleViewHolder) holder).mTextViewMiddle.setOnClickListener(view -> {
                     mButtonClick.onButtonClick();
                });
            }
        }else if (holder instanceof InfoLastViewHolder){
            if (position == 2){
                ((InfoLastViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_main_send_news));
                ((InfoLastViewHolder) holder).mTextViewLast.setText("通知推送");
            }else {
                ((InfoLastViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_main_config));
                ((InfoLastViewHolder) holder).mTextViewLast.setText("设置");
            }
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_HEAD;
        }else if (position == 1){
            return TYPE_MIDDLE;
        }else if (position == 2){
            return TYPE_LAST;
        }else if(position == 3){
            return TYPE_HEAD;
        }else if(position == 4){
            return TYPE_MIDDLE;
        }else {
            return TYPE_LAST;
        }
    }

    private class InfoHeadViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextViewHead;

        public InfoHeadViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img);
            mTextViewHead = (TextView) itemView.findViewById(R.id.text_view_1);

        }
    }
    private class InfoMiddleViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextViewMiddle;


        public InfoMiddleViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img);
            mTextViewMiddle = (TextView) itemView.findViewById(R.id.text_view_1);

        }
    }
    private class InfoLastViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextViewLast;


        public InfoLastViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img);
            mTextViewLast = (TextView) itemView.findViewById(R.id.text_view_1);

        }
    }
}
