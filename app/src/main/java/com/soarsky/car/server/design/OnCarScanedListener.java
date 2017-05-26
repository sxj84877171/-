package com.soarsky.car.server.design;


import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.bean.Car;

import java.util.List;


/**
 * Andriod_Car_App<br>
 * 作者： Elvis<br>
 * 时间： 2016/11/3.<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：扫描监听回调接口<br>
 * 该类为 与终端通讯服务类<br>
 */

public interface OnCarScanedListener {
    /**
     * wifi新扫描的车辆
     * @param list 车辆集合
     */
    public void newCarScannedList(List<Car> list);


    /**
     * 蓝牙扫描的设备
     */
    public  void  newBlueToothScan(BluetoothIBridgeDevice dervice);
}
