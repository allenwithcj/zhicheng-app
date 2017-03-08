package com.zhicheng.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.ui.activity.SearchViewActivity;
import com.zhicheng.utils.common.UIUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2017/1/16.
 */

public class IneedAdapter extends RecyclerView.Adapter {

    private static final int TYPE_INPUT_CHOOSE = 0;
    private static final int TYPE_CONTENT_BOX = 1;
    private static final int TYPE_PHOTO_BOX = 2;

    private final String[] TAGNAME = {"地图位置:","爆料类型:"};
    private boolean isLocation = false;
    //定位信息
    private LocationClient mLocationClient = null;
    private mLocationListener mLocation;

    public interface SaveEdit{
        void onSaveEdit(int position,String s);
    }

    public interface ButtonClick{
        void onButtonClick();
    }

    public interface sendLocation{
        void onSendLocation(Map<String,String> maps);
    }

    private ChoosePhotoAdapter mChoosePhotoAdapter;
    private SaveEdit mSaveEdit;
    private ButtonClick mButtonClick;
    private sendLocation mSendLocation;

    public void setSaveEdit(SaveEdit mSaveEdit){
        if (this.mSaveEdit == null){
            this.mSaveEdit = mSaveEdit;
        }
    }

    public void setButtonClick(ButtonClick mButtonClick){
        if (this.mButtonClick == null){
            this.mButtonClick = mButtonClick;
        }
    }

    public void setSendLocation(sendLocation s){
        if (this.mSendLocation == null){
            this.mSendLocation = s;
        }
    }

    public IneedAdapter(Context context){
        mChoosePhotoAdapter = new ChoosePhotoAdapter();
        mLocationClient = new LocationClient(context);
        initLocation(mLocationClient);
        mLocation = new mLocationListener();
        mLocationClient.registerLocationListener(mLocation);
//        if (!mLocationClient.isStarted()){
//            mLocationClient.start();
//            mLocationClient.requestLocation();
//        }
    }

    public void addPhoto(List<String> photo){
//        BaseApplication.log_say("photoChoose:",photo.toString());
        mChoosePhotoAdapter.addData(photo);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_INPUT_CHOOSE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_input_choose,parent,false);
            return new InputChooseViewHolder(view);
        }else if (viewType == TYPE_CONTENT_BOX){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_input_content,parent,false);
            return new InputContentViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_input_photo,parent,false);
            return new InputPhotoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InputChooseViewHolder){
            ((InputChooseViewHolder) holder).name.setText(TAGNAME[position]);
            if (position == 0) ((InputChooseViewHolder) holder).input_choose.setText("请点此处定位");
            ((InputChooseViewHolder) holder).input_choose.setOnClickListener(v -> {
                if (position == 0){
                    if (!mLocationClient.isStarted()){
                        mLocationClient.start();
                        mLocationClient.requestLocation();
                        isLocation = true;
                    }
                    ((InputChooseViewHolder) holder).mProgressBar.setVisibility(View.VISIBLE);
                }else {
                    if (!isLocation){
                        Intent intent = new Intent(UIUtils.getContext(), SearchViewActivity.class);
                        intent.putExtra("fragment","Search");
                        intent.putExtra("isClassify","true");
                        UIUtils.startActivity(intent);
                    }
                }
            });
            if (position == 0){
                ((InputChooseViewHolder) holder).input_choose.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                        holder.itemView.getResources().getDrawable(R.drawable.ic_explore_black_24dp),null);
            }else {
                ((InputChooseViewHolder) holder).input_choose.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                        holder.itemView.getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp),null);
            }
        }else if (holder instanceof InputContentViewHolder){
            Observable.create(subscriber -> {
                ((InputContentViewHolder) holder).i_bao_content.setTag(position);
                ((InputContentViewHolder) holder).i_bao_content.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        subscriber.onNext(s.toString());
                    }
                });
            })
                    .debounce(150, TimeUnit.MILLISECONDS,AndroidSchedulers.mainThread())
                    .switchMap(new Func1<Object, Observable<String>>() {
                        @Override
                        public Observable<String> call(Object o) {
                            return Observable.just(o.toString());
                        }
                    })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.io())
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            mSaveEdit.onSaveEdit(Integer.parseInt(((InputContentViewHolder) holder).i_bao_content.getTag().toString()),s);
                        }
                    });
        }else if (holder instanceof InputPhotoViewHolder){
            ((InputPhotoViewHolder) holder).mImgBtn.setOnClickListener((v) -> {
                mButtonClick.onButtonClick();
            });
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 2){
            return TYPE_INPUT_CHOOSE;
        }else if (position == 2){
            return TYPE_CONTENT_BOX;
        }else {
            return TYPE_PHOTO_BOX;
        }
    }

    public class InputChooseViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        public TextView input_choose;
        public ProgressBar mProgressBar;

        public InputChooseViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tagName);
            input_choose = (TextView) itemView.findViewById(R.id.input);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    private class InputContentViewHolder extends RecyclerView.ViewHolder {

        private EditText i_bao_content;

        public InputContentViewHolder(View itemView) {
            super(itemView);
            i_bao_content = (EditText) itemView.findViewById(R.id.i_bao_content);
        }
    }

    private class InputPhotoViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView choosePhoto;
        private ImageButton mImgBtn;

        public InputPhotoViewHolder(View itemView) {
            super(itemView);
            choosePhoto = (RecyclerView) itemView.findViewById(R.id.mRecycleView);
            mImgBtn = (ImageButton) itemView.findViewById(R.id.btn);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            choosePhoto.setLayoutManager(linearLayoutManager);
            choosePhoto.setAdapter(mChoosePhotoAdapter);
        }

    }

    private int count = 0;

    private class mLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            BaseApplication.log_say("errorCode:","------------>"+bdLocation.getLocType());
            Map<String,String> map = new HashMap<>();
            map.put("latitude",String.valueOf(bdLocation.getLatitude()));
            map.put("longitude",String.valueOf(bdLocation.getLongitude()));
            map.put("address",bdLocation.getAddrStr());
            map.put("desc",bdLocation.getLocationDescribe());
            if (map.get("address") == null || map.get("address").equals("")){
                if (count < 2){
                    mLocationClient.requestLocation();
                    count++;
                }else {
                    map.put("openMap","true");
                    mSendLocation.onSendLocation(map);
                    mLocationClient.stop();
                    isLocation = false;
                }
            }else {
                mSendLocation.onSendLocation(map);
                mLocationClient.stop();
                isLocation = false;
            }
        }
    }

    private void initLocation(LocationClient client){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

//        option.setLocationNotify(false);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

//        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

//        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

//        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        client.setLocOption(option);
    }
}
