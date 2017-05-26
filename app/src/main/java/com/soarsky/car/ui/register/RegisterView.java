package com.soarsky.car.ui.register;

import com.soarsky.car.base.BaseView;

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

public interface RegisterView extends BaseView{

    /**
     * 注册成功
     */
    public void showSuccess();

    /**
     * 注册失败
     * @param o
     */
    public void showFail(Object o);

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




}
