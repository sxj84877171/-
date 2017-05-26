package com.soarsky.policeclient.ui.accident.add;

import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.server.design.OnMessageResponseListener;

import java.util.ArrayList;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故分析开启关闭是否在工作设置从智能终端读取返回的消息回调接口，参考该接口的实现类AccidentHandlerThread<br>
 */
public interface IAccident {

    /**
     * 开启事故分析，从终端读取数据
     * @param selectCar
     * @param onMessageResponseListener
     */
    void startAccident(ArrayList<Car> selectCar,OnAccidentMessageResponseListener onMessageResponseListener);

    /**
     * 关闭事故分析
     */
    void stopAccident();
    boolean isAccident();

    /**
     * 设置从智能终端读取返回的消息回调接口
     * @param onMessageResponseListener
     */
    void setOnAccidentMessageResponseListener(OnAccidentMessageResponseListener onMessageResponseListener);
}
