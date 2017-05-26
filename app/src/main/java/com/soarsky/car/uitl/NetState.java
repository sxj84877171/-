package com.soarsky.car.uitl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.soarsky.car.ui.ticketed.TicketUpLoad;

/**
 * 车主APP
 * 作者： 何明辉
 * 时间： 2016/12/5
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：网络监听 自动同步数据
 *
 */

public class NetState extends BroadcastReceiver {

    private Context context;
    private TicketUpLoad ticketUpLoad;

    public NetState(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("NetState", "===========================");
        if (NetworkUtils.isAvailable(context)) {

            if (NetworkUtils.isWifiConnected(context)) {

                //判断连接的热点名字是不是车牌号
                if (ValidatorUtils.validatorSsid(NetworkUtils.getWifiName(context))) {
                    stopUpload();
                } else {
                    upload();
                }

            } else {
                upload();
            }

        } else {
            stopUpload();
        }

    }


    public void upload() {
        ticketUpLoad = new TicketUpLoad(context);
    }

    public void stopUpload() {
        if (ticketUpLoad != null) {
            if (ticketUpLoad.getLooper() != null) {
                ticketUpLoad.getLooper().quit();
            }

        }
        ticketUpLoad = null;

    }


}
