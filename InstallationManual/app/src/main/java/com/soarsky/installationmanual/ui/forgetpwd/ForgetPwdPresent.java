package com.soarsky.installationmanual.ui.forgetpwd;

import com.soarsky.installationmanual.base.BasePresenter;
import com.soarsky.installationmanual.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 找回密码Present<br>
 */

public class ForgetPwdPresent extends BasePresenter<ForgetPwdModel,ForgetPwdView> {


    @Override
    public void onStart() {


    }
    /**
     * 发送短信
     * @param phone 电话号码
     * @return
     */
    public void sendsms(final String phone){

        mView.showProgess();
        mModel.sendsms(phone).subscribe(new Subscriber<ResponseDataBean<Void>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                System.out.println("onError:" + Thread.currentThread().getId());
                mView.showFail();

            }

            @Override
            public void onNext(ResponseDataBean<Void> loginParam) {
                mView.stopProgess();
                System.out.println("onNext:=============" + loginParam.toJson());
                if(loginParam.getStatus().equals("200")){
                    mView.sendSmsSuccess();
                }else{
                    mView.showFail(loginParam.getMessage());
                }
            }
        });
    }
    /**
     * 找回密码
     * @param phone 电话
     * @param idCard 身份证号
     * @param yanzhengma 验证码
     * @param pwd 密码
     * @param pwdAgain 确认密码
     * @return
     */
    public void forgetPwd(final String phone,final String yanzhengma,final String pwd,final String pwdAgain,final String idCard){

        mView.showProgess();
        mModel.forgetPwd(phone,yanzhengma,pwd,pwdAgain,idCard).subscribe(new Subscriber<ResponseDataBean<Void>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                System.out.println("onError:" + Thread.currentThread().getId());
                mView.showFail();
            }

            @Override
            public void onNext(ResponseDataBean<Void> loginParam) {
                mView.stopProgess();
                System.out.println("onNext:=============" + loginParam.toJson());
                if(loginParam.getStatus().equals("200")){
                    mModel.saveUsernameAndPassword(idCard);
                    mView.showSuccess();
                }else{
                    mView.showFail(loginParam.getMessage());
                }
            }
        });
    }
}


