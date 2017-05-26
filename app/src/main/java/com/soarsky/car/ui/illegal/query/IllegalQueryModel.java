package com.soarsky.car.ui.illegal.query;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

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
 * 程序功能：切换车辆密码验证model<br>
 * 该类为 IllegalQueryModel<br>
 */


public class IllegalQueryModel implements BaseModel{

    /**
     * 验证查询密码
     * @param carnum 车牌
     * @param qwt 密码
     * @return   ResponseDataBean<Object> 返回参数
     */
    public Observable<ResponseDataBean<Object>> getIlleagaInfo(String carnum, String qwt){
        return api.getIlleagaInfo(carnum,qwt).compose(RxSchedulers.<ResponseDataBean<Object>>io_main());
    }

}
