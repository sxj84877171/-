package com.soarsky.car.bean;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.App;
import com.soarsky.car.uitl.ValidatorUtils;
import com.soarsky.car.uitl.WifiUtil;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：用来保存车辆的基本信息
 * 该类为 接收后台返回数据基类
 */

public class Car {

    public static final String CAR_STATUS_STOP = "1" ;
    public static final String CAR_STATUS_READY = "2" ;
    public static final String CAR_STATUS_OK = "3" ;
    public static final String CAR_STATUS_RUNNING = "4" ;
    /**
     * 是否需要密码
     */
    public static final int PASS_TYPE = WifiUtil.NOPASS;
    /**
     * wifi名称
     */
    private String ssid;
    /**
     * wifi密码
     */
    private String password;
    /**
     * 驾照类型
     */
    private String carType;
    /**
     * 命令类型
     */
    private String carStatus;

    /**
     * 车牌号
     */
    private String carNum;

    /**
     * 驾驶证号
     */
    private String driverNum = "430681199106012619";
    /**
     * 授权码
     */
    private String authNum = "112351321";
    /**
     * 亲情号
     */
    private String phoneNum = "13688883333";
    /**
     * 罚单编号
     */
    private String ticketNo;

    /**
     * 开车玩手机类型
     */
    private String dangerousType;


    /**
     * 蓝牙设备
     */


    private BluetoothIBridgeDevice device;

    /**
     * 默认构造方法，为前期代码业务流通
     * add by 孙向锦
     */
    public Car() {

    }

    /**
     * 根据wifi热点名称初始化车辆信息
     * add by 孙向锦
     *
     * @param ssid wifi热点名称
     */
    public Car(String ssid) {
        if (ValidatorUtils.validatorSsid(ssid)) {
            setSsid(ssid);
            setCarNum(ssid.substring(3, 10));
            setCarType(ssid.substring(1, 3));
            setCarStatus(ssid.substring(0, 1));
            setAuthNum(getCarAuthCode(ssid.substring(3, 10)));
        }
    }

    /**
     * 通过蓝牙设备，初始化车辆信息
     * 0-1 表示车辆状态
     * 1-3 车辆类型
     * 3-10 表示车牌号码
     * @param bluetoothIBridgeDevice
     */
    public Car(BluetoothIBridgeDevice bluetoothIBridgeDevice) {
        setDevice(bluetoothIBridgeDevice);
        String deviceName = bluetoothIBridgeDevice.getDeviceName();
        if (ValidatorUtils.validatorSsid(deviceName)) {
            setCarStatus(deviceName.substring(0, 1));
            setCarType(deviceName.substring(1, 3));
            setCarNum(deviceName.substring(3, 10));
            setAuthNum(getCarAuthCode(deviceName.substring(3, 10)));
        }
    }


    /**
     * 获取借车的授权码
     * @param carNum
     * @return
     */
    private String getCarAuthCode(String carNum){
        List<LicenseInfo> car_list= App.getApp().getCar_list();
        if(null==car_list){
            return "";
        }

        for(int i=0;i<car_list.size();i++){
            if(carNum.equals(car_list.get(i).getPlateno())){
                return car_list.get(i).getAuthcode();
            }
        }

        return  "";
    }



    /***
     * 车辆是否在点火状态或者已经通过防盗验证
     * 主要业务作用是，给自动扫描车辆连接使用
     * @return 是否可行
     */
    public boolean isReady(){
        return CAR_STATUS_READY.equals(getCarStatus()) || CAR_STATUS_OK.equals(getCarStatus());
    }

    /**
     * 车辆是否是行驶状态
     * @return
     */
    public boolean isRunning(){
        return CAR_STATUS_RUNNING.equals(getCarStatus());
    }

    /**
     * 车辆是否是停止状态
     * @return
     */
    public boolean isStop(){
        return CAR_STATUS_STOP.equals(getCarStatus());
    }

    /**
     * 车辆进行准备或者行驶状态
     * @return
     */
    public boolean isReadyGo(){
        return isReady() || isRunning() ;
    }


    public BluetoothIBridgeDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothIBridgeDevice device) {
        this.device = device;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getDriverNum() {
        return driverNum;
    }

    public void setDriverNum(String driverNum) {
        this.driverNum = driverNum;
    }

    public String getAuthNum() {
        return authNum;
    }

    public void setAuthNum(String authNum) {
        this.authNum = authNum;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getDangerousType() {
        return dangerousType;
    }

    public void setDangerousType(String dangerousType) {
        this.dangerousType = dangerousType;
    }
}
