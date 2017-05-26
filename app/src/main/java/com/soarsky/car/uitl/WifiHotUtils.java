package com.soarsky.car.uitl;

import com.soarsky.car.bean.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 何明辉 on 2016/11/23.<br>
 * 热点相关的帮助包<br>
 */

public class WifiHotUtils {
    
    
    //懒汉式单例类.在第一次调用的时候实例化自己   
        private WifiHotUtils() {}  
        private static WifiHotUtils single=null;  
        //静态工厂方法   
        public static WifiHotUtils getInstance() {  
             if (single == null) {    
                 single = new WifiHotUtils();  
             }    
            return single;  
        }  
    
    
    
    
    

    private String provincialStr = "粤湘京津沪渝冀豫云辽黑皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵青藏川宁琼台";

    /**
     * 讲ssid装换成车牌
     * 00A88888
     */
    public String getCarNum(String ssid) {
//        //将省会装换成char数组
//        char[] data = provincialStr.toCharArray();
//        //截取表示省会的数字编码
//        String provincialStr = ssid.substring(0, 2);
//        //根据编码获得省会
//        int dataIndex = Integer.parseInt(provincialStr);
//        return data[dataIndex] + ssid.substring(2);
        return ssid;

    }




    //去掉重复数据
    public  List<Car> toheavy (List<Car> cars) {

        List<Car> _list = new ArrayList<Car>();

        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (Car info : cars) {
            if (info == null) {
                continue;
            }
            String printerIp = info.getSsid();
            if (printerIp != null) {

                String value = hashMap.get(printerIp);
                if (value == null) {
                    //如果value是空的  说明取到的这个userName是第一次取到
                    hashMap.put(printerIp, printerIp);
                    _list.add(info);//st1就是我们想要的去重之后的结果
                } else {
                    continue;
                }
            }
        }
        return _list;
    }
}
