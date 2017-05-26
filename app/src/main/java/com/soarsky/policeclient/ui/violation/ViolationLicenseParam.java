package com.soarsky.policeclient.ui.violation;

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
 * 该类为 代表提交给智能终端的电子罚单信息<br>
 */
import java.util.Date;

public class ViolationLicenseParam {
    /**
     * 罚单时间
     */
    private Date time;

    /**
     * 罚单位置
     */
    private String position;
    /**
     * 车牌号
     */
    private String ssid;
    /**
     * 违章类型
     */
    private String type; //违章类型
    /**
     * 罚款
     */
    private String money; //罚款
    /**
     * 罚了多少分
     */
    private String point; //罚了多少分

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
