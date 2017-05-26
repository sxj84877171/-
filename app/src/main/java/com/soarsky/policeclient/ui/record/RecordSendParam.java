package com.soarsky.policeclient.ui.record;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  记录请求参数类<br>
 */

public class RecordSendParam {

//    String carnum,String page,String place,String stime,String etime
    /**
     * 车牌号
     */
    private String carnum;
    /**
     * 页数
     */
    private String page;
    /**
     * 地址
     */
    private String place;
    /**
     * 开始时间
     */
    private String stime;
    /**
     * 结束时间
     */
    private String etime;


    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }
}
