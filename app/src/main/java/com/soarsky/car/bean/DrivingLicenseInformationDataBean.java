package com.soarsky.car.bean;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/2/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为行驶证信息参数类<br>
 */

public class DrivingLicenseInformationDataBean {


    /**
     * 居住地址
     */
    private String address;
    /**
     * 所属人姓名
     */
    private String name;
    /**
     * 序号
     */
    private int id;
    /**
     * 行驶证状态0：正常，1：注销
     */
    private String status;
    /**
     * 是否检查
     */
    private boolean checked;
    /**
     * 车辆图片
     */
    private String imageUrl;
    /**
     * 车主身份证
     */
    private String idcard;
    /**
     * 准驾车型
     */
    private String platetype;
    /**
     * 集合
     */
    private Object ilist;
    /**
     * 拍照
     */
    private String plateno;
    /**
     * 车辆类型
     */
    private String vehicletype;
    /**
     * 使用性质
     */
    private String usercharacter;
    /**
     * 车辆识别码
     */
    private String vin;
    /**
     * 发动机号
     */
    private String enginno;
    /**
     * 注册日期
     */
    private String registerdate;
    /**
     * 发证日期
     */
    private String issuedate;
    /**
     * 记录
     */
    private String jilu;
    /**
     * 车身颜色
     */
    private String color;
    /**
     * 座位数
     */
    private String seatNum;
    /**
     * 品牌类型
     */
    private String model;
    /**
     * 电话
     */
    private String phone;
    /**
     * 文件
     */
    private Object sourceFile;
    /**
     * 清分日期
     */
    private String clearDate;

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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPlatetype() {
        return platetype;
    }

    public void setPlatetype(String platetype) {
        this.platetype = platetype;
    }

    public Object getIlist() {
        return ilist;
    }

    public void setIlist(Object ilist) {
        this.ilist = ilist;
    }

    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getUsercharacter() {
        return usercharacter;
    }

    public void setUsercharacter(String usercharacter) {
        this.usercharacter = usercharacter;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEnginno() {
        return enginno;
    }

    public void setEnginno(String enginno) {
        this.enginno = enginno;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getJilu() {
        return jilu;
    }

    public void setJilu(String jilu) {
        this.jilu = jilu;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(Object sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }
}
