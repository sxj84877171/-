package com.soarsky.policeclient.bean;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2017/2/24<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为罚单详情业务类<br>
 */

public class DetailsViolationCount {
    /**
     * 车牌号
     */
    private String carnum;
    /**
     * 开单人员
     */
    private String opuser;
    /**
     * 罚款
     */
    private String monery;
    /**
     *违章代号
     */
    private String ino;
    /**
     * 违章原因
     */
    private String inf;
    /**
     * 等级00：违法 01：违规
     */
    private String grade;
    /**
     * 时间
     */
    private String stime;
    /**
     * 扣分
     */
    private String cent;
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * 驾驶人
     */
    private String drivers;
    /**
     * 罚单处理状态
     */
    private String status;
    /**
     * 违章地点
     */
    private String address;
    /**
     * id
     */
    private int id;

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getOpuser() {
        return opuser;
    }

    public void setOpuser(String opuser) {
        this.opuser = opuser;
    }

    public String getMonery() {
        return monery;
    }

    public void setMonery(String monery) {
        this.monery = monery;
    }

    public String getIno() {
        return ino;
    }

    public void setIno(String ino) {
        this.ino = ino;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getCent() {
        return cent;
    }

    public void setCent(String cent) {
        this.cent = cent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
