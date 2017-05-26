package com.soarsky.policeclient.uitl;

import android.bluetooth.BluetoothDevice;
import android.net.wifi.ScanResult;

import com.soarsky.policeclient.bean.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/10/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 *  车辆转换信息类<br>
 */
public class CarUtil {

    /**
     * 蓝牙变成车辆信息
     * @param bluetoothDevice
     * @return
     */
    public static Car scanToCar(BluetoothDevice bluetoothDevice) {
        Car car = null;
        if (ValidatorUtils.validatorSsid(bluetoothDevice.getName())) {
            car = new Car();
            car.setBlueName(bluetoothDevice.getName());
            car.setCarNum(CarUtil.fromSsidToCarNum(bluetoothDevice.getName()));
        }
        return car;
    }


    /**
     * 判断该热点是否是车辆热点
     * @param ssid
     * @return
     */
    private static boolean isCarNumberWifi(String ssid) {
        return ssid != null && ssid.length() == 7;
    }

    public static String fromSsidToCarNum(String ssid){
        /*if(ssid != null){
            int len = ssid.length();
            if(len==8 || len==16){
                return ssid.substring(len-8,len-1);
            }else if(len==15){
                return ssid.substring(len-7,len);
            } else{
                return ssid;
            }
        }else {
            return "";
        }*/
        if(ValidatorUtils.validatorSsid(ssid)){
            return ssid.substring(3,10);
        }else {
            return ssid;
        }


    }

    /**
     * 解析车辆安全带
     * @param type
     * @return
     */
    public static String parseCarAnquandai(String type){
        if(type.equals("0"))
            return "未系安全带";
        else {
            return "系上安全带";
        }
    }

    /**
     * 解析车辆灯光
     * @param type
     * @return
     */
    public static String parseCarDeng(String type){
        if(type.equals("0"))
            return "远光变近光";
        else {
            return "近光变远光";
        }
    }


    public static String parseCarAnquandaiDesc(String s){
        if("未系安全带".equals(s))
            return "0";
        else if("系上安全带".equals(s)){
            return "1";
        }else {
            return "2";
        }
    }

}
