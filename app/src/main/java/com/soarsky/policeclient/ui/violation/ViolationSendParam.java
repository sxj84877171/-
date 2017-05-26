package com.soarsky.policeclient.ui.violation;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 开电子罚单的上传数据<br>
 */

public class ViolationSendParam {
    /**
     * 车牌号
     */
    private String carnum;//车牌号
    /**
     * 扣分
     */
    private String cent;//扣分
    /**
     * 罚款
     */
    private String money;//罚款
    /**
     * 违章地点
     */
    private String address;//违章地点
    /**
     * 违章代号
     */
    private String ino;//违章代号
    /**
     * 违章信息
     */
    private String inf;//违章信息
    /**
     * 驾驶人
     */
    private String drivers;//驾驶人
    /**
     * 违章等级
     */
    private String grade;
    /**
     * 违章时间
     */
    private String stime;//违章时间
    /**
     * 操作人
     */
    private String opuser;//操作人
    /**
     * 图片地址
     */
    private String fileUrl;


    public Map<String, String> createCommitParams() {
        Map<String, String> params = new HashMap<>();
        params.put("carnum", carnum);
        params.put("cent", cent);
        params.put("money", money);
        params.put("address", address);
        params.put("ino", ino);
        params.put("inf", inf);
        params.put("drivers", drivers);
        params.put("grade", grade);
        params.put("stime", stime);
        params.put("opuser", opuser);
        params.put("fileUrl", fileUrl);
        params.put("ptype", "0");
        return params;
    }


    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getCent() {
        return cent;
    }

    public void setCent(String cent) {
        this.cent = cent;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDrivers() {
        return drivers;
    }

    public void setDrivers(String drivers) {
        this.drivers = drivers;
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

    public String getOpuser() {
        return opuser;
    }

    public void setOpuser(String opuser) {
        this.opuser = opuser;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
