package com.soarsky.car.ui.car;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.CarInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/8 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：机动车model <br>
 * 该类为 CarModel <br>
 */

public class CarModel implements BaseModel{
    /**
     * 我的机动车
     * @param param 参数
     * @return CarParam 返回结果的参数
     */
    public Observable<ResponseDataBean<CarInfo>> getCarInfo(CarSendParam param){
        return api.getCarInfo(param.getCarnum()).compose(RxSchedulers.<ResponseDataBean<CarInfo>>io_main());
    }
}
