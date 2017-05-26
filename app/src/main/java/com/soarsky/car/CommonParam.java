package com.soarsky.car;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：所有的信息<br>
 * 该类为<br>
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
    /**
     * 行驶证注册日期
     */
    private String registerCarDate;
    /**
     * 驾驶证注册日期
     */
    private String registerDriverDate;
    /**
     * 借车的车牌号
     */
    private String borrowCarNum ="京A88888";
    /**
     * 借车的授权码
     */
    private String borrowauthCode ="1123581321345589";
    /**
     * 车的识别码
     */
    private String carVin ="";


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

//    /**
//     * 测试用的
//     * @return
//     */
//    public String getOwerPhone() {
//        return "13688883333";
//    }

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

    public String getRegisterCarDate() {
        return registerCarDate;
    }

    public void setRegisterCarDate(String registerCarDate) {
        this.registerCarDate = registerCarDate;
    }

    public String getRegisterDriverDate() {
        return registerDriverDate;
    }

    public void setRegisterDriverDate(String registerDriverDate) {
        this.registerDriverDate = registerDriverDate;
    }


    public String getBorrowCarNum() {
        return borrowCarNum;
    }

    public void setBorrowCarNum(String borrowCarNum) {
        this.borrowCarNum = borrowCarNum;
    }

    public String getBorrowauthCode() {
        return borrowauthCode;
    }

    public void setBorrowauthCode(String borrowauthCode) {
        this.borrowauthCode = borrowauthCode;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }
}
