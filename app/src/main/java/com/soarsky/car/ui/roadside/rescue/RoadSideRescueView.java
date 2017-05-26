package com.soarsky.car.ui.roadside.rescue;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideCarTypeParam;
import com.soarsky.car.bean.RoadSideRescueInfo;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：申请救援接口<br>
 * 该类为 RoadSideRescueView<br>
 */


public interface RoadSideRescueView extends BaseView{
    /**
     * 跳转确认订单界面
     */
    public void gotoRoadSideOrder(ResponseDataBean<RoadSideRescueInfo> roadSideRescueParam);

    /**
     * 车辆类型对话框
     */
    public void showCarTypeDialog(List<String> _list);

    /**
     * 服务对话框
     */
    public void showSeverDialog(List<String> _list);

    /**
     * 获取车辆类型成功
     */
    public void getCarTypeSuccess(ResponseDataBean<List<RoadSideCarTypeParam>> roadSideCarTypeParam);

    /**
     * 获取车辆类型失败
     */
    public void getCarTypeFail();

    /**
     * 上传救援申请成功
     */
    public void uploadResouseSuccess(ResponseDataBean<RoadSideRescueInfo> roadSideRescueParam);
    /**
     * 上传救援申请失败
     */
    public void uploadResouseFail();

    /**
     * 验证数据是否为空
     */
    public boolean verifyData();


}
