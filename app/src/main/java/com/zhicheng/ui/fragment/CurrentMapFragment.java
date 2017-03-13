package com.zhicheng.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.zhicheng.R;
import com.zhicheng.utils.BDLocationInit;

/**
 * Created by Donson on 2017/2/27.
 */

public class CurrentMapFragment extends BaseFragment{

    private TextureMapView mTextureMapView;
    private BaiduMap mBaiduMap;
    //定位信息
    private LocationClient mLocationClient = null;
    private LocationListener mLocation;
    private MyMarkerDragListener myMarkerDragListener;

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
        BDLocationInit.getInstance().initLocation(mLocationClient);
        mLocation = new LocationListener();
        myMarkerDragListener = new MyMarkerDragListener();
        mLocationClient.registerLocationListener(mLocation);
        mBaiduMap.setOnMarkerDragListener(myMarkerDragListener);
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
            MarkerOptions option = new MarkerOptions()
                    .position(point)  //设置marker的位置
                    .icon(bitmap)  //设置marker图标
                    .zIndex(9)  //设置marker所在层级
                    .draggable(true);  //设置手势拖拽
            mBaiduMap.addOverlay(option);
        }
    }

    class MyMarkerDragListener implements BaiduMap.OnMarkerDragListener {

        @Override
        public void onMarkerDrag(Marker marker) {
            //拖拽中

        }

        @Override
        public void onMarkerDragEnd(Marker marker) {
            //拖拽结束

        }

        @Override
        public void onMarkerDragStart(Marker marker) {
            //开始拖拽

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

}
