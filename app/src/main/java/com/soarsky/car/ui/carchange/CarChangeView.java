package com.soarsky.car.ui.carchange;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.ui.login.CarNumParam;

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
 * 程序功能：切换车辆的回调接口
 * 该类为
 */


public interface CarChangeView extends BaseView{

    /**
     * 获取车牌号成功回调
     * @param param
     */
    public void getAllCarnumSuccess(CarNumParam param);

    /***
     * 获取车牌号报错的回调
     */
    public void getAllCarnumFail();

}
