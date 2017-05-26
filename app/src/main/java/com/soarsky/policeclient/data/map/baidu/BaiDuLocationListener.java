package com.soarsky.policeclient.data.map.baidu;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.soarsky.policeclient.data.map.design.OnPositioningResponse;
import com.soarsky.policeclient.uitl.StringUtils;

import java.util.List;

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
 * 该类为百度地图获取本地位置数据的监听接口<br>
 */

public class BaiDuLocationListener implements BDLocationListener {
    /**
     * 获取到数据的回调
     */
    private OnPositioningResponse onPositioningResponse;

    public OnPositioningResponse getOnPositioningResponse() {
        return onPositioningResponse;
    }

    public void setOnPositioningResponse(OnPositioningResponse onPositioningResponse) {
        this.onPositioningResponse = onPositioningResponse;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        if(location!=null && !StringUtils.isEmpty(location.getAddrStr())){
            onPositioningResponse.onSuccess(location.getAddrStr());
        }else {
            onPositioningResponse.onError(0);    //错误码待定义
        }
    }
}
