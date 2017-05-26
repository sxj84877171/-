package com.soarsky.car.bean;


import java.io.Serializable;

/**
 * Andriod_Car_App <br>
 * 作者： 何明辉
 * 时间： 2017/2/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 行车记录实体类
 */

public class TravelRecords implements Serializable{
    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 车类型
     */
    private String ptype;
    /**
     * 车牌号
     */
    private String carnum;
    /**
     * 状态
     */
    private String status;
    /**
     * 时间
     */
    private String ctime;

    /**
     * 备注
     */
    private String remark;
    /**
     *  0 乘车 1离车
     */

    private  int isBlack;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype == null ? null : ptype.trim();
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum == null ? null : carnum.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public int getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(int isBlack) {
        this.isBlack = isBlack;
    }
}