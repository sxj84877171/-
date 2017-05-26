package com.soarsky.policeclient.data.map.design;


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
 * 该类为百度地图开启定位接口<br>
 */


public interface Positioning {
    /**
     * 开启定位功能
     * @param onPositioningResponse 获取到位置数据的回调接口
     */
    void startPositioning(OnPositioningResponse onPositioningResponse);
}
