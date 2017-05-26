package com.soarsky.installationmanual;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.bumptech.glide.Glide;
import com.soarsky.installationmanual.util.SpUtil;

import java.util.HashMap;
import java.util.Iterator;


/**
 * InstallationManual
 * 作者： 孙向锦
 * 时间： 2016/12/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * App 全局控制类，完成对应用程序的监控，数据库初始化操作等逻辑
 */
public class App extends Application {

    private static App app;
    public static final boolean ENCRYPTED = false;

   /* private DaoSession daoSession;
*/
    private Context mcontext;


    /**
     *     所有activity集合
     */
    private static HashMap<String, Activity> activityList	= new HashMap<String, Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SpUtil.init(this);
        String appName = getResources().getString(R.string.app_name);

       /* DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? appName + "-db-encrypted" : appName + "-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();*/

//        CrashHandler.getInstance().setCustomCrashHanler(getApplicationContext());

        Glide.with(this);
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
            Activity activity = activityList.get(keys.next());
            activity.finish();
        }
        System.exit(0);
    }

    public static App getApp() {
        return app;
    }


}
