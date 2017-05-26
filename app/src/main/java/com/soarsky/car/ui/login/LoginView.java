package com.soarsky.car.ui.login;

import android.widget.EditText;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.LoginInfo;
import com.soarsky.car.bean.ResponseDataBean;

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
 * 程序功能：登录回调<br>
 * 该类为 LoginView<br>
 */

public interface LoginView extends BaseView{

    /**
     * 登陆成功回调
     * @param param 参数
     */
    public void showSuccess(LoginInfo param);

    /**
     * 登陆失败的回调
     * @param o 参数
     */
    public void showFail(ResponseDataBean<LoginInfo> o);

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
     * 跳转到忘记密码的回调
     */
    public void gotoForgetPage();

    /**
     * 验证登陆信息是否为空
     * @param userEt 用户名
     * @param passWordEt 密码
     */
    public void verifyLoginInfo(EditText userEt,EditText passWordEt);

    /**
     * 密码的可见性
     */
    public void isVisiablePassword();

}
