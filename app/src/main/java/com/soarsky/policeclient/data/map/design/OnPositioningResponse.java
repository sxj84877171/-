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
 * 该类为百度地图获取本地位置数据的回调接口<br>
 */

public interface OnPositioningResponse {
    /**
     * 成功获取到数据
     * @param s 本地位置
     */
    void onSuccess(String s);

    /**
     * 获取数据失败
     * @param errorCode 错误码
     */
    void onError(int errorCode);
}
