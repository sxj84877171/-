package com.soarsky.policeclient.server.biz;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;
import com.soarsky.policeclient.ui.blacklist.BlackListModel;
import com.soarsky.policeclient.ui.details.DetailsActivity;
import com.soarsky.policeclient.uitl.VibratorUtils;

import java.io.IOException;
import java.util.List;

import rx.Subscriber;


/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2017/1/12<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 黑名单比对<br>
 */

public class CompareToBlackList {
    /**
     * 比对黑名单
     */
    public static boolean compareToBlackList(final String ssid){
        boolean isBlack = false;
        BlackListModel blackListModel = new BlackListModel();
        blackListModel.setContext();
        List<BlackCar> blackCars = blackListModel.getBlackCarQuery1();
        for (BlackCar blackCar:blackCars) {
            if(blackCar.getCarnum().equals(ssid)){
                isBlack = true;
                showNotification(ssid);
                break;
            }
        }
        return isBlack;
    }

    /**
     * 显示Notification
     */
    public static void showNotification(String ssid){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(App.getApp().getApplicationContext()).setSmallIcon(R.mipmap.add).setContentTitle("黑名单车辆:").setContentText(ssid);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(App.getApp().getApplicationContext(), DetailsActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Vibrator vibrator = VibratorUtils.getInstance(App.getApp().getApplicationContext());
        resultIntent.putExtra("carNum",ssid);
// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack for the Intent (but not the Intent itself)
        //stackBuilder.addParentStack(DetailsActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        //stackBuilder.addNextIntent(resultIntent);
        //PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(App.getApp().getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) App.getApp().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        Notification notification = mBuilder.build();

        //notification.defaults|= Notification.DEFAULT_VIBRATE;
        mNotificationManager.notify(ssid.hashCode(), notification);
        //vibrator.vibrate(new long[] { 0, 1000, 500 }, 0);
        vibrator.vibrate(5000);
    }

}
