package com.soarsky.car.ui.illegal.query.set;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.LicensePwdBean;
import com.soarsky.car.bean.LicensePwdSendParam;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：联调与model、activity的桥梁<br>
 * 该类为 密码设置查询present<br>
 */


public class IllegalQuerySetPresent extends BasePresenter<IllegalQuerySetModel,IllegalQuerySetView> {

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
     * 设置查询密码
     *
     * @param param 参数
     */
    public void setQueryPwd(LicensePwdSendParam param) {

        mView.showProgess();
        mModel.setQueryPwd(param).subscribe(new Subscriber<ResponseDataBean<LicensePwdBean>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showIllegalSetFail();
            }

            @Override
            public void onNext(ResponseDataBean<LicensePwdBean> licensePwdParam) {
                mView.stopProgess();
                mView.showIllegalSetSuccess(licensePwdParam);


            }
        });
    }
}
