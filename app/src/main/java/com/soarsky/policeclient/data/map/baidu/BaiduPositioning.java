package com.soarsky.policeclient.data.map.baidu;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.soarsky.policeclient.App;
import com.soarsky.policeclient.data.map.design.OnPositioningResponse;
import com.soarsky.policeclient.data.map.design.Positioning;


/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为百度地图获取本地位置功能类<br>
 */

public class BaiduPositioning implements Positioning {

    //此方法调用必须在主线程
    @Override
    public void startPositioning(OnPositioningResponse onPositioningResponse) {
        LocationClient mLocationClient = new LocationClient(App.getApp().getApplicationContext());
        BaiDuLocationListener baiDuLocationListener = new BaiDuLocationListener();
        baiDuLocationListener.setOnPositioningResponse(onPositioningResponse);
        mLocationClient.registerLocationListener(baiDuLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }
}
