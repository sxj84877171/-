package com.soarsky.car.ui.roadside;

import android.os.Handler;
import android.os.Message;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/3/10
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：时间倒计时
 * 该类为 RoadSideTimeUtil
 */


public class RoadSideTimeUtil {


    /**
     * 倒计时计算
     */
    private void computeTime(long mSecond,long mMin,long mHour,long mDay) {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {
                    // 倒计时结束
                    mHour = 23;
                    mDay--;
                }
            }
        }
    }

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private long mDay = 10;
    private long mHour = 10;
    private long mMin = 30;
    private long mSecond = 00;// 天 ,小时,分钟,秒
    private boolean isRun = true;
    private Handler timeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime(mSecond,mMin,mHour,mDay);

                if (mDay==0&&mHour==0&&mMin==0&&mSecond==0) {
//                    countDown.setVisibility(View.GONE);
                    isRun = false;
                }
            }
        }
    };


}
