package com.soarsky.car.ui.blindterm.validation;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.ui.license.pwd.LicensePwdParam;

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
 * 程序功能：终端绑定功能
 * 该类为
 */


public class BlindTermValidationPresent extends BasePresenter<BlindTermValidationModel,BlindTermValidationView>{

    @Override
    public void onStart() {

    }

    /**
     * 密码的可见性
     */
    public void isVisiablePassword(int type){
        mView.isVisiablePassword(type);
    }

    /**
     * 验证输入信息是否为空
     */
    public void verifyInputInfo(){
        mView.verifyInputInfo();
    }

    public void setQueryPwd(BlindTermValidSendParam param){

        mView.showProgess();
        mModel.setQueryPwd(param).subscribe(new Subscriber<LicensePwdParam>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();

            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
            }

            @Override
            public void onNext(LicensePwdParam licensePwdParam) {

                mView.stopProgess();
            }
        });

    }
}
