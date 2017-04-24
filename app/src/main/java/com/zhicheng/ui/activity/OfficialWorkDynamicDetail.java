package com.zhicheng.ui.activity;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.bean.http.PersonalDynamicResponse;
import com.zhicheng.bean.json.PersonalDynamicRequest;
import com.zhicheng.common.URL;
import com.zhicheng.ui.adapter.OfficialDynamicAdapter;

import java.util.ArrayList;
import java.util.List;

import cc.dagger.photopicker.PhotoPicker;
import me.codeboy.android.aligntextview.AlignTextView;

public class OfficialWorkDynamicDetail extends BaseActivity implements OfficialView,SwipeRefreshLayout.OnRefreshListener {
    private OfficialPresenterImpl mOfficialPresenterImpl;
    private String id;
    private TextView title_name;
    private ImageView img;
    private TextView name;
    private AlignTextView content;
    private RecyclerView mImageRecycler;
    private TextView location;
    private TextView department;//------------->新增
    private TextView time;
    private ImageButton more;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_official_work_dynamic_detail);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText(getResources().getString(R.string.dynamic_detail));
        img = (ImageView) findViewById(R.id.img);
        name = (TextView) findViewById(R.id.name);
        department= (TextView) findViewById(R.id.department);
        content = (AlignTextView) findViewById(R.id.content);
        mImageRecycler = (RecyclerView) findViewById(R.id.image_content);
        location = (TextView) findViewById(R.id.location);
        time = (TextView) findViewById(R.id.time);
        more = (ImageButton) findViewById(R.id.more);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
        mImageRecycler.setLayoutManager(mGridLayoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void refreshData(Object result) {
        if(result instanceof PersonalDynamicResponse){
            if(((PersonalDynamicResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                PersonalDynamicResponse.IqBean.QueryBean.PreMsgBean mPreMsgBean =
                        ((PersonalDynamicResponse) result).getIq().getQuery().getPreMsg();
                BaseApplication.log_say("---------",mPreMsgBean.getDEPT().toString());
                if(mPreMsgBean != null){
                    if (mPreMsgBean.getIMG() != null) {
                        Glide.with(this)
                                .load(R.drawable.profle)
                                .into(img);
                    }
                    name.setText(mPreMsgBean.getUSERID());
                    location.setText(mPreMsgBean.getLOCATION());
                    content.setText(mPreMsgBean.getCOUNT());
                    department.setText(mPreMsgBean.getDEPT());//------------->新增
                    String sendTime = mPreMsgBean.getDATETIME();
                    BaseApplication.log_say("--------->",mPreMsgBean.getDEPT().toString());
                    if (sendTime.length() != 0 && sendTime.length() >2) {
                        time.setText(sendTime.substring(0, sendTime.length() - 2));
                    }
                    mImageRecycler.setAdapter(new ImageRecyclerAdapter(mPreMsgBean.getIMG()));
                }
            }else{
                showMessage(((OfficialWorkDynamicList) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public void addData(Object result) {

    }

    @Override
    public void onRefresh() {
        id = getIntent().getStringExtra("id");
        PersonalDynamicRequest mPersonalDynamicRequest = new PersonalDynamicRequest();
        PersonalDynamicRequest.IqBean iqb = new PersonalDynamicRequest.IqBean();
        PersonalDynamicRequest.IqBean.QueryBean qb = new PersonalDynamicRequest.IqBean.QueryBean();
        iqb.setNamespace("PersonalDynamicRequest");
        qb.setType("3");
        qb.setID(id);
        iqb.setQuery(qb);
        mPersonalDynamicRequest.setIq(iqb);
        Gson gson = new Gson();
        mOfficialPresenterImpl.loadDynamicDetail(gson.toJson(mPersonalDynamicRequest));
    }

    private class ImageRecyclerAdapter extends RecyclerView.Adapter {

        private List<PersonalDynamicResponse.IqBean.QueryBean.PreMsgBean.IMGBean> imgs;

        public ImageRecyclerAdapter(List<PersonalDynamicResponse.IqBean.QueryBean.PreMsgBean.IMGBean> imgs) {
            this.imgs = imgs;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view, parent, false);
            return new imgViewHolder(root);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof imgViewHolder) {

                Glide.with(holder.itemView.getContext())
                        .load(URL.HOST_URL_SERVER_ZHICHENG + imgs.get(position).getHref())
                        .placeholder(R.drawable.glide_loading)
                        .error(R.drawable.glide_failed)
                        .into(((imgViewHolder) holder).mImageView);
                ArrayList<String> paths = new ArrayList<String>();
                for (int i = 0; i < imgs.size(); i++) {
                    paths.add(URL.HOST_URL_SERVER_ZHICHENG + imgs.get(i).getHref());
                }
                ((imgViewHolder) holder).mImageView.setOnClickListener(view -> {
                    PhotoPicker.preview()
                            .paths(paths)
                            .currentItem(position)
                            .start((Activity) holder.itemView.getContext());
                });
            }
        }

        @Override
        public int getItemCount() {
            if (imgs.size() > 9) {
                return 9;
            }
            return imgs.size();
        }

        class imgViewHolder extends RecyclerView.ViewHolder {

            private ImageView mImageView;

            imgViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.img_content);
            }
        }
    }
}
