package com.soarsky.car.ui.illegal.fragment.personal;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.ui.violationdeal.ViolationDealSendParam;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：根据车牌号获取违章信息model<br>
 * 该类为 IllegalPersonalFragmentModel<br>
 */


public class IllegalPersonalFragmentModel implements BaseModel{

    /**
     * 与终端通讯服务类接口
     */
    private IScan scan ;

    /**
     * 请求违章处理请求
     * @param param 参数
     * @return  ResponseDataBean<ViolationDealInfo> 违章信息
     */
    public Observable<ResponseDataBean<ViolationDealInfo>> violationDeal(ViolationDealSendParam param){
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
