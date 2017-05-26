package com.soarsky.car.ui.car;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：机动车model
 * 该类为
 */

public class CarModel implements BaseModel{
    /**
     * 我的机动车
     * @param param
     * @return CarParam
     */
    public Observable<CarParam> getCarInfo(CarSendParam param){
        return Api.getInstance().service.getCarInfo(param.getCarnum()).compose(RxSchedulers.<CarParam>io_main());
    }
}
