package com.soarsky.car.data.local.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/4/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：参数数据
 * 该类为
 */

@Entity
public class ParamSet {

    /**
     * 文本ID
     */
    @Id
    private Long id;

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
    /**
     * 与智能终端同步的状态 0--未同步 1--同步
     */
    @NotNull
    private int tstate;
    public int getTstate() {
        return this.tstate;
    }
    public void setTstate(int tstate) {
        this.tstate = tstate;
    }
    public String getParameterValue() {
        return this.parameterValue;
    }
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
    public String getParameterDescription() {
        return this.parameterDescription;
    }
    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
    }
    public String getUpdatePerson() {
        return this.updatePerson;
    }
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }
    public String getEffectRange() {
        return this.effectRange;
    }
    public void setEffectRange(String effectRange) {
        this.effectRange = effectRange;
    }
    public String getFailureTime() {
        return this.failureTime;
    }
    public void setFailureTime(String failureTime) {
        this.failureTime = failureTime;
    }
    public String getEffectTime() {
        return this.effectTime;
    }
    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }
    public String getParameterCode() {
        return this.parameterCode;
    }
    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }
    public String getSortName() {
        return this.sortName;
    }
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
    public String getSortCode() {
        return this.sortCode;
    }
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }
    public String getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 972164167)
    public ParamSet(Long id, String updateTime, String sortCode, String sortName,
            String parameterCode, String effectTime, String failureTime,
            String effectRange, String updatePerson, String parameterDescription,
            String parameterValue, int tstate) {
        this.id = id;
        this.updateTime = updateTime;
        this.sortCode = sortCode;
        this.sortName = sortName;
        this.parameterCode = parameterCode;
        this.effectTime = effectTime;
        this.failureTime = failureTime;
        this.effectRange = effectRange;
        this.updatePerson = updatePerson;
        this.parameterDescription = parameterDescription;
        this.parameterValue = parameterValue;
        this.tstate = tstate;
    }
    @Generated(hash = 1498094496)
    public ParamSet() {
    }



}
