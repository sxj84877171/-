package com.soarsky.car.ui.carlocation.lovecar;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.CarLocationBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Created by 魏凯 on 2016/11/24.
 */

public class CarLocationModel implements BaseModel {

    public Observable<ResponseDataBean<CarLocationBean>> getCarLocation(String carNum){
        return api.getCarLocation(carNum).compose(RxSchedulers.<ResponseDataBean<CarLocationBean>>io_main());
    }
}
