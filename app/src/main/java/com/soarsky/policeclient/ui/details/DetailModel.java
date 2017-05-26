package com.soarsky.policeclient.ui.details;

        import com.soarsky.policeclient.base.BaseModel;
        import com.soarsky.policeclient.bean.CarDetailsDataBean;
        import com.soarsky.policeclient.bean.ResponseDataBean;
        import com.soarsky.policeclient.data.remote.server1.ApiM;
        import com.soarsky.policeclient.helper.RxSchedulers;

import rx.Observable;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 车辆详情Model<br>
 */
public class DetailModel implements BaseModel {

    /**
     * 根据车牌号获取车辆详情信息
     * @param carNO 车牌号
     * @return
     */
    public Observable<ResponseDataBean<CarDetailsDataBean>> getCarDetails(String carNO){

        return ApiM.getInstance().service.getCarDetails(carNO).compose(RxSchedulers.<ResponseDataBean<CarDetailsDataBean>>io_main());

    }


}
