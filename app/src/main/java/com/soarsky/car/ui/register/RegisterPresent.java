package com.soarsky.car.ui.register;

import com.soarsky.car.base.BasePresenter;

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

public class RegisterPresent extends BasePresenter<RegisterModel,RegisterView>{

    @Override
    public void onStart() {

    }

    /**
     * 注册账号
     * @param registerSendParam
     */
    public void registe(RegisterSendParam registerSendParam){
            mView.showProgess();
            mModel.register(registerSendParam).subscribe(new Subscriber<RegisterParam>() {
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
                public void onNext(RegisterParam registerParam) {
                    mView.stopProgess();
                   if("0".equals(registerParam.getStatus())){
                       mView.showSuccess();
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

}
