package com.soarsky.car.bean;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  获取驾驶证信息类<br>
 */

public class DrivingLicenseInfo {
    /**
     * 地址
     */
    private String address;
    /**
     * 姓名
     */
    private String name;
    /**
     * id
     */
    private int id;
    /**
     * 状态
     */
    private String status;
    /**
     * 有效起始日期
     */
    private String validStartDate;
    /**
     * 有效期限
     */
    private String validityPeriod;
    /**
     * 性别
     */
    private String sex;
    /**
     * 电话号码id
     */
    private String phoneId;
    /**
     * 身份证
     */
    private String idcard;
    /**
     * 记录
     */
    private String jilu;
    /**
     * 文件
     */
    private Object sourceFile;
    /**
     * 驾驶证号
     */
    private String num;
    /**
     * 国藉
     */
    private String guoji;
    /**
     * 文件名
     */
    private String fileNum;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 初次领证日期
     */
    private String initData;
    /**
     * 准驾车型
     */
    private String drivingType;
    /**
     * 图片路径
     */
    private String imgurl;
    /**
     * 清分日期
     */
    private String clearDate;

    /**
     * 驾驶证扣分
     */
    private String score  = "0";

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    /**
     * 驾驶证行驶的距离
     */
    private String distance = "0KM";

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(String validStartDate) {
        this.validStartDate = validStartDate;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getJilu() {
        return jilu;
    }

    public void setJilu(String jilu) {
        this.jilu = jilu;
    }

    public Object getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(Object sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getGuoji() {
        return guoji;
    }

    public void setGuoji(String guoji) {
        this.guoji = guoji;
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInitData() {
        return initData;
    }

    public void setInitData(String initData) {
        this.initData = initData;
    }

    public String getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(String drivingType) {
        this.drivingType = drivingType;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }
}
