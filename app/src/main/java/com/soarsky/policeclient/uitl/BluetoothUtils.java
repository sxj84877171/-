package com.soarsky.policeclient.uitl;

import android.bluetooth.BluetoothAdapter;

import java.lang.reflect.Method;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2017/1/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为操作蓝牙的一些方法，蓝牙的功能类<br>
 */
public class BluetoothUtils {

    /**
     * 开启蓝牙“可发现”功能
     */
    public static void openDiscoverable() {
        BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();
        try {
            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
            setDiscoverableTimeout.setAccessible(true);
            Method setScanMode =BluetoothAdapter.class.getMethod("setScanMode", int.class,int.class);
            setScanMode.setAccessible(true);

            setDiscoverableTimeout.invoke(adapter, 0);
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
