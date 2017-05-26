package com.soarsky.policeclient.data.local.db.bean;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为黑名单车连数据库表<br>
 */
@Entity
public class BlackCar {

/*    //0代表未处理 1代表已处理
    private boolean isHandle;*/

    /**
     * 原因
     */
    private String  reason;
    /**
     * 车牌号
     */
    private String carnum;

    /**
     * 1 增加  0 删除
     */
    private String changetype = "1";


    /**
     * id
     */

    private  String id;
    /**
     * 号牌种类 0 黄牌1蓝牌 2黑牌3白牌
     */
    @Id
    private Long mId;
    private String platetype;
    public String getPlatetype() {
        return this.platetype;

    }
    public void setPlatetype(String platetype) {
        this.platetype = platetype;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCarnum() {
        return this.carnum;
    }
    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
    public String getReason() {
        return this.reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getChangetype() {
        return this.changetype;
    }
    public void setChangetype(String changetype) {
        this.changetype = changetype;
    }
    public Long getMId() {
        return this.mId;
    }
    public void setMId(Long mId) {
        this.mId = mId;
    }

    @Generated(hash = 1973796557)
    public BlackCar(String reason, String carnum, String changetype, String id,
            Long mId, String platetype) {
        this.reason = reason;
        this.carnum = carnum;
        this.changetype = changetype;
        this.id = id;
        this.mId = mId;
        this.platetype = platetype;
    }
    @Generated(hash = 2023387088)
    public BlackCar() {
    }
}
