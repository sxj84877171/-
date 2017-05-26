package com.soarsky.car.ui.drivrecord.map;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class PositionData {

    private List<Position> data;
    private String status;
    private String message;

    public List<Position> getData() {
        return data;
    }

    public void setData(List<Position> data) {
        this.data = data;
    }

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
    public static class Position {
        private int id;
        private String carnum;
        private String lon;
        private String lat;
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
