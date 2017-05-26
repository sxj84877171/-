package com.soarsky.car.ui.home.map.route.point;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.home.map.route.set.MapRouteSetActivity;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/12<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：地图选点<br>
 * 该类为 MapRoutePointActivity<br>
 */


public class MapRoutePointActivity extends BaseActivity<MapRoutePointPresent,MapRoutePointModel> implements MapRoutePointView,View.OnClickListener,BDLocationListener,OnGetGeoCoderResultListener {
    /**
     * 图层
     */
    private MapView routePointMapView;
    /**
     * 返回
     */
    private RelativeLayout routePointBackLay;
    /**
     * 地址
     */
    private TextView routePointAddrTv;
    /**
     * 确定
     */
    private TextView routePointSureTv;
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
    private static final String TAG = "MapRoutePointActivity";
    /**
     * 是否首次定位
     */
    private boolean isFirstLoc = true;
    /**
     * 搜索模块，也可去掉地图模块独立使用
     */
    private GeoCoder mSearch = null;
    /**
     * 改变起始位置的信息 1--起点 2--终点
     */
    private int flag = -1;
    /**
     * 是否首次定位
     */
    private int isFirst = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_map_route_point;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);
        routePointMapView = (MapView) findViewById(R.id.routePointMapView);

        routePointBackLay = (RelativeLayout) findViewById(R.id.routePointBackLay);
        routePointBackLay.setOnClickListener(this);

        routePointAddrTv = (TextView) findViewById(R.id.routePointAddrTv);

        routePointSureTv = (TextView) findViewById(R.id.routePointSureTv);
        routePointSureTv.setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baiduMap = routePointMapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        flag = getIntent().getIntExtra("flag",-1);

        if(flag == 1){
            routePointAddrTv.setText(app.getRouteParam().getStartAddress());
        }else if(flag == 2){
            routePointAddrTv.setText(app.getRouteParam().getEndAddress());
        }




        isFirstLoc = true;
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);

        initLocation();
        mLocationClient.start();
        // 设置为一般地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
        isFirst = 1;
        if(app.getRouteParam()!= null){

            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.carlocation);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(app.getRouteParam().getEndLocation())
                    .icon(bitmap)
                    .zIndex(9);

            //在地图上添加Marker，并显示
            marker = (Marker) baiduMap.addOverlay(option);

            isFirst = 2;
        }


        if(isFirst == 2) {
            baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
                @Override
                public void onMapStatusChangeStart(MapStatus mapStatus) {

                }

                @Override
                public void onMapStatusChange(MapStatus mapStatus) {

                    marker.setPosition(mapStatus.target);
                }

                @Override
                public void onMapStatusChangeFinish(MapStatus mapStatus) {

                    LatLng ll = mapStatus.target;
                    Log.d("TAG", "lat=" + ll.latitude + "lon==" + ll.longitude);
                    showProgess();
                    mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                            .location(ll));
                }
            });
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        routePointMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        routePointMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        //退出时销毁定位
        mLocationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        super.onDestroy();
        routePointMapView.onDestroy();
        mSearch.destroy();
        routePointMapView = null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.routePointBackLay:
                finish();
                break;
            case R.id.routePointSureTv:
                Intent intent = new Intent(this, MapRouteSetActivity.class);
                intent.putExtra("type",2);
                intent.putExtra("flag",flag);
                startActivity(intent);
                break;
        }
    }

    /**
     * 定位的回调
     * @param bdLocation 定位
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {

        if (bdLocation == null || routePointMapView == null)
            return;

        if(isFirstLoc) {
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius()).direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            city = bdLocation.getCity();
            app.setCity(city);

            LatLng ll = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            //设置地图中心点以及缩放级别
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);
            baiduMap.animateMapStatus(u);
            //设置定位数据
            baiduMap.setMyLocationData(locData);


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

    /**
     * 正向编码
     * @param geoCodeResult 结果
     */
    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    /**
     * 反向编码
     * @param reverseGeoCodeResult 结果
     */
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

        stopProgess();
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        routePointAddrTv.setText(""+reverseGeoCodeResult.getAddress());

        if(flag == 1){
            app.getRouteParam().setStartAddress(reverseGeoCodeResult.getAddress());
            app.getRouteParam().setStartLocation(reverseGeoCodeResult.getLocation());
        }else if(flag == 2){
            app.getRouteParam().setEndAddress(reverseGeoCodeResult.getAddress());
            app.getRouteParam().setEndLocation(reverseGeoCodeResult.getLocation());
        }


    }
}
