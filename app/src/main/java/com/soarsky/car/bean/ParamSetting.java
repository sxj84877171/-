package com.soarsky.car.bean;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2017/4/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为获取所有参数设置
 */

public class ParamSetting {

    /**
     * id : 99
     * updateTime : 0
     * sortCode : CCDJ
     * sortName : 乘车登记
     * parameterCode : AP06
     * effectTime : 0
     * failureTime : 0
     * effectRange : 伴君行
     * updatePerson : 0
     * parameterDescription : “已乘车”后扫描不到电子车牌自动报警时间
     * parameterValue : 600/秒
     */

    /**
     * 文本ID
     */
    private int id;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 参数代码
     */
    private String sortCode;

    /**
     * 参数分类名称
     */
    private String sortName;

    /**
     * 参数代码
     */
    private String parameterCode;

    /**
     * 生效日期
     */
    private String effectTime;

    /**
     * 失效时间
     */
    private String failureTime;

    /**
     * 作用范围
     */
    private String effectRange;

    /**
     * 修改人
     */
    private String updatePerson;

    /**
     *参数说明
     */
    private String parameterDescription;

    /**
     *'参数值
     */
    private String parameterValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public String getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(String failureTime) {
        this.failureTime = failureTime;
    }

    public String getEffectRange() {
        return effectRange;
    }

    public void setEffectRange(String effectRange) {
        this.effectRange = effectRange;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public String getParameterDescription() {
        return parameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
}
