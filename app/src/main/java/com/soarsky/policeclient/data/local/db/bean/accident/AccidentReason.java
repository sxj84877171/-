package com.soarsky.policeclient.data.local.db.bean.accident;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故原因数据库表<br>
 */
@Entity
public class AccidentReason {
    @Id
    private Long id;
    /**
     * 原因值
     */
    private String reason;
    public String getReason() {
        return this.reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 179227258)
    public AccidentReason(Long id, String reason) {
        this.id = id;
        this.reason = reason;
    }
    @Generated(hash = 856666981)
    public AccidentReason() {
    }

}
