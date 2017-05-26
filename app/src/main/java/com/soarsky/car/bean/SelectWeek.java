package com.soarsky.car.bean;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/2/24
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 设置保护时间的实体类
 */


public class SelectWeek {
    /**
     * 星期几
     */
    private String  weekDay;
    /**
     * 是否选中 0选中 1不选
     */
    private boolean  isSelect;


    public SelectWeek(String weekDay,boolean isSelect){
        this.weekDay=weekDay;
        this.isSelect=isSelect;
    }



    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
