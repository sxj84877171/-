package com.soarsky.car.ui.blindterm.validation;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.LicensePwdBean;
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
 * 程序功能：验证回调 <br>
 * 该类为 BlindTermValidationView <br>
 */


public interface BlindTermValidationView extends BaseView{

    /**
     * 密码的可见性
     * @param type 可见的类别
     */
    public void isVisiablePassword(int type);

    /**
     * 验证输入信息是否为空
     */
    public void verifyInputInfo();
    /**
     * 设置查询密码成功
     * @param param 参数
     */
    public void setQueryPwdSuccess(ResponseDataBean<LicensePwdBean> param);
    /**
     * 设置查询密码失败
     */
    public void setQueryPwdFail();

}
