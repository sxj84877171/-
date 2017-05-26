package com.soarsky.car.bean;

import com.google.gson.Gson;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/2/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 乘车记录实体类
 */


public class RideRecordSacn {
    /**
     * 车牌号
     */
    private String carNum;

    /**
     * ssid
     */
    private String ssId;
    /**
     * 扫描到的次数
     */
    private int count;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }


    public String getSsId() {
        return ssId;
    }

    public void setSsId(String ssId) {
        this.ssId = ssId;
    }

    public static RideRecordSacn parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,RideRecordSacn.class);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
