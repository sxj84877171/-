package com.soarsky.car.ui.roadside.rescue;

import java.io.Serializable;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/24
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：传递救援信息到确定订单
 * 该类为
 */


public class RescueParam implements Serializable{

    /**
     * 申请救援开始时间
     */
    private String startTime;
    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 车辆型号
     */
    private String carType;
    /**
     * 救援位置
     */
    private String carPosition;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 服务费用
     */
    private String severFee;
    /**
     * 到达时间
     */
    private String reachTime;
    /**
     * 商家电话
     */
    private String severPhone;
    /**
     * 距离
     */
    private String distance;

    private String company;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarPosition() {
        return carPosition;
    }

    public void setCarPosition(String carPosition) {
        this.carPosition = carPosition;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSeverFee() {
        return severFee;
    }

    public void setSeverFee(String severFee) {
        this.severFee = severFee;
    }

    public String getReachTime() {
        return reachTime;
    }

    public void setReachTime(String reachTime) {
        this.reachTime = reachTime;
    }

    public String getSeverPhone() {
        return severPhone;
    }

    public void setSeverPhone(String severPhone) {
        this.severPhone = severPhone;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
