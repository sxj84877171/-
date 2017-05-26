package com.soarsky.car.ui.drivrecord.map;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.CarLocationBean;
import com.soarsky.car.bean.PositionInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by 魏凯 on 2016/11/18.
 */

public class DrivingRecordMapModel implements BaseModel {

    public Observable<ResponseDataBean<List<PositionInfo>>> getPositionDataByDate(String date){
        return api.getDriverData(App.getApp().getCommonParam().getUserName(),App.getApp().getCommonParam().getQueryPwd(),App.getApp().getCommonParam().getCarNum(),date).compose(RxSchedulers.<ResponseDataBean<List<PositionInfo>>>io_main());


    }
    public Observable<ResponseDataBean<CarLocationBean>> getCarLocation(String carNum){
        return api.getCarLocation(carNum).compose(RxSchedulers.<ResponseDataBean<CarLocationBean>>io_main());
    }

}
