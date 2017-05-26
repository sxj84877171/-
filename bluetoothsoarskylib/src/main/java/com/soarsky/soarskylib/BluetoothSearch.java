package com.soarsky.soarskylib;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： Elvis
 * 时间： 2017/5/18
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */
public class BluetoothSearch {

    /**
     * 搜素事件的回调
     */
    public interface OnBluetoothSearchEvent {
        /**
         * 搜素开始
         */
        void onStart();

        /**
         * 搜素完成
         * 回调在主线程，如果长时间的任务，需要切换到子线程处理，后续在动修改到子线程。
         *
         * @param all          所有发现的蓝牙设备
         * @param bondedList   已配对的设备
         * @param unbondedList 未配对的设备
         */
        void onCompleted(List<BluetoothDevice> all, List<BluetoothDevice> bondedList, List<BluetoothDevice> unbondedList);

        /**
         * 新设备被搜素到
         *
         * @param device 新设备
         * @param isBond 是否已配对
         */
        void onBluetoothSearched(BluetoothDevice device, boolean isBond);
    }

    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private Context context;
    private List<BluetoothDevice> allDevices = new ArrayList<>();
    private List<BluetoothDevice> bondedList = new ArrayList<>();
    private List<BluetoothDevice> unbondedList = new ArrayList<>();
    private boolean isRunning = false;
    private OnBluetoothSearchEvent onBluetoothSearchEvent;

    /**
     * 设置搜素事件的回调
     * @param bluetoothSearchEvent
     */
    public void setOnBluetoothSearchEvent(OnBluetoothSearchEvent bluetoothSearchEvent) {
        this.onBluetoothSearchEvent = bluetoothSearchEvent;
    }

    /**
     * 构造函数
     * @param context
     */
    public BluetoothSearch(Context context) {
        this.context = context;
    }

    public void setSearchTime(long time) {

    }


    public synchronized void start() {
        if (isRunning == false) {
            isRunning = true;
            allDevices.clear();
            bondedList.clear();
            unbondedList.clear();
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            context.registerReceiver(mReceiver, filter);
            if (bluetoothAdapter.isDiscovering()) {
                bluetoothAdapter.cancelDiscovery();
            }
            bluetoothAdapter.startDiscovery();
            if (onBluetoothSearchEvent != null) {
                onBluetoothSearchEvent.onStart();
            }
        }
    }

    public synchronized void stop() {
        if (isRunning == true) {
            isRunning = false;
            context.unregisterReceiver(mReceiver);
            if (onBluetoothSearchEvent != null) {
                onBluetoothSearchEvent.onCompleted(allDevices, bondedList, unbondedList);
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public List<BluetoothDevice> getBondedList() {
        return new ArrayList<>(bondedList);
    }

    public List<BluetoothDevice> getUnbondedList() {
        return new ArrayList<>(unbondedList);
    }

    public List<BluetoothDevice> getAllDevices() {
        return new ArrayList<>(allDevices);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device != null) {
                    boolean hasFound = false;
                    for (BluetoothDevice bluetoothDevice : allDevices) {
                        if (bluetoothDevice.getAddress().equals(device.getAddress())) {
                            hasFound = true;
                            break;
                        }
                    }
                    if (!hasFound) {
                        allDevices.add(device);
                        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                            unbondedList.add(device);
                        } else {
                            bondedList.add(device);
                        }
                        if (onBluetoothSearchEvent != null) {
                            onBluetoothSearchEvent.onBluetoothSearched(device, device.getBondState() == BluetoothDevice.BOND_BONDED);
                        }
                    }
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                stop();
            }
        }
    };
}
