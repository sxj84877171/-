package com.soarsky.policeclient.ui.login;

import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.CheckVersionDataBean;
import com.soarsky.policeclient.bean.LoginDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.uitl.LogUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  登录Present <br>
 */

public class LoginPresent extends BasePresenter<LoginModel,LoginView> {

    @Override
    public void onStart() {
    }

    /**
     * 登录
     * @param usernamne 用户名
     * @param password 密码
     */
    public void login(final String usernamne,final String password){

        mView.showProgess();
        mModel.login(usernamne,password).subscribe(new Subscriber<ResponseDataBean<LoginDataBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                System.out.println("onError:" + Thread.currentThread().getId());
                LogUtil.e(e);
                mView.showFail();

            }

            @Override
            public void onNext(ResponseDataBean<LoginDataBean> loginParam) {
                mView.stopProgess();
                System.out.println("onNext:=============" + loginParam.toJson());
                if(loginParam.getStatus().equals(Constants.STATUS)){
                    mModel.saveUsernameAndPassword(usernamne,password);
                    mView.showSuccess(loginParam);
                }else{
                    mView.showFail(loginParam);
               }
            }
        });
    }

    /**
     * 更新app
     * @param type
     */
    public void checkVersion(String type){
        mView.showProgess();
        mModel.checkVersion(type).subscribe(new Subscriber<ResponseDataBean<CheckVersionDataBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(ResponseDataBean<CheckVersionDataBean> checkVersion) {
                mView.stopProgess();
                if (checkVersion.getStatus().equals(SUCCESS_FLAG)){
                    mView.checkSuccess(checkVersion.getData());
                }else {
                    mView.checkFail(checkVersion.getData());
                }
            }
        });
    }

    /**
     * 下载安装文件
     * @param url
     */
    public  void  loadFile(String url){


        mView.showProgess();
        mModel.loadFile(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mView.stopProgess();
                mView.loadSuccess(call,response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mView.stopProgess();
                mView.loadFail(call,t);
            }
        });
    }

}
