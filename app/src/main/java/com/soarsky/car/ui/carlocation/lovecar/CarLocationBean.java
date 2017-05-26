package com.soarsky.car.ui.carlocation.lovecar;

import com.soarsky.car.data.map.design.LonLat;
import com.soarsky.car.ui.login.LoginParam;

import java.util.List;

/**
 * Created by 魏凯 on 2016/11/24.
 */
//{"id":13,"carnum":"湘ANJ388","lon":"112.933547","lat":"28.279279","stimer":"20161125093102"}
public class CarLocationBean {
    private String status;
    private String message;
    private CarLocationBean.DataBean data;
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

    public static class DataBean {
        /**
         * id
         */
        private int id;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getStimer() {
            return stimer;
        }

        public void setStimer(String stimer) {
            this.stimer = stimer;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
