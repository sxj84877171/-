package com.soarsky.car.data.local.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：保存终端获取的异常行驶数据
 * 该类为
 */

@Entity
public class ErrorDriver {
    @Id
    private Long id;
    /**
     * 记录编号
     */
    @NotNull
    private String  driverNo;
    /**
     * 内容
     */
    @NotNull
    private String  content;
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDriverNo() {
        return this.driverNo;
    }
    public void setDriverNo(String driverNo) {
        this.driverNo = driverNo;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 621036875)
    public ErrorDriver(Long id, @NotNull String driverNo, @NotNull String content) {
        this.id = id;
        this.driverNo = driverNo;
        this.content = content;
    }
    @Generated(hash = 477869083)
    public ErrorDriver() {
    }
}
