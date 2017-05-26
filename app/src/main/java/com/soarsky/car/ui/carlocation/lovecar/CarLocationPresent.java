package com.soarsky.car.ui.carlocation.lovecar;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.soarsky.car.R;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.data.map.design.LonLat;
import com.soarsky.car.ui.drivrecord.map.PositionData;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by 魏凯 on 2016/11/24.
 */

public class CarLocationPresent extends BasePresenter<CarLocationModel,CarLocationView> {
    @Override
    public void onStart() {

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
                if (carLocationBean.getData()!=null){
                    LatLng latLng = new LatLng(Double.parseDouble(carLocationBean.getData().getLat()),Double.parseDouble(carLocationBean.getData().getLon()));
                    mView.showCarLocation(latLng);
                }else {
                    mView.noData();
                }

            }
        });
    }
}
