package com.soarsky.policeclient.data.local.db.bean.accident;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 事故分析数据库表<br>
 */
@Entity
public class Accident {

    /**
     * 事故分析数据库表id
     */
    @Id
    private Long id;
    /**
     * 事故分析数据库表数据
     */
    private String data;
    /**
     * 事故分析数据库表是否已经上传
     */
    private boolean hasUpload;
    
    private boolean isUpdate;



    @Generated(hash = 1238455411)
    public Accident(Long id, String data, boolean hasUpload, boolean isUpdate) {
        this.id = id;
        this.data = data;
        this.hasUpload = hasUpload;
        this.isUpdate = isUpdate;
    }

    @Generated(hash = 106332324)
    public Accident() {
    }


    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getHasUpload() {
        return this.hasUpload;
    }

    public void setHasUpload(boolean hasUpload) {
        this.hasUpload = hasUpload;
    }


    public boolean getIsUpdate() {
        return this.isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }




}
