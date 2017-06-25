package com.sxj.carloan;

import android.app.Application;

import com.sxj.carloan.bean.LoginInfo;

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
}
