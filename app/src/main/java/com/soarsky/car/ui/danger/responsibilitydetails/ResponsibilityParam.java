package com.soarsky.car.ui.danger.responsibilitydetails;

import java.io.Serializable;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/23
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：传递的对象给有异议的界面
 * 该类为
 */


public class ResponsibilityParam implements Serializable{
    /**
     * id
     */
    private String id;
    /**
     * 车牌号
     */
    private String carnum;
    /**
     * 驾驶证号
     */
    private String driverLicense;
    /**
     * 对方车牌号
     */
    private String othercarnum;
    /**
     * 对方驾驶证号
     */
    private String otherdriverLicense;

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getOthercarnum() {
        return othercarnum;
    }

    public void setOthercarnum(String othercarnum) {
        this.othercarnum = othercarnum;
    }

    public String getOtherdriverLicense() {
        return otherdriverLicense;
    }

    public void setOtherdriverLicense(String otherdriverLicense) {
        this.otherdriverLicense = otherdriverLicense;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
