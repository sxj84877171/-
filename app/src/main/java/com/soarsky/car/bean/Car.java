package com.soarsky.car.bean;
import com.soarsky.car.uitl.WifiUtil;

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
 * 程序功能：
 * 该类为 车辆实体类
 */

public class Car {

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
     *  命令类型
     */
    private String carStatus;

    /**
     * 车牌号
     */
    private String carNum;

    /**
     * 驾驶证号
     */
    private String driverNum="430681199106012619";
    /**
     * 授权码
     */
    private String authNum="112351321";
    /**
     * 亲情号
     */
    private String  phoneNum="13688883333";
    /**
     * 罚单编号
     */
    private  String  ticketNo;

    /**
     *开车玩手机类型
     */
    private  String  dangerousType;


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
