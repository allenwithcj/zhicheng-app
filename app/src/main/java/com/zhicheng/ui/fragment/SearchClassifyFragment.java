package com.zhicheng.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.CaseQueryPresenterImpl;
import com.zhicheng.api.view.CaseQueryView;
import com.zhicheng.bean.http.CaseQueryResponse;
import com.zhicheng.bean.json.CaseQueryRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Donson on 2017/1/17.
 */

public class SearchClassifyFragment extends BaseFragment implements CaseQueryView,
        SwipeRefreshLayout.OnRefreshListener,RadioGroup.OnCheckedChangeListener,View.OnClickListener {

    private CaseQueryPresenterImpl mCaseQueryPresenterImpl;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mainContent;
    private SearchClassifyAdapter mSearchClassifyAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private int start;
    private RadioGroup rg_type_group;
    private RadioButton allThings,NoFinish,Finished;
    private Button btn_cancel,btn_confirm;
    private LinearLayout date_layout;
    private TextView date_txt;
    private String mCaseTime = "";
    private String mManageState = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static SearchClassifyFragment newInstance(String request){
        SearchClassifyFragment fragment = new SearchClassifyFragment();
        Bundle b = new Bundle();
        b.putString("request",request);
        fragment.setArguments(b);
        return fragment;
    }

    public static SearchClassifyFragment newInstance(){
        SearchClassifyFragment fragment = new SearchClassifyFragment();
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.z_search_classify,container,false);
    }
    @Override
    protected void initEvents() {
        mDrawerLayout = (DrawerLayout) mRootView.findViewById(R.id.drawer_layout);
        mSwipeRefreshLayout = (SwipeRefreshLayout)mRootView.findViewById(R.id.swipeRefresh);
        mainContent = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        mCaseQueryPresenterImpl = new CaseQueryPresenterImpl(this);
        //主内容RecyclerView
        mSearchClassifyAdapter = new SearchClassifyAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mainContent.setLayoutManager(mLinearLayoutManager);
        mainContent.setAdapter(mSearchClassifyAdapter);
        mainContent.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //侧滑栏内容RecyclerView
        rg_type_group = (RadioGroup) mRootView.findViewById(R.id.rg_type_group);
        allThings = (RadioButton)mRootView.findViewById(R.id.allThings);
        NoFinish = (RadioButton)mRootView.findViewById(R.id.NoFinish);
        Finished = (RadioButton)mRootView.findViewById(R.id.Finished);

        btn_cancel = (Button)mRootView.findViewById(R.id.btn_cancel);
        btn_confirm = (Button)mRootView.findViewById(R.id.btn_confirm);
        date_layout = (LinearLayout)mRootView.findViewById(R.id.date_layout);
        date_txt = (TextView)mRootView.findViewById(R.id.Date);


        btn_cancel.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        date_layout.setOnClickListener(this);
        rg_type_group.setOnCheckedChangeListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,null,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void initData(boolean isSavedNull) {
        onRefresh();
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(mRootView,msg,Snackbar.LENGTH_SHORT).show();
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
        if(result instanceof CaseQueryResponse){
            if(((CaseQueryResponse) result).getIq().getQuery().getErrorCode() == 0){
                mSearchClassifyAdapter.addAllDate(((CaseQueryResponse) result).getIq().getQuery().getCaselistcon().getCases());
            }
        }
    }

    @Override
    public void addData(Object result) {
        if(result instanceof CaseQueryResponse){
            if(((CaseQueryResponse) result).getIq().getQuery().getErrorCode() == 0){
                mSearchClassifyAdapter.addDataList(((CaseQueryResponse) result).getIq().getQuery().getCaselistcon().getCases());
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add("筛选").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("筛选")){
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return true;
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        start = 1;
        String entity = createObj(start,mCaseTime,mManageState);
        mCaseQueryPresenterImpl.caseQuery(entity,start);
        start ++;
    }

    private String createObj(int page,String caseTime,String manageSate){
        CaseQueryRequest caseQueryRequest = new CaseQueryRequest();
        CaseQueryRequest.IqBean iq = new CaseQueryRequest.IqBean();
        CaseQueryRequest.IqBean.QueryBean qb = new CaseQueryRequest.IqBean.QueryBean();
        iq.setNamespace("CaseQueryRequest");
        qb.setCaseTime(caseTime);
        qb.setManageStatus(manageSate);
        qb.setPageNum(page);
        iq.setQuery(qb);
        caseQueryRequest.setIq(iq);
        Gson gson = new Gson();
        return gson.toJson(caseQueryRequest);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i == allThings.getId()){
            mManageState = "";
        }else if(i == Finished.getId()){
            mManageState = "1";
        }else if(i == NoFinish.getId()){
            mManageState = "0";
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cancel:
                mCaseTime = "";
                mManageState = "";
                date_txt.setText("");
                allThings.setChecked(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                onRefresh();
                break;
            case R.id.btn_confirm:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                onRefresh();
                break;

            case R.id.date_layout:
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
                        date_txt.setText(sdf.format(date));
                        mCaseTime = date_txt.getText().toString();
                    }
                }).build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;
        }
    }

    private class SearchClassifyAdapter extends RecyclerView.Adapter{
        private List<CaseQueryResponse.IqBean.QueryBean.CaselistconBean.CasesBean> caseList;
        private String[] tag = {"编号:","办理时间:","地址:"};

        public SearchClassifyAdapter(){

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_search_classify_content,parent,false);
            return new SearchClassifyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof SearchClassifyViewHolder){
                ((SearchClassifyViewHolder) holder).caseNo.setText(tag[0]+caseList.get(position).getCasenum());
                String caseTime = caseList.get(position).getCasetime();
                if(caseTime != null){
                    ((SearchClassifyViewHolder) holder).caseTime.setText(tag[1]+caseTime.substring(0,caseTime.length()-2).toString());
                }
                ((SearchClassifyViewHolder) holder).caseAddress.setText(tag[2]+caseList.get(position).getCaseaddress());
                ((SearchClassifyViewHolder) holder).case_layout.setOnClickListener(view -> {
                    if(mManageState.equals("")){


                    }else if(mManageState.equals("1")){
                        //Intent intent=new Intent(UIUtils.getContext(),OfficialFinishDetail.class);
                        //intent.putExtra("detailId",caseList.get(position).getId());
                        //UIUtils.startActivity(intent);

                    }else if(mManageState.equals("0")){
                        //Intent intent = new Intent(UIUtils.getContext(),OfficialNoFinishDetails.class);
                        //intent.putExtra("detailId",caseList.get(position).getId());
                        //UIUtils.startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if(caseList != null){
                return caseList.size();
            }
            return 0;
        }

        public void addAllDate(List<CaseQueryResponse.IqBean.QueryBean.CaselistconBean.CasesBean> caselistcon) {
            this.caseList = caselistcon;
            notifyDataSetChanged();
        }

        public void addDataList(List<CaseQueryResponse.IqBean.QueryBean.CaselistconBean.CasesBean> cases) {
            int page = this.caseList.size();
            this.caseList.addAll(cases);
            this.notifyItemRangeInserted(page, cases.size());
        }

        private class SearchClassifyViewHolder extends RecyclerView.ViewHolder {

            private TextView caseNo;
            private TextView caseTime;
            private TextView caseAddress;
            private RelativeLayout case_layout;

            public SearchClassifyViewHolder(View itemView) {
                super(itemView);
                caseNo = (TextView) itemView.findViewById(R.id.caseNo);
                caseTime = (TextView) itemView.findViewById(R.id.caseTime);
                caseAddress = (TextView) itemView.findViewById(R.id.caseAddress);
                case_layout = (RelativeLayout) itemView.findViewById(R.id.case_layout);
            }
        }
    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mSearchClassifyAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        private void onLoadMore() {
            String entity = createObj(start,mCaseTime,mManageState);
            mCaseQueryPresenterImpl.caseQuery(entity,start);
            start ++;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    }
}
