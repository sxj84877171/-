package com.soarsky.car.ui.drivrecord.map;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.map.A;
import com.soarsky.car.App;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.data.map.design.LonLat;
import com.soarsky.car.ui.carlocation.lovecar.CarLocationBean;


import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by 魏凯 on 2016/11/18.
 */

public class DrivingRecordMapPresent extends BasePresenter<DrivingRecordMapModel, PositionView> {
    //private ICoordinateConverter iCoordinateConverter = new BaiduICoordinateConverter();
    @Override
    public void onStart() {

    }

    public void getPositionDataByDate(String date) {

        mModel.getPositionDataByDate(date).subscribe(new Subscriber<PositionData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("weikai","kk");
            }

            @Override
            public void onNext(PositionData positionData) {
                if(positionData.getData()!=null){
                    List<LatLng> latLngs=new ArrayList<>();
                    for (PositionData.Position position:positionData.getData()) {
                        LonLat lonLat = new LonLat(Double.parseDouble(position.getLon()),Double.parseDouble(position.getLat()));
                        //iCoordinateConverter.converter(lonLat);
                        LatLng latLng = new LatLng(lonLat.getLat(),lonLat.getLon());
                        latLngs.add(latLng);
                    }
                    if(latLngs.size()!=0){
                        mView.hasPositionData(latLngs);
                    }else {
                        getCarLocation(App.getApp().getCommonParam().getCarNum());

                    }
                }

            }
        });
    }


    public void getCarLocation(String carNum) {

        mModel.getCarLocation(carNum).subscribe(new Subscriber<CarLocationBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            public void onNext(CarLocationBean carLocationBean) {
                if(carLocationBean.getData()!=null){
                    LatLng latLng = new LatLng(Double.parseDouble(carLocationBean.getData().getLat()),Double.parseDouble(carLocationBean.getData().getLon()));
                    mView.showCarLocation(latLng);
                }else {
                    mView.noData();
                }

            }
        });
    }
}
