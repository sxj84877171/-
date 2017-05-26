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
 * 程序功能：驾驶证详情信息
 * 该类为   DriviLicenseParam
 */


public class DriviLicenseParam {
    /**
     * 驾驶证号
     */
    private String driviLicenseNum;
    /**
     * 驾照证有效日期
     */
    private String driviLicenseDate;
    /**
     * 分
     */
    private String driviLicenseCent;
    /**
     * 累计公里数
     */
    private String drivDistance;
    /**
     * 准驾车型
     */
    private String driviType;
    /**
     * 清分日期
     */
    private String clearDate;
    /**
     * 当前状态
     */
    private String driviLicenseStatus;

    public String getDriviLicenseNum() {
        return driviLicenseNum;
    }

    public void setDriviLicenseNum(String driviLicenseNum) {
        this.driviLicenseNum = driviLicenseNum;
    }

    public String getDriviLicenseDate() {
        return driviLicenseDate;
    }

    public void setDriviLicenseDate(String driviLicenseDate) {
        this.driviLicenseDate = driviLicenseDate;
    }

    public String getDriviLicenseCent() {
        return driviLicenseCent;
    }

    public void setDriviLicenseCent(String driviLicenseCent) {
        this.driviLicenseCent = driviLicenseCent;
    }

    public String getDrivDistance() {
        return drivDistance;
    }

    public void setDrivDistance(String drivDistance) {
        this.drivDistance = drivDistance;
    }

    public String getDriviType() {
        return driviType;
    }

    public void setDriviType(String driviType) {
        this.driviType = driviType;
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }

    public String getDriviLicenseStatus() {
        return driviLicenseStatus;
    }

    public void setDriviLicenseStatus(String driviLicenseStatus) {
        this.driviLicenseStatus = driviLicenseStatus;
    }
}
