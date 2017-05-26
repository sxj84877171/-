package com.soarsky.car.ui.borrowandreturn.borrowrecord;

public class BorrowRecords {
    /**
     * 主键
     */
    private int id;
    /**
     * 车主电话
     */
    private String owner;

    /**
     * 0 申请
     * 1 申请通过
     * 2 已还车
     * 3
     * 4 拒绝
     */
    private String status;
    /**
     * 拒绝原因
     */
    private String remark;
    /**
     * 类型
     */
    private String model;
    /**
     * 车牌号
     */
    private String carnum;
    /**
     * 车主信息
     */
    private String ownerUser;
    /**
     * 借车人姓名
     */
    private String appuser;
    /**
     * 结束时间
     */
    private String etime;
    /**
     * 借车人号码
     */
    private String borrow;
    /**
     * 创建时间
     */
    private String ctime;
    /**
     * 开始时间
     */
    private String stime;
    /**
     * 审批时间
     */
    private String atime;
    /**
     * 归还时间
     */
    private String rtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(String ownerUser) {
        this.ownerUser = ownerUser;
    }

    public String getAppuser() {
        return appuser;
    }

    public void setAppuser(String appuser) {
        this.appuser = appuser;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getBorrow() {
        return borrow;
    }

    public void setBorrow(String borrow) {
        this.borrow = borrow;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
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

}