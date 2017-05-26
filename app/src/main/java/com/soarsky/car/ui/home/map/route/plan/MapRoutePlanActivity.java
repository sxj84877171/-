package com.soarsky.car.ui.home.map.route.plan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.home.map.route.set.MapRouteParam;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/11<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：路径规划<br>
 * 该类为 MapRoutePlanActivity<br>
 */


public class MapRoutePlanActivity extends BaseActivity<MapRoutePlanPresent,MapRoutePlanModel> implements MapRoutePlanView,View.OnClickListener,BDLocationListener,OnGetRoutePlanResultListener {
    /**
     * 图层
     */
    private MapView routePlanMapView;
    /**
     * 地图
     */
    private BaiduMap baiduMap;
    /**
     * 设置起始点
     */
    private RelativeLayout mapRoutePlanBackLay;
    /**
     * 展开或收的布局
     */
    private RelativeLayout routePlanShowLay;
    /**
     * 目的地
     */
    private TextView mapRoutePlanAddrNameTv;
    /**
     * 距离
     */
    private TextView mapRoutePlanDistanceTv;
    /**
     * 时间
     */
    private TextView mapRoutePlanTimeTv;
    /**
     * 路线规划列表
     */
    private ListView mapRoutePlanListView;
    /**
     * 列表布局
     */
    private LinearLayout mapPlanLay;
    /**
     *声明LocationClient类
     */
    private LocationClient mLocationClient = null;
    /**
     * 是否首次定位
     */
    private boolean isFirstLoc = true;
    /**
     * 关键字回调信息
     */
    private SuggestionResult.SuggestionInfo suggestionInfo;
    /**
     * 路径规划搜索
     */
    private RoutePlanSearch mSearch;
    /**
     * 起点
     */
    private PlanNode stNode;
    /**
     * 终点
     */
    private PlanNode enNode;
    /**
     * 经纬度
     */
    private  LatLng ll;
    /**
     * 节点索引,供浏览节点时使用
     */
    private int nodeIndex = -1;
    /**
     * 驾车路径
     */
    private DrivingRouteResult nowResultdrive  = null;
    /**
     * 默认icon
     */
    boolean useDefaultIcon = false;
    /**
     * 路径
     */
    private RouteLine route = null;
    /**
     * OverlayManager
     */
    private OverlayManager routeOverlay = null;
    /**
     * MapRoutePlanAdapter
     */
    private MapRoutePlanAdapter adapter;
    /**
     * 判断由那种方式路径规划
     */
    private int type = -1;
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG = "MapRoutePlanActivity";
    /**
     * 路径param
     */
    public MapRouteParam param;
    /**
     * iv_switch View
     */
    private ImageView iv_switch;
    /**
     * 标志
     */
    private boolean isPlan = true;
    /**
     * ll_switch 布局
     */
    private LinearLayout ll_switch;
    @Override
    public int getLayoutId() {
        return R.layout.activity_map_route_plan;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        routePlanMapView = (MapView) findViewById(R.id.routePlanMapView);

        mapRoutePlanBackLay = (RelativeLayout) findViewById(R.id.mapRoutePlanBackLay);
        mapRoutePlanBackLay.setOnClickListener(this);

        routePlanShowLay  = (RelativeLayout) findViewById(R.id.routePlanShowLay);
        routePlanShowLay.setOnClickListener(this);

        mapRoutePlanAddrNameTv = (TextView) findViewById(R.id.mapRoutePlanAddrNameTv);
        mapRoutePlanDistanceTv = (TextView) findViewById(R.id.mapRoutePlanDistanceTv);
        mapRoutePlanTimeTv = (TextView) findViewById(R.id.mapRoutePlanTimeTv);
        mapRoutePlanListView = (ListView) findViewById(R.id.mapRoutePlanListView);
        mapPlanLay = (LinearLayout) findViewById(R.id.mapPlanLay);
        mapPlanLay.setOnClickListener(this);

        iv_switch = (ImageView) findViewById(R.id.iv_switch);
        iv_switch.setOnClickListener(this);

        ll_switch = (LinearLayout) findViewById(R.id.ll_switch);
        ll_switch.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baiduMap = routePlanMapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        isFirstLoc = true;
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);
        initLocation();


        // 设置为一般地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);


        type = getIntent().getIntExtra("type",0);
        if(type == 1) {
            suggestionInfo = getIntent().getParcelableExtra("suggestInfo");
            if (suggestionInfo != null) {
                mapRoutePlanAddrNameTv.setText("" + suggestionInfo.key);
                param = new MapRouteParam();
                param.setEndLocation(suggestionInfo.pt);
                param.setEndAddress(""+suggestionInfo.key);
                app.setRouteParam(param);
            }

        }else if(type == 2){

            mapRoutePlanAddrNameTv.setText(app.getRouteParam().getEndAddress());
            mapRoutePlanDistanceTv.setText(""+ String.format("%.1f", DistanceUtil. getDistance(app.getRouteParam().getStartLocation(), app.getRouteParam().getEndLocation())/1000.00)+"");
            showProgess();
            stNode = PlanNode.withLocation(app.getRouteParam().getStartLocation());
            enNode = PlanNode.withLocation(app.getRouteParam().getEndLocation());
            mSearch.drivingSearch((new DrivingRoutePlanOption())
                    .from(stNode)
                    .to(enNode));

        }

        mLocationClient.start();
        mapRoutePlanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                baiduMap.clear();

                DrivingRouteLine routeline = (DrivingRouteLine)adapter.getItem(i);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(baiduMap);
                routeOverlay = overlay;
                baiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(routeline);
                overlay.addToMap();
                overlay.zoomToSpan();
                mapRoutePlanDistanceTv.setText(""+ String.format("%.1f", routeline.getDistance()/1000.00)+"");
                mapRoutePlanTimeTv.setText(""+String.format("%.1f", routeline.getDuration()/60.00));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        routePlanMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        routePlanMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        //退出时销毁定位
        mLocationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        super.onDestroy();
        routePlanMapView.onDestroy();
        routePlanMapView = null;
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mapRoutePlanBackLay:
                finish();
                break;
            case R.id.routePlanShowLay:
                break;
            case R.id.ll_switch:
                if (isPlan){
                    isPlan = false;
                    mapPlanLay.setVisibility(View.VISIBLE);
                    iv_switch.setImageResource(R.mipmap.check_down);

                }else {
                    isPlan = true;
                    mapPlanLay.setVisibility(View.GONE);
                    iv_switch.setImageResource(R.mipmap.check_up);
                }
                break;
        }
    }

    /**
     * 定位回调
     * @param bdLocation
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null || routePlanMapView == null)
            return;

        if(isFirstLoc) {
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(0)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();


            app.setCity(bdLocation.getCity());
            ll = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            //设置地图中心点以及缩放级别
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);
            baiduMap.animateMapStatus(u);
            //设置定位数据
            baiduMap.setMyLocationData(locData);

            app.getRouteParam().setStartAddress(getString(R.string.map_route_set_s));
            app.getRouteParam().setStartLocation(ll);

            if(type == 1) {
                mapRoutePlanDistanceTv.setText(""+ String.format("%.1f", DistanceUtil. getDistance(suggestionInfo.pt, ll)/1000.00)+"");
                showProgess();
                stNode = PlanNode.withLocation(ll);
                enNode = PlanNode.withLocation(suggestionInfo.pt);
                mSearch.drivingSearch((new DrivingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));

            }
            isFirstLoc = false;
        }
    }

    /**
     * 步行路线规划
     * @param walkingRouteResult
     */
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    /**
     * 公交换乘路径规划
     * @param transitRouteResult
     */
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    /**
     * 驾车线路规划
     * @param result
     */
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        stopProgess();
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;


            if (result.getRouteLines().size() > 1) {
                mapPlanLay.setVisibility(View.VISIBLE);
                nowResultdrive = result;

                route = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(baiduMap);
                routeOverlay = overlay;
                baiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
                mapRoutePlanDistanceTv.setText(""+ String.format("%.1f", route.getDistance()/1000.00)+"");
                mapRoutePlanTimeTv.setText(""+String.format("%.1f", route.getDuration()/60.00));

                List<DrivingRouteLine> list= result.getRouteLines();
                adapter = new MapRoutePlanAdapter(this,list);
                mapRoutePlanListView.setAdapter(adapter);

            } else if (result.getRouteLines().size() == 1) {
                Log.d("TAG", "结果数=1");
                mapPlanLay.setVisibility(View.GONE);
                route = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(baiduMap);
                routeOverlay = overlay;
                baiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();

                mapRoutePlanDistanceTv.setText(""+ String.format("%.1f", route.getDistance()/1000.00)+"");
                mapRoutePlanTimeTv.setText(""+String.format("%.1f", route.getDuration()/60.00));
            } else {
                Log.d("TAG", "结果数<0");
                return;
            }
        }

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    /**
     * 骑车线路规划
     * @param bikingRouteResult 结果
     */
    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

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

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

}
