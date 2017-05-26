package com.soarsky.car.server.wifi;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.base.Configure;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.RideRecordSacn;
import com.soarsky.car.ui.trivelrecord.RideManager;
import com.soarsky.car.ui.trivelrecord.alarm.AlarmActivity;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/2/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 乘车记录<br>
 */


public class RideRecordThread extends HandlerThread {
    private static final String TAG = RideRecordThread.class.getSimpleName();
    private Context context;
    private Handler hander;
    /**
     * 乘客搭乘车辆的carNum
     */
    private String carNum;
    /**
     * 是否已经乘车
     */
    private boolean isRidting = false;

    private List<Car> carList = new ArrayList<>();
    private List<RideRecordSacn> baseList = new ArrayList<>();
    private List<RideRecordSacn> tempList = new ArrayList<>();
    private List<RideRecordSacn> latestList = new ArrayList<>();
    /**
     * 已经提示过的车辆
     */
    private List<RideRecordSacn> hasshowList = new ArrayList<>();

    private Scan scan;

    /**
     * 是否是报警状态
     */
    private boolean isPolice = false;

    /**
     * 未获取到智能终端的次数
     */
    private int index = 1;

    /**
     * 是否手动操作报警
     *
     * @param context
     */
    private boolean ishand = false;


    private RideManager rideManager;


    /**
     * 是否在确认乘车的时间范围内
     */
    private boolean isConfirmCar = true;


    /**
     * 确认乘车后未扫描到终端的次数
     */
    private int noCarCount = 0;

    /**
     * 是否正在工作
     */
    private boolean isWorking=false;


    public RideRecordThread(Context context) {
        super(TAG, Thread.NORM_PRIORITY);
        this.context = context;
        scan = Scan.getInstance(context);
        rideManager = new RideManager();
        SpUtil.init(context);
        init();


    }

    private void init() {
        start();
        hander = new Handler(getLooper());
        LogUtil.i("乘车记录————————");
        checkStartWork();
        checkWork();




    }


    /**
     * 循环检测是否进入保护时间
     */

    private  void  checkWork(){

        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkStartWork();
                checkWork();
            }
        },Configure.CHECKUPTIME);

    }


    /**
     * 是否自动开启工作
     */


    public void checkStartWork(){
        if(checkUpTime()){
            if(!isWorking){
                setRide(true);
            }
        }else{
            if(isWorking&&isRidting){
                setRide(false);
            }
        }
    }




    /**
     * 获取扫描结果
     */


    private void getSacnResultDelayed() {
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                toRideRecordList();
            }
        }, Configure.SACN_TIME);
    }




    private synchronized  void toRideRecordListTest(){

        LogUtil.i("乘车记录————————");
        //超过5min中还没有确认乘车 进入报警流程
        if (!isConfirmCar) {
            return;
        }

        if (confirmTimer != null) {
            confirmTimer.start();
        }

        carList = scan.getCarList();
        if (carList == null || carList.size() == 0) {
            index++;
            LogUtil.i("index：" + index);
            if (index > 3) {
                  // 如果用户已经上车 提示下车
                if(isRidting){
                    rideManager.uploadCarRecord(1, carNum, 1);
                    isRidting=false;

                }



            }
        } else {
            index = 1;

            if (baseList.size() == 0) {
                for (Car car : carList) {
                    RideRecordSacn rideRecordSacn = new RideRecordSacn();
                    rideRecordSacn.setSsId(car.getSsid());
                    rideRecordSacn.setCarNum(car.getCarNum());
                    rideRecordSacn.setCount(1);
                    baseList.add(rideRecordSacn);
                }
            } else {
                for (Car car : carList) {
                    RideRecordSacn rideRecordSacn = new RideRecordSacn();
                    rideRecordSacn.setSsId(car.getSsid());
                    rideRecordSacn.setCarNum(car.getCarNum());
                    rideRecordSacn.setCount(1);
                    latestList.add(rideRecordSacn);
                }
                OK:
                for (RideRecordSacn rideRecordSacn : latestList
                        ) {
                    for (RideRecordSacn rideRecordSacn2 : baseList
                            ) {
                        if (rideRecordSacn2.getCarNum().equals(rideRecordSacn.getCarNum())) {
                            rideRecordSacn2.setCount(rideRecordSacn2.getCount() + 1);
                            tempList.add(rideRecordSacn2);
                            break OK;
                        }
                    }
                    tempList.add(rideRecordSacn);
                }

                baseList = tempList;
                tempList = new ArrayList<>();
                latestList = new ArrayList<>();
                int count = 0;
                for (RideRecordSacn rideRecordSacn : baseList) {
                    if (isRidting) {
                        if (rideRecordSacn.getCount() >= 10) {
                            // TODO: 2017/2/21 发送乘车记录 多辆车未做处理
                            count++;
                            carNum = rideRecordSacn.getCarNum();
                        }
                    }
                    LogUtil.i(rideRecordSacn.toJson());
                }
                if (count == 1) {
                    //有且仅有一辆车连续扫描到10次以上
                    LogUtil.i("上传乘车记录");
                    App.getApp().setTrivelCarNum(carNum);
                    rideManager.uploadCarRecord(1, carNum, 0);
                    baseList = new ArrayList<>();
                        isRidting=true;
                    return;
                }

            }

        }
        getSacnResultDelayed();

    }






    /**
     * 获取所乘坐的车辆
     * 当连续三次未获取到智能终端 提示黑车报警 连续10次获取终端有且只用一辆 确认上车不止一辆继续获取 知道只有一辆
     */
    private synchronized void toRideRecordList() {
        LogUtil.i("乘车记录————————");
        //超过5min中还没有确认乘车 进入报警流程
        if (!isConfirmCar) {
            return;
        }

        if (confirmTimer != null) {
            confirmTimer.start();
        }

        carList = scan.getCarList();
        if (carList == null || carList.size() == 0) {
            index++;
            LogUtil.i("index：" + index);
            if (index > 5) {
                // TODO: 2017/2/21 已上车多次未获取到热点  提示黑车  弹出报警
//                if (iRideRecord != null) {
//                    iRideRecord.Alarm();
//                }

                //todo =================================================
//                rideManager.showAlramNotifiction(context);

                Intent intent = new Intent(context, AlarmActivity.class);//将要跳转的界面
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                LogUtil.i("上了黑车");
                if (alarmTimer != null) {
                    alarmTimer.start();
                    confirmTimer.cancel();
                }
            } else {
                getSacnResultDelayed();
            }
        } else {
            index = 1;

            if (baseList.size() == 0) {
                for (Car car : carList) {
                    RideRecordSacn rideRecordSacn = new RideRecordSacn();
                    rideRecordSacn.setSsId(car.getSsid());
                    rideRecordSacn.setCarNum(car.getCarNum());
                    rideRecordSacn.setCount(1);
                    baseList.add(rideRecordSacn);
                }
            } else {
                for (Car car : carList) {
                    RideRecordSacn rideRecordSacn = new RideRecordSacn();
                    rideRecordSacn.setSsId(car.getSsid());
                    rideRecordSacn.setCarNum(car.getCarNum());
                    rideRecordSacn.setCount(1);
                    latestList.add(rideRecordSacn);
                }
                OK:
                for (RideRecordSacn rideRecordSacn : latestList
                        ) {
                    for (RideRecordSacn rideRecordSacn2 : baseList
                            ) {
                        if (rideRecordSacn2.getCarNum().equals(rideRecordSacn.getCarNum())) {
                            rideRecordSacn2.setCount(rideRecordSacn2.getCount() + 1);
                            tempList.add(rideRecordSacn2);
                            break OK;
                        }
                    }
                    tempList.add(rideRecordSacn);
                }

                baseList = tempList;
                tempList = new ArrayList<>();
                latestList = new ArrayList<>();
                int count = 0;
                for (RideRecordSacn rideRecordSacn : baseList) {
                    if (isRidting) {
                        if (rideRecordSacn.getCount() >=3) {
                            count++;
                            carNum = rideRecordSacn.getCarNum();
                        }
                    }
                    LogUtil.i(rideRecordSacn.toJson());
                }
                if (count == 1) {
                    //有且仅有一辆车连续扫描到10次以上
                    LogUtil.i("上传乘车记录");
                    App.getApp().setTrivelCarNum(carNum);
                    rideManager.uploadCarRecord(1, carNum, 0);
                    baseList = new ArrayList<>();
                    confirmTimer.cancel();
                    isLeave();
                    return;
                }

            }
            getSacnResultDelayed();
        }
    }


    /**
     * 提醒来车
     */
    private void promptCar() {
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!isRidting) {

                    carList = scan.getCarList();
                    if (carList == null) {
                        promptCar();
                    } else {
                        for (Car car : carList
                                ) {
                            RideRecordSacn rideRecordSacn = new RideRecordSacn();
                            rideRecordSacn.setSsId(car.getSsid());
                            rideRecordSacn.setCarNum(car.getCarNum());
                            rideRecordSacn.setCount(1);
                            LogUtil.i("提示安全来车1");
                            if (!hasShow(rideRecordSacn)) {
                                LogUtil.i("提示安全来车");
                                //// TODO: 2017/2/22 提示用户安全来车
                                ToastUtil.show(context, "安全来车");
                            }
                        }
                    }
                    promptCar();
                }
                {
                    carList = new ArrayList<>();
                }
            }
        }, Configure.SACN_TIME);


    }


    /**
     * 判断是已上车乘车 但数据大于20Km每小时的时候表示已上车
     */

    private void getSpeed() {

        if (!isRidting) {

            hander.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (isRide()) {

                        isRidting = true;
                        getSacnResultDelayed();
                        /**
                         * 判断乘客什么时候下车
                         */
                    } else {
                        LogUtil.i("speed;" + App.getApp().getSpeed());
                        getSpeed();
                    }
                }
            }, Configure.SACN_TIME);

        }

//        测试
//        isRidting = true;
//        LogUtil.i("乘客已经上车");
//        getSacnResultDelayed();
    }


    /**
     * 判断是否达乘车速度
     */


    private boolean isRide() {
        if (App.getApp().getSpeed() < 20) {
            return false;
        }
        return true;
    }


    /**
     * 判断该车辆是否已经提示过用户
     */

    private boolean hasShow(RideRecordSacn rideRecordSacn) {
        for (RideRecordSacn rideRecordSacn2 : hasshowList
                ) {
            if (rideRecordSacn.getCarNum().equals(rideRecordSacn2.getCarNum())) {
                return true;
            }
        }
        hasshowList.add(rideRecordSacn);

        return false;
    }


    /**
     * 是否已经离车
     * 当连续5次没有扫描到确认乘车的终端的时候判断速度是否达到上车速度如果达到了 做黑车报警 否则判断为离车
     */

    private void isLeave() {
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                carList = scan.getCarList();
                LogUtil.i("noCarCount:" + noCarCount);

                if (carList == null || carList.size() == 0) {
                    noCarCount++;
                } else {
                    for (Car car : carList
                            ) {
                        if (car.getCarNum().equals(carNum)) {
                            noCarCount = 0;
                        } else {
                            noCarCount++;
                        }
                    }
                }

                if (noCarCount >= 5) {
                    if (isRide()) {
                        //TODO 进入报警状态
                        App.getApp().setTrivelCarNum(null);
                        ishand = false;
                        LogUtil.i("我又要报警了");
                        //todo =================================================================
//                        rideManager.showAlramNotifiction(context);
                        Intent intent = new Intent(context, AlarmActivity.class);//将要跳转的界面
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                        alarmTimer.cancel();
                        alarmTimer.start();
                    } else {
                        //TODo 已经下车
                        LogUtil.i("乘客已经下车");
                        isRidting=false;
                        rideManager.uploadCarRecord(1, carNum, 1);

                    }

                } else {
                    isLeave();
                }
                carList = new ArrayList<>();
            }
        }, Configure.SACN_TIME);
    }


    /**
     * 报警状态
     */

    private void callthePoliceStatus() {

        if (isPolice) {
            hander.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //TODO 上传位置信息
                    rideManager.uploadCarAlarm();
                    LogUtil.i("上传位置信息");
                    callthePoliceStatus();
                }
            }, Configure.PASSENGERLEAVE);
        }

    }

    /**
     * 停止报警
     */
    private void stopPolice() {
        //TODO 上传离车消息
        LogUtil.i("停止报警");
        rideManager.uploadCarRecord(1, carNum, 1);
        initData();

    }


    /**
     * 设置是否是乘车模式
     *
     * @param ride
     */
    public void setRide(boolean ride) {
        isWorking = ride;
        initData();
        if (ride) {
            getSpeed();
            promptCar();

//            getSacnResultDelayed();
        }
    }

    /**
     * 是否报警
     *
     * @param police
     */
    public void setPolice(boolean police) {

        if (isPolice) {
            return;
        }
        isPolice = police;
        ishand = true;
        if (isPolice) {
            callthePoliceStatus();
        } else {
            stopPolice();
        }

    }


    /**
     * 自动报警定时器
     */
    private CountDownTimer alarmTimer = new CountDownTimer(25 * 1000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            LogUtil.i("我要报警了");
            if (!ishand) {
                LogUtil.i("我报警了");
                setPolice(true);
            }
        }
    };


    /**
     * 确认乘车定时器
     */

    private CountDownTimer confirmTimer = new CountDownTimer(30 * 1000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            LogUtil.i("确认驾驶员时间结束");
            isConfirmCar = false;
            alarmTimer.cancel();
            alarmTimer.start();


        }
    };




    /**
     * 初始化数据
     */
    private void initData() {

        isWorking = false;
        isRidting = false;
        isPolice = false;
        isConfirmCar = true;
        ishand = false;
        index = 1;
        noCarCount = 0;
        carList = new ArrayList<>();
        baseList = new ArrayList<>();

    }


    /**
     * 检测是否在保护时间
     *
     * @return
     */
    private boolean checkUpTime() {
        if (TextUtils.isEmpty(SpUtil.get(Constants.TRIVELRECORDISOPEN))) {
            return false;
        } else {
            int isAuto = Integer.parseInt(SpUtil.get(Constants.TRIVELRECORDISOPEN));
            if (isAuto == 2) {
                return false;
            }
            Date date = new Date();
            int curentHour = date.getHours();
            int curentMintes = date.getMinutes();
            String startTime = SpUtil.get(Constants.TRIVELRECORDSTARTTIME);
            String endTime = SpUtil.get(Constants.TRIVELRECORDENDTIME);
            if ("".equals(startTime)){
                return false;
            }
            String[] start = startTime.split(":");
            String[] end = endTime.split(":");
            int startHout = Integer.parseInt(start[0]);
            int startMintes = Integer.parseInt(start[1]);
            int endHout = Integer.parseInt(end[0]);
            int endMintes = Integer.parseInt(end[1]);

            if (curentHour > startHout && curentHour < endHout) {
                return true;
            }
            if (curentHour == startHout && curentMintes > startMintes) {
                return true;
            }
            if (curentHour == endHout && curentMintes < endMintes) {
                return true;
            }

            return false;

        }



    }


}
