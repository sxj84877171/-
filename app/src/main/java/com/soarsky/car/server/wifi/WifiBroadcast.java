package com.soarsky.car.server.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
 * 程序功能：监控wifi的状态和扫描到附近的热点<br>
 * 该类为 与终端通讯服务类<br>
 */

public class WifiBroadcast extends BroadcastReceiver {
    private ConfirmDriver confirmDriver;


    private Scan sacn;

    public WifiBroadcast(Scan sacn) {
        this.sacn = sacn;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //WIFI 开启
        if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            if (state == WifiManager.WIFI_STATE_ENABLED) {

                sacn.startScan();

            }
        }
        //得到热点集合
        else if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
            if (sacn != null) {
                sacn.getScanResult(WifiUtil.getInstance().getWifiManager().getScanResults());
            }

        }
    }


}
