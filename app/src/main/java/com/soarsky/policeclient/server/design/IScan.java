package com.soarsky.policeclient.server.design;

import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.server.bluetooth.Blue;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/16
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为wifi扫描附近车辆的功能接口<br>
 */

public interface IScan {
    /**
     * 开启扫描，扫描附近车辆
     */
    void startScan();

    /**
     * 停止扫描
     */
    void stopScan();

    void setCarScanedListener(OnCarScanedListener carScanedListener);

    /**
     * 获取扫描到的车辆列表
     */
    List<Car> getScanedCarList();

    void addCar(Car car);

    void setOnBlueScan(Blue.OnBlueScan onBlueScan);

}
