package com.soarsky.car.ui.carlocation.lovecar;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.drivrecord.map.PositionData;

import rx.Observable;

/**
 * Created by 魏凯 on 2016/11/24.
 */

public class CarLocationModel implements BaseModel {

    public Observable<CarLocationBean> getCarLocation(String carNum){
        return Api.getInstance().service.getCarLocation(carNum).compose(RxSchedulers.<CarLocationBean>io_main());
    }
}
