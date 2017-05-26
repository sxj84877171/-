package com.soarsky.car.ui.illegal.adapter;

import java.io.Serializable;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：传递违章信息实体对象<br>
 * 该类为 IllegalInfo<br>
 */


public class IllegalInfo implements Serializable{
    /**
     * 驾照号
     */
    private String driverNo;
    /**
     * 时间
     */
    private String time;
    /**
     * 地点
     */
    private String address;
    /**
     * 违章原因
     */
    private String illegalReason;
    /**
     * 分
     */
    private String cent;
    /**
     * 罚款
     */
    private String fee;
    /**
     * 文书编号
     */
    private String documentNo;
    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 号牌颜色 0--黄色 1--蓝色
     */
    private String plateType;

    public String getDriverNo() {
        return driverNo;
    }

    public void setDriverNo(String driverNo) {
        this.driverNo = driverNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIllegalReason() {
        return illegalReason;
    }

    public void setIllegalReason(String illegalReason) {
        this.illegalReason = illegalReason;
    }

    public String getCent() {
        return cent;
    }

    public void setCent(String cent) {
        this.cent = cent;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }


    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }
}
