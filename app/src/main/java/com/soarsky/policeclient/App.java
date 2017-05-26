package com.soarsky.policeclient;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;


import com.soarsky.policeclient.base.CrashHandler;
import com.soarsky.policeclient.data.local.db.dao.DaoMaster;
import com.soarsky.policeclient.data.local.db.dao.DaoSession;
import com.soarsky.policeclient.server.bluetooth.Blue;
import com.soarsky.policeclient.uitl.SpUtil;

import org.greenrobot.greendao.database.Database;

import java.util.HashMap;
import java.util.Iterator;


/**
 * 警务通
 * 作者： 孙向锦
 * 时间： 2016/11/3
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 应用程序类，整个Application 负责全局变量控制。数据库句柄控制，crash控制等相关信息控制类
 */
public class App extends Application {

    private static App app;
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    private String userName;

    private String imageUri ="";

    //所有activity集合
    private static HashMap<String, Activity> activityList	= new HashMap<String, Activity>();


    @Override
    public void onCreate() {
        super.onCreate();
        app  = this;
        SpUtil.init(this);
        this.userName = SpUtil.get(Constants.CONS_USERNAME);
        String appName = getResources().getString(R.string.app_name);
        Blue.getInstance(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? appName + "-db-encrypted" : appName + "-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        CrashHandler.getInstance().setCustomCrashHanler(this);
    }


    /**
     *  增加Activity
     */
    public void addActivity(String key,Activity activity)
    {
        Activity tactivity = activityList.get(key);
        if(tactivity == null)
        {
            activityList.put(key, activity);
        }else
        {
            tactivity.finish();
            activityList.put(key, activity);
        }
    }

    /**
     * 退出系统时，结束掉所有Activity
     */
    public void exit()
    {
        Iterator<String> keys = activityList.keySet().iterator();
        while(keys.hasNext())
        {
            activityList.get(keys.next()).finish();
        }
        System.exit(0);
    }

    /**
     * 获取APP实例
     * @return
     */
    public static App getApp() {
        return app;
    }

    /**
     * 获取数据库操作句柄
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }


    /**
     * 获取用户名
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
