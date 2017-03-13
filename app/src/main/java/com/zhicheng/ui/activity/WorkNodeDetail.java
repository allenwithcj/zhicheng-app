package com.zhicheng.ui.activity;

import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Date;
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
    private AlertDialog dialog;
    private String Uid,work,sender,content;
    private Date sendTime;

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
            Uid = mWorkNote.getuID();
            work = mWorkNote.getSendWork();
            sender = mWorkNote.getSendPeopel();
            sendTime = mWorkNote.getCreateTime();
            content = mWorkNote.getContent();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss", Locale.CHINESE);
            publicationunit.setText("单位:"+work);
            NoteTime.setText("发表时间:"+sdf.format(sendTime));
            publicationpeople.setText("发表人:"+sender);
            content_txt.setText("日志内容:");
            mini_content.setText(content);
            mini_content.setSelection(content.length());
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
                    mDataBase.updateByUid(Uid,mini_content.getText().toString());
                }else if(Constant.LOG_OPERATE_TYPE.equals("delete")){
                    mDataBase.deleteWorkNote(Uid);
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
}
