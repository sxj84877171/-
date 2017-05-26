package com.soarsky.car.uitl;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;


import com.soarsky.car.App;
import com.soarsky.car.bean.Car;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 车主APP
 * 作者： 魏凯
 * 时间： 2016/10/10
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * Wifi工具类
 */
public class WifiUtil {

    public static final String WIFI_AP_STATE = "android.net.wifi.WIFI_AP_STATE_CHANGED";
    public static final int WIFI_AP_STATE_ENABLED = 13;
    public static final int WIFI_AP_STATE_DISABLED = 11;
    public static final int NOPASS = 0;
    public static final int WEP = 1;
    public static final int WPA = 2;

    public static final int PORT = 8899;
    public static final String SSID = "USR-C215";
    public static final String MYSSID = "WEIKAI11235";
    public static final String PWD = "weikai123456";

    private WifiManager wifiManager;
    private boolean isWifiApEnabled;
    private static WifiUtil wifiUtil;

    private boolean isWifiConnected;

    private WifiUtil() {
        // 取得WifiManager对象
        this.wifiManager = (WifiManager) App.getApp().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

    }

    public synchronized static WifiUtil getInstance(){
        if(wifiUtil==null){
            wifiUtil=new WifiUtil();
        }
        return wifiUtil;
    }

    public WifiManager getWifiManager(){
        return wifiManager;
    }

    //打开关闭热点
    public void setWifiApEnabled(boolean state) {
        Method method1 = null;
        try {
            method1 = wifiManager.getClass().getMethod("setWifiApEnabled",
                    WifiConfiguration.class, boolean.class);
            WifiConfiguration netConfig = getWifiConfiguration(MYSSID,PWD);
            isWifiApEnabled = (boolean)method1.invoke(wifiManager, netConfig, state);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isWifiApEnabled(){
        return isWifiApEnabled;
    }

    // 打开关闭WIFI
    public void setWifiEnabled(boolean enabled) {
        wifiManager.setWifiEnabled(enabled);
    }

    public boolean isWifiEnable(){
        return wifiManager.isWifiEnabled();
    }

    /**
     * 开启热点作为服务端的配置
     */
    public WifiConfiguration getWifiConfiguration(String ssid, String pwd) {
        WifiConfiguration netConfig = new WifiConfiguration();
        netConfig.SSID = ssid;
        netConfig.preSharedKey = pwd;
        netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        return netConfig;

    }

    /**
     * 客户端添加配置，作为连接认证配置

     */
    public WifiConfiguration getWifiClientConfiguration(String ssid, String Password, int type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + ssid + "\"";

        if (type == NOPASS) {
            config.hiddenSSID = true;
            //config.wepKeys[0] = "";
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);

//            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
            //config.wepTxKeyIndex = 0;
        }
        if (type == WEP) {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (type == WPA) {

            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            //config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status= WifiConfiguration.Status.ENABLED;
        }


        /*WifiConfiguration tempConfig = isExsits(ssid);

        if (tempConfig != null) {
            wifiManager.removeNetwork(tempConfig.networkId);
//            config = tempConfig ;
        }*/

        return config;
    }

    //开启扫描
    public void saoMiaoWifi() {

        wifiManager.startScan();
    }

    public boolean connectWifi(Car car) {
        int netId = wifiManager.addNetwork(getWifiClientConfiguration(car.getSsid(),car.getPassword(),NOPASS));
        return wifiManager.enableNetwork(netId, true);
    }


    /**
     * 判断是否存在该ssid
     * @param SSID
     * @return -1表示不存在
     */
    public  int getNetWorkId(String SSID)
    {
        List<WifiConfiguration> existingConfigs = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs)
        {
            if (existingConfig.SSID.equals("\""+SSID+"\""))
            {
                return existingConfig.networkId;
            }
        }
        return -1;
    }

    /**
     * 删除制定id的配置文件
     * @param id
     */
    public void  removeNetWorkId(int  id){
        wifiManager.removeNetwork(id);
        wifiManager.saveConfiguration();


    }

    /**
     * 断开当前连接的wifi
     * @return
     */
    public  boolean disconnectWifi(){

        WifiInfo wifiInfo=wifiManager.getConnectionInfo();
        int netId=wifiInfo.getNetworkId();
        return  wifiManager.disableNetwork(netId);
    }




    public boolean isWifiConnected(){
        return isWifiConnected;
    }



}
