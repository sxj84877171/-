package com.soarsky.car.data.local.db.bean;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

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
 * 该类为 罚单表实体类
 */
@Entity
public class Ticket {


    /**
     * 罚单编号
     */
    @NotNull
    private String id;
    @NotNull
    /**
     * 是否同步服务器 1未同步 2同步
     */
    private int serviceStatus;
    /**
     * 是否同步终端 1未同步 2同步
     */
    private int terminalStatus;

    /**
     *  车牌号
     */
    @NotNull
    private  String  carnum;


    private String ptype;
    private String stime;
    private String etime;
    private String dType;
    private String idNo;
    private String lon;
    private String lat;
    private String ino;


    @Generated(hash = 1148582270)
    public Ticket(@NotNull String id, int serviceStatus, int terminalStatus,
            @NotNull String carnum, String ptype, String stime, String etime,
            String dType, String idNo, String lon, String lat, String ino) {
        this.id = id;
        this.serviceStatus = serviceStatus;
        this.terminalStatus = terminalStatus;
        this.carnum = carnum;
        this.ptype = ptype;
        this.stime = stime;
        this.etime = etime;
        this.dType = dType;
        this.idNo = idNo;
        this.lon = lon;
        this.lat = lat;
        this.ino = ino;
    }
    @Generated(hash = 941848399)
    public Ticket() {
    }


    public int getTerminalStatus() {
        return this.terminalStatus;
    }
    public void setTerminalStatus(int terminalStatus) {
        this.terminalStatus = terminalStatus;
    }
    public int getServiceStatus() {
        return this.serviceStatus;
    }
    public void setServiceStatus(int serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
   

    public static Ticket parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,Ticket.class);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public String getIno() {
        return this.ino;
    }
    public void setIno(String ino) {
        this.ino = ino;
    }
    public String getLat() {
        return this.lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return this.lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getIdNo() {
        return this.idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getDType() {
        return this.dType;
    }
    public void setDType(String dType) {
        this.dType = dType;
    }
    public String getEtime() {
        return this.etime;
    }
    public void setEtime(String etime) {
        this.etime = etime;
    }
    public String getStime() {
        return this.stime;
    }
    public void setStime(String stime) {
        this.stime = stime;
    }
    public String getPtype() {
        return this.ptype;
    }
    public void setPtype(String ptype) {
        this.ptype = ptype;
    }
    public String getCarnum() {
        return this.carnum;
    }
    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

}