package com.zhicheng.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.service.OfficialBaseGridService;
import com.zhicheng.api.view.CaseQueryView;
import com.zhicheng.bean.http.CaseGridResponse;
import com.zhicheng.bean.json.CaseGridRequest;
import com.zhicheng.bean.json.PersonQueryRequest;
import com.zhicheng.common.URL;
import com.zhicheng.utils.common.AnimationUtils;
import com.zhicheng.utils.common.UIUtils;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BaseGridSearchActivity extends BaseActivity implements View.OnClickListener{
    private TextView title_name;
    private TextView grid_base_no;
    private EditText grid_base_sender;
    private TextView grid_base_start_time,grid_base_end_time;
    private String mStart_tme,mEnd_time;
    private PopupWindow popupWindow;
    private RadioGroup grid_group;
    private RadioButton row_button,village_button,grid_button;
    private ListView grid_listView;
    private ArrayAdapter<String> arrayAdapter;
    private int Type = 0;
    private String str1 = "";
    private String str2 = "";
    private String str3 = "";
    private ViewGroup parentView;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.grid.gridNo")) {
                String value = intent.getStringExtra("value");
                grid_base_no.setText(value);
            }
        }
    };

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_base_grid_search);
        parentView = (ViewGroup) findViewById(android.R.id.content).getRootView();;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.grid.gridNo");
        registerReceiver(receiver, intentFilter);
        title_name = (TextView)findViewById(R.id.title_name);
        grid_base_no = (TextView)findViewById(R.id.grid_base_no);
        grid_base_sender = (EditText)findViewById(R.id.grid_base_sender);
        grid_base_start_time = (TextView)findViewById(R.id.grid_base_start_time);
        grid_base_end_time = (TextView)findViewById(R.id.grid_base_end_time);
        title_name.setText(getResources().getString(R.string.grid_base_more_search));

        grid_base_no.setOnClickListener(this);
        grid_base_start_time.setOnClickListener(this);
        grid_base_end_time.setOnClickListener(this);

    }

    @Override
    protected int getMenuID() {
        return R.menu.official_grid_search;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search){
            PersonQueryRequest.IqBean.QueryBean qb = new PersonQueryRequest.IqBean.QueryBean();
            qb.setPkey("");
            qb.setBegintime(mStart_tme);
            qb.setEndtime(mEnd_time);
            qb.setUserid(grid_base_sender.getText().toString());
            qb.setGrid(grid_base_no.getText().toString());
            qb.setPagenum(1);

            Intent intent = new Intent();
            intent.setAction("com.grid.search");
            intent.putExtra("value",qb);
            sendBroadcast(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.grid_base_no:
                //Intent mIntent = new Intent(this, GridNameActivity.class);
                //UIUtils.startActivity(mIntent);
                showPopupWindow(view);
                break;

            case R.id.grid_base_start_time:
                TimePickerView spvTime = new TimePickerView.Builder(BaseGridSearchActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
                        grid_base_start_time.setText(sdf.format(date));
                        mStart_tme = grid_base_start_time.getText().toString();
                    }
                })
                        .setSubmitText("确认")
                        .setCancelText("取消")
                        .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                        .build();
                //时间选择器
                spvTime.setDate(Calendar.getInstance());
                spvTime.show();
                break;
            case R.id.grid_base_end_time:
                //时间选择器
                TimePickerView epvTime = new TimePickerView.Builder(BaseGridSearchActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
                        grid_base_end_time.setText(sdf.format(date));
                        mEnd_time = grid_base_end_time.getText().toString();
                    }
                })
                        .setSubmitText("确认")
                        .setCancelText("取消")
                        .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                        .build();
                epvTime.setDate(Calendar.getInstance());
                epvTime.show();
                break;
        }
    }

    private void showPopupWindow(View view){
        if (popupWindow!=null && popupWindow.isShowing()){
            popupWindow.dismiss();
            popupWindow=null;
        }else {
            View contentView=LayoutInflater.from(this).inflate(R.layout.b_search_grid_select,null);
            int width = getWindowManager().getDefaultDisplay().getWidth();
            int height  = getWindowManager().getDefaultDisplay().getHeight();
            popupWindow = new PopupWindow(contentView,width -width/4,width);
            AnimationUtils.darkBackgroundColor(getWindow(),0.5f);
            grid_group = (RadioGroup)contentView.findViewById(R.id.grid_group);
            row_button= (RadioButton) contentView.findViewById(R.id.row_button);
            grid_button= (RadioButton) contentView.findViewById(R.id.grid_button);
            village_button= (RadioButton) contentView.findViewById(R.id.village_button);
            grid_listView = (ListView) contentView.findViewById(R.id.grid_listView);
            arrayAdapter = new ArrayAdapter<String>(this,R.layout.text_view);
            grid_listView.setAdapter(arrayAdapter);
            getGridData(0);
            Type = 0;
            grid_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (Type == 0){
                        str1 = parent.getItemAtPosition(position).toString();
                        grid_base_no.setText(str1);
                        Type=3;
                        if (popupWindow != null && popupWindow.isShowing()){
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                    }else if (Type==1){
                        str2=parent.getItemAtPosition(position).toString();
                        grid_base_no.setText(str2);
                        Type=3;
                        if (popupWindow != null && popupWindow.isShowing()){
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                    }else if (Type==2){
                        str3=parent.getItemAtPosition(position).toString();
                        grid_base_no.setText(str3);
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
                    AnimationUtils.darkBackgroundColor(getWindow(),1.0f);
                }
            });
            popupWindow.showAtLocation(parentView, Gravity.CENTER,0,0);
        }

    }

    //去重
    public void removeDuplicate(List<String> list)   {
        HashSet h  =   new  HashSet(list);
        list.clear();
        list.addAll(h);
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

    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}
