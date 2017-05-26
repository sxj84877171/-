package com.soarsky.car.ui.callphone;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.provider.Settings;

import java.util.List;

/**
 * Created by 魏凯 on 2016/12/1.
 */

public class PermissionCheck {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean HasACTION_USAGE_ACCESS_SETTINGSPermission(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            int mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, applicationInfo.uid, applicationInfo.packageName);
            return (mode == AppOpsManager.MODE_ALLOWED);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    //判断是否又打开设置查看使用权的界面
    public static boolean HasACTION_USAGE_ACCESS_SETTINGSPermissionUI(Context context) {
        PackageManager packageManager = context.getApplicationContext()
                .getPackageManager();
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     *    返回值0代表有权限1代表没有界面2代表有界面没有授权
      */

    public static int checkACTION_USAGE_ACCESS_SETTINGSPermission(Context context){
        if(HasACTION_USAGE_ACCESS_SETTINGSPermission(context)){
            return 0;
        }else {
            if(!HasACTION_USAGE_ACCESS_SETTINGSPermissionUI(context)){
                return 1;
            }else {
                return 2;
            }
        }
    }


}
