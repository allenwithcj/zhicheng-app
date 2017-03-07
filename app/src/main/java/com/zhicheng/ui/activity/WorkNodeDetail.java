package com.zhicheng.ui.activity;

import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.database.WorkNote;
import com.zhicheng.api.presenter.impl.WorkNodePresenterImpl;
import com.zhicheng.api.view.WorkNodeView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.json.PersonalLogMaRequest;
import com.zhicheng.common.Constant;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by hp on 2017/3/3.
 */

public class WorkNodeDetail extends BaseActivity implements WorkNodeView {
    private TextView publicationunit;
    private TextView NoteTime;
    private TextView publicationpeople;
    private TextView content_txt;
    private EditText mini_content;
    private LinearLayout btn_layout;
    private Button update,delete;
    private WorkNote mWorkNote;
    private boolean b = false;
    private WorkNodePresenterImpl mWorkNodePresenterImpl;
    private DatabaseHelper mDataBase;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_worknote_detail);
        mDataBase = new DatabaseHelper();
        mWorkNote = (WorkNote) getIntent().getSerializableExtra("mWorkNote");
        mWorkNodePresenterImpl = new WorkNodePresenterImpl(this);
        publicationunit = (TextView) findViewById(R.id.publicationunit);
        NoteTime = (TextView) findViewById(R.id.NoteTime);
        publicationpeople = (TextView) findViewById(R.id.publicationpeople);
        content_txt = (TextView) findViewById(R.id.content_txt);
        mini_content = (EditText) findViewById(R.id.mini_content);
        btn_layout = (LinearLayout) findViewById(R.id.btn_layout);
        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);
        if(mWorkNote != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss", Locale.CHINESE);
            publicationunit.setText("单位:"+mWorkNote.getSendWork());
            NoteTime.setText("发表时间:"+sdf.format(mWorkNote.getCreateTime()));
            publicationpeople.setText("发表人:"+mWorkNote.getSendPeopel());
            content_txt.setText("日志内容:");
            mini_content.setText(mWorkNote.getContent());
            mini_content.setSelection(mWorkNote.getContent().length());
        }
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);

        update.setOnClickListener(view -> {
            Constant.LOG_OPERATE_TYPE = "update";
            PersonalLogMaRequest pr = new PersonalLogMaRequest();
            PersonalLogMaRequest.IqBean iqb = new PersonalLogMaRequest.IqBean();
            iqb.setNamespace("PersonalLogMaRequest");
            PersonalLogMaRequest.IqBean.QueryBean qb = new PersonalLogMaRequest.IqBean.QueryBean();
            qb.setType("4");
            qb.setCon(mini_content.getText().toString());
            qb.setId(mWorkNote.getuID());
            iqb.setQuery(qb);
            pr.setIq(iqb);
            Gson gson = new Gson();
            mWorkNodePresenterImpl.updateWorkNodes(gson.toJson(pr));
        });

        delete.setOnClickListener(view -> {
            Constant.LOG_OPERATE_TYPE = "delete";
            PersonalLogMaRequest pr = new PersonalLogMaRequest();
            PersonalLogMaRequest.IqBean iqb = new PersonalLogMaRequest.IqBean();
            iqb.setNamespace("PersonalLogMaRequest");
            PersonalLogMaRequest.IqBean.QueryBean qb = new PersonalLogMaRequest.IqBean.QueryBean();
            qb.setType("2");
            qb.setId(mWorkNote.getuID());
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
        Snackbar.make(mToolbar,msg,Snackbar.LENGTH_SHORT).show();
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
                showMessage("操作失败");
            }
        }
    }

    @Override
    public void addData(Object result) {

    }
}
