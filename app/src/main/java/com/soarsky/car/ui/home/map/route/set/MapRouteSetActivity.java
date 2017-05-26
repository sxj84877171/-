package com.soarsky.car.ui.home.map.route.set;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.home.map.route.plan.MapRoutePlanActivity;
import com.soarsky.car.ui.home.map.route.select.MapRouteSelectActivity;

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
 * 程序功能：手动设置起始点<br>
 * 该类为 MapRouteSetActivity<br>
 */


public class MapRouteSetActivity extends BaseActivity<MapRouteSetPresent,MapRouteSetModel> implements MapRouteSetView,View.OnClickListener,BDLocationListener {
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 起点
     */
    private TextView mapRouteSetStartTv;
    /**
     * 终点
     */
    private TextView mapRouteSetEndTv;
    /**
     * 起点lay
     */
    private LinearLayout mapRouteSetStartLay;
    /**
     * 终点lay
     */
    private LinearLayout mapRouteSetEndLay;
    /**
     * 切换lay
     */
    private RelativeLayout mapRouteSetChangeLay;
    /**
     * 确认
     */
    private TextView mapRouteSetSureBtn;
    /**
     * 1--默认起始位 2--手动改后的位置
     */
    private int type = -1;
    /**
     * SuggestionResult.SuggestionInfo
     */
    private SuggestionResult.SuggestionInfo suggestionInfo;

    /**
     *声明LocationClient类
     */
    private LocationClient mLocationClient = null;
    /**
     * 是否首次定位
     */
    private boolean isFirstLoc = true;
    /**
     * 经纬度
     */
    private  LatLng ll;
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG = "MapRouteSetActivity";
    /**
     * 标识
     */
    private int flag = -1;


    @Override
    public int getLayoutId() {
        return R.layout.activity_map_route_set;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.map_route_set));

        mapRouteSetStartTv = (TextView) findViewById(R.id.mapRouteSetStartTv);
        mapRouteSetEndTv = (TextView) findViewById(R.id.mapRouteSetEndTv);

        mapRouteSetStartLay = (LinearLayout) findViewById(R.id.mapRouteSetStartLay);
        mapRouteSetStartLay.setOnClickListener(this);

        mapRouteSetEndLay = (LinearLayout) findViewById(R.id.mapRouteSetEndLay);
        mapRouteSetEndLay.setOnClickListener(this);

        mapRouteSetChangeLay = (RelativeLayout) findViewById(R.id.mapRouteSetChangeLay);
        mapRouteSetChangeLay.setOnClickListener(this);

        mapRouteSetSureBtn = (TextView) findViewById(R.id.mapRouteSetSureBtn);
        mapRouteSetSureBtn.setText(getString(R.string.back_sure));
        mapRouteSetSureBtn.setOnClickListener(this);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isFirstLoc = true;
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);
        initLocation();
        mLocationClient.start();

        type = getIntent().getIntExtra("type",0);
        if(type == 1){
            //默认起始位置
            suggestionInfo = getIntent().getParcelableExtra("suggestInfo");
            if(suggestionInfo != null) {
                mapRouteSetStartTv.setText(getString(R.string.map_route_set_s));
                mapRouteSetEndTv.setText("" + suggestionInfo.key);
                Intent intent = new Intent(this, MapRoutePlanActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("suggestInfo", suggestionInfo);
                startActivity(intent);
            }
        }else if(type ==2){
            // 修改起始位置
            flag = getIntent().getIntExtra("flag",0);
            if(flag == 1){
                //开始位置
                mapRouteSetStartTv.setText(app.getRouteParam().getStartAddress());
                mapRouteSetEndTv.setText(app.getRouteParam().getEndAddress());
            }else if(flag == 2){
                //结束位置
                mapRouteSetStartTv.setText(app.getRouteParam().getStartAddress());
                mapRouteSetEndTv.setText(app.getRouteParam().getEndAddress());

            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.mapRouteSetStartLay:
                Intent intent = new Intent();
                intent.setClass(this, MapRouteSelectActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
                break;
            case R.id.mapRouteSetEndLay:
                Intent intent1 = new Intent();
                intent1.setClass(this, MapRouteSelectActivity.class);
                intent1.putExtra("flag",2);
                startActivity(intent1);
                break;
            case R.id.mapRouteSetChangeLay:
                break;
            case R.id.mapRouteSetSureBtn:
                Intent i = new Intent();
                i.setClass(this,MapRoutePlanActivity.class);
                i.putExtra("type",2);
                startActivity(i);
                break;
        }
    }

    /**
     * 定位的回调
     * @param bdLocation 定位
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null)
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

            isFirstLoc = false;
        }
    }

    @Override
    protected void onDestroy() {
        //退出时销毁定位
        mLocationClient.stop();
        super.onDestroy();

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
