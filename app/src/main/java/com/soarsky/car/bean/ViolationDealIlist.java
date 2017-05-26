package com.soarsky.car.bean;

import java.io.Serializable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/22<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为违章信息处理<br>
 */

public class ViolationDealIlist implements Serializable{
    /**
     * 违章编号
     */
    private String ino;
    /**、
     * 违章信息
     */
    private String inf;
    /**
     * 等级
     */
    private String grade;
    /**
     * 经度
     */
    private String lon;
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * 车牌号
     */
    private String carnum;
    /**
     * 时间
     */
    private String stime;
    /**
     * 操作人
     */
    private String opuser;
    /**
     * 维度
     */
    private String lat;
    /**
     * 结束时间
     */
    private String etime;
    /**
     * 文书编号
     */
    private String docNumber;
    /**
     * 车类型
     */
    private String plateType;
    /**
     * 罚款
     */
    private String monery;
    /**
     * 分
     */
    private String cent;
    /**
     * 驾驶人
     */
    private String drivers;
    /**
     * 状态
     */
    private String status;
    /**
     * 地址
     */
    private String address;
    /**
     * 是否申请撤销
     * 0
     * 1
     */
    private String sign;
    /**
     * id
     */
    private int id;



    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }



    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }


    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }



    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getMonery() {
        return monery;
    }

    public void setMonery(String monery) {
        this.monery = monery;
    }

    public String getCent() {
        return cent;
    }

    public void setCent(String cent) {
        this.cent = cent;
    }

    public String getDrivers() {
        return drivers;
    }

    public void setDrivers(String drivers) {
        this.drivers = drivers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
