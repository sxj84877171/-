package com.soarsky.policeclient.base;

import android.content.Context;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.uitl.LogUtil;

/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/12/6<br>
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

    /**
     * 单例模式
     */
    private static CrashHandler instance = new CrashHandler();
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private Context mContext;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return instance;
    }

    /**
     * 设置上下文句柄
     * @param context 上下文句柄
     */
    public void setCustomCrashHanler(Context context) {
        mContext = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //崩溃时将catch住异常
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 崩溃时触发
     * @param thread 线程
     * @param ex 奔溃信息
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtil.e(thread.getId() + thread.getName());
        LogUtil.e("Crash:");
        LogUtil.e(ex);
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, ex);
        }
        App.getApp().exit();
    }

}