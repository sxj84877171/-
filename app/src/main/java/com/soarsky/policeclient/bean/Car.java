package com.soarsky.policeclient.bean;

import android.bluetooth.BluetoothDevice;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/12/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 车辆基本信息<br>
 */
public class Car implements Serializable {
    /**
     * 蓝牙名称
     */
    private String blueName;

    public String getBlueName() {
        return blueName;
    }

    public void setBlueName(String blueName) {
        this.blueName = blueName;
    }

    /**
     * 是否有数据
     */
    private boolean hasData;

    public boolean isHasData() {
        return hasData;
    }
    /**
     * 是否已经读过
     */
    private boolean hasRead;

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    /**
     * 车辆热点密码
     */
    private String password;
    /**
     * 是否是黑车
     */
    private boolean isBlack = false ;

    /**
     * 车辆颜色
     */
    private String color ;

    /**
     * 车辆类型
     */
    private String type ;

    /**
     * 车架号
     */
    private String carNum ;

    /**
     * 年检日期
     */
    private String date ;


    private BluetoothIBridgeDevice bluetoothIBridgeDevice;

    public BluetoothIBridgeDevice getBluetoothIBridgeDevice() {
        return bluetoothIBridgeDevice;
    }

    public void setBluetoothIBridgeDevice(BluetoothIBridgeDevice bluetoothIBridgeDevice) {
        this.bluetoothIBridgeDevice = bluetoothIBridgeDevice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setBlack(boolean black) {
        isBlack = black;
    }

    public boolean isBlack() {
        return isBlack;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Car){
            Car car = (Car)obj;
            if(car.getBlueName() != null){
                return car.getBlueName().equals(blueName);
            }else{
                if(blueName != null){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (blueName == null) {
            return 0;
        }
        return blueName.hashCode();
    }



}
