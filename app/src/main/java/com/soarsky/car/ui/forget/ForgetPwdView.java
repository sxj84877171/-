package com.soarsky.car.ui.forget;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ForgetPwdInfo;
import com.soarsky.car.bean.ResponseDataBean;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：忘记密码View <br>
 * 该类为 ForgetPwdView <br>
 */

public interface ForgetPwdView extends BaseView{

    /**
     * 忘记密码成功的回调
     * @param param 返回参数
     */
    public void showData(ResponseDataBean<String> param);

    /**
     * 忘记密码失败的回调
     */
    public void showonError();

    /**
     * 是否可见
     * @param type 类型
     */
    public void isVisiable(int type);

    /**
     *验证忘记密码的信息
     */
    public void verifyForfetPwdInfo();

    /**
     * 获取验证码成功
     * @param bean 验证码信息
     */
    public void sendSmsSuccess(ResponseDataBean<Void> bean);

    /**
     * 获取验证码失败
     */
    public void sendSmsFail();


}
