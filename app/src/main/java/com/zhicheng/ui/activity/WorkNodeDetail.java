package com.zhicheng.ui.activity;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.WorkNodePresenterImpl;
import com.zhicheng.api.view.WorkNodeView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.PersonalLogMaResponse;
import com.zhicheng.bean.json.PersonalLogMaRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.common.URL;

import java.util.ArrayList;
import java.util.List;

import cc.dagger.photopicker.PhotoPicker;

/**
 * Created by hp on 2017/3/3.
 */

public class WorkNodeDetail extends BaseActivity implements WorkNodeView {
    private TextView publicationunit;
    private TextView NoteTime;
    private TextView publicationpeople;
    private TextView content_txt;
    private TextView mini_content;
    private LinearLayout btn_layout;
    private RecyclerView mRecyclerView;
    private Button update,delete;
    private boolean b = false;
    private WorkNodePresenterImpl mWorkNodePresenterImpl;
    private AlertDialog dialog;
    private String Uid,work,sender,content;
    private String sendTime;
    private PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean prelogsBean;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_worknote_detail);
        prelogsBean = getIntent().getParcelableExtra("prelogsBeen");
        mWorkNodePresenterImpl = new WorkNodePresenterImpl(this);
        publicationunit = (TextView) findViewById(R.id.publicationunit);
        NoteTime = (TextView) findViewById(R.id.NoteTime);
        publicationpeople = (TextView) findViewById(R.id.publicationpeople);
        content_txt = (TextView) findViewById(R.id.content_txt);
        mini_content = (TextView) findViewById(R.id.mini_content);
        btn_layout = (LinearLayout) findViewById(R.id.btn_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.imgs);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(new ImageAdapter(prelogsBean.getCd04()));

        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);
        if(prelogsBean != null){
            Uid = prelogsBean.getCd00();
            work = prelogsBean.getCd05();
            sender = prelogsBean.getCd03();
            sendTime = prelogsBean.getCd01();
            content = prelogsBean.getCd02();
            publicationunit.setText("单位:"+work);
            NoteTime.setText("发表时间:"+sendTime.substring(0,sendTime.length()-2));
            publicationpeople.setText("发表人:"+sender);
            content_txt.setText("日志内容:");
            mini_content.setText(content);
        }
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);

        update.setOnClickListener(view -> {
            dialog = new AlertDialog.Builder(this,R.style.dialog)
                    .setView(R.layout.z_loading_view)
                    .setCancelable(false)
                    .create();
            dialog.show();
            Constant.LOG_OPERATE_TYPE = "update";
            PersonalLogMaRequest pr = new PersonalLogMaRequest();
            PersonalLogMaRequest.IqBean iqb = new PersonalLogMaRequest.IqBean();
            iqb.setNamespace("PersonalLogMaRequest");
            PersonalLogMaRequest.IqBean.QueryBean qb = new PersonalLogMaRequest.IqBean.QueryBean();
            qb.setType("4");
            qb.setCon(mini_content.getText().toString());
            qb.setId(Uid);
            iqb.setQuery(qb);
            pr.setIq(iqb);
            Gson gson = new Gson();
            mWorkNodePresenterImpl.updateWorkNodes(gson.toJson(pr));
        });

        delete.setOnClickListener(view -> {
            dialog = new AlertDialog.Builder(this,R.style.dialog)
                    .setView(R.layout.z_loading_view)
                    .setCancelable(false)
                    .create();
            dialog.show();
            Constant.LOG_OPERATE_TYPE = "delete";
            PersonalLogMaRequest pr = new PersonalLogMaRequest();
            PersonalLogMaRequest.IqBean iqb = new PersonalLogMaRequest.IqBean();
            iqb.setNamespace("PersonalLogMaRequest");
            PersonalLogMaRequest.IqBean.QueryBean qb = new PersonalLogMaRequest.IqBean.QueryBean();
            qb.setType("2");
            qb.setId(Uid);
            iqb.setQuery(qb);
            pr.setIq(iqb);
            Gson gson = new Gson();
            mWorkNodePresenterImpl.deleteWorkNodes(gson.toJson(pr));
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("工作日志详情");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected int getMenuID() {
        return R.menu.official_grid_operate;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_operate) {
            if(b){
                item.setTitle("修改");
                btn_layout.setVisibility(View.GONE);
                b = false;
            }else{
                item.setTitle("取消");
                btn_layout.setVisibility(View.VISIBLE);
                b = true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(String msg) {
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void refreshData(Object result) {
        if(result instanceof CommonResponse){
            if(((CommonResponse) result).getIq().getQuery().getErrorCode() == 0){
                if(Constant.LOG_OPERATE_TYPE.equals("update")){
                    showMessage("修改成功");
                }else if(Constant.LOG_OPERATE_TYPE.equals("delete")){
                    showMessage("删除成功");
                }
                finish();
            }else{
                if(Constant.LOG_OPERATE_TYPE.equals("update")){
                    showMessage("修改失败");
                }else if(Constant.LOG_OPERATE_TYPE.equals("delete")){
                    showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
                }
            }
        }
    }

    @Override
    public void addData(Object result) {

    }

    private class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

        private List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean.Cd04Bean> mImagePath;

        public ImageAdapter(List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean.Cd04Bean> data){
            this.mImagePath = data;
            notifyDataSetChanged();
        }

        @Override
        public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view,parent,false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            String mURL = URL.HOST_URL_SERVER_ZHICHENG + mImagePath.get(position).getHref();
            Glide.with(holder.itemView.getContext())
                    .load(mURL)//设置数据
                    .placeholder(R.drawable.glide_loading)
                    .error(R.drawable.glide_failed)
                    .thumbnail((float) 0.3)
                    .into(holder.mImageView);
            ArrayList<String> paths = new ArrayList<String>();
            for(int i = 0;i<mImagePath.size();i++){
                paths.add(URL.HOST_URL_SERVER_ZHICHENG+mImagePath.get(i).getHref());
            }
            holder.mImageView.setOnClickListener(view -> {
                PhotoPicker.preview()
                            .paths(paths)
                            .currentItem(position)
                            .start((Activity) holder.itemView.getContext());
                });
        }


        @Override
        public int getItemCount() {
            if(mImagePath != null){
                return mImagePath.size();
            }
            return 0;
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {

            private ImageView mImageView;

            public ImageViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.img_content);
            }
        }
    }
}
