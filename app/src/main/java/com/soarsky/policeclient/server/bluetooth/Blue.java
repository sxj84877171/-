package com.soarsky.policeclient.server.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeAdapter;
import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;

import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.server.cmd.BaseCmd;
import com.soarsky.policeclient.server.design.OnConnectListener;
import com.soarsky.policeclient.server.design.OnPacketListener;
import com.soarsky.policeclient.uitl.BluetoothUtils;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.RandomUtils;
import com.soarsky.policeclient.uitl.ValidatorUtils;

import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * android_police_app
 * 作者： 魏凯
 * 时间： 2017/4/18
 * 公司：长沙硕铠电子科技有限公司
 * Email：weikai@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */
public class Blue implements BluetoothIBridgeAdapter.EventReceiver, BluetoothIBridgeAdapter.DataReceiver {

    private BluetoothIBridgeAdapter mAdapter;

    private OnBlueScan onBlueScan;

    private Context context;

    private OnConnectListener onConnectListener;

    private OnBlueScanFinish onBlueScanFinish;


    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    private static int index = 0;

    private OnPacketListener packetListener;

    private Timer timer;

    private TimerTask timerTask;

    public void setPacketListener(OnPacketListener packetListener) {
        this.packetListener = packetListener;
    }


    @Override
    public void onDataReceived(BluetoothIBridgeDevice bluetoothIBridgeDevice, byte[] bytes, int readLength) {
        if (readLength > 0) {
            if(timer!=null){
                timer.cancel();
            }
            //System.arraycopy(bytes, 0, buffer, index, readLength);
            buffer.write(bytes, index, readLength);
            index += readLength;
            if (buffer.toByteArray()[0] == BaseCmd.MARK) {
                if (index > 10) {
                    int msgBodyLength = BaseCmd.parseLength(bytes, 4, 5);
                    if (index >= msgBodyLength + 10) {
                        BaseCmd cmd = BaseCmd.parseCmd(buffer.toByteArray());
                        packetListener.onNewPacket(cmd);
                        buffer.reset();
                    }
                }
            }
        }
    }

    public interface OnBlueScan {

        void onBlueScan(Car car);
    }

    public interface OnBlueScanFinish{
        void onBlueScanFinish();
    }

    public BluetoothIBridgeAdapter getmAdapter() {
        return mAdapter;
    }

    public void setOnConnectListener(OnConnectListener onConnectListener) {
        this.onConnectListener = onConnectListener;
    }

    public void setOnBlueScanFinish(OnBlueScanFinish onBlueScanFinish) {
        this.onBlueScanFinish = onBlueScanFinish;
    }

    private Blue(Context context) {
        this.context = context;
        initBuleTooth();
    }

    public void setOnBlueScan(OnBlueScan onBlueScan) {
        this.onBlueScan = onBlueScan;
    }

    private void initBuleTooth() {
        mAdapter = BluetoothIBridgeAdapter.sharedInstance(context);
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);
        context.registerReceiver(mReceiver, filter);
        if(!mAdapter.isEnabled()){
            mAdapter.setEnabled(true);
        }
        mAdapter.setLinkKeyNeedAuthenticated(false);
        mAdapter.setAutoWritePincode(true);
        mAdapter.registerEventReceiver(this);
        mAdapter.registerDataReceiver(this);
    }

    public void destroy() {
        try{
            context.unregisterReceiver(mReceiver);
            mAdapter.unregisterDataReceiver(this);
            mAdapter.unregisterEventReceiver(this);
            mAdapter.stopDiscovery();
            mAdapter.destroy();
            mAdapter = null;
            onBlueScan = null;
            onConnectListener = null;
        }catch (Exception e){
            Log.e("weikai","weikaio");
        }

    }

    public void stopDiscovery() {
        mAdapter.stopDiscovery();
    }

    private static Blue single = null;

    //静态工厂方法
    public static Blue getInstance(Context context) {
        index = 0;
        if (single == null) {
            single = new Blue(context);
        }
        return single;
    }

    public void startDiscovery() {
        if(!mAdapter.isEnabled()){
            mAdapter.setEnabled(true);
            return;
        }
        mAdapter.startDiscovery();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                abortBroadcast();
                LogUtil.i("配对请求广播");
            }

        }

    };




    @Override
    public void onBluetoothOn() {
        mAdapter.setLocalName(Constants.POLICE + RandomUtils.getRandom());
        BluetoothUtils.openDiscoverable();
        startDiscovery();
    }

    @Override
    public void onBluetoothOff() {

    }

    @Override
    public void onDiscoveryFinished() {
        LogUtil.e("蓝牙扫描结束", "蓝牙扫描结束");
        if(onBlueScanFinish!=null){
            onBlueScanFinish.onBlueScanFinish();
        }
    }

    @Override
    public void onDeviceBonding(BluetoothIBridgeDevice bluetoothIBridgeDevice) {

    }

    @Override
    public void onDeviceBonded(BluetoothIBridgeDevice bluetoothIBridgeDevice) {

    }

    @Override
    public void onDeviceBondNone(BluetoothIBridgeDevice bluetoothIBridgeDevice) {

    }

    @Override
    public void onDeviceFound(BluetoothIBridgeDevice bluetoothIBridgeDevice) {
        LogUtil.e("蓝牙扫描", "蓝牙扫描");
        if(bluetoothIBridgeDevice!=null && !TextUtils.isEmpty(bluetoothIBridgeDevice.getDeviceName())){
            LogUtil.e("blueName",bluetoothIBridgeDevice.getDeviceName());
            if (ValidatorUtils.validatorSsid(bluetoothIBridgeDevice.getDeviceName())) {
                    Car car = new Car();
                    car.setBlueName(bluetoothIBridgeDevice.getDeviceName());
                    car.setCarNum(CarUtil.fromSsidToCarNum(bluetoothIBridgeDevice.getDeviceName()));
                    car.setBluetoothIBridgeDevice(bluetoothIBridgeDevice);
                    if(onBlueScan!=null){
                        onBlueScan.onBlueScan(car);
                    }
            }
        }

    }

    @Override
    public void onDeviceConnected(BluetoothIBridgeDevice bluetoothIBridgeDevice) {
        LogUtil.e("连接成功","连接成功");
        if(onConnectListener !=null){
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if(onConnectListener !=null){
                        onConnectListener.onFailed();
                    }
                }
            };
            timer.schedule(timerTask,10000);
            onConnectListener.onSuccess();
        }
    }

    @Override
    public void onDeviceDisconnected(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {

    }

    @Override
    public void onDeviceConnectFailed(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {
        if(onConnectListener !=null){
            onConnectListener.onFailed();
        }
    }

    @Override
    public void onWriteFailed(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {
        if(onConnectListener !=null){
            onConnectListener.onFailed();
        }
    }

    @Override
    public void onLeServiceDiscovered(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {

    }
}
