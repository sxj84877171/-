package com.soarsky.policeclient.ui.violation;

import com.google.gson.Gson;
import com.soarsky.policeclient.bean.DataBean;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 上传图片bean<br>
 */

public class UploadFile {


    /**
     * msg : 文件上传成功
     * code : 0
     * data : [{"hello.jpg":"http://192.168.100.17:8080/20161111/002d09eba4044f7b9cf90df21dc48488.jpg"}]
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
