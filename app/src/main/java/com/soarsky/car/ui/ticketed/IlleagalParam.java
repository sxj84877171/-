package com.soarsky.car.ui.ticketed;

import com.google.gson.Gson;

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
 * 程序功能：上传违章信息响应
 * 该类为
 */

public class IlleagalParam {


    /**
     * carnum : 湘A00001
     * monery : null
     * cent : null
     * ino : null
     * inf :
     * grade : null
     * etime : 2016-12-02 10:22:10
     * imageUrl : null
     * lon : 116.432152
     * lat : 39.94708
     * stime : 2016-12-01 11:11:11
     * opuser : null
     * address : 北京市东城区东直门北大街甲6号院
     * id : null
     * drivers : 430624198610114416
     * status : 1
     */

    private DataBean data;
    /**
     * data : {"carnum":"湘A00001","monery":null,"cent":null,"ino":null,"inf":"","grade":null,"etime":"2016-12-02 10:22:10","imageUrl":null,"lon":"116.432152","lat":"39.94708","stime":"2016-12-01 11:11:11","opuser":null,"address":"北京市东城区东直门北大街甲6号院","id":null,"drivers":"430624198610114416","status":"1"}
     * message : 操作成功！
     * status : 0
     */

    private String message;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        private String carnum;
        private Object monery;
        private Object cent;
        private Object ino;
        private String inf;
        private Object grade;
        private String etime;
        private Object imageUrl;
        private String lon;
        private String lat;
        private String stime;
        private Object opuser;
        private String address;
        private Object id;
        private String drivers;
        private String status;

        public String getCarnum() {
            return carnum;
        }

        public void setCarnum(String carnum) {
            this.carnum = carnum;
        }

        public Object getMonery() {
            return monery;
        }

        public void setMonery(Object monery) {
            this.monery = monery;
        }

        public Object getCent() {
            return cent;
        }

        public void setCent(Object cent) {
            this.cent = cent;
        }

        public Object getIno() {
            return ino;
        }

        public void setIno(Object ino) {
            this.ino = ino;
        }

        public String getInf() {
            return inf;
        }

        public void setInf(String inf) {
            this.inf = inf;
        }

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public Object getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(Object imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public Object getOpuser() {
            return opuser;
        }

        public void setOpuser(Object opuser) {
            this.opuser = opuser;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getDrivers() {
            return drivers;
        }

        public void setDrivers(String drivers) {
            this.drivers = drivers;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static IlleagalParam parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,IlleagalParam.class);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
