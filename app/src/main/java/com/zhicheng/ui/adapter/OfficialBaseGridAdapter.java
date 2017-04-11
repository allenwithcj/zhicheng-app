package com.zhicheng.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhicheng.R;
import com.zhicheng.bean.http.OfficialBaseGridResponse;
import com.zhicheng.ui.activity.OfficialBaseGridDetail;
import com.zhicheng.ui.activity.SearchViewActivity;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by Donson on 2017/1/17.
 */

public class OfficialBaseGridAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CONTENT = 1;
    private static final int TYPE_BOTTOM = 2;

    private OfficialBaseGridResponse mData;
    private OnButtonClickListener mButtonClick;

    public OfficialBaseGridAdapter() {
    }

    public interface OnButtonClickListener {
        void OnButtonClick(TextView v);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        if (this.mButtonClick == null) {
            this.mButtonClick = listener;
        }
    }

    public void setData(OfficialBaseGridResponse mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_grid_header, parent, false);
            return new HeaderViewHolder(view);
        } else if (viewType == TYPE_CONTENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_grid_content, parent, false);
            return new ContentViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_grid_bottom, parent, false);
            return new BottomViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).img.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp));
            switch (position) {
                case 1:
                    ((ContentViewHolder) holder).tagName.setText("所在社区");
                    ((ContentViewHolder) holder).input.setOnClickListener(v -> {
                        Intent intent = new Intent(UIUtils.getContext(), SearchViewActivity.class);
                        intent.putExtra("fragment", "Search");
                        intent.putExtra("action", "communicate");
                        UIUtils.startActivity(intent);
                    });
                    if (mData != null && mData.getCode() == 1) {
                        ((ContentViewHolder) holder).input.setText(mData.getCommunicate());
                        ((ContentViewHolder) holder).FourAddress.setText(mData.getFourAddressCommunity());
                        ((ContentViewHolder) holder).AllAcreage.setText(mData.getAcreageCommunity());
                        ((ContentViewHolder) holder).AllHousehold.setText(String.valueOf(mData.getHouseholdCommunity()));
                    }
                    break;
                case 2:
                    ((ContentViewHolder) holder).tagName.setText("所在网格");
                    ((ContentViewHolder) holder).input.setOnClickListener(v -> {
                        if (mData.getCommunicate() == null) {
                            Toast.makeText(holder.itemView.getContext(), "请选择所在社区", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(UIUtils.getContext(), SearchViewActivity.class);
                            intent.putExtra("fragment", "Search");
                            intent.putExtra("action", "grid");
                            UIUtils.startActivity(intent);
                        }
                    });
                    if (mData != null && mData.getCode() == 2) {
                        ((ContentViewHolder) holder).input.setText(mData.getGrid());
                        ((ContentViewHolder) holder).FourAddress.setText(mData.getFourAddressGrid());
                        ((ContentViewHolder) holder).AllAcreage.setText(mData.getAcreageGrid());
                        ((ContentViewHolder) holder).AllHousehold.setText(String.valueOf(mData.getHouseholdGrid()));
                    }
                    break;
            }
        } else if (holder instanceof BottomViewHolder) {
            ((BottomViewHolder) holder).belongBuild.setOnClickListener(v -> {
                if (mData.getGrid() == null) {
                    Toast.makeText(holder.itemView.getContext(), "请选择所在网格", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(UIUtils.getContext(), SearchViewActivity.class);
                    intent.putExtra("action", "build");
                    UIUtils.startActivity(intent);
                }
            });
            if (mButtonClick != null) {
                mButtonClick.OnButtonClick(((BottomViewHolder) holder).belongBuild);
            }
            ((BottomViewHolder) holder).addPersonal.setOnClickListener(v -> {
                if (mData.getTypeBuild() == null) {
                    Toast.makeText(holder.itemView.getContext(), "请选择楼栋", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(UIUtils.getContext(), OfficialBaseGridDetail.class);
                    intent.putExtra("typeBuild", mData.getTypeBuild());
                    intent.putExtra("layerBuild", mData.getLayerBuild());
                    intent.putExtra("oneLayerBuild", mData.getOneLayerBuild());
                    intent.putExtra("unitNum", mData.getUnitNum());
                    intent.putExtra("url", "http://nuoche.xiaoyouqiao.com/appapi/build.html");
                    intent.putExtra("title", "楼栋详情");
                    UIUtils.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 1 || position == 2) {
            return TYPE_CONTENT;
        } else {
            return TYPE_BOTTOM;
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ContentViewHolder extends RecyclerView.ViewHolder {

        private TextView tagName;
        private TextView input;
        private ImageView img;
        private TextView FourAddress;
        private TextView AllAcreage;
        private TextView AllHousehold;

        public ContentViewHolder(View itemView) {
            super(itemView);
            tagName = (TextView) itemView.findViewById(R.id.tagName);
            input = (TextView) itemView.findViewById(R.id.input);
            img = (ImageView) itemView.findViewById(R.id.img);
            FourAddress = (TextView) itemView.findViewById(R.id.fourAddress);
            AllAcreage = (TextView) itemView.findViewById(R.id.AllAcreage);
            AllHousehold = (TextView) itemView.findViewById(R.id.AllHousehold);
        }
    }

    private class BottomViewHolder extends RecyclerView.ViewHolder {

        private TextView belongBuild;
        private Button addPersonal;

        public BottomViewHolder(View itemView) {
            super(itemView);
            belongBuild = (TextView) itemView.findViewById(R.id.belongBuild);
            addPersonal = (Button) itemView.findViewById(R.id.addPersonal);
        }
    }
}
