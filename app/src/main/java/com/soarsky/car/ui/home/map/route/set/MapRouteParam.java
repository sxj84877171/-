package com.soarsky.car.ui.home.map.route.set;

import android.os.Parcel;
import android.os.Parcelable;

import com.baidu.mapapi.model.LatLng;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/12<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：起始点信息<br>
 * 该类为 MapRouteParam<br>
 */


public class MapRouteParam implements Parcelable {
    /**
     * 开始位置
     */
    public String startAddress;
    /**
     * 开始经纬度
     */
    public LatLng startLocation;
    /**
     * 结束位置
     */
    public String endAddress;
    /**
     * 结束经纬度
     */
    public LatLng endLocation;

    public static final Parcelable.Creator<MapRouteParam> CREATOR = new Parcelable.Creator<MapRouteParam>() {

        public MapRouteParam createFromParcel(Parcel in) {

            return new MapRouteParam(in);
        };

        public MapRouteParam[] newArray(int size){
            return new MapRouteParam[size];
        }

    };


    public MapRouteParam(){

    }

    private MapRouteParam(Parcel var1){

        this.startAddress = var1.readString();
        this.endAddress = var1.readString();
        this.startLocation = (LatLng)var1.readParcelable(LatLng.class.getClassLoader());
        this.endLocation = (LatLng)var1.readParcelable(LatLng.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel var1, int var2) {
        var1.writeString(this.startAddress);
        var1.writeString(this.endAddress);
        var1.writeParcelable(this.startLocation, 1);
        var1.writeParcelable(this.endLocation, 1);
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public LatLng getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LatLng startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        this.endLocation = endLocation;
    }
}
