package com.soarsky.car.ui.carlocation.lovecar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;


/**
 * Andriod_Car_App
 * 作者： 魏凯
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：weikai@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 爱车位置
 */
public class CarLocationActivity extends BaseActivity<CarLocationPresent,CarLocationModel> implements CarLocationView,View.OnClickListener, BDLocationListener {
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 地图
     */
    private BaiduMap mBaiduMap;
    /**
     * 位置
     */
    private TextView carPosition;

    private ImageView iv_locate;
    private TextView carNum;

    /**
     * 定位的城市
     */
    private TextView mapRouteSetSureBtn;

    /**
     * 距离
     */
    private TextView juli;

    /**
     * 地图
     */
    private BaiduMap baiduMap;
    /**
     *声明LocationClient类
     */
    private LocationClient mLocationClient = null;
    private Double lat;
    private Double lon;

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_location;
    }

    @Override
    public void initView() {
        carPosition = (TextView) findViewById(R.id.carPosition);
        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.car_position));
        mBaiduMap = ((MapView) findViewById(R.id.bmapView)).getMap();

        iv_locate = (ImageView) findViewById(R.id.iv_locate);
        iv_locate.setVisibility(View.VISIBLE);

        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);

        initLocation();
        mLocationClient.start();
        // 设置为一般地图
        mBaiduMap.setMapType(mBaiduMap.MAP_TYPE_NORMAL);

        mapRouteSetSureBtn = (TextView) findViewById(R.id.mapRouteSetSureBtn);

        carNum = (TextView) findViewById(R.id.dangqianweizhi);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        carNum.setText(bundle.getString("carNum"));

        juli = (TextView) findViewById(R.id.juli);

    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getCarLocation(App.getApp().getCommonParam().getCarNum());
    }



    @Override
    public void showCarLocation(LatLng point) {
        GeoCoder mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(onGetGeoCoderResultListener);
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(point));
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(point,14);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.carlocation);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);


        LatLng latLng = new LatLng(lat,lon);
        juli.setText(String.format("%.2f", DistanceUtil. getDistance(point, latLng)/1000.00)+"KM");
    }

    @Override
    public void onError() {
        //ToastUtil.show(this, R.string.throwable);
        new AlertDialog.Builder(mContext).setTitle(mContext.getString(R.string.throwable))
                .setPositiveButton(mContext.getString(R.string.back_sure), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                })
                /*.setNegativeButton(mContext.getString(R.string.back_cancel), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                })*/.show();
    }

    @Override
    public void noData() {
        //ToastUtil.show(this, getString(R.string.no_data_show));
        new AlertDialog.Builder(mContext).setTitle(mContext.getString(R.string.no_data_show))
                .setPositiveButton(mContext.getString(R.string.back_sure), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                })
                /*.setNegativeButton(mContext.getString(R.string.back_cancel), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                })*/.show();
    }

    private OnGetGeoCoderResultListener onGetGeoCoderResultListener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            if (reverseGeoCodeResult != null){
                carPosition.setText(reverseGeoCodeResult.getAddress());
                mapRouteSetSureBtn.setText(reverseGeoCodeResult.getAddressDetail().city);

            }
        }
    };


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        lat = bdLocation.getLatitude();
        lon = bdLocation.getLongitude();

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
