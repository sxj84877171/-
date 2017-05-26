package com.soarsky.car.ui.drivrecord.map;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.carlocation.lovecar.CarLocationBean;
import com.soarsky.car.ui.login.LoginParam;

import rx.Observable;

/**
 * Created by 魏凯 on 2016/11/18.
 */

public class DrivingRecordMapModel implements BaseModel {

    public Observable<PositionData> getPositionDataByDate(String date){
        return Api.getInstance().service.getDriverData(App.getApp().getCommonParam().getUserName(),App.getApp().getCommonParam().getQueryPwd(),App.getApp().getCommonParam().getCarNum(),date).compose(RxSchedulers.<PositionData>io_main());


    }
    public Observable<CarLocationBean> getCarLocation(String carNum){
        return Api.getInstance().service.getCarLocation(carNum).compose(RxSchedulers.<CarLocationBean>io_main());
    }

}
