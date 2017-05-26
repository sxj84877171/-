package com.soarsky.car.ui.roadside.list;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListDataBean;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：救援申请列表model<br>
 * 该类为 RoadSideListModel<br>
 */


public class RoadSideListModel implements BaseModel{

    /**
     * 获取故障救援列表
     * @param carnum 车牌
     * @return  ResponseDataBean<List<RoadSideListDataBean>>   故障救援列表
     */
    public Observable<ResponseDataBean<List<RoadSideListDataBean>>> getRescueList(String carnum){
        return api.getRescueList(carnum).compose(RxSchedulers.<ResponseDataBean<List<RoadSideListDataBean>>>io_main());
    }
}
