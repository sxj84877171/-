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
 * 该类为获取稽查历史记录<br>
 */

public class RecordAuditDataBean {
    /**
     * id
     */
    private int id;
    /**
     * 操作人
     */
    private String opuser;
    /**
     * 车牌号
     */
    private String carnum;
    private String inspectTime;
    /**
     * 经度
     */
    private String lon;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 是否是黑名单
     */
    private boolean isBlack = false ;

    public void setBlack(boolean black) {
        isBlack = black;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpuser() {
        return opuser;
    }

    public void setOpuser(String opuser) {
        this.opuser = opuser;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime;
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
}
