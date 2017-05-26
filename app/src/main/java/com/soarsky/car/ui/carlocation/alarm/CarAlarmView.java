package com.soarsky.car.ui.carlocation.alarm;

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
 * 程序功能：防盗报警回调接口
 * 该类为
 */

public interface CarAlarmView extends BaseView{
    /**
     * 获取报警记录成功
     * @param param
     */
    public void showSuccess(CarAlarmParam param);

    /**
     * 获取报警记录失败
     */
    public void showError();

}
