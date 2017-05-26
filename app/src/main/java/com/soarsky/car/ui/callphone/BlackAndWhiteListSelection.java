package com.soarsky.car.ui.callphone;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import com.soarsky.car.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 魏凯 on 2016/11/30.
 */

public class BlackAndWhiteListSelection {
    public static final List<String> blackList = new ArrayList<>();
    public static final List<String> whiteList = new ArrayList<>();
    public static final String carAppPackage = "com.soarsky.car";
    public static final String baiduMap = "com.baidu.BaiduMap";
    public static final String qq = "com.tencent.mobileqq";
    public static final String taobao = "com.taobao.taobao";
    public static final String weixin = "com.tencent.mm";


    static {
        whiteList.add(carAppPackage);
        whiteList.add(baiduMap);
        String launcherPackage = getLauncherPackageName(App.getApp().getApplicationContext());
        whiteList.add(launcherPackage);
    }
    static {
        blackList.add(qq);
        blackList.add(taobao);
        blackList.add(weixin);
    }

    public static boolean isWhitePackage(String currPackage){
        if(whiteList.contains(currPackage) || !blackList.contains(currPackage)){
            return true;
        }else {
            return false;
        }
    }


    public static String getLauncherPackageName(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo res = context.getPackageManager().resolveActivity(intent, 0);
        if (res.activityInfo == null) {
            // should not happen. A home is always installed, isn't it?
            return null;
        }
        if (res.activityInfo.packageName.equals("android")) {
            // 有多个桌面程序存在，且未指定默认项时；
            return null;
        } else {
        return res.activityInfo.packageName;
        }
    }

}
