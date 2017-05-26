package com.soarsky.policeclient.server.biz;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.ui.details.DetailsActivity;
import com.soarsky.policeclient.uitl.SpUtil;

import com.soarsky.policeclient.server.design.IScan;
import com.soarsky.policeclient.server.design.OnCarScanedListener;

import java.util.ArrayList;
import java.util.List;

import com.soarsky.policeclient.server.bluetooth.*;

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
 * 该类为 扫描结果算法实现类<br>
 */

public class Scan implements IScan {
    /**
     * 保存扫描结果车辆列表
     */
    private static List<Car> carList = new ArrayList<>();
    /**
     * 扫描完成的监听
     */
    private OnCarScanedListener carScanedListener;

    private Blue.OnBlueScan onBlueScan;

    private Blue.OnBlueScanFinish onBlueScanFinish = new Blue.OnBlueScanFinish() {
        @Override
        public void onBlueScanFinish() {
            Blue.getInstance(App.getApp().getApplicationContext()).startDiscovery();
        }
    };

    @Override
    public void startScan() {
        Blue blue = Blue.getInstance(App.getApp().getApplicationContext());
        blue.setOnBlueScanFinish(onBlueScanFinish);
        blue.setOnBlueScan(onBlueScan);
        blue.startDiscovery();
    }


    @Override
    public void setOnBlueScan(Blue.OnBlueScan onBlueScan) {
        this.onBlueScan = onBlueScan;
    }

    @Override
    public void stopScan() {
        Blue.getInstance(App.getApp().getApplicationContext()).stopDiscovery();
    }

    @Override
    public void addCar(Car car) {
        if (!contains(carList, car)) {
            String checkCars = SpUtil.get(Constants.CHECK_CAR);
            String[] checkCarsArray = checkCars.split(",");
            boolean has = false;
            for (String s : checkCarsArray) {
                if (car.getCarNum().equals(s)) {
                    has = true;
                }
            }
            if (!has) {
                if (CompareToBlackList.compareToBlackList(car.getCarNum())) {
                    car.setBlack(true);
                    startAlarm();
                    Intent intent = new Intent(App.getApp().getApplicationContext(), DetailsActivity.class);
                    intent.putExtra("carNum", car.getCarNum());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    App.getApp().getApplicationContext().startActivity(intent);
                }
            }
            carList.add(0,car);
            if (carScanedListener != null) {
                carScanedListener.newCarScanned(carList);
            }
        }
    }

    //获取系统默认铃声的Uri
    private Uri getSystemDefultRingtoneUri(Context context) {
        return RingtoneManager.getActualDefaultRingtoneUri(context,
                RingtoneManager.TYPE_RINGTONE);
    }

    private void startAlarm() {

        try {
            MediaPlayer mMediaPlayer = MediaPlayer.create(App.getApp().getApplicationContext(), getSystemDefultRingtoneUri(App.getApp().getApplicationContext()));
            //mMediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mMediaPlayer.setLooping(false);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Car> getScanedCarList() {
        return carList;
    }

    @Override
    public void setCarScanedListener(OnCarScanedListener carScanedListener) {
        this.carScanedListener = carScanedListener;
    }


    /**
     * 是否包含
     *
     * @param cars
     * @param car
     * @return
     */
    private boolean contains(List<Car> cars, Car car) {
        for (Car aCar : cars) {
            if (aCar.getCarNum().equals(car.getCarNum())) {
                return true;
            }
        }
        return false;
    }
}
