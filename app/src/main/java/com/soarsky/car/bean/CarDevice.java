package com.soarsky.car.bean;


import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/4/5
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class CarDevice {
    /**
     * 车牌号码整个字符标志
     */
    private String deviceName;
    /**
     * 对应的车牌号码
     */
    private String carNum = "";
    /**
     * 车辆状态
     */
    private String carStatus = "";
    /**
     * 车辆类型
     */
    private String carType = "";

    public CarDevice(BluetoothIBridgeDevice bluetoothIBridgeDevice) {
        this.deviceName = bluetoothIBridgeDevice.getDeviceName();
        if (null != deviceName && deviceName.length() >= 11) {
            carNum = deviceName.substring(3, 10);
        }
        if (null != deviceName && deviceName.length() >= 11) {
            carStatus = deviceName.substring(0, 1);
        }
        if (null != deviceName && deviceName.length() == 11) {
            carType = deviceName.substring(1, 3);
        }
    }

    /**
     * 获取车牌号码
     * @return 车牌号码
     */
    public String getCarNum() {
        return carNum;
    }

    /**
     * 获取车辆状态
     * @return 车辆状态
     */
    public String getCarStatus() {
        return carStatus;
    }

    /**
     * 获取车辆类型
     * @return 车辆类型
     */
    public String getCarType() {
        return carType;
    }
}
