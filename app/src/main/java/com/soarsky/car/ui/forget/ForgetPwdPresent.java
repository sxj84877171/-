package com.soarsky.car.ui.forget;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ForgetPwdInfo;
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
 * 程序功能：忘记密码present<br>
 * 该类为 ForgetPwdPresent<br>
 */

public class ForgetPwdPresent extends BasePresenter<ForgetPwdModel,ForgetPwdView>{

    @Override
    public void onStart() {

    }

    /**
     * 忘记密码
     * @param username 用户名
     * @param idnumber 身份证
     * @param phone 号码
     * @param code  验证码
     */
    public void forgetPwd(String username , String realname,String idnumber, String phone,String code ){

        mView.showProgess();

        mModel.forgetPwd(username,realname,idnumber,phone,code).subscribe(new Subscriber<ResponseDataBean<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showonError();
            }

            @Override
            public void onNext(ResponseDataBean<String> forgetParam) {
                mView.stopProgess();
               mView.showData(forgetParam);

            }
        });

    }

    /**
     * 是否可见
     * @param  type 类型
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

    /**
     * 获取验证码
     * @param phone 手机号码
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
