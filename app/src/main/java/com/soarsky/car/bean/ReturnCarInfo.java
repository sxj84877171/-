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
 * 该类为我要还车的参数类<br>
 */

public class ReturnCarInfo {

    /**
     * 借车人姓名
     */
    private Object appuser;
    /**
     * 借车人电话
     */
    private String borrow;
    /**
     * 开始时间
     */
    private String stime;
    /**
     * 结束时间
     */
    private String etime;
    /**
     * 创建时间
     */
    private String ctime;
    /**
     * 车牌号
     */
    private String carnum;
    /**
     * 车主姓名
     */
    private Object ownerUser;
    /**
     * 车辆类型
     */
    private String model;
    /**
     * 审核时间
     */
    private String atime;
    /**
     * 归还时间
     */
    private String rtime;
    /**
     * 拒绝借车理由
     */
    private String remark;
    /**
     * 车主
     */
    private String owner;
    /**
     * 状态
     */
    private String status;
    /**
     * id
     */
    private int id;

    public Object getAppuser() {
        return appuser;
    }

    public void setAppuser(Object appuser) {
        this.appuser = appuser;
    }

    public String getBorrow() {
        return borrow;
    }

    public void setBorrow(String borrow) {
        this.borrow = borrow;
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

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public Object getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(Object ownerUser) {
        this.ownerUser = ownerUser;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
