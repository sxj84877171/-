package com.soarsky.car.bean;

import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailParm;

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
 * 该类为修改借车车辆状态的业务类<br>
 */

public class ModifyStatusInfo {
    /**
     * remark :
     * model : null
     * appuser : null
     * borrow : 13544174435
     * stime : 2016-12-07 10:00:00
     * etime : 2016-12-08 10:00:00
     * rtime : 2016-12-07 17:08:38
     * ctime : 2016-12-06 15:06:08
     * atime : 2016-12-08 10:14:04
     * carnum : 湘A00002
     * owner : 13566554433
     * status : 1
     * id : 11
     */

    /**
     * 拒绝原因
     */
    private String remark;
    /**
     * 车辆类型
     */
    private String model;
    /**
     * 用户信息
     */
    private DetailParm.DataBean.AppuserBean appuser;
    /**
     * 借车人账号
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
     * 归还时间
     */
    private String rtime;
    /**
     * 创建时间
     */
    private String ctime;
    /**
     * 审批时间
     */
    private String atime;
    /**
     * 车牌号码
     */
    private String carnum;
    /**
     * 车主电话
     */
    private String owner;
    /**
     * 状态
     */
    private String status;
    /**
     * 主键
     */
    private int id;

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

    public DetailParm.DataBean.AppuserBean getAppuser() {
        return appuser;
    }

    public void setAppuser(DetailParm.DataBean.AppuserBean appuser) {
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

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
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
