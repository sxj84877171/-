package com.soarsky.car.ui.carlocation.lovecar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
public class CarLocationActivity extends BaseActivity<CarLocationPresent,CarLocationModel> implements CarLocationView,View.OnClickListener{
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
    }

    @Override
    public void onError() {
        new AlertDialog.Builder(mContext).setTitle(mContext.getString(R.string.throwable))
                .setPositiveButton(mContext.getString(R.string.back_sure), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        finish();

                    }
                }).setCancelable(false).show();

    }

    @Override
    public void noData() {

        new AlertDialog.Builder(mContext).setTitle(mContext.getString(R.string.no_data_show))
                .setPositiveButton(mContext.getString(R.string.back_sure), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        finish();

                    }
                }).setCancelable(false).show();
    }

    private OnGetGeoCoderResultListener onGetGeoCoderResultListener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            carPosition.setText(reverseGeoCodeResult.getAddress());
        }
    };
}
