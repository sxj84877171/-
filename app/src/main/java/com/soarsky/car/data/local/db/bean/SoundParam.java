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
 * 程序功能：语音数据
 * 该类为
 */

@Entity
public class SoundParam {
    /**
     * 文本ID
     */
    @Id
    private Long id;

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
    public String getModifyUser() {
        return this.modifyUser;
    }
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
    public String getTextId() {
        return this.textId;
    }
    public void setTextId(String textId) {
        this.textId = textId;
    }
    public String getEffectTime() {
        return this.effectTime;
    }
    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }
    public String getModifyTime() {
        return this.modifyTime;
    }
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 844411503)
    public SoundParam(Long id, String content, String remark, String modifyTime,
            String effectTime, String textId, String modifyUser, int tstate) {
        this.id = id;
        this.content = content;
        this.remark = remark;
        this.modifyTime = modifyTime;
        this.effectTime = effectTime;
        this.textId = textId;
        this.modifyUser = modifyUser;
        this.tstate = tstate;
    }
    @Generated(hash = 1863071604)
    public SoundParam() {
    }

}
