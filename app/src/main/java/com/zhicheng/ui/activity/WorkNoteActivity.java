package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    private String send_content;
    private String GUID;

    private LinearLayout moreTools;
    private PhotoFilter filter;
    private ArrayList<String> mImagePath;//选择照片地址集
    private RecyclerView picRecyclerView;
    private DealAdapter mDealAdapter;
    private AlertDialog dialog;

    private DrawerLayout mDrawerLayout;
    private LinearLayout date_layout;
    private TextView date_txt;
    private EditText sender;

    public WorkNoteActivity getInstance(){
        return instance;
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    //下拉加载更多
    private void refresh() {
        String strEntity = createObj(start);
        mWorkNodePresenter.loadWorkNodes(strEntity,start);
        start ++;
    }

    //加载数据
    private void onLoadWorkNodes() {
        start = 1;
        String strEntity = createObj(start);
        mWorkNodePresenter.loadWorkNodes(strEntity,start);
        start++;
    }

    private String createObj(int start) {
        PersonalLogMaRequest pm = new PersonalLogMaRequest();
        PersonalLogMaRequest.IqBean iqb = new PersonalLogMaRequest.IqBean();
        iqb.setNamespace("PersonalLogMaRequest");
        PersonalLogMaRequest.IqBean.QueryBean qb = new PersonalLogMaRequest.IqBean.QueryBean();
        qb.setType("5");
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
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        date_layout = (LinearLayout) findViewById(R.id.date_layout);
        date_txt = (TextView) findViewById(R.id.Date);
        sender = (EditText) findViewById(R.id.sender);

        mDealAdapter = new DealAdapter();
        mImagePath = new ArrayList<>();
        PhotoPicker.init(new GlideImageLoader(),null);
        filter = PhotoFilter.build();
        filter.showGif(false);
        voice.setOnClickListener(this);
        mInput.setOnClickListener(this);
        emoji.setOnClickListener(this);
        more.setOnClickListener(this);
        date_layout.setOnClickListener(this);

        mLLM = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true);
        mRecyclerView.setLayoutManager(mLLM);
        mWorkNoteAdapter = new WorkNoteAdapter();
        mRecyclerView.setAdapter(mWorkNoteAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {
        onLoadWorkNodes();
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
                dialog = new AlertDialog.Builder(this,R.style.dialog)
                        .setView(R.layout.z_loading_view)
                        .setCancelable(false)
                        .create();
                dialog.show();
                if (mInput.getText().toString().isEmpty()){
                    dialog.dismiss();
                    Snackbar.make(mToolbar,"工作日志不能为空", Snackbar.LENGTH_SHORT).show();
                }else {
                        if(mImagePath.size() != 0){
                            GUID = UUID.randomUUID().toString();
                            send_content = mInput.getText().toString();
                            sendMessage(mImagePath,GUID,send_content);
                        }else{
                            dialog.dismiss();
                            Toast.makeText(this,"工作日志图片不能为空", Toast.LENGTH_SHORT).show();
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
    protected int getMenuID() {
        return R.menu.menu_worknode_search;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("工作日志");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_filter) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }else if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mInput.getWindowToken(),0);
    }


    @Override
    public void showMessage(String msg) {
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshData(Object result) {
        if(result instanceof PersonalLogMaResponse){
            //查询工作日志返回
            if(((PersonalLogMaResponse)result).getIq().getQuery().getErrorCode().equals("0")){
                List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogList = ((PersonalLogMaResponse)result).getIq().getQuery().getPrelogcon().getPrelogs();
                mWorkNoteAdapter.addAllData(prelogList);
            }
            //发送工作日志返回
         }else if(result instanceof CommonResponse){
            if(((CommonResponse)result).getIq().getQuery().getErrorCode() == 0){
                mInput.setText("");
                mImagePath.clear();
                onLoadWorkNodes();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mInput.getWindowToken(),0);
                showMessage("发送日志成功");
                mRecyclerView.smoothScrollToPosition(0);
                if (moreTools.getVisibility() == View.VISIBLE){
                    moreTools.setVisibility(View.GONE);
                }
            }else{
                showMessage("发送日志失败");
            }
        }
    }

    @Override
    public void addData(Object result) {
        //查询工作日志返回
        if(((PersonalLogMaResponse)result).getIq().getQuery().getErrorCode().equals("0")){
            List<PersonalLogMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogList = ((PersonalLogMaResponse)result).getIq().getQuery().getPrelogcon().getPrelogs();
            mWorkNoteAdapter.addDataList(prelogList);
        }
    }

    //新增
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more:
                if (moreTools.getVisibility() == View.GONE){
                    moreTools.setVisibility(View.VISIBLE);
                    picRecyclerView = (RecyclerView) findViewById(R.id.picRecycleView);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(WorkNoteActivity.this);
                    mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    picRecyclerView.setLayoutManager(mLayoutManager);
                    picRecyclerView.setAdapter(mDealAdapter);
                }else {
                    moreTools.setVisibility(View.GONE);
                }
                break;
            case R.id.date_layout:
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
                        date_txt.setText(sdf.format(date));
                    }
                }).build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;
            case R.id.btn_search:

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
