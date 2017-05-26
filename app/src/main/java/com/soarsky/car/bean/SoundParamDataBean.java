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
 * 该类为语音接口返回的参数类
 */

public class SoundParamDataBean {

    /**
     * 文本ID
     */
    private int id;

    /**
     * 文本内容
     */
    private String content;

    /**
     *备注
     */
    private String remark;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 生效时间
     */
    private String effectTime;

    /**
     * 文本ID
     */
    private String textId;

    /**
     * 修改用户
     */
    private String modifyUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
}
