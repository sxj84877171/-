package com.soarsky.car.ui.register;

import com.soarsky.car.base.BaseView;
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
 * 程序功能：注册view<br>
 * 该类为 RegisterView<br>
 */

public interface RegisterView extends BaseView{

    /**
     * 注册成功
     */
    public void showSuccess();

    /**
     * 注册失败
     * @param o 失败参数
     */
    public void showFail(ResponseDataBean o);

    /**
     * 注册报错
     */
    public void showonError();

    /**
     * 验证注册信息
     */
    public void verifyRegister();

    /**
     * 密码的可见性
     * @param type 类别
     */
    public void isVisiablePassword(int type);

    /**
     * 勾选协议
     */
    public void selectAgree();

    /**
     * 跳转到注册协议界面
     */
    public void gotoAgreePage();

    /**
     * 获取验证码成功
     * @param bean 验证信息参数
     */
    public void sendSmsSuccess(ResponseDataBean<Void> bean);

    /**
     * 获取验证码失败
     */
    public void sendSmsFail();


}
