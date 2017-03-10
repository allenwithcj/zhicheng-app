package com.zhicheng.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhicheng.R;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.common.URL;
import com.zhicheng.utils.common.UIUtils;

import me.codeboy.android.aligntextview.CBAlignTextView;

/**
 * Created by Donson on 2017/1/17.
 */

public class OfficialNoFinishDetailAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_SHOW_BOX = 1;
    private static final int TYPE_SHOW_CONTENT = 2;
    private static final int TYPE_SHOW_IMAGES = 3;
    private static final int TYPE_SHOW_DEAL = 4;

    private OfficialDetailResponse mData;
    private String[] tags = UIUtils.getContext().getResources().getStringArray(R.array.NoFinishDetail);
    private String[] tag_last = UIUtils.getContext().getResources().getStringArray(R.array.detail_last);

    //接口
    private ShowPhoto mShowPhoto;

    public interface ShowPhoto{
        void onShowPhoto(int position);
    }

    public void setShowPhoto(ShowPhoto mShowPhoto){
        if (this.mShowPhoto == null){
            this.mShowPhoto = mShowPhoto;
        }
    }

    public OfficialNoFinishDetailAdapter(){
        mData = null;
    }

    public void setData(OfficialDetailResponse data){
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_header,parent,false);
            return new HeaderViewHolder(view);
        }else if (viewType == TYPE_SHOW_BOX){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_box,parent,false);
            return new ShowBoxViewHolder(view);
        }else if (viewType == TYPE_SHOW_CONTENT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_content,parent,false);
            return new ShowContentViewHolder(view);
        }else if (viewType == TYPE_SHOW_IMAGES){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_image,parent,false);
            return new ShowImageViewHolder(view);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_deal,parent,false);
            return new ShowDealViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (mData != null){
            if (holder instanceof HeaderViewHolder){
                ((HeaderViewHolder) holder).NumberId.setText(mData.getIq().getQuery().getFormData().getFormData().getZC01().getViewValue());
                ((HeaderViewHolder) holder).related_matters.setText("相关事项···");
            }else if (holder instanceof ShowBoxViewHolder){
                switch (position){
                    case 1:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position-1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getFormData().getFormData().getZC08().getViewValue());
                        break;
                    case 2:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position-1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText("群众");
                        break;
                    case 3:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position-1]);
                        if(mData.getIq().getQuery().getMap().getType().equals("1")){
                            ((ShowBoxViewHolder) holder).tagContent.setText("立案核实");
                        }else if(mData.getIq().getQuery().getMap().getType().equals("2")){
                            ((ShowBoxViewHolder) holder).tagContent.setText("事件处置");
                        }else if(mData.getIq().getQuery().getMap().getType().equals("3")){
                            ((ShowBoxViewHolder) holder).tagContent.setText("结案核实");
                        }

                        break;
                    case 4:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position-1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getSendUser());
                        break;
                    case 5:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position-1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText("手机号码");
                        break;
                    case 6:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position-1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getSendTime());
                        break;
                    case 8:
                        ((ShowBoxViewHolder) holder).tagName.setText(tag_last[0]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getSendUser());
                        break;
                    case 9:
                        ((ShowBoxViewHolder) holder).tagName.setText(tag_last[1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getMap().getEventNote());
                        break;
                    case 10:
                        ((ShowBoxViewHolder) holder).tagName.setText(tag_last[2]);
                        break;
                }
            }else if (holder instanceof ShowContentViewHolder){
                ((ShowContentViewHolder) holder).tagContentName.setText(tags[6]);
                ((ShowContentViewHolder) holder).tagBaoContent.setText(mData.getIq().getQuery().getFormData().getFormData().getZC04().getViewValue());
            }else if (holder instanceof ShowImageViewHolder){
                ((ShowImageViewHolder) holder).tagImage.setText(tags[7]);
                switch (mData.getIq().getQuery().getAttachments().size()){
                    case 1:
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG+mData.getIq().getQuery().getAttachments().get(0).getHref())
//                                .centerCrop()
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img1);
                        ((ShowImageViewHolder) holder).img1.setOnClickListener(v -> mShowPhoto.onShowPhoto(0));
                        break;
                    case 2:
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG+mData.getIq().getQuery().getAttachments().get(0).getHref())
//                                .centerCrop()
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img1);
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG+mData.getIq().getQuery().getAttachments().get(1).getHref())
//                                .centerCrop()
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img2);
                        ((ShowImageViewHolder) holder).img1.setOnClickListener(v -> mShowPhoto.onShowPhoto(0));
                        ((ShowImageViewHolder) holder).img2.setOnClickListener(v -> mShowPhoto.onShowPhoto(1));
                        break;
                    case 3:
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG+mData.getIq().getQuery().getAttachments().get(0).getHref())
//                                .centerCrop()
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img1);
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG+mData.getIq().getQuery().getAttachments().get(1).getHref())
//                                .centerCrop()
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img2);
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG+mData.getIq().getQuery().getAttachments().get(2).getHref())
//                                .centerCrop()
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.5)
                                .into(((ShowImageViewHolder) holder).img3);
                        ((ShowImageViewHolder) holder).img1.setOnClickListener(v -> mShowPhoto.onShowPhoto(0));
                        ((ShowImageViewHolder) holder).img2.setOnClickListener(v -> mShowPhoto.onShowPhoto(1));
                        ((ShowImageViewHolder) holder).img3.setOnClickListener(v -> mShowPhoto.onShowPhoto(2));
                        break;
                }
            }else if (holder instanceof ShowDealViewHolder){
                switch (4){
                    case 1:
                        ((ShowDealViewHolder) holder).Bao.setText(null);
                        ((ShowDealViewHolder) holder).BaoImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                        break;
                    case 2:
                        ((ShowDealViewHolder) holder).Bao.setText(null);
                        ((ShowDealViewHolder) holder).BaoImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                        ((ShowDealViewHolder) holder).Accept.setText(null);
                        ((ShowDealViewHolder) holder).AcceptImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                        break;
                    case 3:
                        ((ShowDealViewHolder) holder).Bao.setText(null);
                        ((ShowDealViewHolder) holder).BaoImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                        ((ShowDealViewHolder) holder).Accept.setText(null);
                        ((ShowDealViewHolder) holder).AcceptImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                        ((ShowDealViewHolder) holder).Deal.setText(null);
                        ((ShowDealViewHolder) holder).DealImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                        break;
                    case 4:
                        ((ShowDealViewHolder) holder).Bao.setText(mData.getIq().getQuery().getSendTime().substring(0,10));
                        ((ShowDealViewHolder) holder).BaoImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                        ((ShowDealViewHolder) holder).Accept.setText(null);
                        ((ShowDealViewHolder) holder).AcceptImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                        ((ShowDealViewHolder) holder).Deal.setText("办理中...");
                        ((ShowDealViewHolder) holder).Deal.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.red));
                        ((ShowDealViewHolder) holder).DealImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                        ((ShowDealViewHolder) holder).Complete.setText(null);
                        ((ShowDealViewHolder) holder).CompleteImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal_normal));
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_HEADER;
        }else if (position > 0 && position < 7){
            return TYPE_SHOW_BOX;
        }else if (position == 7){
            return TYPE_SHOW_CONTENT;
        }else if (position == 8){
            return TYPE_SHOW_IMAGES;
        }else if (position > 8 && position < getItemCount()-1){
            return TYPE_SHOW_BOX;
        }else {
            return TYPE_SHOW_DEAL;
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView NumberId;
        private TextView related_matters;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            NumberId = (TextView) itemView.findViewById(R.id.NumberId);
            related_matters = (TextView) itemView.findViewById(R.id.related_matters);
        }
    }

    private class ShowBoxViewHolder extends RecyclerView.ViewHolder {

        private TextView tagName;
        private TextView tagContent;

        public ShowBoxViewHolder(View itemView) {
            super(itemView);
            tagName = (TextView) itemView.findViewById(R.id.tagName);
            tagContent = (TextView) itemView.findViewById(R.id.tagContent);
        }
    }

    private class ShowContentViewHolder extends RecyclerView.ViewHolder {

        private TextView tagContentName;
        private CBAlignTextView tagBaoContent;

        public ShowContentViewHolder(View itemView) {
            super(itemView);
            tagContentName = (TextView) itemView.findViewById(R.id.tagContentName);
            tagBaoContent = (CBAlignTextView) itemView.findViewById(R.id.tagBaoContent);
        }
    }

    private class ShowImageViewHolder extends RecyclerView.ViewHolder {

        private TextView tagImage;
        private ImageView img1;
        private ImageView img2;
        private ImageView img3;

        public ShowImageViewHolder(View itemView) {
            super(itemView);
            tagImage = (TextView) itemView.findViewById(R.id.tagImageName);
            img1 = (ImageView) itemView.findViewById(R.id.img1);
            img2 = (ImageView) itemView.findViewById(R.id.img2);
            img3 = (ImageView) itemView.findViewById(R.id.img3);
        }
    }

    private class ShowDealViewHolder extends RecyclerView.ViewHolder {

        private TextView Bao;
        private TextView Accept;
        private TextView Deal;
        private TextView Complete;
        private ImageView BaoImage;
        private ImageView AcceptImage;
        private ImageView DealImage;
        private ImageView CompleteImage;

        public ShowDealViewHolder(View itemView) {
            super(itemView);
            Bao = (TextView) itemView.findViewById(R.id.Bao);
            Accept = (TextView) itemView.findViewById(R.id.Accept);
            Deal = (TextView) itemView.findViewById(R.id.Deal);
            Complete = (TextView) itemView.findViewById(R.id.Complete);
            BaoImage = (ImageView) itemView.findViewById(R.id.BaoImg);
            AcceptImage = (ImageView) itemView.findViewById(R.id.AcceptImg);
            DealImage = (ImageView) itemView.findViewById(R.id.DealImg);
            CompleteImage = (ImageView) itemView.findViewById(R.id.CompleteImg);
        }
    }
}
