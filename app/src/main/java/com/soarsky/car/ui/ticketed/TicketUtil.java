package com.soarsky.car.ui.ticketed;

import java.util.HashMap;
import java.util.Map;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：解析智能终端数据
 * 该类为
 */

public class TicketUtil {

    private  String content;
    public  TicketUtil( String content){
        this.content=content;
    }


    /**
     * 解析出经度
     * @return
     */
    public  String getLon(){

        if(content == null){
            return "";
        }


        String lonAndLat =content.substring(57,content.length());


        String[] lon = lonAndLat.split(",");



        return lon[0];

    }

    /**
     * 解析车牌号
     * @return
     */
    public  String getCarNum(){
        if(content == null){
            return "";
        }

        String carNum =content.substring(2,9);

        return carNum;
    }

    /***
     * 解析出纬度
     * @return
     */
    public  String getLat(){

        if(content == null){
            return "";
        }

        String lonAndLat =content.substring(57,content.length());


        String[] lat = lonAndLat.split(",");

        return lat[1].substring(0,8);

    }

    /***
     * 解析出违章时间
     * @return
     */
    public  String getStime(){

        if(content == null){
            return "";
        }

        String stime =content.substring(9,23);
        return stime;
    }

    /***
     * 解析出违章结束时间
     * @return
     */
    public  String getEtime(){

        if(content == null){
            return "";
        }

        String etime = content.substring(23,37);

        return etime;
    }

    /***
     * 解析出驾驶证号
     * @return
     */
    public  String getIdNo(){

        if(content == null){
            return "";
        }

        String idNo =content.substring(37,57);

        return idNo;
    }

    /***
     * 解析违法信息

     * @return
     */
    public  String getInf(){

        if(content == null){
            return "";
        }

        String inf ="违章停车";

        return inf;
    }

    /**
     * 解析准驾车型

     * @return
     */
    public  String getdType(){

        if(content == null){
            return "";
        }

        String dType =content.substring(0,2);

        return dType;
    }


    /**
     * 封装上传请求数据
     * @return
     */
    public Map<String, String> createCommitParams() {
        Map<String, String> params = new HashMap<>();
        params.put("carnum", getCarNum());
        params.put("idNo", getIdNo());
        params.put("stime", getStime());
        params.put("etime", getEtime());
        params.put("lon", getLon());
        params.put("lat", getLat());
        params.put("inf", getInf());
        params.put("dType", getdType());
        return params;
    }



}
