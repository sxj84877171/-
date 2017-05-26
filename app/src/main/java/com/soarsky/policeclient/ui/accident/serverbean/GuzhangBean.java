package com.soarsky.policeclient.ui.accident.serverbean;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2017/2/24<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为发送故障数据到服务器<br>
 */

public class GuzhangBean {
    /**
     * 故障备注
     */
    private String remark;
    /**
     * 故障id
     */
    private int id;
    /**
     * 故障车牌号
     */
    private String acarnum;
    /**
     * 故障时间
     */
    private String atime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAcarnum() {
        return acarnum;
    }

    public void setAcarnum(String acarnum) {
        this.acarnum = acarnum;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }
}
