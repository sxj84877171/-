package com.soarsky.car.uitl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.soarsky.car.Constants;

/**
 * 车主APP<br>
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
    private Context mContext ;
    public static final String SUFFIX = "_use" ;

    private SpUtil(){

    }


    public static void savePublicKey(Context context,String key,String value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key,value).commit();
    }

    public static String getPublicKey(Context context,String key){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key,"");
    }


    public static void saveUserName(Context context,String userName){
        savePublicKey(context,Constants.USERNAME,userName);
    }

    public static String getUserName(Context context){
        return getPublicKey(context,Constants.USERNAME);
    }



    public static void init(Context context) {
        String username = SpUtil.getUserName(context);
        prefs = context.getSharedPreferences(username+ SUFFIX,Context.MODE_PRIVATE);
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

    public static void setBoolean(Context context,String key,boolean f){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key,f).commit();
    }

    public static String get(String key){
        return prefs.getString(key,"");
    }

    public static boolean getBoolean(Context context,String key){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key,true);
    }

    public static boolean getBoolean(String key){
        return prefs.getBoolean(key,true);
    }

    public static void clear(){
        prefs.edit().clear().commit();
    }
}
