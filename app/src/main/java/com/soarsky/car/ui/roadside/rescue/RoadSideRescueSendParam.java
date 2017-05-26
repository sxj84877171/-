package com.soarsky.car.ui.roadside.rescue;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/24
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：救援信息
 * 该类为
 */


public class RoadSideRescueSendParam {
    /**
     * 车牌号
     */
    private String carnnum;
    /**
     * 车辆类型
     */
    private String ctype;
    /**
     * 位置
     */
    private String location;
    /**
     * 电话
     */
    private String phone;
    /**
     * 服务项目
     */
    private String serverItem;

    public String getCarnnum() {
        return carnnum;
    }

    public void setCarnnum(String carnnum) {
        this.carnnum = carnnum;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getServerItem() {
        return serverItem;
    }

    public void setServerItem(String serverItem) {
        this.serverItem = serverItem;
    }
}
