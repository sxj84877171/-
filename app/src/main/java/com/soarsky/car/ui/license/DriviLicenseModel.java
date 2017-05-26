package com.soarsky.car.ui.license;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：我的驾驶证model<br>
 * 该类为 DriviLicenseModel<br>
 */

public class DriviLicenseModel implements BaseModel{

    /**
     * 获取我的驾驶证
     * @param idNo
     * @param phone
     * @param verCode
     * @return
     */
    public Observable<ResponseDataBean<DrivingLicenseInfo>> getElecDriver(String idNo, String phone, String verCode){
        return api.getElecDriver(idNo,phone,verCode).compose(RxSchedulers.<ResponseDataBean<DrivingLicenseInfo>>io_main());
    }



}
