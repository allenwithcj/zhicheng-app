package com.zhicheng.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.OfficialBaseGridService;
import com.zhicheng.api.common.service.SearchService;
import com.zhicheng.api.presenter.impl.CaseQueryPresenterImpl;
import com.zhicheng.api.view.CaseQueryView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CaseGridResponse;
import com.zhicheng.bean.http.CaseQueryResponse;
import com.zhicheng.bean.http.SearchBaoClassifyResponse;
import com.zhicheng.bean.json.CaseGridRequest;
import com.zhicheng.bean.json.CaseItemRequest;
import com.zhicheng.bean.json.CaseQueryRequest;
import com.zhicheng.common.URL;
import com.zhicheng.holder.itemsprovider.Line;
import com.zhicheng.ui.activity.GridNameActivity;
import com.zhicheng.ui.activity.OfficialFinishDetail;
import com.zhicheng.ui.activity.OfficialNoFinishDetails;
import com.zhicheng.ui.activity.SearchViewActivity;
import com.zhicheng.utils.common.AnimationUtils;
import com.zhicheng.utils.common.UIUtils;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2017/1/17.
 */

public class SearchClassifyFragment extends BaseFragment implements CaseQueryView,
        SwipeRefreshLayout.OnRefreshListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private CaseQueryPresenterImpl mCaseQueryPresenterImpl;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mainContent;
    private SearchClassifyAdapter mSearchClassifyAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private int start;
    private RadioGroup rg_type_group;
    private RadioButton handing,Finished,suspend;
    private Button btn_cancel, btn_confirm;
    private TextView firstClass,secondClass;
    private LinearLayout date_layout,grid_layout,event_layout;
    private TextView date_txt,grid_name;
    private String mCaseTime = "";
    private String mManageState = "";
    private String mGridName = "";
    private String mEventType = "";
    private String parentPoint = "";
    private String str1 = "";
    private String str2 = "";
    private String str3 = "";
    private int Type = 0;
    private RadioGroup grid_group;
    private RadioButton row_button,village_button,grid_button;
    private PopupWindow popupWindow;
    private ListView grid_listView;
    private TextView notice_tv;
    private ArrayAdapter<String> arrayAdapter;
    private int Sin=1;

    //事件统计
    private FloatingActionButton mFab;
    private ImageButton mEvent_close;
    private RecyclerView mEvent_cyc;


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals("com.grid.gridNo")) {
                String value = intent.getStringExtra("value");
                grid_name.setText(value);
//            }else if(intent.getAction().equals("com.search.classify.bao")){
//                ArrayList<String> mNode = intent.getStringArrayListExtra("node");
//                mEventType = mNode.get(8);
//                secondClass.setText(mEventType);
            }else if (intent.getAction().equals("com.searchNewFragment.item.result")){

//                if (!firstClass.getText().equals("第一类")){
//                    String item=intent.getStringExtra("item");
//                    secondClass.setText(item);
//                    mEventType=item;
//                }else{
//                    String item=intent.getStringExtra("item");
//                    firstClass.setText(item);
//                    secondClass.setText("第二类");
//                    mEventType=item;
//                }
                if (Sin==3){
                    String item=intent.getStringExtra("item");
                    secondClass.setText(item);
                    mEventType=item;
                }else {
                    parentPoint = intent.getStringExtra("parentPoint");
                    String item=intent.getStringExtra("item");
                    firstClass.setText(item);
                    secondClass.setText("第二类");
                    mEventType=item;
                    Sin=2;
                }
            }

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static SearchClassifyFragment newInstance(String request) {
        SearchClassifyFragment fragment = new SearchClassifyFragment();
        Bundle b = new Bundle();
        b.putString("request", request);
        fragment.setArguments(b);
        return fragment;
    }

    public static SearchClassifyFragment newInstance() {
        SearchClassifyFragment fragment = new SearchClassifyFragment();
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.z_search_classify, container, false);
    }

    @Override
    protected void initEvents() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.search.classify.bao");
        intentFilter.addAction("com.searchNewFragment.item.result");
        intentFilter.addAction("com.grid.gridNo");
        getActivity().registerReceiver(receiver, intentFilter);
        mDrawerLayout = (DrawerLayout) mRootView.findViewById(R.id.drawer_layout);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        mainContent = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        notice_tv= (TextView) mRootView.findViewById(R.id.notice_tv);
        mCaseQueryPresenterImpl = new CaseQueryPresenterImpl(this);
        //主内容RecyclerView
        mSearchClassifyAdapter = new SearchClassifyAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mainContent.setLayoutManager(mLinearLayoutManager);
        mainContent.setAdapter(mSearchClassifyAdapter);
        mainContent.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //侧滑栏内容RecyclerView
        rg_type_group = (RadioGroup) mRootView.findViewById(R.id.rg_type_group);
        handing = (RadioButton) mRootView.findViewById(R.id.handing);
        Finished = (RadioButton) mRootView.findViewById(R.id.Finished);
        suspend = (RadioButton) mRootView.findViewById(R.id.suspend);

        btn_cancel = (Button) mRootView.findViewById(R.id.btn_cancel);
        btn_confirm = (Button) mRootView.findViewById(R.id.btn_confirm);

        date_layout = (LinearLayout) mRootView.findViewById(R.id.date_layout);
        grid_layout = (LinearLayout) mRootView.findViewById(R.id.grid_layout);

        event_layout= (LinearLayout) mRootView.findViewById(R.id.event_layout);

        date_txt = (TextView) mRootView.findViewById(R.id.Date);
        grid_name = (TextView)mRootView.findViewById(R.id.grid_name);

        firstClass= (TextView) mRootView.findViewById(R.id.firstClass);
        secondClass= (TextView) mRootView.findViewById(R.id.secondClass);

        mFab = (FloatingActionButton)mRootView.findViewById(R.id.mFab);



        btn_cancel.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        date_layout.setOnClickListener(this);
        grid_layout.setOnClickListener(this);

        rg_type_group.setOnCheckedChangeListener(this);

        firstClass.setOnClickListener(this);
        secondClass.setOnClickListener(this);


        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, null, R.string.open, R.string.close) {
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

        //事件类型统计点击事件
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEventPopupWindow(view);
            }
        });
    }


    //事件统计弹出框
    private void showEventPopupWindow(View view) {
        if (popupWindow!=null && popupWindow.isShowing()){
            popupWindow.dismiss();
            popupWindow=null;
        }else {
            View contentView=LayoutInflater.from(getContext()).inflate(R.layout.activity_event_pop,null);
            int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
            int height  = getActivity().getWindowManager().getDefaultDisplay().getHeight();
            popupWindow = new PopupWindow(contentView,width - width/4,height - height/4);
            AnimationUtils.darkBackgroundColor(getActivity().getWindow(),0.5f);
            mEvent_close = (ImageButton)contentView.findViewById(R.id.event_close);
            mEvent_cyc = (RecyclerView)contentView.findViewById(R.id.mEvent_cyc);

            mEvent_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (popupWindow!=null && popupWindow.isShowing()){
                        popupWindow.dismiss();
                        popupWindow=null;
                    }
                }
            });

            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setAnimationStyle(R.style.popwin_anim_style);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    AnimationUtils.darkBackgroundColor(getActivity().getWindow(),1.0f);
                }
            });
            popupWindow.showAtLocation(mRootView, Gravity.CENTER,0,0);
        }
    }

    @Override
    protected void initData(boolean isSavedNull) {
        onRefresh();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
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
        if (result instanceof CaseQueryResponse) {
            if (((CaseQueryResponse) result).getIq().getQuery().getErrorCode() == 0) {
                mSearchClassifyAdapter.addAllDate(((CaseQueryResponse) result).getIq().getQuery().getCaselistcon().getCases());
                String str="<font color='gray_text'>共</font><font color='red'>"+((CaseQueryResponse) result).getIq().getQuery().getCaselistcon().getAllcasenum()+"</font><font color='gray_text'>宗事件，其中在办事件：</font><font color='red'>"
                        +((CaseQueryResponse) result).getIq().getQuery().getCaselistcon().getZtal()+"</font><font color='gray_text'>宗，办结事件：</font><font color='red'>"+((CaseQueryResponse) result).getIq().getQuery().getCaselistcon().getBtal()
                        +"</font><font color='gray_text'>宗，挂起事件</font><font color='red'>"+((CaseQueryResponse) result).getIq().getQuery().getCaselistcon().getGtal()+"</font><font color='gray_text'>宗</font>";
                notice_tv.setText(Html.fromHtml(str));
            } else {
                showMessage(((CaseQueryResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public void addData(Object result) {
        if (result instanceof CaseQueryResponse) {
            if (((CaseQueryResponse) result).getIq().getQuery().getErrorCode() == 0) {
                mSearchClassifyAdapter.addDataList(((CaseQueryResponse) result).getIq().getQuery().getCaselistcon().getCases());
            } else {
                showMessage(((CaseQueryResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add("筛选").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("筛选")) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
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
        String entity = createObj(start, mCaseTime, mManageState, mGridName,mEventType);
        mCaseQueryPresenterImpl.caseQuery(entity, start);
        start++;
    }

    private String createObj(int page, String caseTime, String manageSate, String mGridName,String mEventType) {
        CaseQueryRequest caseQueryRequest = new CaseQueryRequest();
        CaseQueryRequest.IqBean iq = new CaseQueryRequest.IqBean();
        CaseQueryRequest.IqBean.QueryBean qb = new CaseQueryRequest.IqBean.QueryBean();
        iq.setNamespace("CaseQueryRequest");
        qb.setCaseTime(caseTime);
        qb.setManageStatus(manageSate);
        qb.setPageNum(page);
        qb.setEventType(mEventType);
        qb.setCaseName(mGridName);
        iq.setQuery(qb);
        caseQueryRequest.setIq(iq);
        Gson gson = new Gson();
        return gson.toJson(caseQueryRequest);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == handing.getId()) {
            mManageState = "1";
        } else if (i == Finished.getId()) {
            mManageState = "2";
        } else if (i == suspend.getId()) {
            mManageState = "3";
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                rg_type_group.clearCheck();
                mManageState = "";
                mCaseTime = "";
                mGridName = "";
                mEventType = "";
                firstClass.setText("第一类");
                secondClass.setText("第二类");
                date_txt.setText("");
                grid_name.setText("");
                mDrawerLayout.closeDrawer(GravityCompat.START);
                refresh();
                break;

            case R.id.btn_confirm:
                mGridName = grid_name.getText().toString();
                mDrawerLayout.closeDrawer(GravityCompat.START);
                refresh();
                break;

            case R.id.date_layout:
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
                        date_txt.setText(sdf.format(date));
                        mCaseTime = date_txt.getText().toString();
                    }
                }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                        .setSubmitText("确认")
                        .setCancelText("取消")
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;

            case R.id.grid_layout:
//                Intent mIntent = new Intent(getActivity(), GridNameActivity.class);
//                UIUtils.startActivity(mIntent);
                showPopupWindow(view);
                break;
            case R.id.firstClass:
                Sin=2;
                parentPoint="";
                Intent intent =new Intent(getActivity(),SearchViewActivity.class);
                intent.putExtra("fragment","Search");
                intent.putExtra("isClassify","false");
                intent.putExtra("parentPoint",parentPoint);
                UIUtils.startActivity(intent);
                break;
            case R.id.secondClass:
                if (firstClass.getText().equals("第一类")){
                    secondClass.setEnabled(false);
                    Toast.makeText(getContext(),"请选择第一类",Toast.LENGTH_SHORT).show();
                }else {
                    Sin=3;
                    Intent sIntent =new Intent(getActivity(),SearchViewActivity.class);
                    sIntent.putExtra("fragment","Search");
                    sIntent.putExtra("isClassify","false");
                    sIntent.putExtra("parentPoint",parentPoint);
                    UIUtils.startActivity(sIntent);
                }
                break;


        }
    }
    private void showPopupWindow(View view){
        if (popupWindow!=null && popupWindow.isShowing()){
            popupWindow.dismiss();
            popupWindow=null;
        }else {
            View contentView=LayoutInflater.from(getContext()).inflate(R.layout.b_search_grid_select,null);
            int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
            int height  = getActivity().getWindowManager().getDefaultDisplay().getHeight();
            popupWindow = new PopupWindow(contentView,width -width/4,width);
            AnimationUtils.darkBackgroundColor(getActivity().getWindow(),0.5f);
            grid_group = (RadioGroup)contentView.findViewById(R.id.grid_group);
            row_button= (RadioButton) contentView.findViewById(R.id.row_button);
            grid_button= (RadioButton) contentView.findViewById(R.id.grid_button);
            village_button= (RadioButton) contentView.findViewById(R.id.village_button);
            grid_listView = (ListView) contentView.findViewById(R.id.grid_listView);
            arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.text_view);
            grid_listView.setAdapter(arrayAdapter);
            getGridData(0);
            Type = 0;
            grid_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (Type == 0){
                        str1 = parent.getItemAtPosition(position).toString();
                        grid_name.setText(str1);
                        Type=3;
                        if (popupWindow != null && popupWindow.isShowing()){
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                    }else if (Type==1){
                        str2=parent.getItemAtPosition(position).toString();
                        grid_name.setText(str2);
                        Type=3;
                        if (popupWindow != null && popupWindow.isShowing()){
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                    }else if (Type==2){

                        str3=parent.getItemAtPosition(position).toString();
                        grid_name.setText(str3);
                        if (popupWindow != null && popupWindow.isShowing()){
                            popupWindow.dismiss();
                            popupWindow = null;
                            Type=3;
                        }
                    }

                }
            });

            grid_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == row_button.getId()) {
                        getGridData(0);
                        Type = 0;
                    } else if (i == village_button.getId()) {
                        getGridData(1);
                        Type = 1;
                    } else if (i == grid_button.getId()) {
                        getGridData(2);
                        Type = 2;
                    }
                }
            });

            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setAnimationStyle(R.style.popwin_anim_style);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    AnimationUtils.darkBackgroundColor(getActivity().getWindow(),1.0f);
                }
            });
            popupWindow.showAtLocation(mRootView, Gravity.CENTER,0,0);
        }

    }



    private class SearchClassifyAdapter extends RecyclerView.Adapter {
        private List<CaseQueryResponse.IqBean.QueryBean.CaselistconBean.CasesBean> caseList;
        private String[] tag = {"编号:", "办理时间:", "事件详情:"};

        public SearchClassifyAdapter() {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_search_classify_content, parent, false);
            return new SearchClassifyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof SearchClassifyViewHolder) {
                ((SearchClassifyViewHolder) holder).caseNo.setText(tag[0] + caseList.get(position).getCasenum());
                String caseTime = caseList.get(position).getCasetime();
                if (caseTime != null) {
                    ((SearchClassifyViewHolder) holder).caseTime.setText(tag[1] + caseTime.substring(0, caseTime.length() - 2).toString());
                }
                ((SearchClassifyViewHolder) holder).caseAddress.setText(tag[2] + caseList.get(position).getCaseaddress());
                ((SearchClassifyViewHolder) holder).case_layout.setOnClickListener(view -> {
                    Intent intent = new Intent(UIUtils.getContext(), OfficialFinishDetail.class);
                    intent.putExtra("detailId", caseList.get(position).getId());
                    UIUtils.startActivity(intent);
                });
            }
        }

        @Override
        public int getItemCount() {
            return caseList == null ? 0 : caseList.size();
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
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem != -1 && lastVisibleItem + 1 == mSearchClassifyAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        private void onLoadMore() {
            String entity = createObj(start, mCaseTime, mManageState,mGridName,mEventType);
            mCaseQueryPresenterImpl.caseQuery(entity, start);
            start++;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    }

    //去重
    public void removeDuplicate(List<String> list)   {
        HashSet h  =   new  HashSet(list);
        list.clear();
        list.addAll(h);
        System.out.println(list);
    }

    private void getGridData(int type){

        CaseGridRequest mCaseGridRequest = new CaseGridRequest();
        CaseGridRequest.IqBean iqb = new CaseGridRequest.IqBean();
        CaseGridRequest.IqBean.QueryBean qb = new CaseGridRequest.IqBean.QueryBean();
        iqb.setNamespace("CaseGridRequest");
        qb.setFlag("2");
        iqb.setQuery(qb);
        mCaseGridRequest.setIq(iqb);
        Gson gson = new Gson();

        OfficialBaseGridService mOfficialBaseGridService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialBaseGridService.class);
        mOfficialBaseGridService.loadGridNames(gson.toJson(mCaseGridRequest))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<CaseGridResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            showMessage(null);
                            return;
                        }
                    }

                    @Override
                    public void onNext(Response<CaseGridResponse> mCaseGridResponseResponse) {

                        if (mCaseGridResponseResponse.isSuccessful()) {
                            arrayAdapter.clear();
                            if (type == 0){
                                List<CaseGridResponse.IqBean.QueryBean.ItemsBean> items = (List<CaseGridResponse.IqBean.QueryBean.ItemsBean>) mCaseGridResponseResponse.body().getIq().getQuery().getItems();
                                List<String> data = new ArrayList<String>();
                                for (CaseGridResponse.IqBean.QueryBean.ItemsBean str : items){
                                    data.add(str.getFirstname());
                                }
                                removeDuplicate(data);
                                arrayAdapter.addAll(data);
                                arrayAdapter.notifyDataSetChanged();
                            }else if (type==1){
                                List<CaseGridResponse.IqBean.QueryBean.ItemsBean> items=mCaseGridResponseResponse.body().getIq().getQuery().getItems();
                                List<String> data = new ArrayList<String>();
                                for (CaseGridResponse.IqBean.QueryBean.ItemsBean str : items){
                                    data.add(str.getSecondname());
                                }
                                removeDuplicate(data);
                                arrayAdapter.addAll(data);
                                arrayAdapter.notifyDataSetChanged();
                            }else  if (type==2){
                                List<CaseGridResponse.IqBean.QueryBean.ItemsBean> items=mCaseGridResponseResponse.body().getIq().getQuery().getItems();
                                List<String> data = new ArrayList<String>();
                                for (CaseGridResponse.IqBean.QueryBean.ItemsBean str : items){
                                    data.add(str.getThirdname());
                                }
                                removeDuplicate(data);
                                arrayAdapter.addAll(data);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        } else {
                            showMessage(mCaseGridResponseResponse.message());
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
        if (popupWindow!=null && popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }


}
