package com.soarsky.car.ui.login;

import android.util.Log;
import android.widget.EditText;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.ui.license.DriviLicenseParam;
import com.soarsky.car.ui.license.DriviLicenseSendParam;
import com.soarsky.car.server.check.ConfirmDriverService;

import com.soarsky.car.uitl.LogUtil;

import rx.Subscriber;
import rx.functions.Action1;

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

public class LoginPresent extends BasePresenter<LoginModel,LoginView>{


    @Override
    public void onStart() {
        String username = mModel.getUsername();
        String password = mModel.getPassword();
        mModel.setContext(context);
        mView.initUsernameAndPassword(username,password);

    }

    /**
     * 登陆请求
     * @param usernamne
     * @param password
     */
    public void login(String usernamne,String password){

        mView.showProgess();

        mModel.login(usernamne,password).subscribe(new Subscriber<LoginParam>() {
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
            public void onNext(LoginParam loginParam) {

                mView.stopProgess();

                if(loginParam.getStatus().equals("0")){
                    mView.showSuccess(loginParam);

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
     * 获取车牌号
     * @param phone
     */
    public void getAllCarnum(String phone){

        mView.showProgess();

        mModel.getAllCarnum(phone).subscribe(new Subscriber<CarNumParam>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.getAllCarnumFail();

            }

            @Override
            public void onNext(CarNumParam carNumParam) {

                mView.stopProgess();
                mView.getAllCarnumSuccess(carNumParam);

            }
        });

    }

    /**
     * 获取我的驾驶证
     * @param param
     */
    public void getElecDriver(DriviLicenseSendParam param){
        mView.showProgess();
        mModel.getElecDriver(param.getIdNo(),param.getPhone(),param.getVerCode()).subscribe(new Subscriber<DriviLicenseParam>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showLicenseError();
            }

            @Override
            public void onNext(DriviLicenseParam driviLicenseParam) {

                mView.stopProgess();
                mView.showLicenseSuccess(driviLicenseParam);
            }
        });
    }

  

    /**
     * 跳转忘记密码
     */
    public void gotoForgetPage(){
        mView.gotoForgetPage();
    }
    /**
     * 验证登陆信息是否为空
     * @param userEt
     * @param passWordEt
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

}


