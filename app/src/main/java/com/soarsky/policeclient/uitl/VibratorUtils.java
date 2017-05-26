package com.soarsky.policeclient.uitl;

import android.content.Context;
import android.os.Vibrator;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2017/1/14<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为震动单例类<br>
 */
public class VibratorUtils {
    private static Vibrator vibrator;
    private VibratorUtils(){

    }

    public static Vibrator getInstance(Context context){
        if(vibrator==null){
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }
        return vibrator;
    }
}
