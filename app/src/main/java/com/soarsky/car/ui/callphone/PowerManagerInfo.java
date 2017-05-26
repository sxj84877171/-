package com.soarsky.car.ui.callphone;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by 魏凯 on 2016/11/30.
 */

public class PowerManagerInfo {
    public static boolean isScreenLight(Context context){
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }
}
