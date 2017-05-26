package com.soarsky.car.ui.register;

import com.soarsky.car.Constants;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.umeng.analytics.MobclickAgent;

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
 * 程序功能：注册present<br>
 * 该类为RegisterPresent<br>
 */

public class RegisterPresent extends BasePresenter<RegisterModel,RegisterView>{

    @Override
    public void onStart() {

    }

    /**
     * 注册账号
     * @param registerSendParam 参数
     */
    public void registe(final RegisterSendParam registerSendParam){
            mView.showProgess();
            mModel.register(registerSendParam).subscribe(new Subscriber<ResponseDataBean>() {
                @Override
                public void onCompleted() {
                    System.out.print("onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    System.out.print("onError");
                    mView.stopProgess();
                    mView.showonError();
                }

                @Override
                public void onNext(ResponseDataBean registerParam) {
                    mView.stopProgess();
                   if(Constants.REQUEST_SUCCESS.equals(registerParam.getStatus())){
                       mView.showSuccess();
                       MobclickAgent.onProfileSignIn("Android",registerSendParam.getUsername());
                   }else{
                       mView.showFail(registerParam);
                   }
                }
            });
    }

    /**
     * 验证注册信息
     */
    public void verifyRegister(){
        mView.verifyRegister();
    }
    /**
     * 密码的可见性
     */
    public void isVisiablePassword(int type){
        mView.isVisiablePassword(type);
    }

    /**
     * 勾选协议
     */
    public void selectAgree(){
        mView.selectAgree();
    }

    /**
     * 跳转到协议界面
     */
    public void gotoAgreePage(){
        mView.gotoAgreePage();
    }

    /**
     * 获取验证码
     * @param phone 电话
     * @param code 验证码
     */
    public void sendsms(String phone,String code){

        mView.showProgess();
        mModel.sendsms(phone,code).subscribe(new Subscriber<ResponseDataBean<Void>>() {
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
            public void onNext(ResponseDataBean<Void> voidResponseDataBean) {
                mView.stopProgess();
                mView.sendSmsSuccess(voidResponseDataBean);

            }
        });

    }

}
