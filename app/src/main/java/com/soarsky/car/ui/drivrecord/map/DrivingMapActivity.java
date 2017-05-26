package com.soarsky.car.ui.drivrecord.map;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.ArcOptions;
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
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.soarsky.car.R;

import java.util.ArrayList;
import java.util.List;

public class DrivingMapActivity extends AppCompatActivity {
    MapView mMapView = null;
    BaiduMap mBaiduMap;
    private List<String> positionList = new ArrayList<>();
    private GeoCoder mSearch;
    private List<LatLng> latLngs=new ArrayList<>();
    int index;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_map);
        initPositionList();
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //定义地图状态

        mBaiduMap.setOnMapLoadedCallback(onMapLoadedCallback);
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(listener);
        mSearch.geocode(new GeoCodeOption()
                .city("长沙市")
                .address(positionList.get(index)));
    }

    private BaiduMap.OnMapLoadedCallback onMapLoadedCallback = new BaiduMap.OnMapLoadedCallback() {
        @Override
        public void onMapLoaded() {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for(LatLng latLng : latLngs){
                builder.include(latLng);
            }
            LatLngBounds latLngBounds = builder.build();
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(latLngBounds);
            mBaiduMap.setMapStatus(mMapStatusUpdate);
        }
    };

    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
                mSearch.geocode(new GeoCodeOption()
                        .city("长沙市")
                        .address(positionList.get(index)));
                return;
            }
            //获取地理编码结果

            latLngs.add(result.getLocation());
            index++;
            if(index<7){
                mSearch.geocode(new GeoCodeOption()
                        .city("长沙市")
                        .address(positionList.get(index)));
            }
            if(index==6){
                mMapView.setVisibility(View.VISIBLE);
                OverlayOptions ooPolyline = new PolylineOptions().width(10)
                        .color(Color.GREEN).points(latLngs);
                mBaiduMap.addOverlay(ooPolyline);
                showMark(R.drawable.start,latLngs.get(0));
                showMark(R.drawable.end,latLngs.get(latLngs.size()-1));
                mSearch.destroy();
            }

        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
            }
            //获取反向地理编码结果
        }
    };
    private void showMark(int resourceId,LatLng point){
        //定义Maker坐标点
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(resourceId);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    private void initPositionList(){
        positionList.add("和馨园");
        positionList.add("岳麓区梅溪湖步步高新天地");
        positionList.add("麓谷明珠");
        positionList.add("麓谷信息港A座");
        positionList.add("五一广场");
        positionList.add("万家丽广场");
        positionList.add("大塘公寓小区");

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
