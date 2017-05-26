package com.soarsky.policeclient.bean;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2017/2/24<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为忘记密码参数类<br>
 */

public class ForgetPwdDataBean {
    /**
     * id
     */
    private int id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 状态
     */
    private String status;
    /**
     * 标识
     */
    private Object token;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 电话号码
     */
    private String phoneId;
    /**
     * 身份证号
     */
    private String idcard;
    /**
     * 截止日期
     */
    private Object lastTime;
    private Object mac;
    private int organizationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Object getLastTime() {
        return lastTime;
    }

    public void setLastTime(Object lastTime) {
        this.lastTime = lastTime;
    }

    public Object getMac() {
        return mac;
    }

    public void setMac(Object mac) {
        this.mac = mac;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
}
