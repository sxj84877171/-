package com.soarsky.car.ui.forget;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.register.RegisterParam;

import rx.Observable;
import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class ForgetPwdModel implements BaseModel{

    /**
     * 忘记密码
     * @param username
     * @param idnumber
     * @param phone
     * @return
     */
    public Observable<ForgetParam> forgetPwd(String username , String idnumber, String phone ){
        return Api.getInstance().service.forgetPassword(username,idnumber,phone).compose(RxSchedulers.<ForgetParam>io_main());

    }



}
