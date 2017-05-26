package com.soarsky.car.bean;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为违章查询信息类<br>
 */

public class ViolationQueryInfo {
    /**
     * 处理数
     */
    private int dealCount;
    /**
     * 罚款
     */
    private int countMoney;
    /**
     * 分
     */
    private int countCent;
    /**
     * 最近时间
     */
    private String lastTime;


    private List<ViolationRecordInfo> ilist;

    public int getDealCount() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
    }

    public int getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(int countMoney) {
        this.countMoney = countMoney;
    }

    public int getCountCent() {
        return countCent;
    }

    public void setCountCent(int countCent) {
        this.countCent = countCent;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public List<ViolationRecordInfo> getIlist() {
        return ilist;
    }

    public void setIlist(List<ViolationRecordInfo> ilist) {
        this.ilist = ilist;
    }
}
