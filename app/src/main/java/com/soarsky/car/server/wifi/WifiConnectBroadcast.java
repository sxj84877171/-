package com.soarsky.car.server.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.WifiUtil;

/**
 * Andriod_Car_App
 * 作者：何明辉
 * 时间： 2016/11/15.
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：监控所连接的热点是不是我们所要的热点
 * 该类为 与终端通讯服务类
 */

public class WifiConnectBroadcast extends BroadcastReceiver {

    private ConnectCar connectCar;

    public WifiConnectBroadcast(){

    }

    public WifiConnectBroadcast(ConnectCar connectCar){
        this.connectCar = connectCar;
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            //wifi连接上与否
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            LogUtil.i("infostate:"+info.getState()+"         6"+info.isConnected());

            if (info.isConnected()) {
                final WifiInfo wifiInfo = WifiUtil.getInstance().getWifiManager().getConnectionInfo();
                LogUtil.i("SSID:"+wifiInfo.getSSID());
                if (wifiInfo.getSSID().equals("\"" + connectCar.getCar().getSsid() + "\"")) {
                    connectCar.sendMessage(connectCar.getCar());
                    LogUtil.i("2");
                }else {
                    connectCar.connectFail();
                    LogUtil.i("3");
                }
            }
        }
    }
}
