package com.soarsky.car.ui.home.map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.home.map.search.MapSearchActivity;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/1/10
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：地图界面
 * 该类为 MainMapActivity
 */


public class MainMapActivity extends BaseActivity<MainMapPresent,MainMapModel> implements MainMapView,View.OnClickListener,BDLocationListener {
    /**
     * 返回
     */
    private LinearLayout mapBackLay;
    /**
     * 搜索布局
     */
    private LinearLayout mainMapSearchLay;

    /**
     *搜索框
     */
    private EditText mainMapSearchEt;
    /**
     * 图层
     */
    private MapView main_MapView;
    /**
     * 地图
     */
    private BaiduMap baiduMap;
    /**
     *声明LocationClient类
     */
    private LocationClient mLocationClient = null;
    /**
     * 地图标注
     */
    private Marker marker;
    /**
     * 城市
     */
    private String city ="";
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG = "MainMapActivity";
    /**
     * 是否首次定位
     */
    private boolean isFirstLoc = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_map;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        mapBackLay = (LinearLayout) findViewById(R.id.mapBackLay);
        mapBackLay.setOnClickListener(this);

        mainMapSearchEt = (EditText) findViewById(R.id.mainMapSearchEt);
        mainMapSearchEt.setOnClickListener(this);

        mainMapSearchLay = (LinearLayout) findViewById(R.id.mainMapSearchLay);
        mainMapSearchLay.setOnClickListener(this);

        main_MapView = (MapView) findViewById(R.id.main_MapView);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baiduMap = main_MapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);

        initLocation();
        mLocationClient.start();
        // 设置为一般地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

                if(marker != null) {
                    if (mapStatus != null) {
                        marker.setPosition(mapStatus.target);
                    }
                }
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                if(mapStatus != null) {
                    LatLng ll = mapStatus.target;
                    Log.d("TAG", "lat=" + ll.latitude + "lon==" + ll.longitude);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        main_MapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        main_MapView.onPause();
    }

    @Override
    protected void onDestroy() {
        //退出时销毁定位
        mLocationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        super.onDestroy();
        main_MapView.onDestroy();
        main_MapView = null;
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mapBackLay:
                finish();
                break;
            case R.id.mainMapSearchLay:
            case R.id.mainMapSearchEt:
                Intent i = new Intent(this, MapSearchActivity.class);
                startActivity(i);
                break;
        }
    }

    /**
     * 定位回调
     * @param bdLocation 定位
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null || main_MapView == null)
            return;
        if(isFirstLoc) {
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius()).direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            city = bdLocation.getCity();
            app.setCity(city);
            mainMapSearchEt.setText(bdLocation.getAddrStr());

            LatLng ll = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            //设置地图中心点以及缩放级别
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);
            baiduMap.animateMapStatus(u);
            //设置定位数据
            baiduMap.setMyLocationData(locData);
            baiduMap.clear();
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.carlocation);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(ll)
                    .icon(bitmap)
                    .zIndex(9);

            //在地图上添加Marker，并显示
            marker = (Marker) baiduMap.addOverlay(option);
            isFirstLoc = false;
        }
    }

    /**
     * 配置定位SDK参数
     */
    private void initLocation(){

        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        int span=1000;
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }


}
