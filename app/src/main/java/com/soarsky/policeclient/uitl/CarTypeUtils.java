package com.soarsky.policeclient.uitl;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/22<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 车辆类型的一些数据结构转换<br>
 */
public class CarTypeUtils {
    //TODO 车辆类型需要转换  01有轨电车02无轨电车03轮式自行机械车04轻便摩托车05普通二轮摩托车06普通三轮摩托车07残疾人专用小型自动挡载客汽车08三轮汽车09低速载货汽车10小型自动挡汽车11小型汽车12大型货车13中型客车14城市公交车15牵引车16大型客车

    /**
     * 车辆类型需要转换
     * @param data
     * @return
     */
    public static String carType(String data){
        HashMap<String,String> map = new HashMap<>();
        map.put("00","未知车辆类型");
        map.put("01","有轨电车");
        map.put("02","无轨电车");
        map.put("03","轮式自行机械车");
        map.put("04","轻便摩托车");
        map.put("05","普通二轮摩托车");
        map.put("06","普通三轮摩托车");
        map.put("07","残疾人专用小型自动挡载客汽车");
        map.put("08","三轮汽车");
        map.put("09","低速载货汽车");
        map.put("10","小型自动挡汽车");
        map.put("11","小型汽车");
        map.put("12","大型货车");
        map.put("13","中型客车");
        map.put("14","城市公交车");
        map.put("15","牵引车");
        map.put("16","大型客车");
        return map.get(data);
    }

    /**
     * 解析车辆类型
     * @param ssid
     * @return
     */
    public static String parseCarType(String ssid){
        return ssid.substring(1,3);
    }

    /**
     * 获取车辆类型列表
     * @return
     */
    public static ArrayList<String> getCarTypeList(){
        ArrayList<String> carTypes = new ArrayList<>();
        carTypes.add("有轨电车");
        carTypes.add("无轨电车");
        carTypes.add("轮式自行机械车");
        carTypes.add("轻便摩托车");
        carTypes.add("普通二轮摩托车");
        carTypes.add("普通三轮摩托车");
        carTypes.add("残疾人专用小型自动挡载客汽车");
        carTypes.add("三轮汽车");
        carTypes.add("低速载货汽车");
        carTypes.add("小型自动挡汽车");
        carTypes.add("小型汽车");
        carTypes.add("大型货车");
        carTypes.add("中型客车");
        carTypes.add("城市公交车");
        carTypes.add("牵引车");
        carTypes.add("大型客车");
        return carTypes;
    }
}
