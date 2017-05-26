package com.soarsky.installationmanual.ui.login;

import android.widget.EditText;


import com.soarsky.installationmanual.base.BasePresenter;
import com.soarsky.installationmanual.bean.LoginInfo;
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
 * 该类为 登陆Present<br>
 */

public class LoginPresent extends BasePresenter<LoginModel,LoginView> {


    @Override
    public void onStart() {

    }
    /**
     * 登陆请求
     * @param usernamne 用户名
     * @param password 密码
     * @return
     */
    public void login(final String usernamne,final String password){

        mView.showProgess();
        mModel.login(usernamne,password).subscribe(new Subscriber<ResponseDataBean<LoginInfo>>() {
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
            public void onNext(ResponseDataBean<LoginInfo> loginParam) {
                mView.stopProgess();
                if(loginParam.getStatus().equals("200")){
                    mModel.saveUsernameAndPassword(usernamne);
                    mModel.saveLoginInfo(loginParam.getData());
                    mView.showSuccess();
                }else{
                    mView.showFail(loginParam.getMessage());
                }
            }
        });
    }
}


