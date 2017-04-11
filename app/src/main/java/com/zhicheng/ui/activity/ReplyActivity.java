package com.zhicheng.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhicheng.R;
import com.zhicheng.bean.http.OfficialDetailResponse;

import java.util.List;

import me.codeboy.android.aligntextview.AlignTextView;

public class ReplyActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private List<OfficialDetailResponse.IqBean.QueryBean.RepliesBean> mRepliesBeen;
    private ReplyAdapter mAdapter;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_reply);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ReplyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_clear));
    }

    @Override
    protected void initData() {
        mRepliesBeen = (List<OfficialDetailResponse.IqBean.QueryBean.RepliesBean>) getIntent().getSerializableExtra("replies");
        mAdapter.setData(mRepliesBeen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        mToolbar.setTitle("回复");
        return true;
    }

    class ReplyAdapter extends RecyclerView.Adapter {
        private List<OfficialDetailResponse.IqBean.QueryBean.RepliesBean> mRepliesBeen;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_replies_item, parent, false);
            return new ReplyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ReplyViewHolder) {
                if (mRepliesBeen.get(position).getSendUserImg() != null) {
                    Glide.with(holder.itemView.getContext())
                            .load(R.drawable.profle)
                            .into(((ReplyViewHolder) holder).img);
                }
                ((ReplyViewHolder) holder).sendUser.setText(mRepliesBeen.get(position).getSendUser());
                ((ReplyViewHolder) holder).nodeName.setText(mRepliesBeen.get(position).getNodeName());
                ((ReplyViewHolder) holder).content.setText(mRepliesBeen.get(position).getContent());
                ((ReplyViewHolder) holder).time.setText(mRepliesBeen.get(position).getSendTime());
            }

        }

        @Override
        public int getItemCount() {
            if (mRepliesBeen != null) {
                return mRepliesBeen.size();
            }
            return 0;
        }

        public void setData(List<OfficialDetailResponse.IqBean.QueryBean.RepliesBean> mRepliesBeen) {
            this.mRepliesBeen = mRepliesBeen;
            notifyDataSetChanged();
        }
    }

    public class ReplyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView sendUser;
        private TextView nodeName;
        private AlignTextView content;
        private RecyclerView mImageRecycler;
        private TextView time;

        public ReplyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            sendUser = (TextView) itemView.findViewById(R.id.sendUser);
            nodeName = (TextView) itemView.findViewById(R.id.nodeName);
            content = (AlignTextView) itemView.findViewById(R.id.content);
            mImageRecycler = (RecyclerView) itemView.findViewById(R.id.image_content);
            time = (TextView) itemView.findViewById(R.id.time);
            GridLayoutManager mGridLayoutManager = new GridLayoutManager(itemView.getContext(), 3);
            mImageRecycler.setLayoutManager(mGridLayoutManager);
        }
    }
}
