package com.soarsky.car.ui.drivrecord;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.LicensePwdBean;
import com.soarsky.car.bean.ResponseDataBean;

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
 * 程序功能：其回调接口
 * 该类为 DrivRecordView
 */

public interface DrivRecordView extends BaseView{
    /**
     * 设置查询密码成功
     * @param licensePwdParam 查询密码返回参数
     */
    public void onSuccess(ResponseDataBean<LicensePwdBean> licensePwdParam);

    /**
     * 设置查询密码失败
     */
    public void onError();

}
