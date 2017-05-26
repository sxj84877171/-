package com.soarsky.car.data.map.baidu;


import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.soarsky.car.data.map.design.ICoordinateConverter;
import com.soarsky.car.data.map.design.LonLat;


/**
 * Created by 魏凯 on 2016/11/22.
 */

public class BaiduICoordinateConverter implements ICoordinateConverter {
    @Override
    public LonLat converter(LonLat lonLat) {
        LatLng sourceLatLng = new LatLng(lonLat.getLon(),lonLat.getLat());
        // 将GPS设备采集的原始GPS坐标转换成百度坐标
        CoordinateConverter converter  = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.GPS);
// sourceLatLng待转换坐标
        converter.coord(sourceLatLng);
        LatLng desLatLng = converter.convert();
        lonLat.setLon(desLatLng.longitude);
        lonLat.setLat(desLatLng.latitude);
        return lonLat;
    }
}
