package com.soarsky.installationmanual.ui.register;

import android.widget.EditText;

import com.soarsky.installationmanual.base.BasePresenter;
import com.soarsky.installationmanual.bean.LoginInfo;
import com.soarsky.installationmanual.bean.RegisterSendBean;
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
 * 该类为 注册Present<br>
 */

public class RegisterPresent extends BasePresenter<RegisterModel,RegisterView> {


    @Override
    public void onStart() {


    }

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
    public void register(final RegisterSendBean registerSendBean){

        mView.showProgess();
        mModel.register(registerSendBean).subscribe(new Subscriber<ResponseDataBean<Void>>() {
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
                    mModel.save(registerSendBean.getIdNo());
                    mView.showSuccess();
                }else{
                    mView.showFail(loginParam.getMessage());
                }
            }
        });
    }
}


