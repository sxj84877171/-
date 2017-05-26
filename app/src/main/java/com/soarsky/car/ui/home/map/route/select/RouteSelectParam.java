package com.soarsky.car.ui.home.map.route.select;

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
 * 程序功能：位置的详细信息<br>
 * 该类为 RouteSelectParam<br>
 */


public class RouteSelectParam implements Parcelable{

    /**
     * 位置
     */
    public String address;
    /**
     * 经纬度
     */
    public LatLng location;

    public static final Parcelable.Creator<RouteSelectParam> CREATOR = new Parcelable.Creator<RouteSelectParam>() {

        public RouteSelectParam createFromParcel(Parcel in) {

            return new RouteSelectParam(in);
        };

        public RouteSelectParam[] newArray(int size){
            return new RouteSelectParam[size];
        }

    };

    public RouteSelectParam(){

    }

    private RouteSelectParam(Parcel var1){

        this.address = var1.readString();
        this.location = (LatLng)var1.readParcelable(LatLng.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel var1, int var2) {

        var1.writeString(this.address);
        var1.writeParcelable(this.location, 1);
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
