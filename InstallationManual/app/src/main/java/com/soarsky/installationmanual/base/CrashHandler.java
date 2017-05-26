package com.soarsky.installationmanual.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.widget.Toast;

import com.soarsky.installationmanual.App;
import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.util.LogUtil;


/**
 * 安装手册<br>
 * 作者： 孙向锦<br>
 * 时间： 2017/02/09<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 添加对程序异常的处理，全局捕获程序crash，<br>
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
//                Toast.makeText(mContext, mContext.getString(R.string.uncaughtmsg), Toast.LENGTH_LONG).show();
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

//        builder.setMessage(mContext.getString(R.string.uncaughtmsg));
//        builder.setTitle(mContext.getString(R.string.uncaughttip));
//        builder.setPositiveButton(mContext.getString(R.string.uncaughtsure), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//                App.getApp().exit();
//            }
//        });
       /* builder.setNegativeButton(mContext.getString(R.string.uncaughtcancle), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });*/
        builder.create().show();
    }




}