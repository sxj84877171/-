package com.sxj.carloan;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.sxj.carloan.bean.LoginInfo;
import com.sxj.carloan.rongyi.RecordService;
import com.sxj.carloan.util.CrashHandler;

/**
 * Created by admin on 2017/6/25.
 */

public class App extends Application {

    private LoginInfo loginInfo;

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    private static App application;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 启动 Marvel service
        CrashHandler.getInstance().init();
        startService(new Intent(this, RecordService.class));
    }

    public static App getInstance() {
        return application;
    }

}
