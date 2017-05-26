package com.soarsky.car.ui.callphone;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 魏凯 on 2016/11/30.
 */

public class MonitorDrivingPhone extends HandlerThread implements IMonitorDrivingPhone {
    public static final String TAG = MonitorDrivingPhone.class.getSimpleName();

    public static final long interval = 5 * 1000;
    private Handler handler;
    private Context context;
    private CurrScreenAppInfo currScreenAppInfo;
    private boolean isWork;

    public MonitorDrivingPhone(Context context) {
        super(TAG);
        this.context = context;
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
    }


    private void monitor() {
        Log.e("MonitorDrivingPhone","monitor");
        boolean isScreenLight = PowerManagerInfo.isScreenLight(context);
        if (isScreenLight) {
            String packageName = currScreenAppInfo.getCurrScreenPackage(context);
            if (packageName != null) {
                Log.e("MonitorDrivingPhone","packageName != null");
                boolean isWhitePackage = BlackAndWhiteListSelection.isWhitePackage(packageName);
                if (!isWhitePackage) {
                    //给智能终端发送消息车主开车玩手机
                    ////TODO 给智能终端发送消息车主开车玩手机
                    Log.e("MonitorDrivingPhone","!isWhitePackage");
                    Toast.makeText(context,"请不要开车玩手机",Toast.LENGTH_SHORT).show();

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

}
