package com.soarsky.car.ui.illegal.fragment.driver;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.helper.RxSchedulers;

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
 * 程序功能：根据驾驶证获取违章信息model<br>
 * 该类为 IllegalDriverFragmentModel<br>
 */


public class IllegalDriverFragmentModel implements BaseModel{

    /**
     * 请求违章处理请求
     * @param idNo 身份证
     * @return <ResponseDataBean<ViolationDealInfo> 返回参数
     */
    public Observable<ResponseDataBean<ViolationDealInfo>> getIlleagaInfoByIdNo(String idNo){
        return api.getIlleagaInfoByIdNo(idNo).compose(RxSchedulers.<ResponseDataBean<ViolationDealInfo>>io_main());
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
