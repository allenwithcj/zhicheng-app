package com.zhicheng.ui.adapter;

import android.content.Intent;
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
import com.zhicheng.ui.activity.ReplyActivity;
import com.zhicheng.utils.common.UIUtils;

import java.io.Serializable;
import java.util.List;

import me.codeboy.android.aligntextview.CBAlignTextView;

/**
 * Created by IBM on 2017/2/10.
 */

public class OfficialFinishDetailAdapter extends RecyclerView.Adapter {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_SHOW_BOX = 1;
    private static final int TYPE_SHOW_CONTENT = 2;
    private static final int TYPE_SHOW_IMAGES = 3;
    private static final int TYPE_SHOW_DEAL = 4;

    //接口
    private ShowPhoto mShowPhoto;

    public interface ShowPhoto {
        void onShowPhoto(int position);
    }

    public void setShowPhoto(ShowPhoto mShowPhoto) {
        if (this.mShowPhoto == null) {
            this.mShowPhoto = mShowPhoto;
        }
    }

    private OfficialDetailResponse mData;
    private String[] tags = UIUtils.getContext().getResources().getStringArray(R.array.NoFinishDetail);
    private String[] tag_last = UIUtils.getContext().getResources().getStringArray(R.array.detail_last);

    public OfficialFinishDetailAdapter() {
    }

    public void setData(OfficialDetailResponse data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_header, parent, false);
            return new HeaderViewHolder(view);
        } else if (viewType == TYPE_SHOW_BOX) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_box, parent, false);
            return new ShowBoxViewHolder(view);
        } else if (viewType == TYPE_SHOW_CONTENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_content, parent, false);
            return new ShowContentViewHolder(view);
        } else if (viewType == TYPE_SHOW_IMAGES) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_image, parent, false);
            return new ShowImageViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_show_deal, parent, false);
            return new ShowDealViewHolder(view);
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mData != null) {
            if (holder instanceof HeaderViewHolder) {
                ((HeaderViewHolder) holder).NumberId.setText(mData.getIq().getQuery().getTitle());
                if (mData.getIq().getQuery().getReplies() != null
                        && mData.getIq().getQuery().getReplies().size() != 0) {
                    ((HeaderViewHolder) holder).replies.setVisibility(View.VISIBLE);
                    ((HeaderViewHolder) holder).replies.setText(mData.getIq().getQuery().getReplies().size() + "条回复");
                    ((HeaderViewHolder) holder).replies.setOnClickListener(view -> {
                        List<OfficialDetailResponse.IqBean.QueryBean.RepliesBean> mRepliesBeanList = mData.getIq().getQuery().getReplies();
                        Intent intent = new Intent(holder.itemView.getContext(), ReplyActivity.class);
                        intent.putExtra("replies", (Serializable) mRepliesBeanList);
                        UIUtils.startActivity(intent);
                    });
                }
            } else if (holder instanceof ShowBoxViewHolder) {
                switch (position) {
                    case 1:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position - 1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getMap().getReportChannel());
                        break;

                    case 2:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position - 1]);
                        if (mData.getIq().getQuery().getMap().getType() == 1) {
                            ((ShowBoxViewHolder) holder).tagContent.setText("立案核实");
                        } else if (mData.getIq().getQuery().getMap().getType() == 2) {
                            ((ShowBoxViewHolder) holder).tagContent.setText("事件处置");
                        } else if (mData.getIq().getQuery().getMap().getType() == 3) {
                            ((ShowBoxViewHolder) holder).tagContent.setText("结案核实");
                        }

                        break;
                    case 3:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position - 1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getSendUser());
                        break;
                    case 4:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position - 1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText("手机号码");
                        break;
                    case 5:
                        ((ShowBoxViewHolder) holder).tagName.setText(tags[position - 1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getSendTime());
                        break;
                    case 6:
                        ((ShowBoxViewHolder) holder).tagName.setText(tag_last[0]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getSendUser());
                        break;
                    case 7:
                        ((ShowBoxViewHolder) holder).tagName.setText(tag_last[1]);
                        ((ShowBoxViewHolder) holder).tagContent.setText(mData.getIq().getQuery().getMap().getEventNote());
                        break;
                    case 8:
                        ((ShowBoxViewHolder) holder).tagName.setText(tag_last[2]);
                        break;
                }
            } else if (holder instanceof ShowContentViewHolder) {
                ((ShowContentViewHolder) holder).tagContentName.setText(tags[5]);
                ((ShowContentViewHolder) holder).tagBaoContent.setText(mData.getIq().getQuery().getMap().getEventDescription());
            } else if (holder instanceof ShowImageViewHolder) {
                ((ShowImageViewHolder) holder).tagImage.setText(tags[6]);
                int images = mData.getIq().getQuery().getAttachments().size();
                if (images != 0) {
                    if (images == 1) {
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + mData.getIq().getQuery().getAttachments().get(0).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img1);
                        ((ShowImageViewHolder) holder).img1.setOnClickListener(v -> mShowPhoto.onShowPhoto(0));
                    } else if (images == 2) {
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + mData.getIq().getQuery().getAttachments().get(0).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img1);
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + mData.getIq().getQuery().getAttachments().get(1).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img2);
                        ((ShowImageViewHolder) holder).img1.setOnClickListener(v -> mShowPhoto.onShowPhoto(0));
                        ((ShowImageViewHolder) holder).img2.setOnClickListener(v -> mShowPhoto.onShowPhoto(1));
                    } else if (images == 3) {
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + mData.getIq().getQuery().getAttachments().get(0).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img1);
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + mData.getIq().getQuery().getAttachments().get(1).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .centerCrop()
                                .into(((ShowImageViewHolder) holder).img2);
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + mData.getIq().getQuery().getAttachments().get(2).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img3);
                        ((ShowImageViewHolder) holder).img1.setOnClickListener(v -> mShowPhoto.onShowPhoto(0));
                        ((ShowImageViewHolder) holder).img2.setOnClickListener(v -> mShowPhoto.onShowPhoto(1));
                        ((ShowImageViewHolder) holder).img3.setOnClickListener(v -> mShowPhoto.onShowPhoto(2));
                    } else {
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + mData.getIq().getQuery().getAttachments().get(0).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img1);
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + mData.getIq().getQuery().getAttachments().get(1).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .centerCrop()
                                .into(((ShowImageViewHolder) holder).img2);
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + mData.getIq().getQuery().getAttachments().get(2).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(((ShowImageViewHolder) holder).img3);
                        ((ShowImageViewHolder) holder).img1.setOnClickListener(v -> mShowPhoto.onShowPhoto(0));
                        ((ShowImageViewHolder) holder).img2.setOnClickListener(v -> mShowPhoto.onShowPhoto(1));
                        ((ShowImageViewHolder) holder).img3.setOnClickListener(v -> mShowPhoto.onShowPhoto(2));
                    }

                }
            } else if (holder instanceof ShowDealViewHolder) {
                String mFlowStatus = mData.getIq().getQuery().getMap().getFlowStatus();
                if (mFlowStatus != null && mFlowStatus.equals("1")) {
                    ((ShowDealViewHolder) holder).Bao.setText(null);
                    ((ShowDealViewHolder) holder).BaoImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                    ((ShowDealViewHolder) holder).Accept.setText(null);
                    ((ShowDealViewHolder) holder).AcceptImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                    ((ShowDealViewHolder) holder).Deal.setText("办理中...");
                    ((ShowDealViewHolder) holder).DealImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                } else if (mFlowStatus != null && mFlowStatus.equals("2")) {
                    String mStartTime = mData.getIq().getQuery().getMap().getFlowStartTime();
                    if (!mStartTime.equals("")) {
                        ((ShowDealViewHolder) holder).Bao.setText(mStartTime.substring(0, mStartTime.length() - 2));
                    } else {
                        ((ShowDealViewHolder) holder).Bao.setText("");
                    }
                    ((ShowDealViewHolder) holder).BaoImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                    ((ShowDealViewHolder) holder).Accept.setText(null);
                    ((ShowDealViewHolder) holder).AcceptImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                    ((ShowDealViewHolder) holder).Deal.setText(null);
                    ((ShowDealViewHolder) holder).Deal.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.red));
                    ((ShowDealViewHolder) holder).DealImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                    String mEndTime = mData.getIq().getQuery().getMap().getFlowEndTime();
                    if (mEndTime != null && mEndTime.length() > 2) {
                        ((ShowDealViewHolder) holder).Complete.setText(mEndTime.substring(0, mEndTime.length() - 2));
                    } else {
                        ((ShowDealViewHolder) holder).Complete.setText("");
                    }
                    ((ShowDealViewHolder) holder).CompleteImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.i_show_deal));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return 11;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position > 0 && position < 6) {
            return TYPE_SHOW_BOX;
        } else if (position == 6) {
            return TYPE_SHOW_CONTENT;
        } else if (position == 7) {
            return TYPE_SHOW_IMAGES;
        } else if (position > 7 && position < getItemCount() - 1) {
            return TYPE_SHOW_BOX;
        } else {
            return TYPE_SHOW_DEAL;
        }

    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView NumberId;
        private TextView replies;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            NumberId = (TextView) itemView.findViewById(R.id.NumberId);
            replies = (TextView) itemView.findViewById(R.id.replies);
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
