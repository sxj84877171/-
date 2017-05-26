package com.soarsky.car.bean;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/1/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：封装故障数据符合UI要求
 * 该类为 封装数据实体类
 */


public class FaultRecordInfo {

    /**
     * 月份
     */
    private String month;
    /**
     * 具体的数据
     */
    private List<FaultRecordDataBean> data;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<FaultRecordDataBean> getData() {
        return data;
    }

    public void setData(List<FaultRecordDataBean> data) {
        this.data = data;
    }
}
