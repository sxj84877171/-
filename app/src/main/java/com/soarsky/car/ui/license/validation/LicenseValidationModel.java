package com.soarsky.car.ui.license.validation;

import com.soarsky.car.base.BaseModel;
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
 * 程序功能：驾驶证验证Model<br>
 * 该类为LicenseValidationModel<br>
 */

public class LicenseValidationModel implements BaseModel{

    /**
     * 短信验证
     * @param phone 电话
     * @param ctype  0:伴君行注册 1驾照绑定App 2 终端绑定App 3 忘记密码
     * @return
     */
    public Observable<ResponseDataBean<Void>> sendsms(String phone, String ctype){
        return api.sendsms(phone,ctype).compose(RxSchedulers.<ResponseDataBean<Void>>io_main());
    }



}
