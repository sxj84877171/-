package com.soarsky.car.data.map.design;

import com.soarsky.car.data.map.design.LonLat;

/**
 * Created by 魏凯 on 2016/11/22.
 */

public interface ICoordinateConverter {

    LonLat converter(LonLat lonLat);
}
