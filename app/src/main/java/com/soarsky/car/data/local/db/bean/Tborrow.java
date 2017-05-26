package com.soarsky.car.data.local.db.bean;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：借车表
 * 该类为 借车表对应的实体类
 */
@Entity
public class Tborrow {

    @Id
    private int id;
    /**
     * 借车者的姓名
     */
    @NotNull
    private String borrower;
    /**
     * 车主姓名
     */
    @NotNull
    private String owner;
    /***
     * 车牌号
     */
    @NotNull
    private String carnum;
    /**
     * 借车开始时间
     */
    @NotNull
    private String starttime;
    /**
     * 借车结束时间
     */
    @NotNull
    private String endtime;
    /**
     * 还车时间
     */
    @NotNull
    private String backtime;
    /***
     * 状态 0代表未还 1代表已还
     */
    @NotNull
    private int status;
    /**
     * 借车的授权码
     */
    @NotNull
    private String authcode;
    /**
     * 可扩展的字段
     */
    private String value;
    /***
     * 与后台同步的状态 0--未同步 1--同步
     */
    private int sstate;
    /***
     * 与终端同步的状态 0--未同步 1--同步
     */
    private int tstate;
    public int getTstate() {
        return this.tstate;
    }
    public void setTstate(int tstate) {
        this.tstate = tstate;
    }
    public int getSstate() {
        return this.sstate;
    }
    public void setSstate(int sstate) {
        this.sstate = sstate;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getAuthcode() {
        return this.authcode;
    }
    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getBacktime() {
        return this.backtime;
    }
    public void setBacktime(String backtime) {
        this.backtime = backtime;
    }
    public String getEndtime() {
        return this.endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getStarttime() {
        return this.starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getCarnum() {
        return this.carnum;
    }
    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
    public String getOwner() {
        return this.owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getBorrower() {
        return this.borrower;
    }
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Generated(hash = 1112725314)
    public Tborrow(int id, @NotNull String borrower, @NotNull String owner,
            @NotNull String carnum, @NotNull String starttime,
            @NotNull String endtime, @NotNull String backtime, int status,
            @NotNull String authcode, String value, int sstate, int tstate) {
        this.id = id;
        this.borrower = borrower;
        this.owner = owner;
        this.carnum = carnum;
        this.starttime = starttime;
        this.endtime = endtime;
        this.backtime = backtime;
        this.status = status;
        this.authcode = authcode;
        this.value = value;
        this.sstate = sstate;
        this.tstate = tstate;
    }
    @Generated(hash = 49978065)
    public Tborrow() {
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
