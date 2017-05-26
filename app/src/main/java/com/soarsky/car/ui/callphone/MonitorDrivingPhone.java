package com.soarsky.car.ui.callphone;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.soarsky.car.App;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.uitl.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Andriod_Car_App
 * 作者： 魏凯
 * 时间： 2016/12/26
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 开车玩手机监听
 */

public class MonitorDrivingPhone extends HandlerThread implements IMonitorDrivingPhone {
    public static final String TAG = MonitorDrivingPhone.class.getSimpleName();
    /**
     * 扫描时间
     */
    public static final long interval = 5 * 1000;
    private Handler handler;
    private Context context;
    private CurrScreenAppInfo currScreenAppInfo;
    private boolean isWork;
    private ConfirmDriverService confirmDriverService;
    private App app;
    private boolean can = true;
    public MonitorDrivingPhone(Context context) {
        super(TAG);
        this.context = context;
        app=App.getApp();
        currScreenAppInfo = new CurrScreenAppInfo();
    }


    @Override
    public void startMonitor() {
        start();
        isWork = true;
        handler = new Handler(getLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                monitor();
            }
        });
        LogUtil.i("开始正在玩手机监听");
    }


    private void monitor() {
        boolean isScreenLight = PowerManagerInfo.isScreenLight(context);
        if (isScreenLight) {
            String packageName = currScreenAppInfo.getCurrScreenPackage(context);
            if (packageName != null) {
                boolean isWhitePackage = BlackAndWhiteListSelection.isWhitePackage(packageName);
                if (!isWhitePackage) {
                    //给智能终端发送消息车主开车玩手机
                    ////TODO 给智能终端发送消息车主开车玩手机

                    if(confirmDriverService!=null && can&&app.isConfirmDriver()){
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                can = true;
                            }
                        },45*1000);
                        LogUtil.i("监听到正在玩手机");

                        Car car=new Car();
                        car.setCarNum(app.getCarNum());
                        car.setSsid(app.getSsID());
                        car.setDevice(app.getiBridgeDevice());
                        car.setDangerousType("03");
//                        confirmDriverService.connectCar(car,13);
                        confirmDriverService.byBluetoothSendMeeage(car,13);
                        can = false;

                    }

                }
            }
        }
        postDelayedInterval();
    }

    private void postDelayedInterval() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                monitor();
            }
        }, interval);
    }


    @Override
    public void stopMonitor() {
        isWork = false;
        quit();
    }

    @Override
    public boolean isMonitor() {
        return isWork;
    }

    private void startACTION_USAGE_ACCESS_SETTINGSPermissionActivity() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);    //通过尝试这个flag符合
        context.startActivity(intent);
    }


    public ConfirmDriverService getConfirmDriverService() {
        return confirmDriverService;
    }

    public void setConfirmDriverService(ConfirmDriverService confirmDriverService) {
        this.confirmDriverService = confirmDriverService;
    }
}
