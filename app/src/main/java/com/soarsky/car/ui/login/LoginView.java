package com.soarsky.car.ui.login;

import android.widget.EditText;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.ui.license.DriviLicenseParam;

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

public interface LoginView extends BaseView{

    /**
     * 登陆成功回调
     * @param param
     */
    public void showSuccess(LoginParam param);

    /**
     * 登陆失败的回调
     * @param o
     */
    public void showFail(Object o);

    /**
     * 登陆报错的回调
     */
    public void showonError();

    public void initUsernameAndPassword(String username, String password);

    /**
     * 跳转到注册界面
     */
    public void gotoRegisterrPage();

    /**
     * 获取车牌号成功回调
     * @param param
     */
    public void getAllCarnumSuccess(CarNumParam param);

    /***
     * 获取车牌号报错的回调
     */
    public void getAllCarnumFail();

    /**
     * 跳转到忘记密码的回调
     */
    public void gotoForgetPage();

    /**
     * 验证登陆信息是否为空
     * @param userEt
     * @param passWordEt
     */
    public void verifyLoginInfo(EditText userEt,EditText passWordEt);

    /**
     * 密码的可见性
     */
    public void isVisiablePassword();

    /**
     *获取驾驶证成功
     * @param param
     */
    public void showLicenseSuccess(DriviLicenseParam param);

    /**
     * 获取驾驶失败
     */
    public void showLicenseError();



}
