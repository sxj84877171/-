package com.soarsky.car.ui.roadside.list.order;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListOrderInfo;

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
 * 程序功能：救援申请列表接口
 * 该类为 RoadSideListOrderView
 */


public interface RoadSideListOrderView extends BaseView{

    /**
     * 获取故障救援详细信息成功
     * @param roadSideListOrderParam
     */
    public void getRescueByIdSuccess(ResponseDataBean<RoadSideListOrderInfo> roadSideListOrderParam);

    /**
     * 获取故障救援详细信息失败
     */
    public void getRescueByIdFail();

    /**
     * 取消故障救援记录成功
     */
    public void delRescueByIdSuccess(ResponseDataBean<Object> roadSideListOrderDelRescueParam);

    /**
     * 取消故障救援记录失败
     */
    public void delRescueByIdFail();

    /**
     * 取消订单的对话框
     */
    public void showOrderDelete();

    /**
     * 网络连接失败的回调
     */
    public void showError(Throwable e);
}
