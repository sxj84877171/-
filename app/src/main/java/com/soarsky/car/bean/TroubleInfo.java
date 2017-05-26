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
 * 该类为上传车辆故障信息返回参数数据实体类<br>
 */

public class TroubleInfo {
    /**
     * 备注
     */
    private String remark;
    /**
     * 主键id
     */
    private int id;
    /**
     * 车牌号
     */
    private String acarnum;
    /**
     * 评审时间
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
