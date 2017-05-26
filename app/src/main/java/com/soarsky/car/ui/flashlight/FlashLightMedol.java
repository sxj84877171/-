package com.soarsky.car.ui.flashlight;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  闪灯找车页面逻辑层<br>
 */

public class FlashLightMedol implements BaseModel {

    /***
     * 获取所有操作车牌
     * @param phone
     * @return  行驶证信息
     */
    public Observable<ResponseDataBean<List<LicenseInfo>>> getAllCarnum(String phone){
        return api.getAllCarnum(phone).compose(RxSchedulers.<ResponseDataBean<List<LicenseInfo>>>io_main());
    }

    /**
     * 闪灯找车
     * @param carnum 车牌号
     * @param lon 经度
     * @param lat 纬度
     * @return   车辆的状态
     */
    public Observable<ResponseDataBean<String>> flashLight(String carnum,String lon,String lat){
        return api.flashLight(carnum,lon,lat).compose(RxSchedulers.<ResponseDataBean<String>>io_main());
    }
}
