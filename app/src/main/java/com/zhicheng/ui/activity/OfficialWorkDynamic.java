package com.zhicheng.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.ui.adapter.OfficialDynamicAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Donson on 2017/1/15.
 */

public class OfficialWorkDynamic extends BaseActivity implements OfficialView,SwipeRefreshLayout.OnRefreshListener{

    private int start = 1;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private OfficialPresenterImpl mOfficialPresenterImpl;
    private OfficialDynamicAdapter mOfficialDynamicAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    //新增动态
    private ViewGroup parentView;
    private PopupWindow mPopupWindow;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official_basegrid);
        parentView = (ViewGroup) findViewById(android.R.id.content).getRootView();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        mOfficialDynamicAdapter = new OfficialDynamicAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mOfficialDynamicAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(mToolbar,msg,Snackbar.LENGTH_SHORT).show();
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
        if (result instanceof OfficialWorkDynamicList){
            mOfficialDynamicAdapter.setDataList(((OfficialWorkDynamicList) result).getIq().getQuery().getPrelogcon().getPrelogs());
        }
    }

    private void onLoadMore(){
        String strEntity = createObj(start,"2");
        mOfficialPresenterImpl.loadDynamic(strEntity,start);
        start++;
    }

    @Override
    public void addData(Object result) {
        if (result instanceof OfficialWorkDynamicList){
            mOfficialDynamicAdapter.addDataList(((OfficialWorkDynamicList) result).getIq().getQuery().getPrelogcon().getPrelogs());
        }
    }

    @Override
    public void onRefresh() {
        start = 1;
        String strEntity = createObj(start,"2");
        mOfficialPresenterImpl.loadDynamic(strEntity,start);
        start++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("工作动态");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_span){
            //发送动态，弹出POPView形式
            //mOfficialPresenterImpl.upDynamic();已经写好了上传动态
            Snackbar.make(mToolbar,"发送动态",Snackbar.LENGTH_SHORT).show();
            View pop_view = getLayoutInflater().inflate(R.layout.c_deal,parentView,false);
            EditText mEdit = (EditText) pop_view.findViewById(R.id.suggestion);
            String guid = UUID.randomUUID().toString();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getMenuID() {
        return R.menu.menu_workspan;
    }

    //创建请求json
    private String createObj(int page,String type){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        map1.put("namespace","PersonalDynamicRequest");
        if (type.equals("2")){
            map2.put("type",type);
            map2.put("page",String.valueOf(page));
        }else {
            map2.put("type",type);
            map2.put("id","");//GUID唯一id
            map2.put("cont","");//内容
            map2.put("attguid","");//附件guid
            map2.put("location","");//位置
        }
        map1.put("query",map2);
        map.put("iq",map1);
        return gson.toJson(map);
    }

    class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mOfficialDynamicAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    }
}
