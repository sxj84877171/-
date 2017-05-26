package com.soarsky.car.ui.roadside;

import com.soarsky.car.base.BaseView;

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
 * 程序功能：道路救援回调接口<br>
 * 该类为 RoadSideView<br>
 */


public interface RoadSideView extends BaseView{

    /**
     * 跳转救援申请
     */
    public void gotoRoadSideApply();

    /**
     * 跳转申请列表
     */
    public void gotoRoadSideApplyList();

}
