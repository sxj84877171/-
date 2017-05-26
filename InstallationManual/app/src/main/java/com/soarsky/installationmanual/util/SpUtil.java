package com.soarsky.installationmanual.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 安装手册
 * 作者： 孙向锦
 * 时间： 2017/02/09
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 *  少量信息保存的工具类
 */
public class SpUtil {
    static SharedPreferences prefs;

    public static void init(Context context) {
        if(prefs == null)
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).commit();
    }

    public static void save(String key,String value){
        prefs.edit().putString(key,value).commit();
    }

    public static String get(String key){
        return prefs.getString(key,"");
    }
    public static boolean getBoolean(String key){
        return prefs.getBoolean(key,true);
    }

    public static void clear(){
        prefs.edit().clear().commit();
    }
}
