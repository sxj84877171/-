package com.soarsky.policeclient.ui.accident.add;

import com.soarsky.policeclient.bean.AccidentDataBean;

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
 * 该类为事故分析从终端读取数据消息回调接口<br>
 */
public interface OnAccidentMessageResponseListener {

    /**
     * 从终端获取到事故分析选择的车辆的数据的回调
     * @param accidentDataBean 从终端获取到事故分析选择的车辆的数据
     */
    void onResponse(AccidentDataBean.AccidentItemBean.AccidentCarBean accidentDataBean);

    /**
     * 获取车辆数据完成的回调
     */
    void onFinished();

    /**
     * 获取车辆数据未完成的回调
     */
    void onUnFinished();
}
