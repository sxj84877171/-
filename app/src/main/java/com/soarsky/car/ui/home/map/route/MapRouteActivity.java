package com.soarsky.car.ui.home.map.route;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.home.map.route.set.MapRouteSetActivity;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：查看路线<br>
 * 该类为 MapRouteActivity<br>
 */


public class MapRouteActivity extends BaseActivity<MapRoutePresent,MapRouteModel> implements MapRouteView,View.OnClickListener,BDLocationListener {

    /**
     * 图层
     */
    private MapView routeMapView;
    /**
     * 地图
     */
    private BaiduMap baiduMap;
    /**
     * 回到设置起始位置
     */
    private RelativeLayout mapRouteBackLay;
    /**
     * 终点name
     */
    private TextView mapRouteNameTv;
    /**
     * 起始点距离
     */
    private TextView mapRouteDistanceTv;
    /**
     * 终点地址
     */
    private TextView mapRouteAddressTv;
    /**
     * 查看路线
     */
    private TextView mapRouteLineTv;
    /**
     * 本机地图
     */
    private TextView mapLocalTv;
    /**
     *声明LocationClient类
     */
    private LocationClient mLocationClient = null;
    /**
     * 是否首次定位
     */
    private boolean isFirstLoc = true;
    /**
     * SuggestionResult.SuggestionInfo
     */
    private SuggestionResult.SuggestionInfo suggestionInfo;
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG = "MapRouteActivity";


    @Override
    public int getLayoutId() {
        return R.layout.activity_map_route;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        routeMapView = (MapView) findViewById(R.id.routeMapView);

        mapRouteBackLay = (RelativeLayout) findViewById(R.id.mapRouteBackLay);
        mapRouteBackLay.setOnClickListener(this);

        mapRouteNameTv = (TextView) findViewById(R.id.mapRouteNameTv);
        mapRouteDistanceTv = (TextView) findViewById(R.id.mapRouteDistanceTv);
        mapRouteAddressTv = (TextView) findViewById(R.id.mapRouteAddressTv);

        mapRouteLineTv = (TextView) findViewById(R.id.mapRouteLineTv);
        mapRouteLineTv.setOnClickListener(this);
        mapLocalTv = (TextView) findViewById(R.id.mapLocalTv);
        mapLocalTv.setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baiduMap = routeMapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        isFirstLoc = true;
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);
        initLocation();
        mLocationClient.start();

        // 设置为一般地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        suggestionInfo = getIntent().getParcelableExtra("suggestInfo");
        if( suggestionInfo!= null) {
            mapRouteNameTv.setText(""+suggestionInfo.key);
            mapRouteAddressTv.setText(""+suggestionInfo.city+""+suggestionInfo.district);
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.carlocation);
            //构建MarkerOption，用于在地图上添加Marker
            if(suggestionInfo.pt!= null) {
                OverlayOptions option = new MarkerOptions()
                        .position(suggestionInfo.pt)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                baiduMap.addOverlay(option);
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        routeMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        routeMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        //退出时销毁定位
        mLocationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        super.onDestroy();
        routeMapView.onDestroy();
        routeMapView = null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mapRouteLineTv:
                Intent intent = new Intent(this, MapRouteSetActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("suggestInfo",suggestionInfo);
                startActivity(intent);
                break;
            case R.id.mapLocalTv:

                break;
            case R.id.mapRouteBackLay:
                finish();
                break;
        }
    }

    /**
     * 定位的回调
     * @param bdLocation 定位
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null || routeMapView == null)
            return;

        if(isFirstLoc) {
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius()).direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();

            app.setCity(bdLocation.getCity());
            LatLng ll = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            //设置地图中心点以及缩放级别
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 12);
            baiduMap.animateMapStatus(u);
            //设置定位数据
            baiduMap.setMyLocationData(locData);
            mapRouteDistanceTv.setText(""+ String.format("%.2f", DistanceUtil. getDistance(suggestionInfo.pt, ll)/1000.00)+"KM");
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
