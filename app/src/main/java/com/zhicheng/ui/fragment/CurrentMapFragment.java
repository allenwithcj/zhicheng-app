package com.zhicheng.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zhicheng.R;
import com.zhicheng.utils.BDLocationInit;
import com.zhicheng.utils.common.PermissionUtils;

import java.util.List;

/**
 * Created by Donson on 2017/2/27.
 */

public class CurrentMapFragment extends BaseFragment {

    private MapView mMapView;
    private BaiduMap mBaiduMap;//地图管理器
    private Marker mMarker;//覆盖物
    private LatLng mLatLng;
    private ImageButton request_location;

    //定位信息
    private LocationClient mLocationClient = null;
    private LocationListener mLocation;
    private TextView title_name;

    public static CurrentMapFragment newInstance() {
        CurrentMapFragment fragment = new CurrentMapFragment();

        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_main_current_map, container, false);
    }

    @Override
    protected void initEvents() {
        PermissionUtils.requestLocationPermission(getActivity());
        title_name = (TextView)mRootView.findViewById(R.id.title_name);
        mMapView = (MapView) mRootView.findViewById(R.id.map);
        request_location = (ImageButton) mRootView.findViewById(R.id.request_location);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getContext());
        BDLocationInit.getInstance().initLocation(mLocationClient);
        mLocation = new LocationListener();
        mLocationClient.registerLocationListener(mLocation);
        //mark覆盖物点击监听器
        mBaiduMap.setOnMarkerClickListener(new MyOnMarkerClickListener());
        //mark覆盖物拖拽监听器
        mBaiduMap.setOnMarkerDragListener(new MyOnMarkerDragListener());

        title_name.setText(getResources().getString(R.string.map));

        request_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLocationClient != null && mLocationClient.isStarted()) {
                    mLocationClient.requestLocation();
                } else if (mLocationClient != null && !mLocationClient.isStarted()) {
                    mLocationClient.start();
                }
            }
        });

    }


    @Override
    protected void initData(boolean isSavedNull) {
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.requestLocation();
        } else if (mLocationClient != null && !mLocationClient.isStarted()) {
            mLocationClient.start();
        }

    }

    private boolean isFirst = true;

    private class LocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData myLocationData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(100)
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(myLocationData);

            if (isFirst) {
                //isFirst = false;
                mLatLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                mBaiduMap.clear();
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(mLatLng));
                //添加覆盖物
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_location_marker);
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(mLatLng)
                        .icon(bitmap)
                        .draggable(true)
                        .animateType(MarkerOptions.MarkerAnimateType.drop);
                mMarker = (Marker) (mBaiduMap.addOverlay(markerOptions));
                setPopupTipsInfo(mMarker);
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(mLatLng, 18.0f);
                mBaiduMap.animateMapStatus(mapStatusUpdate);
            }

        }
    }

    class MyOnMarkerClickListener implements BaiduMap.OnMarkerClickListener {

        @Override
        public boolean onMarkerClick(Marker marker) {
            setPopupTipsInfo(marker);
            return false;
        }
    }

    class MyOnMarkerDragListener implements BaiduMap.OnMarkerDragListener {

        @Override
        public void onMarkerDrag(Marker marker) {

        }

        @Override
        public void onMarkerDragEnd(Marker marker) {
            setPopupTipsInfo(marker);
        }

        @Override
        public void onMarkerDragStart(Marker marker) {

        }
    }

    private void setPopupTipsInfo(Marker mMarker) {
        //获取当前经纬度
        final LatLng latLng = mMarker.getPosition();
        final String[] addr = new String[1];
        //实例化一个地理编码查询对象
        GeoCoder geoCoder = GeoCoder.newInstance();
        ReverseGeoCodeOption option = new ReverseGeoCodeOption();
        option.location(latLng);
        geoCoder.reverseGeoCode(option);
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                addr[0] = reverseGeoCodeResult.getAddress();
                ReverseGeoCodeResult.AddressComponent addressComponent = reverseGeoCodeResult.getAddressDetail();
                StringBuilder sb = new StringBuilder();
                List<PoiInfo> poiInfos = reverseGeoCodeResult.getPoiList();
                if (poiInfos != null) {
                    sb.append("\nPoilist size:" + poiInfos.size());
                    for (PoiInfo p : poiInfos) {
                        sb.append("\n\taddress:" + p.address);
                        sb.append("name:" + p.name + " postCode:" + p.postCode);
                    }
                }
                Button button = new Button(getActivity());
                button.setBackgroundResource(R.drawable.location_tips);
                button.setText(addr[0]);
                InfoWindow infoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), latLng, -47, new InfoWindow.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick() {
                        mBaiduMap.hideInfoWindow();
                    }
                });
                mBaiduMap.showInfoWindow(infoWindow);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
            isFirst = true;
        }
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationClient.stop();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
        mBaiduMap.clear();
        mMapView.onDestroy();
    }

}
