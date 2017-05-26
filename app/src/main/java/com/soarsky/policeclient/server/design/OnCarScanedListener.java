package com.soarsky.policeclient.server.design;

import com.soarsky.policeclient.bean.Car;

import java.util.List;

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
 * 扫描附近车辆结果的响应接口<br>
 */

public interface OnCarScanedListener {

    /**
     * 当扫描到车辆时，回调函数
     * @param car
     */
     void newCarScanned(List<Car> car);


}
