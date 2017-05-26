package com.soarsky.car.ui.illegal.query.set;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.LicensePwdBean;
import com.soarsky.car.bean.ResponseDataBean;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：密码设置响应回调<br>
 * 该类为 IllegalQuerySetView<br>
 */


public interface IllegalQuerySetView extends BaseView{

    /**
     * 设置查询密码成功
     * @param licensePwdParam 驾照信息
     */
    public void showIllegalSetSuccess(ResponseDataBean<LicensePwdBean> licensePwdParam);

    /**
     * 设置查询密码失败
     */
    public void showIllegalSetFail();
    /**
     * 密码的可见性
     * @param type 类别
     */
    public void isVisiablePassword(int type);
}
