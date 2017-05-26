package com.soarsky.policeclient.server;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.IBinder;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.server.biz.Check;
import com.soarsky.policeclient.server.biz.ElTicket;
import com.soarsky.policeclient.server.bluetooth.Blue;
import com.soarsky.policeclient.server.design.ICheck;
import com.soarsky.policeclient.server.design.IElTicket;
import com.soarsky.policeclient.server.design.IScan;
import com.soarsky.policeclient.server.design.OnCarScanedListener;
import com.soarsky.policeclient.server.design.OnConnectListener;
import com.soarsky.policeclient.server.design.OnMessageResponseListener;
import com.soarsky.policeclient.ui.accident.add.AccidentHandlerThread;
import com.soarsky.policeclient.ui.accident.add.AddAccidentPresent;
import com.soarsky.policeclient.ui.accident.add.IAccident;
import com.soarsky.policeclient.ui.accident.add.OnAccidentMessageResponseListener;
import com.soarsky.policeclient.ui.blacklist.BlackListFromServer;
import com.soarsky.policeclient.ui.home.HomeActivity;
import com.soarsky.policeclient.ui.violation.ViolationLicenseParam;
import com.soarsky.policeclient.uitl.NetState;

import java.util.ArrayList;
import java.util.List;

/**
 * 警务通
 * 作者： 魏凯
 * 时间： 2016/11/3
 * 公司：长沙硕铠电子科技有限公司
 * Email：weikai@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 应用服务类
 */
public class CheckService extends Service implements ICheck,IScan,IElTicket,IAccident {
    public CheckService() {
    }

    /**
     * 稽查功能
     */
    private Check mCheck ;
    /**
     * 开电子罚单功能
     */
    private IElTicket elTicket;
    /**
     * 网络状态监听
     */
    private NetState netState=new NetState(this);
    /**
     * 从服务器获取黑名单功能
     */
    private BlackListFromServer blackListFromServer;
    /**
     * 事故分析上传
     */
    private AccidentHandlerThread accidentHandlerThread;
    /**
     * 是否在稽查
     */
    private boolean isChecking;


    @Override
    public IBinder onBind(Intent intent) {
        
        return new LocalBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void openElTicket(ViolationLicenseParam violationLicenseParam) {
        elTicket.openElTicket(violationLicenseParam);
    }

    @Override
    public void connect(Car car) {
        elTicket.connect(car);
    }

    public void setOnConnectListener(OnConnectListener onConnectListener) {
        elTicket.setOnConnectListener(onConnectListener);
    }

    @Override
    public void setOnResponseListener(OnMessageResponseListener onResponseListener) {
        elTicket.setOnResponseListener(onResponseListener);
    }


    @Override
    public void startAccident(ArrayList<Car> selectCar,OnAccidentMessageResponseListener onMessageResponseListener) {
        if(accidentHandlerThread == null){
            accidentHandlerThread = new AccidentHandlerThread(this);
            accidentHandlerThread.setOnAccidentMessageResponseListener(onMessageResponseListener);
            accidentHandlerThread.startAccident(selectCar,onMessageResponseListener);
        }else {
            if(!accidentHandlerThread.isAccident()){
                accidentHandlerThread = new AccidentHandlerThread(this);
                accidentHandlerThread.setOnAccidentMessageResponseListener(onMessageResponseListener);
                accidentHandlerThread.startAccident(selectCar,onMessageResponseListener);
            }else {
                accidentHandlerThread.accident();
                accidentHandlerThread.setOnAccidentMessageResponseListener(onMessageResponseListener);
            }
        }

    }

    @Override
    public void stopAccident() {
        accidentHandlerThread.stopAccident();

    }

    @Override
    public boolean isAccident() {
        return accidentHandlerThread.isAccident();
    }

    @Override
    public void setOnAccidentMessageResponseListener(OnAccidentMessageResponseListener onMessageResponseListener) {
        accidentHandlerThread.setOnAccidentMessageResponseListener(onMessageResponseListener);
    }

    public class LocalBinder extends Binder{

        public CheckService getService(){
            return CheckService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerConectReceiver();
        mCheck = new Check(this);
        elTicket = new ElTicket(this);
        blackListFromServer = new BlackListFromServer(this);
        blackListFromServer.startGetBlackListFromServer();
        new AddAccidentPresent().getReasonList();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopCheck(true);
        unregisteConectReceiver();
        Blue.getInstance(this).destroy();
        super.onDestroy();
    }

    @Override
    public void startCheck(boolean changeName) {
        // 点击稽查主动同步一次黑名单 add by 孙向锦
            blackListFromServer.syncBlackCar();
        if(!isChecking){
            isChecking = true;
            mCheck = new Check(this);
            startForground();
            mCheck.startCheck(changeName);
        }
    }

    /**
     * 开启前台服务
     */
    private void startForground(){
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentText("正在稽查");
        builder.setContentTitle("警务通");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Intent in = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, in, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        startForeground(10000, notification);
    }

    @Override
    public void stopCheck(boolean changeName) {
        if(isChecking){
            isChecking = false;
            stopForeground(true);
            mCheck.stopCheck(changeName);
        }
    }

    @Override
    public boolean isChecking() {
        return isChecking;
    }

    @Override
    public void startScan() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void stopScan() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void setCarScanedListener(OnCarScanedListener carScanedListener) {
        mCheck.setCarScanedListener(carScanedListener);
    }

    @Override
    public List<Car> getScanedCarList() {
        return mCheck.getScanedCarList();
    }

    @Override
    public void addCar(Car car) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void setOnBlueScan(Blue.OnBlueScan onBlueScan) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void registerConectReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netState, intentFilter);

    }


    private void unregisteConectReceiver(){
        try {
            unregisterReceiver(netState);
        }catch (Exception e){

        }

    }




}
