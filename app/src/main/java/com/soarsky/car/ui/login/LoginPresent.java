package com.soarsky.car.ui.login;

import android.widget.EditText;

import com.soarsky.car.Constants;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.LoginInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.server.check.ConfirmDriverService;

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
 * 程序功能：登录<br>
 * 该类为 LoginPresent<br>
 */

public class LoginPresent extends BasePresenter<LoginModel,LoginView>{


    @Override
    public void onStart() {
        String username = mModel.getUsername();
        String password = mModel.getPassword();
        mModel.setContext(context);
//        mView.initUsernameAndPassword(username,password);

    }

    /**
     * 登陆请求
     * @param usernamne 用户名
     * @param password 密码
     */
    public void login(final String usernamne, final String password){

        mView.showProgess();
        mModel.login(usernamne,password).subscribe(new Subscriber<ResponseDataBean<LoginInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               System.out.println("onError:" + Thread.currentThread().getId());
                e.printStackTrace();
//                LogUtil.e(Log.getStackTraceString(e));
                mView.stopProgess();
                mView.showonError();

            }

            @Override
            public void onNext(ResponseDataBean<LoginInfo> loginParam) {

                mView.stopProgess();

                mModel.saveUsernameAndPassword(usernamne,password);

                if(loginParam.getStatus().equals(Constants.REQUEST_SUCCESS)){
                    mView.showSuccess(loginParam.getData());

                }else{
                    mView.showFail(loginParam);
                }
            }
        });
    }

    /**
     * 跳转到注册界面
     */
    public void register(){
        mView.gotoRegisterrPage();
    }

    /**
     * 跳转忘记密码
     */
    public void gotoForgetPage(){
        mView.gotoForgetPage();
    }
    /**
     * 验证登陆信息是否为空
     * @param userEt 用户名
     * @param passWordEt 密码
     */
    public void verifyLoginInfo(EditText userEt,EditText passWordEt){
        mView.verifyLoginInfo(userEt,passWordEt);
    }
    /**
     * 密码的可见性
     */
    public void isVisiablePassword(){
        mView.isVisiablePassword();
    }

    /**
     * 是否绑定驾驶证
     */
    public  boolean isBindDrivingLicence(){
        return  mModel.isBindDrivingLicence();

    }

    /**
     * 设置服务
     * @param confirmDriverService 服务
     */
    public void  setConfirmDriverService(ConfirmDriverService confirmDriverService){
        mModel.setConfirmDriverService(confirmDriverService);

    }


    /**
     * 开始连接工作
     */
    public  void startConnectWork(){
        mModel.startConnectWork();
    }

    /**
     * 保存数据
     */
    public void saveData(){

    }

}


