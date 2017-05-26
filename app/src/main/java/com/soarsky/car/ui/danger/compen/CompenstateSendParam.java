package com.soarsky.car.ui.danger.compen;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/22
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：上传快赔的信息
 * 该类为 数据实体类
 */


public class CompenstateSendParam {

    /**
     * 事故时间
     */
    private String stime;
    /**
     * 位置
     */
    private String location;
    /**
     * 车牌号码
     */
    private String fcarnum;
    /**
     * 驾驶证
     */
    private String fidNo;
    /**
     * 本方责任划分
     */
    private String faffirm;
    /**
     * 对方车牌号
     */
    private String scarnum;
    /**
     * 对方驾驶证
     */
    private String sidNo;
    /**
     * 对方责任划分
     */
    private String saffirm;

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFcarnum() {
        return fcarnum;
    }

    public void setFcarnum(String fcarnum) {
        this.fcarnum = fcarnum;
    }

    public String getFidNo() {
        return fidNo;
    }

    public void setFidNo(String fidNo) {
        this.fidNo = fidNo;
    }

    public String getFaffirm() {
        return faffirm;
    }

    public void setFaffirm(String faffirm) {
        this.faffirm = faffirm;
    }

    public String getScarnum() {
        return scarnum;
    }

    public void setScarnum(String scarnum) {
        this.scarnum = scarnum;
    }

    public String getSidNo() {
        return sidNo;
    }

    public void setSidNo(String sidNo) {
        this.sidNo = sidNo;
    }

    public String getSaffirm() {
        return saffirm;
    }

    public void setSaffirm(String saffirm) {
        this.saffirm = saffirm;
    }
}
