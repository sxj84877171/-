package com.soarsky.car.ui.forget;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.ui.register.RegisterParam;

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

public class ForgetPwdPresent extends BasePresenter<ForgetPwdModel,ForgetPwdView>{

    @Override
    public void onStart() {

    }

    /**
     * 忘记密码
     * @param username
     * @param idnumber
     * @param phone
     */
    public void forgetPwd(String username , String idnumber, String phone ){

        mView.showProgess();

        mModel.forgetPwd(username,idnumber,phone).subscribe(new Subscriber<ForgetParam>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showonError();
            }

            @Override
            public void onNext(ForgetParam forgetParam) {
                mView.stopProgess();
               mView.showData(forgetParam);

            }
        });

    }

    /**
     * 是否可见
     */
    public void isVisiable(int type){
        mView.isVisiable(type);
    }

    /**
     *验证忘记密码的信息
     */
    public void verifyForfetPwdInfo(){
        mView.verifyForfetPwdInfo();
    }

}
