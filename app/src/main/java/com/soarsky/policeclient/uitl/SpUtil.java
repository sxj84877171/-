package com.soarsky.policeclient.uitl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/10/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 *  少量信息保存的工具类<br>
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

}
