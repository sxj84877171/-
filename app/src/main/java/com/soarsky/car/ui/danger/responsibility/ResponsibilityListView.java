package com.soarsky.car.ui.danger.responsibility;

import com.soarsky.car.base.BaseView;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public interface ResponsibilityListView extends BaseView {

    /**
     * 获取快赔列表成功
     * @param responsibilityListParam
     */
    public void getFastListSuccess(ResponsibilityListParam responsibilityListParam);
    /**
     * 获取快赔列表失败
     */
    public void getFastListFail();
}
