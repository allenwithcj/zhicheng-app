package com.zhicheng.ui.adapter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.bean.http.OfficialWorkDynamicDetail;

import java.util.ArrayList;
import java.util.List;

import me.codeboy.android.aligntextview.AlignTextView;

/**
 * Created by Donson on 2017/1/15.
 */

public class OfficialDynamicAdapter extends RecyclerView.Adapter {

    private static final int TYPE_CONTENT = 0;
    private static final int TYPE_VIDEO_CONTENT = 1;
    private static final int TYPE_SHARE_CONTENT = 2;

    private PopupWindow mPop;
    private View pop_view;
    private TextView praise;
    private TextView comment;

    private List<OfficialWorkDynamicDetail> mOfficialDynamicDetail;

    public OfficialDynamicAdapter(){
        mOfficialDynamicDetail = new ArrayList<OfficialWorkDynamicDetail>();
    }

    public OfficialDynamicAdapter(List<OfficialWorkDynamicDetail> mOfficialDynamicDetail){
        this.mOfficialDynamicDetail = mOfficialDynamicDetail;
    }

    public void setAdapterData(List<OfficialWorkDynamicDetail> mOfficialDynamicDetail){
        this.mOfficialDynamicDetail.clear();
        this.mOfficialDynamicDetail.addAll(mOfficialDynamicDetail);
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_official_dynamic,parent,false);
        return new OfficialDynamicHolder(root);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OfficialDynamicHolder){
            if (mOfficialDynamicDetail.get(position).getImg() != null){
                Glide.with(holder.itemView.getContext())
                        .load(mOfficialDynamicDetail.get(position).getImg())
                        .into(((OfficialDynamicHolder) holder).img);
            }
            ((OfficialDynamicHolder) holder).name.setText(mOfficialDynamicDetail.get(position).getName());
            ((OfficialDynamicHolder) holder).content.setText(mOfficialDynamicDetail.get(position).getContent());
            ((OfficialDynamicHolder) holder).location.setText(mOfficialDynamicDetail.get(position).getLocation());
            ((OfficialDynamicHolder) holder).time.setText(mOfficialDynamicDetail.get(position).getTime());
            ((OfficialDynamicHolder) holder).more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);
                    if (mPop != null){
                        mPop.dismiss();
                        mPop = null;
                    }
                    if (pop_view == null){
                        pop_view = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.item_popwindow_official,null);
                    }
                    mPop = new PopupWindow(pop_view,280,80,true);
                    mPop.setAnimationStyle(R.anim.popup_enter);
                    pop_view.setOnTouchListener((v1, event) -> {
                        if (mPop != null && mPop.isShowing()){
                            mPop.dismiss();
                            mPop = null;
                        }
                        return false;
                    });
                    mPop.showAtLocation(v, Gravity.NO_GRAVITY,location[0]-mPop.getWidth()-20,location[1]-20);
                    if (praise == null || comment == null){
                        praise = (TextView) pop_view.findViewById(R.id.praise);
                        comment = (TextView) pop_view.findViewById(R.id.comment);
                    }
                    praise.setOnClickListener(v1 -> {
                        Snackbar.make(holder.itemView,"赞",Snackbar.LENGTH_SHORT).show();
                        //处理过程
                        mPop.dismiss();
                    });
                    comment.setOnClickListener(v1 -> {
                        Snackbar.make(holder.itemView,"评论",Snackbar.LENGTH_SHORT).show();
                        //处理过程
                        mPop.dismiss();
                    });
                }
            });
            ((OfficialDynamicHolder) holder).mImageRecycler.setAdapter(new ImageRecyclerAdapter(mOfficialDynamicDetail.get(position).getImage_content()));
        }
    }

    @Override
    public int getItemCount() {
        return mOfficialDynamicDetail.size();
    }

    private class OfficialDynamicHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView name;
        private AlignTextView content;
        private RecyclerView mImageRecycler;
        private TextView location;
        private TextView time;
        private ImageButton more;

        OfficialDynamicHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.name);
            content = (AlignTextView) itemView.findViewById(R.id.content);
            mImageRecycler = (RecyclerView) itemView.findViewById(R.id.image_content);
            location = (TextView) itemView.findViewById(R.id.location);
            time = (TextView) itemView.findViewById(R.id.time);
            more = (ImageButton) itemView.findViewById(R.id.more);
            GridLayoutManager mGridLayoutManager = new GridLayoutManager(itemView.getContext(),3);
            mImageRecycler.setLayoutManager(mGridLayoutManager);
        }
    }

    private class ImageRecyclerAdapter extends RecyclerView.Adapter{

        private List<String> imgs;

        ImageRecyclerAdapter(List<String> imgs){
            this.imgs = imgs;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view,parent,false);
            return new imgViewHolder(root);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof imgViewHolder){
                Glide.with(holder.itemView.getContext())
                        .load(imgs.get(position))
                        .into(((imgViewHolder) holder).mImageView);
            }
        }

        @Override
        public int getItemCount() {
            if (imgs.size() > 9){
                return 9;
            }
            return imgs.size();
        }

        class imgViewHolder extends RecyclerView.ViewHolder{

            private ImageView mImageView;

            imgViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.img_content);
            }
        }
    }
}
