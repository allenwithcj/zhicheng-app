package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.presenter.impl.ExperiencePresenterImpl;
import com.zhicheng.api.view.ExperienceView;
import com.zhicheng.api.view.UpThingsView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.ExperienceCommonResponse;
import com.zhicheng.bean.json.ExperienceRequest;
import com.zhicheng.bean.json.UpFileRequest;
import com.zhicheng.bean.json.UpThingsRequest;
import com.zhicheng.module.imageloader.GlideImageLoader;
import com.zhicheng.ui.adapter.ChoosePhotoAdapter;
import com.zhicheng.utils.common.PermissionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cc.dagger.photopicker.PhotoPicker;
import cc.dagger.photopicker.picker.PhotoFilter;

public class ExperienceExchangeAddActivity extends BaseActivity implements ExperienceView {
    private RecyclerView mRecyclerView;
    private MyExpAdapter mAdapter;
    private ChoosePhotoAdapter mChoosePhotoAdapter;
    private TextView title_name;
    private DatabaseHelper mData;
    private ArrayList<String> mImagePath;
    private PhotoFilter filter;
    private AlertDialog dialog;
    private ExperiencePresenterImpl mExperiencePresenterImpl;
    private String guid;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_experience_exchange);

        mImagePath = new ArrayList<>();
        mChoosePhotoAdapter = new ChoosePhotoAdapter();
        mData = new DatabaseHelper();
        title_name = (TextView) findViewById(R.id.title_name);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyExpAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mExperiencePresenterImpl = new ExperiencePresenterImpl(this);
        PhotoPicker.init(new GlideImageLoader(), null);
        filter = PhotoFilter.build();
        filter.showGif(false);

        title_name.setText(getResources().getString(R.string.Experiment));
    }

    @Override
    protected void initData() {
        PermissionUtils.requestLocationPermission(this);
        mAdapter.setButtonClick(() -> {
            PermissionUtils.requestCameraPermission(this);
            PermissionUtils.requestStoragePermission(this);
            PhotoPicker.load()
                    .filter(filter) // 照片属性过滤
                    .gridColumns(4) // 照片列表显示列数
                    .showCamera(true)
                    .multi()
                    .maxPickSize(3) // 最大选择数
                    .selectedPaths(mImagePath) // 已选择的照片地址
                    .start(ExperienceExchangeAddActivity.this); // 从Fragment、Activity中启动
        });
    }

    @Override
    protected int getMenuID() {
        mToolbar.setTitle("");
        return super.getMenuID();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPicker.REQUEST_SELECTED) {
            if (resultCode == RESULT_OK) {
                List<String> mData = data.getStringArrayListExtra(PhotoPicker.EXTRA_RESULT);
                mImagePath = (ArrayList<String>) mData;
                mAdapter.addPhoto(mData);
            }
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String msg) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void queryExpResponse(Object result) {

    }

    @Override
    public void loadExpResponse(Object result) {

    }

    @Override
    public void upExpResponse(Object result) {
        if(result instanceof CommonResponse){
            if(((CommonResponse) result).getIq().getQuery().getErrorCode() == 0){
                //上传经验交流
                upExperience(guid);
            }else{
                showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
            }
        }else if(result instanceof ExperienceCommonResponse){
            if (((ExperienceCommonResponse) result).getIq().getQuery().getErrorCode() == 0) {
                showMessage("提交成功");
                this.finish();
            } else {
                showMessage(((ExperienceCommonResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    class MyExpAdapter extends RecyclerView.Adapter{
        private static final int TYPE_INPUT = 0;
        private static final int TYPE_CONTENT = 1;
        private static final int TYPE_SUBMIT =2;

        private ButtonClick mButtonClick;

        public void setButtonClick(ButtonClick mButtonClick) {
            if (this.mButtonClick == null) {
                this.mButtonClick = mButtonClick;
            }
        }

        public void addPhoto(List<String> photo) {
            mChoosePhotoAdapter.addData(photo);
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if(viewType == TYPE_INPUT){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_exp_input,parent,false);
                return new MyInputViewHolder(view);
            }else if(viewType == TYPE_CONTENT ){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_exp_content,parent,false);
                return new MyContentViewHolder(view);
            }else{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_exp_submit,parent,false);
                return new MySubmitViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyInputViewHolder){
                if(mData.getLocalConfig() != null){
                    ((MyInputViewHolder) holder).exp_sender.setText(mData.getLocalConfig().getUserName());
                    ((MyInputViewHolder) holder).exp_dept.setText(mData.getLocalConfig().getDepartment());
                }

            }else if(holder instanceof MyContentViewHolder){
                ((MyContentViewHolder) holder).mImgBtn.setOnClickListener(view -> {
                    mButtonClick.onButtonClick();
                });
            }else if(holder instanceof MySubmitViewHolder){
                ((MySubmitViewHolder) holder).submit_btn.setOnClickListener(view -> {
                    dialog = new AlertDialog.Builder(holder.itemView.getContext(), R.style.dialog)
                            .setView(R.layout.z_loading_view)
                            .setCancelable(false)
                            .create();
                    dialog.show();
                    if (null != mImagePath) {
                        if (mImagePath.size() != 0) {
                            //上传附件
                            guid = UUID.randomUUID().toString();
                            UpFileRequest uf = new UpFileRequest();
                            UpFileRequest.IqBean ufIB = new UpFileRequest.IqBean();
                            UpFileRequest.IqBean.QueryBean ufIBQB = new UpFileRequest.IqBean.QueryBean();
                            ufIBQB.setAttachmentGUID(guid);
                            ufIB.setQuery(ufIBQB);
                            ufIB.setNamespace("AttachmentUpdateRequest");
                            uf.setIq(ufIB);
                            Gson gson = new Gson();
                            String jFile = gson.toJson(uf);
                            mExperiencePresenterImpl.UpThings(guid, jFile, mImagePath);
                        } else {
                            upExperience("");
                        }
                    }
                });
            }

        }

        @Override
        public int getItemViewType(int position) {
            if(position == 0){
                return TYPE_INPUT;
            }else if(position == 1){
                return TYPE_CONTENT;
            }else{
                return TYPE_SUBMIT;
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
    class MyInputViewHolder extends RecyclerView.ViewHolder{
        private EditText exp_title;
        private TextView exp_sender;
        private TextView exp_dept;


        public MyInputViewHolder(View itemView) {
            super(itemView);

            exp_title = (EditText) itemView.findViewById(R.id.exp_title);
            exp_sender = (TextView) itemView.findViewById(R.id.exp_sender);
            exp_dept = (TextView) itemView.findViewById(R.id.exp_dept);
        }
    }


    class MyContentViewHolder extends RecyclerView.ViewHolder{
        private EditText i_exp_content;
        private RecyclerView choosePhoto;
        private ImageButton mImgBtn;

        public MyContentViewHolder(View itemView) {
            super(itemView);
            i_exp_content = (EditText) itemView.findViewById(R.id.i_exp_content);
            choosePhoto = (RecyclerView) itemView.findViewById(R.id.mRecycleView);
            mImgBtn = (ImageButton) itemView.findViewById(R.id.btn);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            choosePhoto.setLayoutManager(linearLayoutManager);
            choosePhoto.setAdapter(mChoosePhotoAdapter);
        }
    }

    class MySubmitViewHolder extends RecyclerView.ViewHolder{
        private Button submit_btn;

        public MySubmitViewHolder(View itemView) {
            super(itemView);
            submit_btn = (Button) itemView.findViewById(R.id.submit_btn);


        }
    }

    public interface ButtonClick {
        void onButtonClick();
    }


    private void upExperience(String guid){
        MyInputViewHolder inputHolder = (MyInputViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
        MyContentViewHolder contentHolder = (MyContentViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(1));
        ExperienceRequest mExperienceRequest = new ExperienceRequest();
        ExperienceRequest.IqBean iqb = new ExperienceRequest.IqBean();
        ExperienceRequest.IqBean.QueryBean iqbQB = new ExperienceRequest.IqBean.QueryBean();
        iqb.setNamespace("SubmitFormRequest");
        iqbQB.setTITLE_NAME(inputHolder.exp_title.getText().toString());
        iqbQB.setPOSTED_PERSON(inputHolder.exp_sender.getText().toString());
        iqbQB.setPOSTED_BY(mData.getLocalConfig().getUserId());
        iqbQB.setDEPARTMENT(mData.getLocalConfig().getDepartmentID());
        iqbQB.setCONTENT(contentHolder.i_exp_content.getText().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        iqbQB.setPOSTED_TIME(sdf.format(new Date()));
        //经验交流 201704101440000
        iqbQB.setFormNo("201704101440000");
        iqbQB.setTaskTitle("经验交流");
        iqbQB.setANNEX(guid);
        iqb.setQuery(iqbQB);
        mExperienceRequest.setIq(iqb);

        Gson gson = new Gson();
        String strEntity = gson.toJson(mExperienceRequest);
        mExperiencePresenterImpl.UpExperience(5,strEntity);
    }

}
