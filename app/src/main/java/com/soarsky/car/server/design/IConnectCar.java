package com.soarsky.car.server.design;

import com.soarsky.car.bean.Car;
import com.soarsky.car.ui.home.main.OnVolumeListener;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：连接热点<br>
 * 该类为 与终端通讯服务类<br>
 */

public interface IConnectCar {

    /**
     * 申请一 命令类型
     */
    public static final int CONNECT_TYPE_APPLY_1 = 1 ;
    public static final int CONNECT_TYPE_APPLY_2 = 2 ;
    public static final int CONNECT_TYPE_APPLY_3 = 3 ;

    /**
     * 连接终端
     * @param car 车辆
     * @param type 申请类型
     *             @CONNECT_TYPE_APPLY_1
     *             @CONNECT_TYPE_APPLY_2
     *             @CONNECT_TYPE_APPLY_3
     *             1 :　shenqing1
     *             ...
     *             14 设置音量
     */
    void connect(Car car,int type);

    public void setOnConnectListener(OnConnectListener onConnectListener);


}
