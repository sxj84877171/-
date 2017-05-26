package com.soarsky.car.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/24
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 文件上传返回结果
 */


public class UploadFile {

    /**
     * msg : 文件上传成功
     * code : 0
     * data : [{filamae:"hello.jpg",fileurl:"http://192.168.100.17:8080/20161111/002d09eba4044f7b9cf90df21dc48488.jpg"}]
     */



    private String msg;
    private String code;

    /**
     * hello.jpg : http://192.168.100.17:8080/20161111/002d09eba4044f7b9cf90df21dc48488.jpg
     */

    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static UploadFile  parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,UploadFile.class);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
