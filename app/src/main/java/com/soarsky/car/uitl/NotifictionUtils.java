package com.soarsky.car.uitl;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

import com.soarsky.car.App;
import com.soarsky.car.R;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/5/15
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class NotifictionUtils {
        private  int  notifictionID=101;
    private  Context context;

      //懒汉式单例类.在第一次调用的时候实例化自己
          private NotifictionUtils() {
              context= App.getApp();

          }
          private static NotifictionUtils single=null;
          //静态工厂方法
          public static NotifictionUtils getInstance() {
               if (single == null) {
                   single = new NotifictionUtils();
               }
              return single;
          }


    public  void showNotifiction( String message) {
        notifictionID++;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setAutoCancel(true);//点击后消失
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知栏消息标题的头像
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);//设置通知铃声
        builder.setTicker("提示");
        builder.setContentText(message);//通知内容
        builder.setContentTitle("伴君行");
        //利用PendingIntent来包装我们的intent对象,使其延迟跳转

        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(notifictionID, builder.build());
    }

}
