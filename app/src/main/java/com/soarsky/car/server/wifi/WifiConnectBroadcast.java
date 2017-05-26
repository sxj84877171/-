package com.soarsky.car.server.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.WifiUtil;

/**
 * Andriod_Car_App<br>
 * 作者：何明辉<br>
 * 时间： 2016/11/15.<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：监控所连接的热点是不是我们所要的热点<br>
 * 该类为 与终端通讯服务类<br>
 */

public class WifiConnectBroadcast extends BroadcastReceiver {

    private ConnectCar connectCar;
    private WifiManager my_wifiManager;
    private DhcpInfo dhcpInfo;
    /**
     * 监听类型 1是断开连接 2是连接
     */
    private int type;

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


            if (type == 1) {
                if (!info.isConnected()) {
                    connectCar.connectNext();
                }
            } else if (type == 2) {

                if (info.isConnected()) {
                    final WifiInfo wifiInfo = WifiUtil.getInstance().getWifiManager().getConnectionInfo();
                    my_wifiManager = WifiUtil.getInstance().getWifiManager();
                    dhcpInfo = my_wifiManager.getDhcpInfo();

                    if (wifiInfo.getSSID().equals("\"" + connectCar.getCar().getSsid() + "\"")) {
                        connectCar.sendMessage(connectCar.getCar());
                    } else {
                        connectCar.connectFail();
                    }


                }
            }
        }
    }


    public void setType(int type) {
        this.type = type;
    }


    public String intToIp(int paramInt) {
        return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "."
                + (0xFF & paramInt >> 24);
    }



}
