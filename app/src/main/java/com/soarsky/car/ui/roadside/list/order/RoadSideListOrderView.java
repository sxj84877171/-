package com.soarsky.car.ui.roadside.list.order;

import com.soarsky.car.base.BaseView;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public interface RoadSideListOrderView extends BaseView{

    /**
     * 获取故障救援详细信息成功
     * @param roadSideListOrderParam
     */
    public void getRescueByIdSuccess(RoadSideListOrderParam roadSideListOrderParam);

    /**
     * 获取故障救援详细信息失败
     */
    public void getRescueByIdFail();

    /**
     * 取消故障救援记录成功
     */
    public void delRescueByIdSuccess(RoadSideListOrderDelRescueParam roadSideListOrderDelRescueParam);

    /**
     * 取消故障救援记录失败
     */
    public void delRescueByIdFail();

    /**
     * 取消订单的对话框
     */
    public void showOrderDelete();
}
