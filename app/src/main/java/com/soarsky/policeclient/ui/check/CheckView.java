package com.soarsky.policeclient.ui.check;

import com.soarsky.policeclient.base.BaseView;
import com.soarsky.policeclient.bean.Car;

import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 稽查对应的View<br>
 */

public interface CheckView extends BaseView {

    /**
     * 添加车辆列表到界面
     * @param car 要添加的车辆列表
     */
    public void addCar(List<Car> car);
    /**
     * 删除车辆列表到界面
     * @param car 要删除的车辆列表
     */
    public void removeCar(List<Car> car);

    /**
     * 显示车辆列表
     * @param list 要显示的车辆列表
     */
    public void showList(List<Car> list);

}
