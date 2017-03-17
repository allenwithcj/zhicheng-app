package com.zhicheng.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.database.LocalConfig;
import com.zhicheng.api.presenter.impl.LoginPresenterImpl;
import com.zhicheng.api.view.LoginView;
import com.zhicheng.bean.http.LoginResponse;
import com.zhicheng.bean.json.LoginRequest;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import roboguice.inject.InjectView;

/**
 * Created by Donson on 2017/2/8.
 */

public class LoginActivity extends BaseActivity implements LoginView{

    @InjectView(R.id.account_name) private EditText account_name;
    @InjectView(R.id.account_pwd) private EditText account_pwd;
    @InjectView(R.id.login) private TextView login;
    private LoginPresenterImpl mLoginPresenterImpl;
    private DatabaseHelper mDataBase;

    @Override
    protected void initEvents() {
        setContentView(R.layout.b_login);
        mLoginPresenterImpl = new LoginPresenterImpl(this);
        mDataBase = new DatabaseHelper();
        login.setOnClickListener(v -> {
            if (account_name.getText().toString().isEmpty() || account_pwd.getText().toString().isEmpty()){
                showMessage("用户名密码不能为空");
            }else {
                if (mDataBase.getLocalConfig() == null){
                    WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    android.view.Display display = windowManager.getDefaultDisplay();
                    int widthPX = display.getWidth();
                    int heightPX = display.getHeight();
//                    TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
                    LocalConfig localConfig = new LocalConfig();
                    localConfig.setId(mDataBase.generateNewPrimaryKey());
                    localConfig.setMobileVersion(Build.VERSION.RELEASE);
                    localConfig.setResolution(widthPX+","+heightPX);
                    localConfig.setName(account_name.getText().toString());
                    localConfig.setPwd(account_pwd.getText().toString());
                    localConfig.setDeviceId("");
                    localConfig.setLanguageType("0");
                    localConfig.setToken(newRandomUUID());
                    localConfig.setVersion(getVersion());
                    mDataBase.setLocalConfig(localConfig);
                }
                LocalConfig localConfig = mDataBase.getLocalConfig();
                LoginRequest lr = new LoginRequest();
                LoginRequest.IqBean lrIq = new LoginRequest.IqBean();
                LoginRequest.IqBean.QueryBean irIqQB = new LoginRequest.IqBean.QueryBean();
                lrIq.setMobileVersion(localConfig.getMobileVersion());
                lrIq.setNamespace("LoginRequest");
                lrIq.setVersion(localConfig.getVersion());
                lrIq.setResolution(localConfig.getResolution());
                irIqQB.setName(account_name.getText().toString());
                irIqQB.setPassword(getBase64Pwd(account_pwd.getText().toString()));
                irIqQB.setDeviceId(localConfig.getDeviceId());
                irIqQB.setLanguageType(localConfig.getLanguageType());
                irIqQB.setToken(localConfig.getToken());
                lrIq.setQuery(irIqQB);
                lr.setIq(lrIq);
                Gson gson = new Gson();
                String strEntity = gson.toJson(lr);
                //Debug
                BaseApplication.log_say(TAG,strEntity);
                mLoginPresenterImpl.login(strEntity);
                login.setClickable(false);
                login.setText("登陆中...");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                BaseApplication.clearAllActivity();
                return true;
            default:
                return true;
        }
    }

    @Override
    public void showMessage(String msg) {
        login.setClickable(true);
        login.setText("登陆");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
//        Snackbar snackbar = Snackbar.make(mToolbar,msg,Snackbar.LENGTH_SHORT);
//        View view = snackbar.getView();
//        Snackbar.SnackbarLayout.LayoutParams params = (Snackbar.SnackbarLayout.LayoutParams) view.getLayoutParams();
//        params.gravity = Gravity.TOP;
//        view.setLayoutParams(params);
//        TextView mText = (TextView) view.findViewById(R.id.snackbar_text);
//        mText.setTextSize(10);
//        mText.setPadding(12,0,12,0);
//        snackbar.show();
    }

    @Override
    public void loginResponse(Object result) {
        if (result instanceof LoginResponse){
            if (((LoginResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                try {
                    mDataBase.updateLocalConfig((LoginResponse) result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.setAction("com.mainFragment.update");
                this.sendBroadcast(intent);
                showMessage("登陆成功");
                this.finish();
            }else {
                showMessage("登陆失败:"+((LoginResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }



    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
     public String getVersion() {
             try {
                     PackageManager manager = this.getPackageManager();
                     PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
                    return info.versionName;
                 } catch (Exception e) {
                     e.printStackTrace();
                     return this.getString(R.string.can_not_find_version_name);
                 }
         }
    /**
     * 生成 token
     *
     */
    private String newRandomUUID(){
        String token = UUID.randomUUID().toString();
        return token.replaceAll("-","");
    }
    /**
     * 密码加密
     * Base64
     * utf-8 default
     */
    private String getBase64Pwd(String pwd){
        String encodePwd = null;
        try {
            encodePwd = Base64.encodeToString(account_pwd.getText().toString().getBytes("utf-8"),Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodePwd;
    }

    @Override
    protected void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(account_name.getWindowToken(),0);
    }
}
