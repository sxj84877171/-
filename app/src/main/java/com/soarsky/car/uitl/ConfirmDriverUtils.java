package com.soarsky.car.uitl;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.App;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.LicenseInfo;

import java.util.Iterator;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/3/27
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class ConfirmDriverUtils {

    /**
     * 判断申请类型
     * @return 0 车主 1亲情号 2借车 3都不是
     */

    public  static int getConnectType(Car car){
        List<LicenseInfo> car_list= App.getApp().getCar_list();
        if(null==car_list){
            return 3;
        }
        for(int i=0;i<car_list.size();i++){
            if(car.getCarNum().equals(car_list.get(i).getPlateno())){
                return car_list.get(i).getSign();
            }
        }
        return 3;

    }



    /**
     * 获取借车的授权码
     * @param carNum
     * @return
     */
    public static String getAuthCode(String carNum){
        List<LicenseInfo> car_list=App.getApp().getCar_list();
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



    /**
     * 判断蓝牙设备是否存在
     *
     * @param device
     * @return
     */
    public static boolean deviceExisted(List<Car> mDevices,BluetoothIBridgeDevice device) {
        if (device == null)
            return false;

        Iterator<Car> it = mDevices.iterator();
        while (it.hasNext()) {
            BluetoothIBridgeDevice d = it.next().getDevice();
            if (d != null && d.equals(device))
                return true;
        }
        return false;
    }


}
