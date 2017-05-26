package com.soarsky.installationmanual.bean;

/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2017/3/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 品牌系列下的款式bean<br>
 */
public class BrandItemNextBean {
    /**
     * 车辆类型
     */
    private String cartype;
    /**
     * 品牌系列
     */
    private String audi;
    /**
     * 自动挡还是手动挡
     */
    private String automatic;
    /**
     * 上市时间
     */
    private String ctime;
    /**
     * 车辆排量
     */
    private String output;
    /**
     * 车辆参数
     */
    private String parameter;

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public String getAudi() {
        return audi;
    }

    public void setAudi(String audi) {
        this.audi = audi;
    }

    public String getAutomatic() {
        return automatic;
    }

    public void setAutomatic(String automatic) {
        this.automatic = automatic;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
