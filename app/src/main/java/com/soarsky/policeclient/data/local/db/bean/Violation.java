package com.soarsky.policeclient.data.local.db.bean;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 违章记录表<br>
 */
@Entity
public class  Violation {
    @Id
    private Long id;

    private String sId;
    /**
     * 车牌号
     */
    @NotNull
    private String carnum;//车牌号
    /**
     * 扣分
     */
    @NotNull
    private int cent;//扣分
    /**
     * 罚款
     */
    @NotNull
    private int money;//罚款
    /**
     * 违章地点
     */
    @NotNull
    private String address;//违章地点
    /**
     * 违章代号
     */
    @NotNull
    private String ino;//违章代号
    /**
     * 违章信息
     */
    @NotNull
    private String inf;//违章信息
    /**
     * 驾驶人
     */
    @NotNull
    private String drivers;//驾驶人
    /**
     * 违章等级
     */
    @NotNull
    private String grade;
    /**
     * 违章时间
     */
    @NotNull
    private Date stime;//违章时间
    /**
     * 操作人
     */
    @NotNull
    private String opuser;//操作人
    /**
     * 状态 0 未上传  1 已上传
     */
    @NotNull
    private int  status; //状态 0 未上传  1 已上传
    /**
     * 时间和车牌号组成
     */
    @NotNull
    private String flagId;//时间和车牌号组成
    /**
     * 本地图片路径
     */
    private String  localimgurlurl;
    /**
     * 网络图片路径
     */
    private String fileurl;//文件路径








    @Generated(hash = 1892974858)
    public Violation(Long id, String sId, @NotNull String carnum, int cent,
            int money, @NotNull String address, @NotNull String ino,
            @NotNull String inf, @NotNull String drivers, @NotNull String grade,
            @NotNull Date stime, @NotNull String opuser, int status,
            @NotNull String flagId, String localimgurlurl, String fileurl) {
        this.id = id;
        this.sId = sId;
        this.carnum = carnum;
        this.cent = cent;
        this.money = money;
        this.address = address;
        this.ino = ino;
        this.inf = inf;
        this.drivers = drivers;
        this.grade = grade;
        this.stime = stime;
        this.opuser = opuser;
        this.status = status;
        this.flagId = flagId;
        this.localimgurlurl = localimgurlurl;
        this.fileurl = fileurl;
    }

    @Generated(hash = 1789693990)
    public Violation() {
    }








    public static Violation parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,Violation.class);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getFileurl() {
        return this.fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getLocalimgurlurl() {
        return this.localimgurlurl;
    }

    public void setLocalimgurlurl(String localimgurlurl) {
        this.localimgurlurl = localimgurlurl;
    }

    public String getFlagId() {
        return this.flagId;
    }

    public void setFlagId(String flagId) {
        this.flagId = flagId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOpuser() {
        return this.opuser;
    }

    public void setOpuser(String opuser) {
        this.opuser = opuser;
    }

    public Date getStime() {
        return this.stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDrivers() {
        return this.drivers;
    }

    public void setDrivers(String drivers) {
        this.drivers = drivers;
    }

    public String getInf() {
        return this.inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public String getIno() {
        return this.ino;
    }

    public void setIno(String ino) {
        this.ino = ino;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCent() {
        return this.cent;
    }

    public void setCent(int cent) {
        this.cent = cent;
    }

    public String getCarnum() {
        return this.carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSId() {
        return this.sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

}
