package com.soarsky.car.bean;

import com.google.gson.Gson;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/4/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class TerminalParamBean {
    /**
     * 参数代码
     */
    private String K;


    /**
     * 参数值
     */
    private  String V;


    public String getK() {
        return K;
    }

    public void setK(String k) {
        K = k;
    }

    public String getV() {
        return V;
    }

    public void setV(String v) {
        V = v;
    }

    public static TerminalParamBean parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,TerminalParamBean.class);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
