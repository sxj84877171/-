package com.soarsky.car.ui.drivrecord.map;

import com.baidu.mapapi.model.LatLng;
import com.soarsky.car.base.BaseView;

import java.util.List;

/**
 * Created by 魏凯 on 2016/11/22.
 */

public interface PositionView extends BaseView {
    void hasPositionData(List<LatLng> latLngs);

    void showCarLocation(LatLng latLng);

    void onError();
    void noData();
}
