package com.soarsky.car.ui.danger.responsibilitydetails.haveobjection;

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
 * 程序功能：上传后台的数据
 * 该类为
 */


public class FastDissentSendParam {
    /**
     * id
     */
    private String id;
    /**
     * 车牌号
     */
    private String carnum;
    /**
     * 原因
     */
    private String reason;
    /**
     * 己方责任
     */
    private String faffirm;
    /**
     * 对方责任
     */
    private String saffirm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFaffirm() {
        return faffirm;
    }

    public void setFaffirm(String faffirm) {
        this.faffirm = faffirm;
    }

    public String getSaffirm() {
        return saffirm;
    }

    public void setSaffirm(String saffirm) {
        this.saffirm = saffirm;
    }
}
