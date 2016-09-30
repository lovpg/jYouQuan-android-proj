package com.lbsm.kdang.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.frame.activity.BaseFragmentActivity;
import com.lbsm.kdang.R;
import com.lbsm.kdang.picture.IntentUtil;

public class LocationActivity extends BaseFragmentActivity implements BDLocationListener{
	
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	
	// 定位相关
	private LocationClient mLocClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(this);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	};

	@Override
	protected void onPause() {
		super.onPause();
		// activity 暂停时同时暂停地图控件
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// activity 恢复时同时恢复地图控件
		mMapView.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// activity 销毁时同时销毁地图控件
		mMapView.onDestroy();
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.location_share:
			IntentUtil.startActivityLeft(new Intent(this, PostActivity.class), this);
			break;

		default:
			break;
		}
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub
		 if (location == null || mMapView == null) {
             return;
         }
		MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
				// 此处设置开发者获取到的方向信息，顺时针0-360
				.direction(100).latitude(location.getLatitude())
				.longitude(location.getLongitude()).build();
		mBaiduMap.setMyLocationData(locData);
		LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
		mBaiduMap.animateMapStatus(u);
         
	}
}
