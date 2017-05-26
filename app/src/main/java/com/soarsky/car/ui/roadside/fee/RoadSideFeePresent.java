package com.soarsky.car.ui.roadside.fee;

import com.soarsky.car.base.BasePresenter;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class RoadSideFeePresent extends BasePresenter<RoadSideFeeModel,RoadSideFeeView>{

    @Override
    public void onStart() {

    }

    /**
     * 选择支付方式
     * @param way
     */
    public void selectFeeWay(int way){
        mView.selectFeeWay(way);
    }

    /**
     * 切换支付
     */
    public void switchFeeWay(int i){
        mView.switchFeeWay(i);
    }
}
