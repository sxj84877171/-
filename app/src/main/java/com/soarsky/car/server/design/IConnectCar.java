package com.soarsky.car.server.design;

import com.soarsky.car.bean.Car;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：连接热点
 * 该类为 与终端通讯服务类
 */

public interface IConnectCar {

    void connect(Car car,int type);

    public void setOnConnectListener(OnConnectListener onConnectListener);

}
