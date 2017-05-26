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
 * 该类为单条快赔详细信息<br>
 */

public class ResponsibilityDetailsParam {
    /**
     * 地址
     */
    private String position;
    /**
     * 路径
     */
    private String url;
    /**
     * 文件
     */
    private String sourceFile;
    /**
     * 本方车
     */
    private String firstCar;
    /**
     * 对方车
     */
    private String secondCar;
    /**
     * 事故发生时间
     */
    private String occurredTime;
    /**
     * 最终责任
     */
    private String finalAffirm;
    /**
     * 本方驾照
     */
    private String firstLicense;
    /**
     * 本方电话
     */
    private String firstPhone;
    /**
     * 事故编号
     */
    private String accidentNumber;
    /**
     * 分类
     */
    private String ptype;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 本方读取状态
     */
    private String fstatus;
    /**
     * 对方读取状态
     */
    private String sstatus;
    /**
     * 本方原因
     */
    private String firstReason;
    /**
     * 对方原因
     */
    private String secondReason;
    /**
     * 本方责任
     */
    private String firstAffirm;
    /**
     * 对方电话
     */
    private String secondPhone;
    /**
     * 对方驾照
     */
    private String secondLicense;
    /**
     * 对方责任
     */
    private String secondAffirm;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 处理时间
     */
    private String handleTime;
    /**
     * 状态
     */
    private String status;
    /**
     * id
     */
    private int id;
    /**
     * 处理者
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

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public void setSecondReason(String secondReason) {
        this.secondReason = secondReason;
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

    public String getFinalAffirm() {
        return finalAffirm;
    }

    public void setFinalAffirm(String finalAffirm) {
        this.finalAffirm = finalAffirm;
    }

    public String getFirstLicense() {
        return firstLicense;
    }

    public void setFirstLicense(String firstLicense) {
        this.firstLicense = firstLicense;
    }

    public String getFirstPhone() {
        return firstPhone;
    }

    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
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



    public String getFirstAffirm() {
        return firstAffirm;
    }

    public void setFirstAffirm(String firstAffirm) {
        this.firstAffirm = firstAffirm;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
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

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
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

    public String getPtype() {
        return ptype;
    }

    public String getSecondReason() {
        return secondReason;
    }
}
