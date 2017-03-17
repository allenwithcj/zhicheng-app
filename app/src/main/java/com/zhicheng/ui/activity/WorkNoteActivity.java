package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.database.WorkNote;
import com.zhicheng.api.presenter.impl.WorkNodePresenterImpl;
import com.zhicheng.api.view.WorkNodeView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.PersonalLogMaResponse;
import com.zhicheng.bean.json.PersonalLogMaRequest;
import com.zhicheng.bean.json.UpFileRequest;
import com.zhicheng.module.imageloader.GlideImageLoader;
import com.zhicheng.ui.adapter.WorkNoteAdapter;
import com.zhicheng.utils.common.PermissionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import cc.dagger.photopicker.PhotoPicker;
import cc.dagger.photopicker.picker.PhotoFilter;

/**
 * Created by Donson on 2017/1/19.
 */

public class WorkNoteActivity extends BaseActivity implements WorkNodeView,
        SwipeRefreshLayout.OnRefreshListener,View.OnClickListener,ApiCompleteListener{

    private int start;
    public static WorkNoteActivity instance;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private WorkNoteAdapter mWorkNoteAdapter;
    private WorkNodePresenterImpl mWorkNodePresenter;
    private LinearLayoutManager mLLM;
    private ImageView voice;
    private EditText mInput;
    private ImageView emoji;
    private ImageView more;

    private DatabaseHelper mDataBase;
    private int indexLast = 0;
    private String send_content;
    private String GUID;

    private LinearLayout moreTools;
    private PhotoFilter filter;
    private ArrayList<String> mImagePath;//选择照片地址集
    private RecyclerView picRecyclerView;
    private DealAdapter mDealAdapter;

    public WorkNoteActivity getInstance(){
        return instance;
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        start = 1;
        String strEntity = createObj(start);
        mWorkNodePresenter.loadWorkNodes(strEntity,start);
        start ++;
    }

    private String createObj(int start) {
        PersonalLogMaRequest pm = new PersonalLogMaRequest();
        PersonalLogMaRequest.IqBean iqb = new PersonalLogMaRequest.IqBean();
        iqb.setNamespace("PersonalLogMaRequest");
        PersonalLogMaRequest.IqBean.QueryBean qb = new PersonalLogMaRequest.IqBean.QueryBean();
        qb.setType("3");
        qb.setPage(start);
        iqb.setQuery(qb);
        pm.setIq(iqb);
        Gson gson = new Gson();
        return gson.toJson(pm);
    }

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_worknote);
        instance = this;
        mWorkNodePresenter = new WorkNodePresenterImpl(this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        voice = (ImageView) findViewById(R.id.voice);
        mInput = (EditText) findViewById(R.id.inputNote);
        emoji = (ImageView) findViewById(R.id.emoji);
        more = (ImageView) findViewById(R.id.more);
        moreTools = (LinearLayout) findViewById(R.id.moreTools);

        mDealAdapter = new DealAdapter();
        mImagePath = new ArrayList<>();
        PhotoPicker.init(new GlideImageLoader(),null);
        filter = PhotoFilter.build();
        filter.showGif(false);
        voice.setOnClickListener(this);
        mInput.setOnClickListener(this);
        emoji.setOnClickListener(this);
        more.setOnClickListener(this);

        mLLM = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true);
        mRecyclerView.setLayoutManager(mLLM);
        mWorkNoteAdapter = new WorkNoteAdapter();
        mRecyclerView.setAdapter(mWorkNoteAdapter);
        mDataBase = new DatabaseHelper();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {
        onRefresh();
        mInput.setImeOptions(EditorInfo.IME_ACTION_SEND);
        mInput.setImeActionLabel("发送",EditorInfo.IME_ACTION_SEND);
        mInput.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mInput.setSingleLine(false);
        mInput.setMaxLines(5);
        mInput.setHorizontallyScrolling(false);
        mInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null
                    && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN)){
                if (mInput.getText().toString().isEmpty()){
                    Snackbar.make(mToolbar,"工作日志不能为空", Snackbar.LENGTH_SHORT).show();
                }else {
                    if(mImagePath != null){
                        if(mImagePath.size() != 0){
                            GUID = UUID.randomUUID().toString();
                            send_content = mInput.getText().toString();
                            sendMessage(mImagePath,GUID,send_content);
                            if (moreTools.getVisibility() == View.VISIBLE){
                                Animation animation = AnimationUtils.loadAnimation(this,R.anim.activity_translate_out);
                                moreTools.startAnimation(animation);
                                moreTools.setVisibility(View.GONE);
                                mImagePath.clear();
                                mDealAdapter.notifyDataSetChanged();
                            }
                            mRecyclerView.smoothScrollToPosition(0);
                            v.setText("");
                        }else{
                            Toast.makeText(this,"工作日志图片不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                return true;
            }
            return false;
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public void showProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    public void sendMessage(ArrayList<String> mImagePath, String GUID, String content) {
        String attGUID = UUID.randomUUID().toString();
        Gson gson = new Gson();
        UpFileRequest uf = new UpFileRequest();
        UpFileRequest.IqBean ufIB = new UpFileRequest.IqBean();
        UpFileRequest.IqBean.QueryBean ufIBQB = new UpFileRequest.IqBean.QueryBean();
        ufIBQB.setAttachmentGUID(attGUID);
        ufIB.setQuery(ufIBQB);
        ufIB.setNamespace("AttachmentUpdateRequest");
        uf.setIq(ufIB);
        String jFile = gson.toJson(uf);
        mWorkNodePresenter.sendWorkNodes(jFile,mImagePath,content,attGUID,GUID,this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("工作日志");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mInput.getWindowToken(),0);
    }


    @Override
    public void showMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshData(Object result) {
        if(result instanceof PersonalLogMaResponse){
            //查询工作日志返回
            if(((PersonalLogMaResponse)result).getIq().getQuery().getErrorCode() == 0){
                List<PersonalLogMaResponse.IqBean.QueryBean.Prelogcon.Prelog> prelogList = ((PersonalLogMaResponse)result).getIq().getQuery().getPrelogcon().getPrelogs();
                List<WorkNote> workNotes = new ArrayList<>();
                for(PersonalLogMaResponse.IqBean.QueryBean.Prelogcon.Prelog prelog : prelogList){
                    WorkNote news = new WorkNote();
                    news.setId(mDataBase.generateNewPrimaryKey());
                    news.setuID(prelog.getCd00());
                    try {
                        String sendTime = prelog.getCd01();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
                        news.setCreateTime(sdf.parse(sendTime.substring(0,sendTime.length()-2).toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    news.setContent(prelog.getCd02());
                    news.setSendPeopel(prelog.getCd03());
                    news.setSendWork(prelog.getCd05());
                    workNotes.add(news);
                    if(!mDataBase.queryByUID(news.getuID())){
                        mDataBase.setWorkNote(news);
                    }
                }
                mWorkNoteAdapter.addAllData(workNotes);
            }else{
                List<WorkNote> data = mDataBase.getWorkNote();
                Collections.reverse(data);
                if (data.size() < 10){
                    mWorkNoteAdapter.addAllData(data);
                }else {
                    indexLast = 10;
                    mWorkNoteAdapter.addAllData(data.subList(0,indexLast));
                }
                mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


                    private int lastVisibleItemPosition;
                    private int targetIndex;

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mWorkNoteAdapter.getItemCount()){
                            targetIndex = indexLast+10;
                            if (targetIndex > data.size()){
                                targetIndex = data.size();
                            }
                            if (indexLast != 0 && indexLast < data.size()){
                                mWorkNoteAdapter.insertData(data.subList(indexLast,targetIndex));
                                BaseApplication.log_say(TAG,""+indexLast);
                                indexLast += 10;
                            }

                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        lastVisibleItemPosition = mLLM.findLastVisibleItemPosition();
                    }
                });
            }
        }
        //发送工作日志返回
        if(result instanceof CommonResponse){
            WorkNote news = new WorkNote();
            news.setId(mDataBase.generateNewPrimaryKey());
            news.setuID(GUID);
            news.setCreateTime(new Date(System.currentTimeMillis()));
            news.setContent(send_content);
            news.setSendPeopel(mDataBase.getLocalConfig().getName());
            news.setSendWork(mDataBase.getLocalConfig().getDepartment());
            if(((CommonResponse)result).getIq().getQuery().getErrorCode() == 0){
                mDataBase.setWorkNote(news);
            }else{
                showMessage("发送日志失败");
            }
            mWorkNoteAdapter.addData(news);
            mWorkNoteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addData(Object result) {

    }

    //新增
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more:
                if (moreTools.getVisibility() == View.GONE){
                    Animation animation = AnimationUtils.loadAnimation(this,R.anim.activity_translate_in);
                    moreTools.startAnimation(animation);
                    moreTools.setVisibility(View.VISIBLE);
                    picRecyclerView = (RecyclerView) findViewById(R.id.picRecycleView);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(WorkNoteActivity.this);
                    mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    picRecyclerView.setLayoutManager(mLayoutManager);
                    picRecyclerView.setAdapter(mDealAdapter);
                }else {
                    Animation animation = AnimationUtils.loadAnimation(this,R.anim.activity_translate_out);
                    moreTools.startAnimation(animation);
                    moreTools.setVisibility(View.GONE);
                }
                break;
        }
    }
    //新增
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPicker.REQUEST_SELECTED){
            if (resultCode == RESULT_OK){
                List<String> mData = data.getStringArrayListExtra(PhotoPicker.EXTRA_RESULT);
                mImagePath = (ArrayList<String>) mData;
                mDealAdapter.addData(mData);
            }
        }
    }

    @Override
    public void onComplected(Object result) {

    }

    @Override
    public void onFailed(BaseResponse msg) {

    }


    private class DealAdapter extends RecyclerView.Adapter{

        private List<String> mData;

        public DealAdapter(){
            mData = new ArrayList<>();
        }

        public void addData(List<String> d){
            this.mData = d;
            this.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_image_view,parent,false);
            return new DealViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof DealViewHolder){
                if (position == mData.size()){
                    Glide.with(holder.itemView.getContext())
                            .load(R.mipmap.icon_addpic_unfocused)
                            .into(((DealViewHolder) holder).mImageView);
                    ((DealViewHolder) holder).mImageView.setOnClickListener(v -> {
                        PermissionUtils.requestCameraPermission(WorkNoteActivity.this);
                        PermissionUtils.requestStoragePermission(WorkNoteActivity.this);
                        PhotoPicker.load()
                                .filter(filter) // 照片属性过滤
                                .gridColumns(4) // 照片列表显示列数
                                .showCamera(true)
                                .multi()
                                .maxPickSize(15) // 最大选择数
                                .selectedPaths(mImagePath) // 已选择的照片地址
                                .start(WorkNoteActivity.this); // 从Fragment、Activity中启动
                    });
                }else {
                    Glide.with(holder.itemView.getContext())
                            .load("file://" + mData.get(position))
                            .into(((DealViewHolder) holder).mImageView);
                }
            }
        }

        @Override
        public int getItemCount() {
            return mData.size()+1;
        }
    }

    private class DealViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public DealViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img_content);
        }
    }

}
