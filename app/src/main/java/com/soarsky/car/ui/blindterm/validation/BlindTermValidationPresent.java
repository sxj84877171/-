package com.soarsky.car.ui.blindterm.validation;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.BlindTermValidSendParam;
import com.soarsky.car.bean.LicensePwdBean;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：终端绑定功能 <br>
 * 该类为 验证一些密码及其正则的判断 <br>
 */


public class BlindTermValidationPresent extends BasePresenter<BlindTermValidationModel,BlindTermValidationView>{

    @Override
    public void onStart() {

    }

    /**
     * 密码的可见性
     * @param type 是否可见性的类型
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

    /**
     * 设置查询密码
     * @param param 查询密码的参数
     * @return
     */
    public void setQueryPwd(BlindTermValidSendParam param){

        mView.showProgess();
        mModel.setQueryPwd(param).subscribe(new Subscriber<ResponseDataBean<LicensePwdBean>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();

            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.setQueryPwdFail();
            }

            @Override
            public void onNext(ResponseDataBean<LicensePwdBean> licensePwdParam) {

                mView.stopProgess();
                mView.setQueryPwdSuccess(licensePwdParam);
            }
        });

    }


}
