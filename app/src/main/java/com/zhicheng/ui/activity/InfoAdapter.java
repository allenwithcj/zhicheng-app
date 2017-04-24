package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhicheng.R;
import com.zhicheng.ui.activity.ContactActivity;
import com.zhicheng.ui.activity.MyjobActivity;
import com.zhicheng.ui.activity.OfficialSended;
import com.zhicheng.ui.activity.SettingActivity;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by Donson on 2017/2/8.
 */

public class InfoAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LAST = 1;
    private boolean b;

    public void setVersion(boolean b) {
        this.b = b;
        notifyDataSetChanged();
    }

    public interface ButtonClick {
        void onButtonClick();
    }


    private ButtonClick mButtonClick;

    public void setButtonClick(ButtonClick mButtonClick) {
        if (this.mButtonClick == null) {
            this.mButtonClick = mButtonClick;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEAD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.c_card_text_view, parent, false);
            return new InfoHeadViewHolder(view);
        } else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.c_card_text_view_last, parent, false);
            return new InfoLastViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InfoHeadViewHolder) {
            if (position == 0) {
                ((InfoHeadViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.contact));
                ((InfoHeadViewHolder) holder).mTextViewHead.setText("通讯录");
                ((InfoHeadViewHolder) holder).mTextViewHead.setOnClickListener(view -> {
                    UIUtils.startActivity(new Intent(UIUtils.getContext(), ContactActivity.class));
                    ((InfoHeadViewHolder) holder).mVersion_notice.setText("");
                });
            }else if(position == 1){
                ((InfoHeadViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.baoliao));
                ((InfoHeadViewHolder) holder).mTextViewHead.setText("我的爆料");
                ((InfoHeadViewHolder) holder).mTextViewHead.setOnClickListener(view -> {
                    UIUtils.startActivity(new Intent(UIUtils.getContext(), OfficialSended.class));
                    ((InfoHeadViewHolder) holder).mVersion_notice.setText("");
                });
            }else if (position==2){
                ((InfoHeadViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.job));
                ((InfoHeadViewHolder) holder).mTextViewHead.setText("我的工作动态");
                ((InfoHeadViewHolder) holder).mTextViewHead.setOnClickListener(view ->{
                    UIUtils.startActivity(new Intent(UIUtils.getContext(), MyjobActivity.class));
                    ((InfoHeadViewHolder) holder).mVersion_notice.setText("");
                });
            }else if (position==3){
                ((InfoHeadViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.experience));
                ((InfoHeadViewHolder) holder).mTextViewHead.setText("我的经验交流");
                ((InfoHeadViewHolder) holder).mTextViewHead.setOnClickListener(view->{
                    UIUtils.startActivity(new Intent(UIUtils.getContext(),MyjobActivity.class));
                    ((InfoHeadViewHolder) holder).mVersion_notice.setText("");
                });
            }
            else if(position == 4){
                ((InfoHeadViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.news));
                ((InfoHeadViewHolder) holder).mTextViewHead.setText("通知推送");
                ((InfoHeadViewHolder) holder).mVersion_notice.setText("");
            }else if(position == 5){
                ((InfoHeadViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.night));
                ((InfoHeadViewHolder) holder).mTextViewHead.setText("夜间模式");
                ((InfoHeadViewHolder) holder).mVersion_notice.setText("");

            }else if(position == 6){
                ((InfoHeadViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.refresh));
                ((InfoHeadViewHolder) holder).mTextViewHead.setText("在线更新");
                if (b) {
                    ((InfoHeadViewHolder) holder).mVersion_notice.setText("发现新版本");
                } else {
                    ((InfoHeadViewHolder) holder).mVersion_notice.setText("已是最新版本");
                }
                ((InfoHeadViewHolder) holder).mTextViewHead.setOnClickListener(view -> {
                    mButtonClick.onButtonClick();
                });
            }
        } else if (holder instanceof InfoLastViewHolder) {
            if(position == 7){
                ((InfoLastViewHolder) holder).mImageView.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.set));
                ((InfoLastViewHolder) holder).mTextViewLast.setText("设置");
                ((InfoLastViewHolder) holder).mTextViewLast.setOnClickListener(view -> {
                    Intent intent = new Intent(holder.itemView.getContext(), SettingActivity.class);
                    UIUtils.startActivity(intent);
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 7) {
            return TYPE_LAST;
        } else {
            return TYPE_HEAD;
        }
    }

    private class InfoHeadViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextViewHead;
        private TextView mVersion_notice;

        public InfoHeadViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img);
            mTextViewHead = (TextView) itemView.findViewById(R.id.text_view_1);
            mVersion_notice = (TextView) itemView.findViewById(R.id.version_notice);

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
