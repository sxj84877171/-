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
 * 该类为违章处理信息<br>
 */

public class ViolationDealInfo {
    /**
     * 总分
     */
    private int countCent;
    /**
     * 总罚款
     */
    private int countMoney;
    /**
     * 处理条
     */
    private int dealCount;
    /**
     * 最近时间
     */
    private String lastTime;

    private List<ViolationDealIlist> ilist;

    public int getCountCent() {
        return countCent;
    }

    public void setCountCent(int countCent) {
        this.countCent = countCent;
    }

    public int getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(int countMoney) {
        this.countMoney = countMoney;
    }

    public int getDealCount() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public List<ViolationDealIlist> getIlist() {
        return ilist;
    }

    public void setIlist(List<ViolationDealIlist> ilist) {
        this.ilist = ilist;
    }
}
