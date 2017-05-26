package com.soarsky.car.ui.carlocation.alarm;

import java.util.List;

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
 * 程序功能：防盗报警
 * 该类为
 */

public class CarAlarmParam {


    /**
     * message : 操作成功！
     * data : [{"address":"岳麓大道信息港A座","id":13,"drivers":"430624198802101142","carnum":"湘A00001","lon":null,"lat":null,"stimer":"2016-11-25 10:00:00"},{"address":"麓谷公园","id":14,"drivers":null,"carnum":"湘A00001","lon":null,"lat":null,"stimer":"2016-11-25 18:00:00"}]
     * status : 0
     */

    private String message;
    private String status;
    /**
     * address : 岳麓大道信息港A座
     * id : 13
     * drivers : 430624198802101142
     * carnum : 湘A00001
     * lon : null
     * lat : null
     * stimer : 2016-11-25 10:00:00
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * 地址
         */
        private String address;
        /**
         * id
         */
        private int id;
        /**
         * 驾驶员
         */
        private String drivers;
        /**
         * 车牌号
         */
        private String carnum;
        /**
         * 经度
         */
        private String lon;
        /**
         * 纬度
         */
        private String lat;
        /**
         * 上传时间
         */
        private String stimer;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDrivers() {
            return drivers;
        }

        public void setDrivers(String drivers) {
            this.drivers = drivers;
        }

        public String getCarnum() {
            return carnum;
        }

        public void setCarnum(String carnum) {
            this.carnum = carnum;
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

        public String getStimer() {
            return stimer;
        }

        public void setStimer(String stimer) {
            this.stimer = stimer;
        }
    }
}
