package com.soarsky.car.ui.license.validation;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

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
 * 程序功能：驾驶证验证Present<br>
 * 该类为LicenseValidationPresent<br>
 */

public class LicenseValidationPresent extends BasePresenter<LicenseValidationModel,LicenseValidationView>{


    @Override
    public void onStart() {

    }


    /**
     * 获取验证码
     * @param phone 电话
     * @param ctype 类型
     */
    public void sendsms(String phone,String ctype){

        mView.showProgess();
        mModel.sendsms(phone,ctype).subscribe(new Subscriber<ResponseDataBean<Void>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.sendSmsFail();
            }

            @Override
            public void onNext(ResponseDataBean<Void> bean) {
                mView.stopProgess();
                mView.sendSmsSuccess(bean);
            }
        });

    }




}
