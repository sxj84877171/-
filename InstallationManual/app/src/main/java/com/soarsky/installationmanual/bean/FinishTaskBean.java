package com.soarsky.installationmanual.bean;

/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2017/2/27<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为完成和未完成的任务bean<br>
 */
public class FinishTaskBean {
    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 未完成时间
     */
    private String time;
    /**
     * 状态
     */
    private int status;
    /**
     * 是否已经读过消息
     */
    private boolean hasRead;
    /**
     * 分配时间
     */
    private String fenPeiShiJian;
    /**
     * 开始时间
     */
    private String kaiShiShiJian;
    /**
     * 完成时间
     */
    private String wanChengShiJian;
    /**
     * 完成状态
     */
    private int finishStatus;

    public String getFenPeiShiJian() {
        return fenPeiShiJian;
    }

    public void setFenPeiShiJian(String fenPeiShiJian) {
        this.fenPeiShiJian = fenPeiShiJian;
    }

    public String getKaiShiShiJian() {
        return kaiShiShiJian;
    }

    public void setKaiShiShiJian(String kaiShiShiJian) {
        this.kaiShiShiJian = kaiShiShiJian;
    }

    public String getWanChengShiJian() {
        return wanChengShiJian;
    }

    public void setWanChengShiJian(String wanChengShiJian) {
        this.wanChengShiJian = wanChengShiJian;
    }

    public int getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(int finishStatus) {
        this.finishStatus = finishStatus;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }
}
