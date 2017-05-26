package com.soarsky.car.server.design;


import com.soarsky.car.bean.Car;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：扫描接口化设计<br>
 * 该类为 与终端通讯服务类<br>
 */

public interface IScan {

    /**
     * 开启扫描，扫描附近车辆
     */
    void startScan();

    /**
     * 设置扫描监听的回调函数
     * @param carScanedListener
     */

    void setCarScanedListener(OnCarScanedListener carScanedListener);

    /**
     * 获得扫描结果的回调函数
     * @return 扫描到的车的列表
     */
    public List<Car> getScanedCarList();


}
