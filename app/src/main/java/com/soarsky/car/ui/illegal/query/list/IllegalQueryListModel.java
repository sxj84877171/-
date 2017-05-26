package com.soarsky.car.ui.illegal.query.list;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.violationdeal.ViolationDealSendParam;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：切换车辆违章信息model<br>
 * 该类为 IllegalQueryListModel<br>
 */


public class IllegalQueryListModel implements BaseModel{

    /**
     * 请求违章处理请求
     * @param param 入参
     * @return ResponseDataBean<ViolationDealInfo> 返回参数
     */
    public Observable<ResponseDataBean<ViolationDealInfo>> violationDeal(ViolationDealSendParam param) {
        return api.violationDeal(param.getCarnum()).compose(RxSchedulers.<ResponseDataBean<ViolationDealInfo>>io_main());
    }

    /**
     * 违章劝离超过十分钟记录违章
     * @param id 违章id
     * @return
     */
    public Observable<ResponseDataBean<Void>> appViolationAdvice(String id){
        return api.appViolationAdvice(id).compose(RxSchedulers.<ResponseDataBean<Void>>io_main());
    }

}
