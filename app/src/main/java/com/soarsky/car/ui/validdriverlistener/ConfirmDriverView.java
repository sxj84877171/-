package com.soarsky.car.ui.validdriverlistener;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.Car;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者：何明辉<br>
 * 时间： 2016/11/15.<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为确认驾驶员视图层<br>
 */

public interface ConfirmDriverView extends BaseView {
    /**
     * 获取扫描结果
     * @param list 车的集合
     */
    public void showList(List<Car> list);

    /**
     * 确认驾驶员成功
     */
    public void onConfirmDriversSucess();

    /**
     * 确认驾驶员失败
     */
    public void onConfirmDriversFailed();

}
