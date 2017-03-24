package com.zhicheng;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.pgyersdk.crash.PgyCrashManager;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.database.LocalConfig;
import com.zhicheng.api.common.service.LoginService;
import com.zhicheng.bean.http.LoginResponse;
import com.zhicheng.bean.json.LoginRequest;
import com.zhicheng.common.URL;
import com.zhicheng.ui.activity.BaseActivity;
import com.zhicheng.ui.activity.LoginActivity;
import com.zhicheng.utils.common.FileUtils;
import com.zhicheng.utils.common.UIUtils;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Response;
import roboguice.RoboGuice;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2016/12/29.
 */

public class BaseApplication extends Application {

    public final static String TAG = "BaseApplication";
    public final static boolean DEBUG = true;
    private static BaseApplication mBaseApplication;
    private static int mainTid;
    //activity集合 管理所有Activity
    private static List<BaseActivity> mActivities;
    static {
        RoboGuice.setUseAnnotationDatabases(false);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        PgyCrashManager.register(getApplicationContext());
        mActivities = new LinkedList<>();
        mBaseApplication = this;
        mainTid = Process.myTid();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        SDKInitializer.initialize(getApplicationContext());
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
    }

    /**
     * 获取Application
     *
     * @return
     */
    public static Context getApplication(){
        return mBaseApplication;
    }

    /**
     * 获取主线程ID
     *
     * @return
     */
    public static int getMainTid(){
        return mainTid;
    }

    /**
     * 添加一个activity
     *
     * @param mBaseActivity
     */
    public void addActivity(BaseActivity mBaseActivity){
        mActivities.add(mBaseActivity);
    }

    /**
     * 删除一个activity
     *
     * @param mBaseActivity
     */
    public void removeActivity(BaseActivity mBaseActivity){
        mActivities.remove(mBaseActivity);
    }

    /**
     * 结束当前所有Activity
     */
    public static void clearAllActivity(){
        ListIterator<BaseActivity> iterator = mActivities.listIterator();
        BaseActivity activity;
        while (iterator.hasNext()){
            activity = iterator.next();
            if (activity != null){
                activity.finish();
            }
        }
    }

    public static void quiteApplication(){
        clearAllActivity();
        System.exit(0);
    }

    public static void checkLogin(){
        DatabaseHelper mDataBase = new DatabaseHelper();
        LocalConfig config = mDataBase.getLocalConfig();
        if (config != null && config.getUserName() != null && config.getPwd() != null){
            LoginRequest.IqBean.QueryBean irIqQB = new LoginRequest.IqBean.QueryBean();
            LoginRequest.IqBean lrIq = new LoginRequest.IqBean();
            LoginRequest lr = new LoginRequest();
            lrIq.setNamespace("LoginRequest");
            lrIq.setMobileVersion(config.getMobileVersion());
            lrIq.setVersion(config.getVersion());
            lrIq.setResolution(config.getResolution());
            irIqQB.setName(config.getName());
            irIqQB.setPassword(FileUtils.getBase64Pwd(config.getPwd()));
            irIqQB.setDeviceId(config.getDeviceId());
            irIqQB.setLanguageType(config.getLanguageType());
            irIqQB.setToken(config.getToken());
            lrIq.setQuery(irIqQB);
            lr.setIq(lrIq);
            Gson gson = new Gson();
            String strEntity = gson.toJson(lr);
            Observable.just(strEntity)
                    .flatMap(new Func1<String, Observable<Response<LoginResponse>>>() {
                        @Override
                        public Observable<Response<LoginResponse>> call(String s) {
                            LoginService mLoginService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,LoginService.class);

                            return mLoginService.loginRequest(s);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Response<LoginResponse>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof UnknownHostException){
                                Toast.makeText(getApplication(),"",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(getApplication(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(Response<LoginResponse> loginResponseResponse) {
                            if (loginResponseResponse.isSuccessful()){
                                if (loginResponseResponse.body() != null){
                                    LoginResponse lr = loginResponseResponse.body();
                                    BaseApplication.log_say(TAG,lr.getIq().getQuery().getErrorMessage());
//                                    Toast.makeText(getApplication(),"欢迎回来",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getApplication(),loginResponseResponse.message(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            UIUtils.startActivity(new Intent(getApplication(), LoginActivity.class));
        }
    }



    public static void log_say(String tag,String content){
        if (BaseApplication.DEBUG){
            Log.i(tag,content);
        }
    }
}
