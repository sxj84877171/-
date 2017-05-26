package com.soarsky.car.bean;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/2/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为上传申请后台返回的数据<br>
 */

public class RoadSideRescueInfo {
    /**
     * 电话
     */
    private String phone;
    /**
     * 开始时间
     */
    private String createTime;
    /**
     * 费用
     */
    private String cost;
    /**
     * 公司
     */
    private String company;
    /**
     * 距离
     */
    private String distance;
    /**
     * 需要达到的时间
     */
    private String reachTime;
    /**
     * 服务项目
     */
    private String serviceItems;
    /**
     * 公司电话
     */
    private String companyPhone;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 车类型
     */
    private String carType;
    /**
     * 状态
     */
    private String status;
    /**
     * 地址
     */
    private String address;
    /**
     * id
     */
    private String id;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getReachTime() {
        return reachTime;
    }

    public void setReachTime(String reachTime) {
        this.reachTime = reachTime;
    }

    public String getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(String serviceItems) {
        this.serviceItems = serviceItems;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
