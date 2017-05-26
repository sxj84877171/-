package com.soarsky.car;

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
 * 程序功能：所有的信息
 * 该类为
 */

public class CommonParam {

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 查询密码
     */
    private String queryPwd;
    /***
     * 车主号码
     */
    private String owerPhone;
    /****
     * 链接上的车牌号，方便设置亲情号所取
     */
    private String carNum;

    /**
     *  判断用户是否与亲情号码绑定
     */
    private String isBind;
    /**
     * 授权吗
     */
    private  String  authCode;
    /**
     * 驾驶证号
     */
    private String drivingLicence;
    /**
     * 准驾车型
     */
    private String drivingType;


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getQueryPwd() {
        return queryPwd;
    }

    public void setQueryPwd(String queryPwd) {
        this.queryPwd = queryPwd;
    }

    public String getOwerPhone() {
        return owerPhone;
    }

    public void setOwerPhone(String owerPhone) {
        this.owerPhone = owerPhone;
    }

    public String getIsBind() {
        return isBind;
    }

    public void setIsBind(String isBind) {
        this.isBind = isBind;
    }

    public String getAuthCode() {
        return authCode;
    }



    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public String getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(String drivingType) {
        this.drivingType = drivingType;
    }
}
