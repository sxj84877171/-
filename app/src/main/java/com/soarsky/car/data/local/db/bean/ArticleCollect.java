package com.soarsky.car.data.local.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/11<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为   文章收藏<br>
 */
@Entity
public class ArticleCollect {

    @Id
    private Long articleCollectId;
    /**
     * 文章id
     */
    @NotNull
    private int id;
    /**
     * 标题
     */
    @NotNull
    private String title;
    /**
     * 日期
     */
    @NotNull
    private String date;
    /**
     * 简介
     */
    @NotNull
    private String remark;


    @NotNull
    private boolean isCollect;
    /**
     * 内容
     */
    @NotNull
    private String content;


    /**
     * 文章类型
     */
    @NotNull
    private String type;

    /**
     * 关键字
     */
    @NotNull
    private String keyWord;


    /**
     * 是否阅读
     */
    @NotNull
    private boolean isRead;

    /**
     * 使用时间
     */
    @NotNull
    private String uTime;


    /**
     * 用户名
     */
    @NotNull
    private String appUser;

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyWord() {
        return this.keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public boolean isRead() {
        return this.isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getUtime() {
        return this.uTime;
    }

    public void setUtime(String uTime) {
        this.uTime = uTime;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }
    public Long getArticleCollectId() {
        return this.articleCollectId;
    }
    public void setArticleCollectId(Long articleCollectId) {
        this.articleCollectId = articleCollectId;
    }
    public boolean getIsCollect() {
        return this.isCollect;
    }
    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public String getUTime() {
        return this.uTime;
    }

    public void setUTime(String uTime) {
        this.uTime = uTime;
    }

    public boolean getIsRead() {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
    @Generated(hash = 918927846)
    public ArticleCollect(Long articleCollectId, int id, @NotNull String title,
            @NotNull String date, @NotNull String remark, boolean isCollect,
            @NotNull String content, @NotNull String type, @NotNull String keyWord,
            boolean isRead, @NotNull String uTime, @NotNull String appUser) {
        this.articleCollectId = articleCollectId;
        this.id = id;
        this.title = title;
        this.date = date;
        this.remark = remark;
        this.isCollect = isCollect;
        this.content = content;
        this.type = type;
        this.keyWord = keyWord;
        this.isRead = isRead;
        this.uTime = uTime;
        this.appUser = appUser;
    }

    @Generated(hash = 1524039132)
    public ArticleCollect() {
    }
}
