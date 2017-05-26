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
 * 该类为快赔返回信息数据实体类<br>
 */

public class CompenstateParam {
    /**
     * 位置
     */
    private String position;
    /**
     *路径
     */
    private String url;
    /**
     * 文件
     */
    private String sourceFile;
    /**
     * 本方车牌号码
     */
    private String firstCar;
    /**
     * 对方车牌号码
     */
    private String secondCar;
    /**
     * 发生时间
     */
    private String occurredTime;
    /**
     * 本方责任认定结果
     */
    private String finalAffirm;
    /**
     * 本方驾驶证号
     */
    private String firstLicense;
    /**
     * 本方电话号码
     */
    private String firstPhone;
    /**
     * 事故编号
     */
    private String accidentNumber;
    /**
     * 照片分类
     */
    private String ptype;
    /**
     * 记录生成时间
     */
    private String createTime;
    /**
     * 本方信息读状
     */
    private String fstatus;
    /***
     * 对方读状态
     */
    private String sstatus;
    /***
     * 本方有异议申诉理由
     */
    private String firstReason;
    /**
     * 对方有异议申请理由
     */
    private String secondReason;
    /**
     *本方责任认定结果
     */
    private String firstAffirm;
    /**
     * 对方电话号码
     */
    private String secondPhone;
    /**
     * 本方电话号码
     */
    private String secondLicense;
    /**
     * 对方责任认定结果
     */
    private String secondAffirm;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 事故处理时间
     */
    private String handleTime;
    /**
     * 状态
     */
    private String status;
    /***
     * id
     */
    private int id;
    /**
     * 处理警员编号
     */
    private String handler;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void setFinalAffirm(String finalAffirm) {
        this.finalAffirm = finalAffirm;
    }

    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getFirstCar() {
        return firstCar;
    }

    public void setFirstCar(String firstCar) {
        this.firstCar = firstCar;
    }

    public String getSecondCar() {
        return secondCar;
    }

    public void setSecondCar(String secondCar) {
        this.secondCar = secondCar;
    }

    public String getOccurredTime() {
        return occurredTime;
    }

    public void setOccurredTime(String occurredTime) {
        this.occurredTime = occurredTime;
    }


    public String getFirstLicense() {
        return firstLicense;
    }

    public void setFirstLicense(String firstLicense) {
        this.firstLicense = firstLicense;
    }


    public String getAccidentNumber() {
        return accidentNumber;
    }

    public void setAccidentNumber(String accidentNumber) {
        this.accidentNumber = accidentNumber;
    }



    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFstatus() {
        return fstatus;
    }

    public void setFstatus(String fstatus) {
        this.fstatus = fstatus;
    }

    public String getSstatus() {
        return sstatus;
    }

    public void setSstatus(String sstatus) {
        this.sstatus = sstatus;
    }

    public String getFirstReason() {
        return firstReason;
    }

    public void setFirstReason(String firstReason) {
        this.firstReason = firstReason;
    }

    public String getSecondReason() {
        return secondReason;
    }

    public void setSecondReason(String secondReason) {
        this.secondReason = secondReason;
    }

    public String getFirstAffirm() {
        return firstAffirm;
    }

    public void setFirstAffirm(String firstAffirm) {
        this.firstAffirm = firstAffirm;
    }


    public String getSecondLicense() {
        return secondLicense;
    }

    public void setSecondLicense(String secondLicense) {
        this.secondLicense = secondLicense;
    }

    public String getSecondAffirm() {
        return secondAffirm;
    }

    public void setSecondAffirm(String secondAffirm) {
        this.secondAffirm = secondAffirm;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getFinalAffirm() {
        return finalAffirm;
    }

    public String getFirstPhone() {
        return firstPhone;
    }

    public String getPtype() {
        return ptype;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public String getHandleTime() {
        return handleTime;
    }
}
