package com.zhicheng.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by Donson on 2017/1/15.
 */

public class MapFragment extends BaseFragment {

    private TextureMapView mTextureMapView;
    private BaiduMap mBaiduMap;
    private GeoCoder geoCoder;
    private SearchView mSearchView;
    private FloatingActionButton mFab;
    private ArrayList<String> location;
//    //定位信息
//    private LocationClient mLocationClient = null;
//    private LocationListener mLocation;

    public static MapFragment newInstance(){
        MapFragment fragment = new MapFragment();

        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_main_map,container,false);
    }

    @Override
    protected void initEvents() {
        geoCoder = GeoCoder.newInstance();
        mTextureMapView = (TextureMapView) mRootView.findViewById(R.id.map);
        mSearchView = (SearchView) mRootView.findViewById(R.id.search);
        mFab = (FloatingActionButton) mRootView.findViewById(R.id.fab_location);
        mBaiduMap = mTextureMapView.getMap();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(30.994140,119.9015525)));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(19));
        location = new ArrayList<>();
//        mLocationClient = new LocationClient(getContext());
//        initLocation(mLocationClient);
//        mLocation = new LocationListener();
//        mLocationClient.registerLocationListener(mLocation);
//        mLocationClient.start();
//        mLocationClient.requestLocation();
    }

    @Override
    protected void initData(boolean isSavedNull) {
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR){
                    Toast.makeText(getContext(),"未找到该地址",Toast.LENGTH_SHORT).show();
                }else {
                    mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(geoCodeResult.getLocation()));
                    mBaiduMap.clear();
                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_location_marker);
                    OverlayOptions option = new MarkerOptions()
                            .position(geoCodeResult.getLocation())
                            .icon(bitmap);
                    mBaiduMap.addOverlay(option);
                    geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(geoCodeResult.getLocation()));
                }
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null ||  result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(getContext(),"未找到该地址",Toast.LENGTH_SHORT).show();
                }else {
                    mSearchView.setQueryHint(result.getAddress());
                    location.clear();
                    location.add(String.valueOf(result.getLocation().latitude));
                    location.add(String.valueOf(result.getLocation().longitude));
                    location.add(result.getAddress());
                    location.add("");
                }
            }
        });
        mFab.setOnClickListener(v -> {
            if (location != null && location.size() > 0){
                Intent intent = new Intent();
                intent.setAction("com.MapFragment.Location");
                intent.putStringArrayListExtra("location",location);
                getActivity().sendBroadcast(intent);
                getActivity().finish();
            }
        });
        Observable.create(subscriber -> {
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    subscriber.onNext(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
//                    if (!newText.isEmpty()){
//                        subscriber.onNext(newText);
//                    }
                    return false;
                }
            });
        }).debounce(150, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .switchMap(new Func1<Object, Observable<String>>() {
                    @Override
                    public Observable<String> call(Object o) {
                        return Observable.just(o.toString());
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        geoCoder.geocode(new GeoCodeOption().address(s).city("长兴"));
                    }
                });
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mBaiduMap.clear();
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_location_marker);
                OverlayOptions option = new MarkerOptions()
                        .position(latLng)
                        .icon(bitmap);
                mBaiduMap.addOverlay(option);
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                mBaiduMap.clear();
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_location_marker);
                OverlayOptions option = new MarkerOptions()
                        .position(mapPoi.getPosition())
                        .icon(bitmap);
                mBaiduMap.addOverlay(option);
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(mapPoi.getPosition()));
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mTextureMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        geoCoder.destroy();
//        mLocationClient.stop();
        mTextureMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTextureMapView.onDestroy();
    }


}
