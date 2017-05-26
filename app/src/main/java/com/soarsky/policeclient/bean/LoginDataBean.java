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
 * 该类为登录接口的业务类<br>
 */

public class LoginDataBean {
    /**
     * id
     */
    private String id;
    /**
     * 验证码
     */
    private Object token;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private String status;
    /**
     * 邮件
     */
    private Object email;
    /**
     * 图片
     */
    private String image;
    /**
     * 身份证
     */
    private String idcard;
    /**
     * 验证状态
     */
    private String tokenStatus;
    /**
     * 状态0完善，1、未完善
     */
    private String isFinish;
    /**
     * 登录状态
     */
    private Object loginStatus;
    /**
     * 最后登录时间
     */
    private Object lastTime;
    /**
     * 设备地址
     */
    private Object mac;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 创建时间
     */
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(String tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public Object getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Object loginStatus) {
        this.loginStatus = loginStatus;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
