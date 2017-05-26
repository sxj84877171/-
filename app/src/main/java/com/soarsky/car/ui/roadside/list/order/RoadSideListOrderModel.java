package com.soarsky.car.ui.roadside.list.order;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListOrderInfo;
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
 * 程序功能：救援申请列表model
 * 该类为 RoadSideListOrderModel
 */


public class RoadSideListOrderModel implements BaseModel{
    /**
     *获取故障救援详细信息
     * @param id
     * @return 故障救援详细
     */
    public Observable<ResponseDataBean<RoadSideListOrderInfo>> getRescueById(String id){
        return api.getRescueById(id).compose(RxSchedulers.<ResponseDataBean<RoadSideListOrderInfo>>io_main());
    }

    /**
     * 取消故障救援记录
     * @param id
     * @return 故障救援记录
     */
    public Observable<ResponseDataBean<Object>> delRescueById(String id){
        return api.delRescueById(id).compose(RxSchedulers.<ResponseDataBean<Object>>io_main());
    }

}
