package com.soarsky.car.data.local.db.bean;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

import java.sql.Blob;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：亲情号码表
 * 该类为 亲情号码
 */
@Entity
public class FamilyNum {

   @Id
    private Long id;
    /**
     * 车牌号
     */
    @NotNull
    private String carnum;
    /**
     * 用户名
     */
    @NotNull
    private String username;
    /**
     *亲情号码

     */

    private String phone;

  

    private String value;
    /***
     * 是否是车主号码
     */

    private int is_owner;



    /**
     * 与后台服务绑定的状态 0--未绑定 1--绑定
     */
    @NotNull

    private int sstate;
    /**
     * 与智能终端同步的状态 0--未同步 1--同步
     */
    @NotNull
    private int tstate;
    public int getTstate() {
        return this.tstate;
    }
    public void setTstate(int tstate) {
        this.tstate = tstate;
    }
    public int getSstate() {
        return this.sstate;
    }
    public void setSstate(int sstate) {
        this.sstate = sstate;
    }
    public int getIs_owner() {
        return this.is_owner;
    }
    public void setIs_owner(int is_owner) {
        this.is_owner = is_owner;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getCarnum() {
        return this.carnum;
    }
    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1336253910)
    public FamilyNum(Long id, @NotNull String carnum, @NotNull String username,
            String phone, String value, int is_owner, int sstate, int tstate) {
        this.id = id;
        this.carnum = carnum;
        this.username = username;
        this.phone = phone;
        this.value = value;
        this.is_owner = is_owner;
        this.sstate = sstate;
        this.tstate = tstate;
    }
    @Generated(hash = 1576750483)
    public FamilyNum() {
    }

    public static FamilyNum parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,FamilyNum.class);
    }
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    








}
