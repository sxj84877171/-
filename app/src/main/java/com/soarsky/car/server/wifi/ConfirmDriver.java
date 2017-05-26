package com.soarsky.car.server.wifi;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.base.Configure;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.data.local.db.DriverRecordDb;
import com.soarsky.car.data.local.db.ErrorDriverDb;
import com.soarsky.car.data.local.db.TroubleDb;
import com.soarsky.car.data.local.db.bean.DriverRecord;
import com.soarsky.car.data.local.db.bean.ErrorDriver;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.data.local.db.bean.Trouble;
import com.soarsky.car.server.cmd.BaseCmd;
import com.soarsky.car.server.cmd.ConnectCarCommandResponse;
import com.soarsky.car.server.cmd.DangerousDrivingCommandResponse;
import com.soarsky.car.server.cmd.DearNumCommandResponse;
import com.soarsky.car.server.cmd.MoveCarCommandResponse;
import com.soarsky.car.server.cmd.ReadDataCommandResponse;
import com.soarsky.car.server.cmd.SyncData;
import com.soarsky.car.server.cmd.TicketCommandResponse;
import com.soarsky.car.server.cmd.TroubleCommandResponse;
import com.soarsky.car.server.cmd.VolumeCommandResponse;
import com.soarsky.car.server.design.ConfirmDriverSucessCallBack;
import com.soarsky.car.server.design.IAutoConfirmDriverListener;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnCmdListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.server.design.OnReaderThreadListener;
import com.soarsky.car.data.local.db.FamilyNumDb;
import com.soarsky.car.ui.home.main.OnVolumeListener;
import com.soarsky.car.ui.ticketed.TicketDb;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.WifiUtil;

import java.util.Date;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者：何明辉<br>
 * 时间： 2016/11/15.<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：与热点的自动和手动连接<br>
 * 该类为 与终端通讯服务类<br>
 */

public class ConfirmDriver extends HandlerThread {
    private Context context;
    public static final String TAG = ConfirmDriver.class.getSimpleName();
    /**
     * 附近的车
     */
    private static List<Car> autocarList;
    private Handler handler;
    private ConnectCar connectCar;
    /**
     * 请求次数
     */
    private int index = 0;
    /**
     * 热点数 但resultCount等于index 表示一轮请求全部完成
     */
    private int resultCount = 0;
    /**
     * 是否是自动连接
     */
    private boolean isAuto = Configure.ISAUTOCOONECT;
    /**
     * 是否却驾驶员
     */
    private boolean isConfirmDriver = false;
    /**
     * 扫描接口
     */
    private Scan iScan;
    /**
     * 连接监听
     */
    private OnConnectListener connectListener;
    /**
     * 自动确认驾驶员监听用来通知前段界面确认驾驶员成功
     */
    private IAutoConfirmDriverListener iAutoConfirmDriverListener;
    /**
     * 正在连接的车辆
     */
    private Car connectingCar;
    /**
     * 当前连接的ssid
     */
    private String ssid;
    private App app;
    /**
     * 确认驾驶员成功回调
     */
    private ConfirmDriverSucessCallBack confirmDriverSucessCallBack;
    /**
     * 确认驾驶员成功的车辆
     */
    private Car currentCar;
    /**
     * 同步数据类
     */
    private SyncData syncData;
    private AliveRunable aliveRunable;
    /**
     * 请求类型
     */
    private int connectType=-1;


    /**
     * 是否有主动连接
     */
    private boolean activityConnect = false;


    private OnVolumeListener onVolumeListener;

    public ConfirmDriver(Context context) {
        super(TAG, Thread.NORM_PRIORITY);
        this.context = context;
        connectCar = new ConnectCar(context);
        iScan = Scan.getInstance(context);
        connectCar.setOnCmdListener(listener);
        connectCar.setVolumeListener(onVolumeListener);
        app = (App) context.getApplicationContext();
        SpUtil.init(context);
        init();

    }


    /**
     * 开始扫描
     */
    public  void  startScan(){
         if(iScan==null)         {
             iScan=Scan.getInstance(context);
         }
        iScan.openWifi();
    }




    /**
     * 获取热点
     */
    public synchronized void getCarList() {
       //判断司机是否还在
        if(isConfirmDriver){
            driverLiveTime();
        }
        autocarList = iScan.getAutocarList();
        if (autocarList == null || autocarList.size() == 0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getCarList();
                }
            }, Configure.SACN_RESULT);
        } else {
            /**
             * 驾驶员还未确认
             */
            if (!isConfirmDriver) {
                index = 0;
                resultCount = autocarList.size();
                connectCar(autocarList.get(0));
            }
        }

    }

    /**
     * 初始化
     */

    public void init() {
        start();
        handler = new Handler(getLooper());
    }


    /**
     * 自动连接车辆
     * @param car 车对象
     */
    public void connectCar(final Car car) {

        connectingCar = car;
        ssid = car.getSsid();
        index++;
        LogUtil.i("connectCar:" + car.getCarNum() + "myCar:" + App.getApp().getCommonParam().getCarNum()+"index:"+index);
       final int  connectType=getConnectType(car);
        if (isAuto) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //断开当前连接的wifi
                    switch (connectType){
                        case 0:
                            connectCar.connect(car, 1);
                            connectCar.setOnReaderThreadListener(onReaderThreadListener);
                            break;
                        case 1:
                            connectCar.connect(car, 1);
                            connectCar.setOnReaderThreadListener(onReaderThreadListener);
                            break;
                        case 2:
                            car.setAuthNum(getAuthCode(car.getCarNum()));
                            connectCar.connect(car, 2);
                            connectCar.setOnReaderThreadListener(onReaderThreadListener);
                            break;
                        case 3:
                            onReaderThreadListener.onfail("不是亲情号也不是借车");
                            break;
                    }
                }
            });
        }


    }


    /**
     * 主动连接车辆
     * @param car 车对象
     * @param type 申请类型
     */
    public  synchronized  void handConnectCar(final Car car, final int type) {
        //ToDo 3月23加上 还未测试过 connectCar.stopCurrentTask();
        connectCar.stopCurrentTask();

        connectType=type;
        activityConnect=true;
        ssid = car.getSsid();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //断开当前连接的wifi
                connectCar.connect(car, type);
                connectCar.setOnReaderThreadListener(onReaderThreadListener);
            }
        });

    }


    /**
     * 设置扫描监听
     * @param onCarScanedListener OnCarScanedListener
     */
    public void setScanListenser(OnCarScanedListener onCarScanedListener) {
        if (iScan != null) {
            iScan.setCarScanedListener(onCarScanedListener);
        }

    }

    /**
     * 终端消息回复监听
     */

    private OnCmdListener listener = new OnCmdListener() {
        @Override
        public void onNewCmd(BaseCmd cmd) {

            activityConnect=false;
            long endSucessTime = new Date().getTime();
            SpUtil.save("endSucessTime", endSucessTime + "");
            if (cmd instanceof ConnectCarCommandResponse) {

                //断开当前连接的wifi
                ConnectCarCommandResponse connectCarCommandResponse = (ConnectCarCommandResponse) cmd;

                //保存最后一次成功连接终端的毫秒数
                if (connectCarCommandResponse.isDriver()) {
                    //保存确认驾驶员的ssid
                    isConfirmDriver = true;
                    if(connectType== Constants.APPLY_FIVE){
                        App.getApp().setCarNum(ssid.substring(3,10));
                        App.getApp().setSsID(ssid);
                    }else{
                        App.getApp().setCarNum(ssid.substring(3,10));
                        App.getApp().setSsID("4"+ssid.substring(1));
                    }

                    isDriverAlive();
                    if (isAuto) {
                        iAutoConfirmDriverListener.onConnectCar(connectingCar);
                    } else {
                        if (connectListener != null) {
                            connectListener.onSuccess();
                        }
                    }
                    if (confirmDriverSucessCallBack != null) {
                        confirmDriverSucessCallBack.onSucess();
                    }
                    /**
                     * 恢复网络
                     */
                    WifiUtil.getInstance().recoverWifi();


                } else {
                    if (isAuto) {
                        connectFinsh();
                    } else {
                        if (connectListener != null) {
                            connectListener.onFailed("未确认驾驶员");
                        }
                    }

                }
                /**
                 * 移除wifi配置信息
                 */
                WifiUtil.getInstance().removeNetWorkId(ssid);
                connectCar.onDestory();
            }

            //亲情号码
            if (cmd instanceof DearNumCommandResponse) {
                connectCar.onDestory();
                DearNumCommandResponse dearNumCommandResponse = (DearNumCommandResponse) cmd;
                /**
                 * 更新亲情号码状态
                 */
                if (dearNumCommandResponse.isSucess()) {
                    FamilyNumDb.getInstance(context).updateData();
                }
                /**
                 * 恢复网络连接
                 */
                WifiUtil.getInstance().removeNetWorkId(ssid);
                WifiUtil.getInstance().recoverWifi();
            }
            //故障查询
            if (cmd instanceof TroubleCommandResponse) {
                TroubleCommandResponse troubleCommandResponse = (TroubleCommandResponse) cmd;
                TroubleDb.getInstance(context).deleteAll();
                if (troubleCommandResponse.hasTrouble()) {
                    Trouble trouble = new Trouble();
                    trouble.setCarNum(app.getCommonParam().getCarNum());
                    trouble.setTroubleMessage(troubleCommandResponse.getTroubleStr());
                    trouble.setCreateTime(new Date());
                    trouble.setStatus("0");
                    TroubleDb.getInstance(context).insertData(trouble);
                    SpUtil.save("TROUBLESTR", troubleCommandResponse.getTroubleStr());
                    if (connectListener != null) {
                        connectListener.onResult(troubleCommandResponse.getTroubleStr());
                    }


                } else {
                    /**
                     * 没有故障
                     */
                    SpUtil.save("TROUBLESTR", "");
                    if (connectListener != null) {
                        connectListener.onSuccess();
                    }
                }

                /**
                 * 读取罚单消息
                 */
//                connectCar.sendMessage(CmdManage.getInstance().getUnReadTicket(currentCar));
            }
            if (cmd instanceof TicketCommandResponse) {
                TicketCommandResponse ticketCommandResponse = (TicketCommandResponse) cmd;
                if (ticketCommandResponse.getMessageType() != 0) {
                    /***
                     * 同步亲情号
                     */
//                    LogUtil.i("亲情号");
//                    if (syncData != null) {
//                        syncData.getUnSyncFamilyNum();
//                    }


                } else {
                    List<Ticket> ticketList = ticketCommandResponse.getTicketList();
                    for (Ticket ticket : ticketList) {
                        TicketDb.getInstance(context).inserData(ticket);
                    }
                    /**
                     * 读取用车记录
                     */
//                    connectCar.sendMessage(CmdManage.getInstance().getCarStatus(currentCar));
                }
            }

            if (cmd instanceof ReadDataCommandResponse) {
                ReadDataCommandResponse readDataCommandResponse = (ReadDataCommandResponse) cmd;
                if (readDataCommandResponse.getRequestType()==2) {
                    //车况
                    DriverRecord driverRecord = new DriverRecord();
                    driverRecord.setContent(readDataCommandResponse.getContentStr());
//                    driverRecord.setRecordNo(readDataCommandResponse.getTicketNo());
                    DriverRecordDb.getInstance(context).insertData(driverRecord);
                }
                if (readDataCommandResponse.getRequestType()==1) {
                    //行驶异常数据
                    ErrorDriver errorDriver = new ErrorDriver();
                    errorDriver.setContent(readDataCommandResponse.getContentStr());
//                    errorDriver.setDriverNo(readDataCommandResponse.getTicketNo());
                    ErrorDriverDb.getInstance(context).insertData(errorDriver);
                }
            }
            if (cmd instanceof DangerousDrivingCommandResponse) {
            }
            if (cmd instanceof VolumeCommandResponse) {
                /**
                 * 同步罚单
                 */

                if (syncData != null) {
                    syncData.getUnSyncFamilyNum();
                }
            }

            if (cmd instanceof MoveCarCommandResponse) {
                MoveCarCommandResponse moveCarCommandResponse = (MoveCarCommandResponse) cmd;
                if (connectListener != null) {
                    connectListener.onResult(moveCarCommandResponse.getResponseType());
                }
            }

        }
    };


    /**
     * 判断司机是否还在
     */
    public void isDriverAlive() {
              LogUtil.i("检测驾驶员是否还在");
               if(aliveRunable==null){
                   aliveRunable=new AliveRunable();
               }
               handler.postDelayed(aliveRunable, Configure.ISDRIVERALIVE);
           }



    /**
     * 检测驾驶员是否还在的runable
     *
     */
    private class  AliveRunable implements  Runnable {


        @Override
        public void run() {
            Car car = new Car();
            car.setSsid(App.getApp().getSsID());
            car.setCarNum(App.getApp().getCarNum());
            currentCar = car;
            if (!activityConnect) {
                connectCar.connect(car, 12);
                connectCar.setOnReaderThreadListener(onReaderThreadListener);
            }
            syncData = new SyncData(context, connectCar);
            if (isConfirmDriver) {
                handler.postDelayed(this, Configure.ISDRIVERALIVE);
            }
        }
    }




    /**
     * 连接监听
     */
    private OnReaderThreadListener onReaderThreadListener = new OnReaderThreadListener() {
        @Override
        public void onSucess() {
            activityConnect=false;
        }
        /**
         *
         * @param reason 失败原因
         */
        @Override
        public void onfail(String reason) {
            /**
             * 如果不是确认驾驶员 失败了就恢复网络当申请类型大于申请5就都不是确认驾驶员了
             */
            if(connectCar.getMeeeageType()>Constants.APPLY_FIVE){
                WifiUtil.getInstance().recoverWifi();
            }
            activityConnect=false;
            if (!isConfirmDriver && connectListener != null) {
                if(connectCar.getMeeeageType()==Constants.APPLY_THREE||connectCar.getMeeeageType()==Constants.APPLY_FOUR||connectCar.getMeeeageType()==Constants.APPLY_FIVE){
                    connectListener.onFailed("连接失败");
                }
            }
            LogUtil.i("onfail:" + index + "   reason :=" + reason);
            //断开当前连接的wifi
            if (isConfirmDriver) {
                //获取当前毫秒数
                driverLiveTime();

            } else {
                connectFinsh();
            }


        }

    };


    /**
     * 判断驾驶员离开时间
     */
    private void driverLiveTime(){
        long time = new Date().getTime();
        //获取最后一次成功连接终端的时间
        String entimestr = SpUtil.get("endSucessTime");
        long endtime;
        if (!TextUtils.isEmpty(entimestr)) {

            endtime = Long.parseLong(entimestr);
        } else {
            endtime = new Date().getTime();
        }
        //如果两次时间间隔大于10分钟  表示司机已经离开
        if (time - endtime >Configure.DRIVERLEAVE) {
            //// TODO: 2016/11/29 停止重复连接
            isConfirmDriver = false;
            App.getApp().setConfirmDriver(false);
            if (confirmDriverSucessCallBack != null) {
                confirmDriverSucessCallBack.oncancle();
            }
            if(iAutoConfirmDriverListener!=null){
                iAutoConfirmDriverListener.onDriverOffLine();
            }


        }
    }




    /**
     * 判断一次连接是否完成
     */
    public void connectFinsh() {
        if (isAuto) {
            //如果是自动连接 循环执行未连接的热点
            if (index == resultCount) {
                //第一批热点全部连接完成
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getCarList();
                    }
                }, Configure.ISDRIVERALIVE);

            } else {
                if (index < autocarList.size()) {
                    Car car = autocarList.get(index);
                    if (!isConfirmDriver) {
                        connectCar(car);
                    }

                } else {

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getCarList();
                        }
                    }, Configure.ISDRIVERALIVE);
                }
            }
        } else {
            //提示界面 确认驾驶员失败
//            connectListener.onFailed();
        }

    }


    /**
     * 是否为自动连接
     *
     * @param auto
     */
    public void setAuto(boolean auto) {
        isAuto = auto;
        if (auto) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getCarList();
                }
            },2000);

        }else{
            /**
             * 停止自动扫描工作
             */
        if(autocarList!=null){
            autocarList.clear();
            index=0;
        }

        }

    }

    /**
     * 设置连接监听
     */
    public void setConnectListener(OnConnectListener connectListener) {
        this.connectListener = connectListener;
    }

    /**
     * 音量连接监听
     */
    private OnVolumeListener volumeListener;
    public void setVolumeListener(OnVolumeListener volumeListener) {
        this.volumeListener = volumeListener;
        connectCar.setVolumeListener(volumeListener);
    }


    public void setIAutoConfirmDriverListener(IAutoConfirmDriverListener iAutoConfirmDriverListener) {
        this.iAutoConfirmDriverListener = iAutoConfirmDriverListener;
    }


    /**
     *
     * @return 0 车主 1亲情号 2借车 3都不是
     */

    private  int getConnectType(Car car){
        List<LicenseInfo> car_list=app.getCar_list();
        if(null==car_list){
            return 3;
        }
        for(int i=0;i<car_list.size();i++){
            if(car.getCarNum().equals(car_list.get(i).getPlateno())){
                return car_list.get(i).getSign();
            }
        }
        return 3;

    }



    /**
     * 获取借车的授权码
     * @param carNum
     * @return
     */
    private  String getAuthCode(String carNum){
        List<LicenseInfo> car_list=app.getCar_list();
        if(null==car_list){
            return "";
        }

        for(int i=0;i<car_list.size();i++){
            if(carNum.equals(car_list.get(i).getPlateno())){
                return car_list.get(i).getAuthcode();
            }
        }

        return  "";
    }

    /**
     * 设置确认驾驶员成功回调
     *
     * @param confirmDriverSucessCallBack
     */
    public void setConfirmDriverSucessCallBack(ConfirmDriverSucessCallBack confirmDriverSucessCallBack) {
        this.confirmDriverSucessCallBack = confirmDriverSucessCallBack;
    }

    /**
     * 停止HandlerThered
     */
    public void onDestroy() {
        if(iScan!=null){
            iScan.onDestory();
        }
        iScan=null;
        if(getLooper()!=null){
            getLooper().quit();
        }
    }
}
