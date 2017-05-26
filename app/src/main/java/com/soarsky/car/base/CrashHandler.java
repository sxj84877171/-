package com.soarsky.car.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.uitl.LogUtil;

/**
 * 车主APP
 * 作者： 孙向锦
 * 时间： 2016/11/16
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 添加对程序异常的处理，全局捕获程序crash，
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance = new CrashHandler();
    private Context mContext;

    private CrashHandler() {}

    public static CrashHandler getInstance() {
        return instance;
    }

    public void setCustomCrashHanler(Context context) {
        mContext = context;
        //崩溃时将catch住异常
        Thread.setDefaultUncaughtExceptionHandler(this);

    }

    /**
     * 崩溃时触发
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        LogUtil.e(thread.getId() + thread.getName());
        LogUtil.e(ex);
//        App.getApp().exit();
        if (!handleException(ex) && Thread.getDefaultUncaughtExceptionHandler() != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread,ex);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            App.getApp().exit();
        }


    }


    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     *
     * @param ex
     * @return true：如果处理了该异常信息；否则返回 false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 使用 Toast 来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, mContext.getString(R.string.uncaughtmsg), Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        return true;
    }


    /**
     * 程序崩溃显示dialog
     */
    public void uncaughtDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setMessage(mContext.getString(R.string.uncaughtmsg));
        builder.setTitle(mContext.getString(R.string.uncaughttip));
        builder.setPositiveButton(mContext.getString(R.string.uncaughtsure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                App.getApp().exit();
            }
        });
       /* builder.setNegativeButton(mContext.getString(R.string.uncaughtcancle), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });*/
        builder.create().show();
    }




}