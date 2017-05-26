package com.soarsky.car.ui.carlocation.lovecar;

import com.baidu.mapapi.model.LatLng;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.CarLocationBean;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * Created by 魏凯 on 2016/11/24.
 */

public class CarLocationPresent extends BasePresenter<CarLocationModel,CarLocationView> {
    @Override
    public void onStart() {

    }

    public void getCarLocation(String carNum) {

        mModel.getCarLocation(carNum).subscribe(new Subscriber<ResponseDataBean<CarLocationBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            public void onNext(ResponseDataBean<CarLocationBean> carLocationBean) {
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
