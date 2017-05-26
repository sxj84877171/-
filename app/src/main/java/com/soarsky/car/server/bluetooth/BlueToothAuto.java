package com.soarsky.car.server.bluetooth;

import android.os.Handler;
import android.os.HandlerThread;

import com.soarsky.car.App;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.server.design.IConnectListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.uitl.ConfirmDriverUtils;
import com.soarsky.car.uitl.LogUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/3/27
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：蓝牙自动确认驾驶员
 * 该类为
 */


public class BlueToothAuto extends HandlerThread {
    public static final String TAG = BlueToothAuto.class.getSimpleName();
    /**
     * 要自动确认的车辆集合
     */
    private List<Car> carList = new ArrayList<>();

    private Handler handler;

    private int index = 0;
    private int connetIndex = 0;

    private BlueToothManage blueToothManage = BlueToothManage.getInstance();

    public BlueToothAuto() {
        super(TAG, Thread.NORM_PRIORITY);
        blueToothManage.setAutoConnectListener(connectListener);
        LogUtil.i("-----------------------"+carList.size());
        start();
        handler = new Handler(getLooper());
        startConnet();

    }


    /**
     * 新扫描的车辆
     *
     * @param car
     */
    public void addCar(Car car) {
        synchronized (carList) {
            carList.add(car);
        }

    }


    /**
     * 开始
     */

    private void startConnet() {
        LogUtil.i("开始自动确认驾驶员"+Thread.currentThread().getId());
        //已经确认驾驶员
        if (App.getApp().isConfirmDriver()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startConnet();
                    connetIndex=0;
                }
            }, 5000);
            return;
        }

        if (carList.size() == 0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startConnet();
                }
            }, 5000);

            return;
        }
        index = carList.size();

        connet(0);

    }


    /**
     * 连接
     */
    private void connet(int index) {

        if (App.getApp().isConfirmDriver()) {
            return;
        }
        if(carList.size()>index){

        Car car = carList.get(index);
        switch (ConfirmDriverUtils.getConnectType(car)) {
            case 0:
                blueToothManage.setCar(car, BlueToothManage.ATUOCONNETTYPE, 1);
                break;
            case 1:
                blueToothManage.setCar(car, BlueToothManage.ATUOCONNETTYPE, 1);
                break;
            case 2:
                car.setAuthNum(ConfirmDriverUtils.getAuthCode(car.getCarNum()));
                blueToothManage.setCar(car, BlueToothManage.ATUOCONNETTYPE, 2);
                break;
            case 3:

                break;

        }

         handler.postDelayed(runnable,45*1000);

        }else{
            startConnet();
        }
    }


    /**
     * 判断一次连接是否完成
     */

    IConnectListener connectListener =new  IConnectListener(){
        @Override
        public void finsh() {
            handler.removeCallbacks(runnable);
            connetIndex++;
            if (connetIndex < index) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        connet(connetIndex);
                    }
                }, 5000);


            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startConnet();
                        connetIndex=0;
                    }
                }, 5000);

            }
        }
    };





    /**
     * 退出自动确认驾驶员
     */
    public void onDestory() {
        LogUtil.i("自动确认关闭");
        blueToothManage.setAutoConnectListener(null);
        getLooper().quit();

    }


    /**
     * 给终端发送自动确认驾驶员请求 终端超过45秒未回复 直接跳过
     */
    Runnable   runnable=new Runnable() {
        @Override
        public void run() {
            connetIndex++;
            if (connetIndex < index) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        connet(connetIndex);
                    }
                }, 5000);


            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startConnet();
                        connetIndex=0;
                    }
                }, 5000);

            }
        }
    };



}
