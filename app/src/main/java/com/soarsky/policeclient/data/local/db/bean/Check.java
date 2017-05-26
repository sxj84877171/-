package com.soarsky.policeclient.data.local.db.bean;

import com.google.gson.Gson;
import com.soarsky.policeclient.uitl.TimeUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
 * 该类为 稽查记录表<br>
 */

@Entity
public class Check {
    @Id
    private Long id;
    /**
     * 用户名
     */
    private String opuser;
    /**
     * 经度
     */
    private String lon;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 车牌号
     */
    @NotNull
    private String carnum;
    /**
     * 稽查时间
     */
    @NotNull
    private Date inspectTime;
    /**
     * 稽查状态 0未上传 1已上传
     */
    @NotNull
    private String status;//0未上传 1已上传
    /**
     * 是否是黑名单列表 1 是 0 否
     */
    private String isBlackList;//1 是 0 否


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  

    public String getCarnum() {
        return this.carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
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

    public String getOpuser() {
        return this.opuser;
    }

    public void setOpuser(String opuser) {
        this.opuser = opuser;
    }


    public String getIsBlackList() {
        return this.isBlackList;
    }

    public void setIsBlackList(String isBlackList) {
        this.isBlackList = isBlackList;
    }

    @Generated(hash = 1644112245)
    public Check(Long id, String opuser, String lon, String lat,
            @NotNull String carnum, @NotNull Date inspectTime,
            @NotNull String status, String isBlackList) {
        this.id = id;
        this.opuser = opuser;
        this.lon = lon;
        this.lat = lat;
        this.carnum = carnum;
        this.inspectTime = inspectTime;
        this.status = status;
        this.isBlackList = isBlackList;
    }

    @Generated(hash = 1080183208)
    public Check() {
    }

    /**
     * 创建提交map
     * @return
     */
    public Map<String, String> createCommitMap() {
        Map<String, String> param = new HashMap<>();
        param.put("carnum", carnum);
        param.put("time", TimeUtils.date2String(inspectTime));
        param.put("lon", "112.888");
        param.put("lat", "28.124");
        param.put("opuser", opuser);
        param.put("signs",isBlackList);
        return param;

    }

    public static Check parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,Check.class);
    }
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    public Date getInspectTime() {
        return this.inspectTime;
    }

    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
