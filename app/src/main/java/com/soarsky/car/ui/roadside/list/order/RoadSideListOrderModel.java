package com.soarsky.car.ui.roadside.list.order;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class RoadSideListOrderModel implements BaseModel{
    /**
     *获取故障救援详细信息
     * @param id
     * @return
     */
    public Observable<RoadSideListOrderParam> getRescueById(String id){
        return Api.getInstance().service.getRescueById(id).compose(RxSchedulers.<RoadSideListOrderParam>io_main());
    }

    /**
     * 取消故障救援记录
     * @param id
     * @return
     */
    public Observable<RoadSideListOrderDelRescueParam> delRescueById(String id){
        return Api.getInstance().service.delRescueById(id).compose(RxSchedulers.<RoadSideListOrderDelRescueParam>io_main());
    }

}
