package com.sxj.carloan.bean;

public class VersionInfo implements java.io.Serializable {
    private static final long serialVersionUID = -7898838680968519957L;
    private int app_versionCode;
    private int id;
    private String app_versionName;

    public int getApp_versionCode() {
        return this.app_versionCode;
    }

    public void setApp_versionCode(int app_versionCode) {
        this.app_versionCode = app_versionCode;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApp_versionName() {
        return this.app_versionName;
    }

    public void setApp_versionName(String app_versionName) {
        this.app_versionName = app_versionName;
    }
}
