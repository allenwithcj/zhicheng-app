package com.zhicheng.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
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
import com.zhicheng.R;
import com.zhicheng.ui.activity.SearchViewActivity;
import com.zhicheng.utils.BDLocationInit;
import com.zhicheng.utils.common.PermissionUtils;
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

    private static final int TYPE_INPUT_CHOOSE_EDITTEXT = 0;
    private static final int TYPE_INPUT_CHOOSE_TEXTVIEW = 1;
    private static final int TYPE_CONTENT_BOX = 2;
    private static final int TYPE_PHOTO_BOX = 3;

    private final String[] TAGNAME = {"地图位置:", "爆料类型:"};
    private boolean isLocation = false;
    //定位信息
    private LocationClient mLocationClient = null;
    private mLocationListener mLocation;
    private Map<String, String> map;

    public interface SaveEdit {
        void onSaveEdit(int position, String s);
    }

    public interface ButtonClick {
        void onButtonClick();
    }

    public interface sendLocation {
        void onSendLocation(Map<String, String> maps);
    }

    private ChoosePhotoAdapter mChoosePhotoAdapter;
    private SaveEdit mSaveEdit;
    private ButtonClick mButtonClick;
    private sendLocation mSendLocation;

    public void setSaveEdit(SaveEdit mSaveEdit) {
        if (this.mSaveEdit == null) {
            this.mSaveEdit = mSaveEdit;
        }
    }

    public void setButtonClick(ButtonClick mButtonClick) {
        if (this.mButtonClick == null) {
            this.mButtonClick = mButtonClick;
        }
    }

    public void setSendLocation(sendLocation s) {
        if (this.mSendLocation == null) {
            this.mSendLocation = s;
        }
    }

    public IneedAdapter(Context context) {
        mChoosePhotoAdapter = new ChoosePhotoAdapter();
        mLocationClient = new LocationClient(context);
        BDLocationInit.initLocation(mLocationClient);
        mLocation = new mLocationListener();
        mLocationClient.registerLocationListener(mLocation);
    }

    public void addPhoto(List<String> photo) {
        mChoosePhotoAdapter.addData(photo);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_INPUT_CHOOSE_EDITTEXT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_input_choose, parent, false);
            return new InputChooseEdittTextViewHolder(view);
        } else if (viewType == TYPE_INPUT_CHOOSE_TEXTVIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_input_choose_textview, parent, false);
            return new InputChooseTextViewViewHolder(view);
        } else if (viewType == TYPE_CONTENT_BOX) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_input_content, parent, false);
            return new InputContentViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_input_photo, parent, false);
            return new InputPhotoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InputChooseEdittTextViewHolder) {
            ((InputChooseEdittTextViewHolder) holder).name.setText(TAGNAME[0]);
            ((InputChooseEdittTextViewHolder) holder).input_choose.setOnClickListener(v -> {
                if (TextUtils.isEmpty(((InputChooseEdittTextViewHolder) holder).input_choose.getText())) {
                    if (!mLocationClient.isStarted()) {
                        mLocationClient.start();
                        mLocationClient.requestLocation();
                        isLocation = true;
                    }
                    ((InputChooseEdittTextViewHolder) holder).mProgressBar.setVisibility(View.VISIBLE);
                }
            });

            ((InputChooseEdittTextViewHolder) holder).input_choose.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    holder.itemView.getResources().getDrawable(R.drawable.ic_explore_black_24dp), null);
        } else if (holder instanceof InputChooseTextViewViewHolder) {
            ((InputChooseTextViewViewHolder) holder).name.setText(TAGNAME[1]);
            ((InputChooseTextViewViewHolder) holder).input_choose.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    holder.itemView.getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp), null);
            ((InputChooseTextViewViewHolder) holder).input_choose.setOnClickListener(view -> {
                Intent intent = new Intent(UIUtils.getContext(), SearchViewActivity.class);
                intent.putExtra("fragment", "Search");
                intent.putExtra("isClassify", "true");
                UIUtils.startActivity(intent);
            });
        } else if (holder instanceof InputContentViewHolder) {
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
                    .debounce(150, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
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
                            mSaveEdit.onSaveEdit(Integer.parseInt(((InputContentViewHolder) holder).i_bao_content.getTag().toString()), s);
                        }
                    });
        } else if (holder instanceof InputPhotoViewHolder) {
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
        if (position == 0) {
            return TYPE_INPUT_CHOOSE_EDITTEXT;
        } else if (position == 1) {
            return TYPE_INPUT_CHOOSE_TEXTVIEW;
        } else if (position == 2) {
            return TYPE_CONTENT_BOX;
        } else {
            return TYPE_PHOTO_BOX;
        }
    }

    public class InputChooseEdittTextViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView input_choose;
        public ProgressBar mProgressBar;

        public InputChooseEdittTextViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tagName);
            input_choose = (TextView) itemView.findViewById(R.id.input);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    public class InputChooseTextViewViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView input_choose;

        public InputChooseTextViewViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tagName);
            input_choose = (TextView) itemView.findViewById(R.id.input);
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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            choosePhoto.setLayoutManager(linearLayoutManager);
            choosePhoto.setAdapter(mChoosePhotoAdapter);
        }

    }

    private int count = 0;

    private class mLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            map = new HashMap<>();
            map.put("latitude", String.valueOf(bdLocation.getLatitude()));
            map.put("longitude", String.valueOf(bdLocation.getLongitude()));
            map.put("address", bdLocation.getAddrStr());
            map.put("desc", bdLocation.getLocationDescribe());
            if (map.get("address") == null || map.get("address").equals("")) {
                if (count < 2) {
                    mLocationClient.requestLocation();
                    count++;
                } else {
                    map.put("openMap", "true");
                    mSendLocation.onSendLocation(map);
                    mLocationClient.stop();
                    isLocation = false;
                }
            } else {
                mSendLocation.onSendLocation(map);
                mLocationClient.stop();
                isLocation = false;
            }
        }
    }

}
