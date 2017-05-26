package com.soarsky.installationmanual.bean;

/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2017/2/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为<br>
 */
public class RegisterSendBean {

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 电话
     */
    private String phone;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 验证码
     */
    private String vcode;
    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String conPassword;


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConPassword() {
        return conPassword;
    }

    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }
}
