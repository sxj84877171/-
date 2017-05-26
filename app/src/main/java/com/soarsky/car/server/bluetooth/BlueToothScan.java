package com.soarsky.car.server.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeAdapter;
import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.base.Configure;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.CarDevice;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.ConfirmDriverSucessCallBack;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.wifi.ConfirmDriver;
import com.soarsky.car.uitl.ConfirmDriverUtils;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.ValidatorUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/3/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class BlueToothScan extends HandlerThread implements IScan, BluetoothIBridgeAdapter.EventReceiver {
    public static final String TAG = BlueToothScan.class.getSimpleName().toString();
    private Handler handler;
    private BluetoothIBridgeAdapter mAdapter;
    /**
     * 确认驾驶员扫描监听类
     */

    private OnCarScanedListener carScanedListener;

    /**
     * 请人移车扫描监听类
     */
    private OnCarScanedListener movecarScanedListener;

    private BlueToothAuto blueToothAuto;


    /**
     * 扫描到的蓝牙设备集合
     */
    private ArrayList<BluetoothIBridgeDevice> mDevices = new ArrayList<>();

    /**
     * 自动连接蓝牙设备集合
     */
    private ArrayList<BluetoothIBridgeDevice> autoDevices = new ArrayList<>();
    /**
     * 请人移车设备集合
     */

    private  ArrayList<BluetoothIBridgeDevice> moveCarDevices = new ArrayList<>();

    private Context context;


    /**
     * 驾驶员确认成功和离开回调
     */

    private ConfirmDriverSucessCallBack confirmDriverSucessCallBack;

    /**
     * 上一次扫描到确认驾驶员车辆的时间
     */
    private long time = 0;


    BlueToothManage blueToothManage;


    boolean result=false;

    boolean  isScan=true;

    /**
     * @param context 上下文 用来注册广播和取消广播注册
     */
    public BlueToothScan(Context context) {
        super(TAG, Thread.NORM_PRIORITY);
        this.context = context;


        IntentFilter filter = new IntentFilter(
                BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        filter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);
        context.registerReceiver(mReceiver, filter);
        init();

    }


    private static BlueToothScan single = null;

    //静态工厂方法
    public static BlueToothScan getInstance(Context context) {
        if (single == null) {
            single = new BlueToothScan(context);
        }
        return single;
    }


    private void init() {

        start();
        handler = new Handler(getLooper());
        startAtuo();

    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            clearDevices();

        }
    };

    @Override
    public void startScan() {
        LogUtil.i("startScan");
        handler.post(runnable);
    }


    /**
     * 开始扫描
     */

    public void startDiscovery() {



        if(mAdapter!=null){
           if(BluetoothAdapter.getDefaultAdapter().isDiscovering()) {
               mAdapter.stopDiscovery();
           }
            handler.removeCallbacks(scanRunable);
            handler.postDelayed(scanRunable,2000);
            LogUtil.i("startDiscovery");
        }



    }


    /**
     * 扫描Runable
     */

    Runnable  scanRunable=new Runnable() {
        @Override
        public void run() {
            if(isScan){
                mAdapter.startDiscovery();
            }
        }
    };


    /**
     * 关闭扫描
     */
    public void stopDiscovery() {
        if (mAdapter != null) {
            mAdapter.stopDiscovery();
        }
    }

    /**
     * 开始自动连接
     */
    public void startAtuo() {
        if (!Configure.ISAUTOCOONECT) {
            return;
        }

        if (!App.getApp().isConfirmDriver()) {
            clearAutoDevices();
            if (blueToothAuto == null) {
                blueToothAuto = new BlueToothAuto();
            }

        }

    }


    /**
     * 关闭自动连接
     */
    public void stopAuto() {
        LogUtil.i("关闭自动连接");
        if (blueToothAuto != null) {
            blueToothAuto.onDestory();
            blueToothAuto = null;

        }

    }


    @Override
    public void setCarScanedListener(OnCarScanedListener carScanedListener) {
        this.carScanedListener = carScanedListener;
    }



    public  void   setMovecarScanedListener(OnCarScanedListener carScanedListener){
        this.movecarScanedListener=carScanedListener;
    }


    @Override
    public List<Car> getScanedCarList() {
        return null;
    }


    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setBluetoothAdapter(BluetoothIBridgeAdapter adapter) {
        if (adapter != null) {
            mAdapter = adapter;
            mAdapter.registerEventReceiver(this);
        } else {
            mAdapter.unregisterEventReceiver(this);
            mAdapter = null;
        }
    }

    /**
     * 清空数据
     */

    public void clearDevices() {

        LogUtil.i("清空数据");
        if (mDevices != null) {
            ArrayList<BluetoothIBridgeDevice> newList = new ArrayList<BluetoothIBridgeDevice>();
            Iterator<BluetoothIBridgeDevice> it = mDevices.iterator();
            while (it.hasNext()) {
                BluetoothIBridgeDevice d = it.next();
            }
            if (newList != null) {
                synchronized (mDevices) {
                    mDevices = newList;
                }
            }
        }
        LogUtil.i("mDevices长度" + mDevices.size());
        isScan=true;
        startDiscovery();
    }



    /**
     * 清空数据
     */

    public void clearAutoDevices() {

        LogUtil.i("清空自动连接数据数据");
        if (autoDevices != null) {
            ArrayList<BluetoothIBridgeDevice> newList = new ArrayList<BluetoothIBridgeDevice>();
            Iterator<BluetoothIBridgeDevice> it = autoDevices.iterator();
            while (it.hasNext()) {
                BluetoothIBridgeDevice d = it.next();
            }
            if (newList != null) {
                synchronized (autoDevices) {
                    autoDevices = newList;
                }
            }
        }
        LogUtil.i("mautoDevices长度" + autoDevices.size());
        isScan=true;
        startDiscovery();
    }

    /**
     * 清空数据
     */

    public void clearMoveDevices() {

        LogUtil.i("清空数据");
        if (moveCarDevices != null) {
            ArrayList<BluetoothIBridgeDevice> newList = new ArrayList<BluetoothIBridgeDevice>();
            Iterator<BluetoothIBridgeDevice> it = moveCarDevices.iterator();
            while (it.hasNext()) {
                BluetoothIBridgeDevice d = it.next();
            }
            if (newList != null) {
                synchronized (moveCarDevices) {
                    moveCarDevices = newList;
                }
            }
        }
        LogUtil.i("moveCarDevices" + moveCarDevices.size());
        isScan=true;
        startDiscovery();
    }



    /**
     * 监听蓝牙是否关闭
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();

            LogUtil.i("action" + action);

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_OFF) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.setEnabled(true);
                        }
                    },2000);


                }
                if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_ON) {
//                    if (mAdapter != null) {
//                        mAdapter.startDiscovery();
//                    }
                }
            }
            if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            }
            if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            }

            if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                //如果没有将广播终止，则会出现一个一闪而过的配对框。
                abortBroadcast();
                LogUtil.i("配对请求广播");
            }

        }

    };


    public void destory() {
        context.unregisterReceiver(mReceiver);
        stopAuto();
        single = null;
        getLooper().quit();
    }


    /**
     * 判断蓝牙设备是否存在
     *
     * @param device
     * @return
     */
    private boolean deviceExisted(BluetoothIBridgeDevice device) {
        if (device == null)
            return false;

        Iterator<BluetoothIBridgeDevice> it = mDevices.iterator();
        while (it.hasNext()) {
            BluetoothIBridgeDevice d = it.next();
            if (d != null && d.equals(device))
                return true;
        }
        return false;
    }




    /**
     * 判断蓝牙设备是否存在
     *
     * @param device
     * @return
     */
    private boolean deviceautoExisted(BluetoothIBridgeDevice device) {
        if (device == null)
            return false;

        Iterator<BluetoothIBridgeDevice> it = autoDevices.iterator();
        while (it.hasNext()) {
            BluetoothIBridgeDevice d = it.next();
            if (d != null && d.equals(device))
                return true;
        }
        return false;
    }


    /**
     * 判断蓝牙设备是否存在
     *
     * @param device
     * @return
     */
    private boolean moveCarExisted(BluetoothIBridgeDevice device) {
        if (device == null)
            return false;

        Iterator<BluetoothIBridgeDevice> it = moveCarDevices.iterator();
        while (it.hasNext()) {
            BluetoothIBridgeDevice d = it.next();
            if (d != null && d.equals(device))
                return true;
        }
        return false;
    }


    @Override
    public void onBluetoothOn() {

    }

    @Override
    public void onBluetoothOff() {

    }

    @Override
    public void onDiscoveryFinished() {
        LogUtil.i("扫描完成");
        startDiscovery();


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
        LogUtil.i("有设备被发现"+bluetoothIBridgeDevice.getDeviceName());

        /**
         * *112湘A44444-
         * 前5位表示序列号第6位表示状态 第7 8位表示车子状态
         */
        handler.removeCallbacks(resertRunnable);

        if (null == bluetoothIBridgeDevice.getDeviceName()) {
            return;
        }
        if (!ValidatorUtils.validatorBlueTooth(bluetoothIBridgeDevice.getDeviceName())) {
            return;
        }
        Car car = new Car(bluetoothIBridgeDevice);

        if(car.getCarStatus().equals(Car.CAR_STATUS_STOP)){
            if(movecarScanedListener!=null&&!moveCarExisted(bluetoothIBridgeDevice)){
                movecarScanedListener.newBlueToothScan(bluetoothIBridgeDevice);
            }
            return;
        }
        if (car.getCarNum().equals(App.getApp().getCarNum())) {
            time = new Date().getTime();
            App.getApp().setiBridgeDevice(bluetoothIBridgeDevice);
            if (car.isStop()) {
                App.getApp().setConfirmDriver(false);
                confirmDriverSucessCallBack.oncancle();

            }

        }
        if (!deviceExisted(bluetoothIBridgeDevice)) {
            synchronized (mDevices) {
                if (carScanedListener != null) {
                    carScanedListener.newBlueToothScan(bluetoothIBridgeDevice);
                }
                mDevices.add(bluetoothIBridgeDevice);
            }
        }

        if (!deviceautoExisted(bluetoothIBridgeDevice)) {
            synchronized (autoDevices) {
                if (car.isReady()) {
                    LogUtil.i("开始自动连接1");
                    if (ConfirmDriverUtils.getConnectType(car) < 3) {
                        LogUtil.i("开始自动连接2");
                        //TODO 开始自动连接
                        if (blueToothAuto != null) {
                            LogUtil.i("添加到自动连接集合");
                            blueToothAuto.addCar(car);
                        }
                    }
                    autoDevices.add(bluetoothIBridgeDevice);
                }
            }
        }
    }

    @Override
    public void onDeviceConnected(BluetoothIBridgeDevice bluetoothIBridgeDevice) {

    }

    @Override
    public void onDeviceDisconnected(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {

    }

    @Override
    public void onDeviceConnectFailed(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {

    }

    @Override
    public void onWriteFailed(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {

    }

    @Override
    public void onLeServiceDiscovered(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {

    }


    /**
     * 验证驾驶员是否还在
     */



    /**
     * @param confirmDriverSucessCallBack
     */
    public void setConfirmDriverSucessCallBack(ConfirmDriverSucessCallBack confirmDriverSucessCallBack) {
        this.confirmDriverSucessCallBack = confirmDriverSucessCallBack;
    }

    /**
     * 获取扫描最新的car数据
     */
    public List<BluetoothIBridgeDevice> getCarDeviceList() {
        return mDevices;
    }



    private  Runnable  resertRunnable=new Runnable() {
        @Override
        public void run() {
            android.bluetooth.BluetoothAdapter.getDefaultAdapter().startDiscovery();
            stopDiscovery();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startDiscovery();
                }
            },5000);

        }
    };


    /**
     *更新获取终端信息时间
     */
    public void upteTime(){

        LogUtil.i("更新最新获取终端时间");
        time = new Date().getTime();
    }


    /**
     * 是否扫描
     */
    public void  isScan(boolean isScan){
        this.isScan=isScan;
        if(isScan){

            clearAutoDevices();

        }else{
            stopDiscovery();}

    }

}
