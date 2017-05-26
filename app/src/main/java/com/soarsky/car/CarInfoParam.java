package com.soarsky.car;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/4/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：Car的详情
 * 该类为 CarInfoParam
 */


public class CarInfoParam {
    /**
     * 车牌号
     */
    public String CarNum;
    /**
     * 车身颜色
     */
    public String CarColor;
    /**
     * 车类型
     */
    public String CarType;
    /**
     * 车识别码
     */
    public String CarIdenty;
    /**
     * 车的发动机编号
     */
    public String CarEngineNo;
    /**
     * 车辆所有人
     */
    public String OwerName;
    /**
     * 联系方式
     */
    public String TelePhone;
    /**
     * 行驶状态
     */
    public String CarDriviStatus;
    /**
     * 年检期止
     */
    public String CarDate;

    public String getCarNum() {
        return CarNum;
    }

    public void setCarNum(String carNum) {
        CarNum = carNum;
    }

    public String getCarColor() {
        return CarColor;
    }

    public void setCarColor(String carColor) {
        CarColor = carColor;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public String getCarIdenty() {
        return CarIdenty;
    }

    public void setCarIdenty(String carIdenty) {
        CarIdenty = carIdenty;
    }

    public String getCarEngineNo() {
        return CarEngineNo;
    }

    public void setCarEngineNo(String carEngineNo) {
        CarEngineNo = carEngineNo;
    }

    public String getOwerName() {
        return OwerName;
    }

    public void setOwerName(String owerName) {
        OwerName = owerName;
    }

    public String getTelePhone() {
        return TelePhone;
    }

    public void setTelePhone(String telePhone) {
        TelePhone = telePhone;
    }

    public String getCarDriviStatus() {
        return CarDriviStatus;
    }

    public void setCarDriviStatus(String carDriviStatus) {
        CarDriviStatus = carDriviStatus;
    }

    public String getCarDate() {
        return CarDate;
    }

    public void setCarDate(String carDate) {
        CarDate = carDate;
    }
}
