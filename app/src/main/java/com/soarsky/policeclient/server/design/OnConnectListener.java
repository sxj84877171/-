package com.soarsky.policeclient.server.design;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2017/1/12<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为连接响应接口<br>
 */
public interface OnConnectListener {

    /**
     * 连接成功
     */
    void onSuccess();

    /**
     * 连接失败
     */
    void onFailed();
}
