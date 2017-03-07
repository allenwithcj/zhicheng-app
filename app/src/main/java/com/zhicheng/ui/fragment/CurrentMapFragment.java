package com.zhicheng.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.zhicheng.R;

/**
 * Created by Donson on 2017/2/27.
 */

public class CurrentMapFragment extends BaseFragment{

    private TextureMapView mTextureMapView;
    private BaiduMap mBaiduMap;
        //定位信息
    private LocationClient mLocationClient = null;
    private LocationListener mLocation;

    public static CurrentMapFragment newInstance(){
        CurrentMapFragment fragment = new CurrentMapFragment();

        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_main_current_map,container,false);
    }

    @Override
    protected void initEvents() {
        mTextureMapView = (TextureMapView) mRootView.findViewById(R.id.map);
        mBaiduMap = mTextureMapView.getMap();
        mLocationClient = new LocationClient(getContext());
        initLocation(mLocationClient);
        mLocation = new LocationListener();
        mLocationClient.registerLocationListener(mLocation);
    }

    @Override
    protected void initData(boolean isSavedNull) {
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(30.994140,119.9015525)));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(19));
        mLocationClient.start();
        mLocationClient.requestLocation();
    }

    private boolean isFirst = true;

    private class LocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            LatLng point = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            mBaiduMap.clear();
            if (isFirst) {
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
                isFirst = false;
            }
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_location_marker);
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            mBaiduMap.addOverlay(option);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
            isFirst = true;
        }
        mTextureMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationClient.stop();
        mTextureMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient.isStarted()){
            mLocationClient.stop();
        }
        mTextureMapView.onDestroy();
    }


    private void initLocation(LocationClient client){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1500;
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
