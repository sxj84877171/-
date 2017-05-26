package com.soarsky.car.uitl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.soarsky.car.ui.carcheck.TroubleUpload;
import com.soarsky.car.ui.ticketed.TicketUpLoad;

/**
 * 车主APP<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/5<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：网络监听 自动同步数据<br>
 *
 */

public class NetState extends BroadcastReceiver {

    private Context context;
    /**
     * 罚单上传类
     */
    private TicketUpLoad ticketUpLoad;
    /**
     * 故障上传类
     */
    private TroubleUpload troubleUpload;

    public NetState(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

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
        troubleUpload=new TroubleUpload(context);
    }

    public void stopUpload() {
        if (troubleUpload != null) {
            if (troubleUpload.getLooper() != null) {
                troubleUpload.getLooper().quit();
            }

        }
        troubleUpload = null;
        if (ticketUpLoad != null) {
            if (ticketUpLoad.getLooper() != null) {
                ticketUpLoad.getLooper().quit();
            }

        }
        ticketUpLoad = null;

    }


}
