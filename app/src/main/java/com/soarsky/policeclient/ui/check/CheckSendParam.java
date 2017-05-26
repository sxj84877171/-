package com.soarsky.policeclient.ui.check;

import java.util.HashMap;
import java.util.Map;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 上传稽查信息参数类 已废弃 未使用<br>
 */

public class CheckSendParam {
    private String carnum;
    private String time;
    private String lon;
    private String lat;
    private String opuser;

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getOpuser() {
        return opuser;
    }

    public void setOpuser(String opuser) {
        this.opuser = opuser;
    }


    private Map<String,String > createCommitParams(){
        Map<String,String > param=new HashMap<>();
        param.put("carnum",carnum);
        param.put("time",time);
        param.put("lon",lon);
        param.put("lat",lat);
        param.put("opuser",opuser);
        return  param;
    }



}
