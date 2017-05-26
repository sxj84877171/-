package com.soarsky.car.ui.illegal.query.list;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ViolationDealInfo;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：切换车辆违章信息view<br>
 * 该类为 IllegalQueryListView<br>
 */


public interface IllegalQueryListView extends BaseView{
    /**
     *获取违章数据成功
     * @param param 入参
     */
    public void showSuccess(ResponseDataBean<ViolationDealInfo> param);
    /**
     *获取违章数据失败
     */
    public void showError();

    /**
     * 违章劝离撤销成功
     * @param param
     */
    public void appViolationAdviceSuccess(ResponseDataBean<Void> param);

    /**
     * 违章劝离撤销失败
     */
    public void appViolationAdviceFail();

}
