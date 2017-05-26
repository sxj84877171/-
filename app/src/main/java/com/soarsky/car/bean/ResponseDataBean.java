package com.soarsky.car.bean;

import com.google.gson.Gson;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/11<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为   服务器的响应参数类<br>
 */

public class ResponseDataBean<T> {
    /**
     * 状态
     */
    private String status = "0" ;
    /**
     * 内容
     */
    private String message ;
    /**
     * 实体类
     */
    private T data ;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    public static ResponseDataBean parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,ResponseDataBean.class);
    }
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
