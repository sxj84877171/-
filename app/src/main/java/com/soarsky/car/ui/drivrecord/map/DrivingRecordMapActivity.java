package com.soarsky.car.ui.drivrecord.map;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import cn.qqtheme.framework.picker.DatePicker;

public class DrivingRecordMapActivity extends BaseActivity<DrivingRecordMapPresent,DrivingRecordMapModel> implements PositionView,View.OnClickListener{
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private TextView dateTv;
    private RelativeLayout rlDate;
    private List<LatLng> latLngs;
    /**
     * 返回
     */
    private LinearLayout backLay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_driving_record_map;
    }

    @Override
    public void initView() {
 		Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int date = c.get(Calendar.DATE);
        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);
        ((TextView) findViewById(R.id.topicTv)).setText(getString(R.string.driv_record));
        dateTv = (TextView) findViewById(R.id.date);
        rlDate = (RelativeLayout) findViewById(R.id.rl_date);
        rlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker picker = new DatePicker(DrivingRecordMapActivity.this, DatePicker.YEAR_MONTH_DAY);

                picker.setSelectedItem(year,month+1,date);
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        StringBuilder sb = new StringBuilder();
                        String theDay;
                        String themonth;
                        if(month.startsWith("0")){
                            themonth = month.substring(1);
                        }else {
                            themonth = month;
                        }
                        if(day.startsWith("0")){
                            theDay = day.substring(1);
                        }else {
                            theDay = day;
                        }
                        sb.append(year).append("-").append(themonth).append("-").append(theDay);
                        dateTv.setText(sb.toString());
                        mBaiduMap.clear();
                        mPresenter.getPositionDataByDate(sb.toString());
                    }
                });
                picker.show();
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(month);
        sb.append("-");
        sb.append(date);
        dateTv.setText(sb.toString());
        //mPresenter.getPositionDataByDate(curDateyyyy_MM_dd);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMapLoadedCallback(onMapLoadedCallback);
    }


    @Override
    protected String getHeaderTitle() {
        return getString(R.string.driv_record);
    }


    @Override
    public void hasPositionData(List<LatLng> latLngs) {
        this.latLngs = latLngs;
        OverlayOptions ooPolyline = new PolylineOptions().width(10)
                .color(Color.GREEN).points(latLngs);
        mBaiduMap.addOverlay(ooPolyline);
        showMark(latLngs.get(0),R.drawable.start);
        showMark(latLngs.get(latLngs.size()-1),R.drawable.end);
        newMapBound();
    }

    @Override
    public void showCarLocation(LatLng latLng) {
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng,14);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.carlocation);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .icon(bitmap);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }



    @Override
    public void onError() {
        ToastUtil.show(this, R.string.throwable);
    }

    @Override
    public void noData() {
        ToastUtil.show(this, "没有数据");
    }

    private void newMapBound(){
        if(latLngs!=null){
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for(LatLng latLng : latLngs){
                builder.include(latLng);
            }
            LatLngBounds latLngBounds = builder.build();
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(latLngBounds);
            mBaiduMap.setMapStatus(mMapStatusUpdate);
        }

    }

    private BaiduMap.OnMapLoadedCallback onMapLoadedCallback = new BaiduMap.OnMapLoadedCallback() {
        @Override
        public void onMapLoaded() {
            newMapBound();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getPositionDataByDate(dateTv.getText().toString());
    }


    private void showMark(LatLng point,int drawableId){
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(drawableId);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }
}
