package com.soarsky.car.ui.carlocation.lovecar;

import com.baidu.mapapi.model.LatLng;
import com.soarsky.car.base.BaseView;

/**
 * Created by 魏凯 on 2016/11/24.
 */

public interface CarLocationView extends BaseView{
    void showCarLocation(LatLng latLng);
    void onError();
    void noData();
}
