package com.soarsky.car.ui.trivelrecord;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TravelRecords;
import com.soarsky.car.data.remote.server1.ApiM;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.trivelrecord.alarm.AlarmActivity;
import com.soarsky.car.ui.trivelrecord.blacklist.BlackListActivity;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.TimeUtils;

import java.util.Date;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/2/22<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 行车记录数据上传类
 */


public class RideManager {



    private App app;
    public RideManager(){
        app=App.getApp();
        SpUtil.init(app);

    }

    /**
     *
     * @param ptype 号牌类型 0-3
     * @param carnum 车牌号
     * @param flag 0乘车 1离车
     */

    public  void  uploadCarRecord(final int ptype, String  carnum, int flag){
        String  stime= TimeUtils.date2String(new Date());
        ApiM.getInstance().service.uploadCarRecord(SpUtil.get(Constants.USERNAME),stime,ptype+"",carnum,flag+"")
                .compose(RxSchedulers.<ResponseDataBean<TravelRecords>>io_main()).subscribe(new Subscriber<ResponseDataBean<TravelRecords>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseDataBean<TravelRecords> travelRecordsResponseDataBean) {
                if(travelRecordsResponseDataBean.getData().getIsBlack()==1&&ptype==0){
//                    showBlackListNotifiction(App.getApp());

                    Intent intent = new Intent(App.getApp(), BlackListActivity.class);//将要跳转的界面
                    intent.putExtra("traverlRecords",travelRecordsResponseDataBean.getData());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    App.getApp().startActivity(intent);
                }
                LogUtil.i(travelRecordsResponseDataBean.toJson());

            }
        });


    }


    /**
     * 上传位置星系
     */

    public  void  uploadCarAlarm(){
        String  stime= TimeUtils.date2String(new Date());
        ApiM.getInstance().service.uploadCarAlarm(SpUtil.get(Constants.USERNAME),stime,"","",stime,app.getLongitude()+"",app.getLatitude()+"").
                compose(RxSchedulers.<ResponseDataBean>io_main()).subscribe(new Subscriber<ResponseDataBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseDataBean responseDataBean) {

                LogUtil.i(responseDataBean.toJson());

            }
        });

    }


    /**
     *     黑名单通知栏
     */

    public static void showBlackListNotifiction(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent intent = new Intent(context, BlackListActivity.class);//将要跳转的界面
        builder.setAutoCancel(true);//点击后消失
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知栏消息标题的头像
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);//设置通知铃声
        builder.setTicker("警告");
        builder.setContentText("当前车辆是黑名单车辆，请尽快下车！");//通知内容
        builder.setContentTitle("伴君行");
        //利用PendingIntent来包装我们的intent对象,使其延迟跳转
        PendingIntent intentPend = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(intentPend);
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    /**
     *     无终端通知栏
     */

    public static void showAlramNotifiction(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent intent = new Intent(context, AlarmActivity.class);//将要跳转的界面
        builder.setAutoCancel(true);//点击后消失
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知栏消息标题的头像
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);//设置通知铃声
        builder.setTicker("警告");
        builder.setContentText("您可能乘坐的是无牌照非法车辆");//通知内容
        builder.setContentTitle("伴君行");
        //利用PendingIntent来包装我们的intent对象,使其延迟跳转
        PendingIntent intentPend = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(intentPend);
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }


}
