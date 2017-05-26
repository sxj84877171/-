package com.soarsky.car.server.check;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeAdapter;
import com.soarsky.car.Constants;
import com.soarsky.car.base.BaseService;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.bluetooth.BlueToothManage;
import com.soarsky.car.server.bluetooth.BlueToothScan;
import com.soarsky.car.server.bluetooth.BlueToothConnet;
import com.soarsky.car.server.dangerousdriving.PhoneStateManage;
import com.soarsky.car.server.design.ConfirmDriverSucessCallBack;
import com.soarsky.car.server.design.IAutoConfirmDriverListener;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.server.wifi.ConfirmDriver;
import com.soarsky.car.server.wifi.RideRecordThread;
import com.soarsky.car.server.wifi.Scan;
import com.soarsky.car.ui.callphone.IMonitorDrivingPhone;
import com.soarsky.car.ui.callphone.MonitorDrivingPhone;
import com.soarsky.car.ui.callphone.PermissionCheck;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.NetState;
import com.soarsky.car.uitl.TimeUtils;

import java.util.Date;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：手动连接车辆 开启扫描 监听网络<br>
 * 该类为 与终端通讯服务类<br>
 */



public class ConfirmDriverService extends BaseService implements IScan , IMonitorDrivingPhone {
    /**
     * 如果手机在该时间范围内，无法检测到车辆的行驶或者运动状态
     */
    public static final int CHECK_IS_DRIVER_TIME = 10 * 60 * 1000 ;
    /**
     * 检查驾驶员是否驾驶的频率
     */
    public static final int CHECK_DRIVER_TIME_FREQUENCY = 30 * 1000;
    private MonitorDrivingPhone monitorDrivingPhone;
    private ConfirmDriver confirmDriver;
    /**
     * 来电监听
     */
    private PhoneStateManage phoneStateManage;
    /**
     * 网络监听
     */
    private NetState netState;
    /**
     * 是否开启扫描
     */
    private boolean isScan=false;
    /**
     * 行车记录监听
     */
    private RideRecordThread rideRecordThread;
    private Context context;

    /**
     * 蓝牙服务适配器
     */
    private BluetoothIBridgeAdapter mAdapter;

    /**
     * 搜素类
     */
    private Scan IScan;


    private IBinder mBinder = new LocalBinder();
    private PowerManager.WakeLock mWakeLock;

    private BlueToothConnet buleToothConnet;

    /**
     * 蓝牙扫描类
     */
    private BlueToothScan blueToothScan;


    /***
     * 蓝牙与终端连接事件管理
     */
    private BlueToothManage blueToothManage;


    /**
     *
     */
    private IAutoConfirmDriverListener iAutoConfirmDriverListener;
    @Override
    public void onCreate() {
        LogUtil.i("onCreate");
        IScan=Scan.getInstance(this);
        IScan.startScan();
//        confirmDriver = new ConfirmDriver(this);
        rideRecordThread=new RideRecordThread(this);
        this.context=this;
//        confirmDriver.setConfirmDriverSucessCallBack(confirmDriverSucessCallBack);
        registerConectReceiver();
        initBuleTooth();

        new CarAppInitTask(this).start();



    }


    /**
     * 绑定服务会进这个方法
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();

    }


    @Override
    public void startScan() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 设置扫描监听
     * @param carScanedListener
     */
    @Override
    public void setCarScanedListener(OnCarScanedListener carScanedListener) {


        if (confirmDriver != null) {
            confirmDriver.setScanListenser(carScanedListener);
        }

        if(blueToothScan!=null){
            blueToothScan.setCarScanedListener(carScanedListener);
        }
        if(blueToothScan!=null){

        }

        if(IScan!=null){
            IScan.setCarScanedListener(carScanedListener);
        }

    }



    public void setMoveCarScanedListener(OnCarScanedListener carScanedListener) {
        if(blueToothScan!=null){
            blueToothScan.setMovecarScanedListener(carScanedListener);
        }
    }

    /**
     * 获得附近扫描的热点
     */
    @Override
    public List<Car> getScanedCarList() {
        return null;
    }

    /**
     * 开启开车玩手机监听
     */
    @Override
    public void startMonitor() {

        LogUtil.i("startMonitor");
        if(monitorDrivingPhone==null){

            monitorDrivingPhone = new MonitorDrivingPhone(this);
            monitorDrivingPhone.setConfirmDriverService(this);
            monitorDrivingPhone.startMonitor();
        }else {
            if(!monitorDrivingPhone.isMonitor()){
                monitorDrivingPhone.startMonitor();
            }
        }
    }


    /**
     * 停止开车玩手机监听
     */
    @Override
    public void stopMonitor() {
        if(monitorDrivingPhone!=null){
            if(monitorDrivingPhone.isMonitor()){
                monitorDrivingPhone.stopMonitor();
                monitorDrivingPhone = null;
            }
        }
    }

    @Override
    public boolean isMonitor() {
        if(monitorDrivingPhone!=null){
            return monitorDrivingPhone.isMonitor();
        }
        return false;
    }


    public class LocalBinder extends Binder {
        public ConfirmDriverService getService() {
            return ConfirmDriverService.this;
        }

        BluetoothIBridgeAdapter getBluetoothAdapter() {
            return mAdapter;
        }

    }

    /**
     * 解绑服务回调
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    /**
     * 重新绑定服务回调
     * @param intent
     * @return
     */
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    /**
     * 服务销毁回调
     */
    @Override
    public void onDestroy() {

        LogUtil.i("service  onDestroy");

        unregisteConectReceiver();
        if (confirmDriver != null) {
            confirmDriver.onDestroy();
            confirmDriver=null;
        }
        if(blueToothScan!=null){
            blueToothScan.destory();
            blueToothScan=null;
        }
        if(mAdapter!=null){
            mAdapter.destroy();
            mAdapter=null;
        }

        if(IScan!=null){
            IScan.onDestory();
        }
        if(buleToothConnet!=null){
            buleToothConnet.destory();
            buleToothConnet=null;
        }
        BlueToothManage.getInstance().destory();

        super.onDestroy();
    }

    /**
     * 开始连接工作
     */

    public void startWork() {
        LogUtil.i("开始确认驾驶员工作");
        if (confirmDriver != null) {
            if(!isScan){
                confirmDriver.startScan();
                isScan=true;
            }
        }
    }


    /**
     * 设置自动连接和手动连接
     * @param isAuto  true 自动  false  手动
     */

    public void setAuto(boolean isAuto) {
        if(isAuto){
            if(blueToothScan!=null){
                blueToothScan.startAtuo();
            }

        }else{
            if(blueToothScan!=null){
                blueToothScan.stopAuto();
            }

        }
    }

    /**
     * 连接车辆
     * @param car 车对象
     * @param type 申请类型
     */
    public void connectCar(Car car, int type) {

        if (confirmDriver != null) {
            confirmDriver.handConnectCar(car, type);
        }
    }

    /**
     * 设置音量
     */
    public void  setVolume(){
        blueToothManage.init(Constants.VOLUME_SET);
    }

    /**
     * 设置确认驾驶员监听
     *
     * @param connectListener OnConnectListener
     */
    public void setConnectListener(OnConnectListener connectListener) {

//        connectListener.onFailed("");
        if (confirmDriver != null) {
            confirmDriver.setConnectListener(connectListener);
        }
        if(blueToothManage!=null){
            blueToothManage.setConnectListener(connectListener);
        }
    }




    /**
     * 设置自动确认驾驶员监听
     * @param iAutoConfirmDriverListener IAutoConfirmDriverListener
     */

    public void setAutoConfirmDriverLisener(IAutoConfirmDriverListener iAutoConfirmDriverListener) {
        this.iAutoConfirmDriverListener=iAutoConfirmDriverListener;

        if (confirmDriver != null) {
            confirmDriver.setIAutoConfirmDriverListener(iAutoConfirmDriverListener);
        }

        if(blueToothManage!=null){
            blueToothManage.setServiceAutoConnectListener(iAutoConfirmDriverListener);
        }

    }

    /**
     * 注册网络监听广播
     */
    private void registerConectReceiver() {
        if (netState == null) {
            netState = new NetState(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netState, intentFilter);
        }
    }

    /**
     * 取消网络注册监听广播
     */
    private void unregisteConectReceiver() {
        if (netState != null) {
            try {
                unregisterReceiver(netState);
            } catch (Exception e) {
            }
        }
    }


    /**
     * 确认驾驶员回调
     */
     ConfirmDriverSucessCallBack confirmDriverSucessCallBack =new ConfirmDriverSucessCallBack(){
         /**
          * 确认驾驶员成功
          */
         @Override
         public void onSucess() {
             LogUtil.i("确认驾驶员成功了-----"+ TimeUtils.date2String(new Date()));
//             blueToothScan.checkDriverisLeave();
             if(PermissionCheck.HasACTION_USAGE_ACCESS_SETTINGSPermission(ConfirmDriverService.this)){
                 startMonitor();
             }
             Handler mainHandler = new Handler(Looper.getMainLooper());
             mainHandler.post(new Runnable() {
                 @Override
                 public void run() {
                     phoneStateManage=new PhoneStateManage(context.getApplicationContext(),ConfirmDriverService.this);
                     phoneStateManage.registerListener();
                 }
             });



         }

         @Override
         public void oncancle() {
             LogUtil.i("驾驶员离开-----"+ TimeUtils.date2String(new Date()));
             setAuto(true);
             iAutoConfirmDriverListener.onDriverOffLine();
             stopMonitor();
             if(phoneStateManage!=null){
                 phoneStateManage.unRegisterListener();
             }
         }
     };

    /**
     * 是否开始乘车登记
     * @param isRide    TRUE 开始 false 退出
     */
    public   void  startTrvelRecord(boolean isRide){
        if(rideRecordThread!=null){
            rideRecordThread.setRide(true);
        }
    }

    /**
     * 开始乘车报警
     * @param isAlarm TRUE 开始 false 退出
     */

    public  void  trvelAlarm(boolean isAlarm){
        if(rideRecordThread!=null){
            rideRecordThread.setPolice(isAlarm);
        }
    }


    /**
     * 初始化蓝牙模块
     */

    private void  initBuleTooth(){

        mAdapter = BluetoothIBridgeAdapter.sharedInstance(this);
        if(!mAdapter.isEnabled()){
            mAdapter.setEnabled(true);
        }
        if(Build.VERSION.SDK_INT >= 10){
            mAdapter.setLinkKeyNeedAuthenticated(false);
        }else{
            mAdapter.setLinkKeyNeedAuthenticated(true);
        }
        mAdapter.setAutoWritePincode(true);
        mAdapter.setPincode("1234");
        blueToothScan=BlueToothScan.getInstance(context);
        blueToothScan.setBluetoothAdapter(mAdapter);
        blueToothScan.setConfirmDriverSucessCallBack(confirmDriverSucessCallBack);
        blueToothScan.startScan();
        blueToothManage=BlueToothManage.getInstance();
        blueToothManage.setConfirmDriverSucessCallBack(confirmDriverSucessCallBack);
        buleToothConnet= BlueToothConnet.getInstance();
        buleToothConnet.setBluetoothAdapter(mAdapter);
        LogUtil.i("蓝牙初始化完成");

    }


    /**
     * 通过蓝牙发送数据
     * @param car  车辆
     * @param applType 申请类型
     */
    public   void  byBluetoothSendMeeage(Car car,int  applType){
        blueToothManage.setCar(car,BlueToothManage.CONNECTTYPE,applType);
    }


    /**
     * 清空扫描结果
     */
    public  void  clearDervice(){
        blueToothScan.clearDevices();
    }

    /**
     * 清空扫描结果
     */
    public  void  clearMoveCarDervice(){
        blueToothScan.clearMoveDevices();
    }



}
