package com.soarsky.car.ui.roadside.list.detail;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListOrderInfo;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：列表订单详情接口<br>
 * 该类为 RoadSideListDetailView<br>
 */


public interface RoadSideListDetailView extends BaseView{

    /**
     * 获取故障救援详细信息成功
     * @param roadSideListOrderParam 故障救援详细信息
     */
    public void getRescueByIdSuccess(ResponseDataBean<RoadSideListOrderInfo> roadSideListOrderParam);

    /**
     * 获取故障救援详细信息失败
     */
    public void getRescueByIdFail();

}
