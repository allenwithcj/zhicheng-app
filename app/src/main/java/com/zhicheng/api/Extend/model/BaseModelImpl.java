package com.zhicheng.api.Extend.model;

import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.Extend.service.BaseService;
import com.zhicheng.api.Extend.model.BaseModel;
import com.zhicheng.bean.json.UpFileRequest;
import com.zhicheng.common.URL;
import com.zhicheng.luban.Luban;
import com.zhicheng.utils.common.UIUtils;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2017/4/21.
 *
 */

public class BaseModelImpl implements BaseModel {

    private static BaseService mBaseService;

    private static Gson gson = new Gson();

    private BaseModelImpl(){}

    private static BaseModelImpl mBaseModelImpl;

    public static BaseModelImpl getBaseModelImpl(){
        if (mBaseModelImpl == null){
            mBaseModelImpl = new BaseModelImpl();
        }
        if (mBaseService == null){
            mBaseService = ServiceFactory.createServiceString(URL.HOST_URL_SERVER_ZHICHENG,BaseService.class);
        }
        return mBaseModelImpl;
    }

    public interface onResponseListener{
        void onResponse(String str,String guid);
    }

    private onResponseListener mOnResponseListener;

    public void setOnResponseListener(onResponseListener listener){
        if (mOnResponseListener == null){
            this.mOnResponseListener = listener;
        }
    }

    public BaseService getBaseService(){
        if (mBaseService == null){
            mBaseService = ServiceFactory.createServiceString(URL.HOST_URL_SERVER_ZHICHENG,BaseService.class);
        }
        return mBaseService;
    }

    public void ServiceImage(List<String> imgs,String guid){
        String request = FileRequest(guid);
        final MultipartBody.Builder builder = new MultipartBody.Builder();
        if (imgs.size() != 0) {
            //图片压缩
            List<File> mFiles = new ArrayList<>();
            for (int i = 0; i < imgs.size(); i++) {
                mFiles.add(new File(imgs.get(i)));
            }
            if (mFiles.size() > 0) {
                Luban.get(UIUtils.getContext())
                        .load(mFiles)
                        .putGear(Luban.FIRST_GEAR)
                        .asList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        })
                        .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<File>>>() {
                            @Override
                            public Observable<? extends List<File>> call(Throwable throwable) {
                                return Observable.empty();
                            }
                        }).subscribe(new Action1<List<File>>() {
                    @Override
                    public void call(List<File> files) {
                        if (files.size() > 0) {
                            List<String> imgs = new ArrayList<String>();
                            for (int i = 0; i < files.size(); i++) {
                                imgs.add(files.get(i).getAbsolutePath());
                            }
                            if (imgs.size() > 0) {
                                Observable.from(imgs)
                                        .map(s -> {
                                            File file = new File(s);
                                            builder.addFormDataPart("file", file.getName(), RequestBody.create(MultipartBody.FORM, file));
                                            return s;
                                        }).last()
                                        .flatMap(new Func1<String, Observable<Response<String>>>() {
                                            @Override
                                            public Observable<Response<String>> call(String s) {
                                                RequestBody body = RequestBody.create(MediaType.parse("application/json"), request);
                                                return mBaseService.connectImage(body, builder.build());
                                            }
                                        }).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Subscriber<Response<String>>() {
                                            @Override
                                            public void onCompleted() {

                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                if (e instanceof UnknownHostException){
                                                    showMessage("未知接口");
                                                    return;
                                                }
                                                showMessage("未知错误");
                                            }

                                            @Override
                                            public void onNext(Response<String> stringResponse) {
                                                if (stringResponse.isSuccessful()){
                                                    mOnResponseListener.onResponse(stringResponse.body(),guid);
                                                }else {
                                                    showMessage("访问失败");
                                                }
                                            }
                                        });
                            }
                        }
                    }
                });
            }
        }
    }

    public void ServiceConnect(String json){
        mBaseService.connect(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException){
                            showMessage("未知接口");
                            return;
                        }
                        showMessage("未知错误");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        if (stringResponse.isSuccessful()){
                            mOnResponseListener.onResponse(stringResponse.body(),"");
                        }else {
                            showMessage("访问失败");
                        }
                    }
                });

    }

    public static <T> T JsonToJava(String json,Class<T> type){
        return gson.fromJson(json,type);
    }

    public static String JavaToJson(Class type){
        return gson.toJson(type);
    }

    public static boolean isContainNameSpace(String str,String namespace){
        return str.contains("\"namespace\":\""+namespace+"\"");
    }

    private void showMessage(String msg){
        Toast.makeText(UIUtils.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public static String parseRequest(String namespace,Object query){
        Map<String,Object> request = new HashMap<>();
        Map<String,Object> iq = new HashMap<>();
        iq.put("namespace",namespace);
        iq.put("query",query);
        request.put("iq",iq);
        return gson.toJson(request);
    }

    private String FileRequest(String Guid){
        UpFileRequest request = new UpFileRequest();
        UpFileRequest.IqBean iq = new UpFileRequest.IqBean();
        UpFileRequest.IqBean.QueryBean query = new UpFileRequest.IqBean.QueryBean();
        query.setAttachmentGUID(Guid);
        iq.setQuery(query);
        iq.setNamespace("AttachmentUpdateRequest");
        request.setIq(iq);
        return gson.toJson(request);
    }

}
