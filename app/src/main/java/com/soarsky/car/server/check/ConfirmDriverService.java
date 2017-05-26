package com.soarsky.car.server.check;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.IBinder;

import com.soarsky.car.base.BaseService;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.design.ConfirmDriverSucessCallBack;
import com.soarsky.car.server.design.IAutoConfirmDriverListener;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.server.wifi.ConfirmDriver;
import com.soarsky.car.ui.callphone.IMonitorDrivingPhone;
import com.soarsky.car.ui.callphone.MonitorDrivingPhone;
import com.soarsky.car.ui.callphone.OnMonitorDrivingResponseListener;
import com.soarsky.car.ui.callphone.PermissionCheck;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.NetState;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：手动连接车辆 开启扫描 监听网络
 * 该类为 与终端通讯服务类
 */



public class ConfirmDriverService extends BaseService implements IScan , IMonitorDrivingPhone {
    private MonitorDrivingPhone monitorDrivingPhone;
    private ConfirmDriver confirmDriver;

    private NetState netState;


    /**
     * 服务创建
     */
    @Override
    public void onCreate() {

        confirmDriver = new ConfirmDriver(this);
        confirmDriver.setConfirmDriverSucessCallBack(confirmDriverSucessCallBack);
        registerConectReceiver();
    }


    /**
     * 绑定服务会进这个方法
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.i("onBind");
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
        if(monitorDrivingPhone==null){
            monitorDrivingPhone = new MonitorDrivingPhone(this);
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
    }

    /**
     * 解绑服务回调
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.i("onUnbind");
//        setAuto(true);
        return super.onUnbind(intent);
    }


    /**
     * 重新绑定服务回调
     * @param intent
     * @return
     */
    @Override
    public void onRebind(Intent intent) {
        LogUtil.i("onRebind");

        super.onRebind(intent);
    }

    /**
     * 服务销毁回调
     */
    @Override
    public void onDestroy() {
        unregisteConectReceiver();
        super.onDestroy();
    }

    /**
     * 开始连接工作
     */

    public void startWork() {
        LogUtil.i("开始确认驾驶员工作");


        if (confirmDriver != null) {
            confirmDriver.getCarList();
        }
    }


    /**
     * 设置自动连接和手动连接
     * true 自动  false  手动
     */

    public void setAuto(boolean isAuto) {
        if (confirmDriver != null) {

            confirmDriver.setAuto(isAuto);
        }
    }


    /**
     * 连接车辆
     *
     * @param car
     */

    public void connectCar(Car car, int type) {
        if (confirmDriver != null) {
            confirmDriver.handConnectCar(car, type);
        }
    }


    /**
     * 设置确认驾驶员监听
     *
     * @param connectListener
     */
    public void setConnectListener(OnConnectListener connectListener) {

        if (confirmDriver != null) {
            confirmDriver.setConnectListener(connectListener);
        }
    }

    /**
     * 设置自动确认驾驶员监听
     */

    public void setAutoConfirmDriverLisener(IAutoConfirmDriverListener iAutoConfirmDriverListener) {
        if (confirmDriver != null) {
            confirmDriver.setIAutoConfirmDriverListener(iAutoConfirmDriverListener);
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
             if(PermissionCheck.HasACTION_USAGE_ACCESS_SETTINGSPermission(ConfirmDriverService.this)){
                 startMonitor();
             }
         }

         @Override
         public void oncancle() {
             stopMonitor();
         }
     };



}
