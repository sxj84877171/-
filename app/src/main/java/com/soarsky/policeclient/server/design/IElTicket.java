package com.soarsky.policeclient.server.design;

import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.ui.violation.ViolationLicenseParam;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为开电子罚单接口<br>
 */

public interface IElTicket {

    /**
     * 开电子罚单
     */
    void openElTicket(ViolationLicenseParam violationLicenseParam);

    /**
     * 连接车辆接口
     */
    void connect(Car car);

    /**
     * 设置连接回调接口
     */
    public void setOnConnectListener(OnConnectListener onConnectListener);

    /**
     * 设置消息响应接口
     */
    void setOnResponseListener(OnMessageResponseListener onResponseListener);

}
