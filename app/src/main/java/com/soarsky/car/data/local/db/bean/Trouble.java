package com.soarsky.car.data.local.db.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/29
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

@Entity
public class Trouble {
    @Id
    private Long id;
    /**
     * 车牌号
     */
    @NotNull
    private String carNum;
    /**
     * 故障码
     */
    @NotNull
    private String troubleMessage;

    /**
     * 是否上传终端 0未上传1已上传
     */
    @NotNull
    private String  status;
    /**
     * 记录创建时间
     */
    @NotNull
    private Date createTime;
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTroubleMessage() {
        return this.troubleMessage;
    }
    public void setTroubleMessage(String troubleMessage) {
        this.troubleMessage = troubleMessage;
    }
    public String getCarNum() {
        return this.carNum;
    }
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1150815682)
    public Trouble(Long id, @NotNull String carNum, @NotNull String troubleMessage,
            @NotNull String status, @NotNull Date createTime) {
        this.id = id;
        this.carNum = carNum;
        this.troubleMessage = troubleMessage;
        this.status = status;
        this.createTime = createTime;
    }
    @Generated(hash = 1123787452)
    public Trouble() {
    }

   

}
