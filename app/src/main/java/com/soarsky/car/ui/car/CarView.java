package com.soarsky.car.ui.car;

import com.soarsky.car.base.BaseView;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：机动车回调接口
 * 该类为
 */

public interface CarView extends BaseView{
    /**
     * 获取机动车成功
     * @param param
     */
    public void showSuccess(CarParam param);

    /**
     * 获取机动车失败
     */
    public void showError();

}
