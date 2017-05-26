package com.soarsky.car.data.map.design;

/**
 * Created by 魏凯 on 2016/11/22.
 */

public class LonLat {
    public LonLat(double lon, double lat){
        this.lon = lon;
        this.lat = lat;
    }
    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
