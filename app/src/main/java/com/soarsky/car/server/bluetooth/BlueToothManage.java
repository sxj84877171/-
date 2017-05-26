package com.soarsky.car.server.bluetooth;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.EmptySubscriber;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.remote.server1.ApiServiceImpl;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.server.cmd.BaseCmd;
import com.soarsky.car.server.design.ConfirmDriverSucessCallBack;
import com.soarsky.car.server.design.IAutoConfirmDriverListener;
import com.soarsky.car.server.design.IConnectListener;
import com.soarsky.car.server.design.OnCmdListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;

import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static com.soarsky.car.Constants.DREIVER_ALIVE_TIME;
import static com.soarsky.car.server.check.ConfirmDriverService.CHECK_IS_DRIVER_TIME;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/3/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class BlueToothManage extends HandlerThread {
    public static final String TAG = BlueToothManage.class.getSimpleName();
    private BlueToothConnet buleToothConnet;
    private Handler handler;
    /**
     * 要连接的车辆
     */
    private Car car;
    /**
     * 是否正在连接中
     */
    private boolean isWorking = false;

    /**
     * 自动连接
     */
    public static final int ATUOCONNETTYPE = 1;
    /**
     * 手动连接
     */
    public static final int CONNECTTYPE = 2;

    /**
     * 请求类型
     */
    private int connetType;


    /**
     * 服务断设置过来的监听
     */
    private OnConnectListener serviceConnectListener;

    /**
     * 自动确认驾驶员过来的监听
     */
    private IConnectListener autoConnectListener;

    /**
     * 终端返回数据解析
     */
    private BuleToothDataAnalyze buleToothDataAnalyze;


    /**
     * 服务端确认驾驶员过来的监听
     */

    private IAutoConfirmDriverListener serviceAutoConnectListener;


    /**
     * 驾驶员确认成功和离开回调
     */

    private ConfirmDriverSucessCallBack confirmDriverSucessCallBack;


    /**
     * 版本升级中
     */
    private boolean isTerminalUpdate = false;






    private BlueToothManage() {
        super(TAG, Thread.NORM_PRIORITY);
        start();
        handler = new Handler(getLooper());
        buleToothConnet = BlueToothConnet.getInstance();
        buleToothConnet.setPacketListener(onCmdListener);
        buleToothDataAnalyze = new BuleToothDataAnalyze(this);

    }

    private static BlueToothManage single = null;

    //静态工厂方法
    public static BlueToothManage getInstance() {
        if (single == null) {
            single = new BlueToothManage();
        }
        return single;
    }

    /**
     * 设置要连接的车辆
     *
     * @param car
     * @param type      连接类型  1 自动 2 手动
     * @param applyType 请求类型  1 自动 2 手动
     */
    public void setCar(Car car, int type, int applyType) {

        this.car = car;
        connetType = applyType;
        if (type == ATUOCONNETTYPE) {
            buleToothConnet.setConnectListener(connectListener);
            init(connetType);
        } else {
            buleToothConnet.setConnectListener(connectListener);
            BlueToothScan.getInstance(App.getApp()).isScan(false);
            init(connetType);
        }



    }

    /**
     * 初始化 判断是不是自己车
     * 12345112湘A44444
     * 前5位表示序列号第6位表示状态 第7 8位表示车子状态
     */
    public void init(int connetType) {
        LogUtil.i("申请类型：" + connetType);

        buleToothConnet.connectDervice(car, connetType);
    }

    /**
     * @param connetType
     * @param packageCode 传送升级数据包编号或者读取罚单起始序号
     */
    public void init(int connetType, int packageCode) {
        LogUtil.i("申请类型：" + connetType);
        buleToothConnet.connectDervice(car, connetType, packageCode);
    }


    /**
     * @param connetType
     * @param content    传送的数据包内容
     */
    public void init(int connetType, String content) {
        LogUtil.i("申请类型：" + connetType);
        buleToothConnet.connectDervice(car, connetType, content);
    }


    /**
     * 设置连接监听
     */
    public void setConnectListener(OnConnectListener connectListener) {
        this.serviceConnectListener = connectListener;
    }

    /**
     * 确认驾驶员成功
     */

    public void confirmDriverSucess() {
        App.getApp().setCarNum(car.getCarNum());
        App.getApp().setConfirmDriver(true);
        App.getApp().setiBridgeDevice(car.getDevice());
        //通知前段确认驾驶员成功
        if (serviceAutoConnectListener != null) {
            serviceAutoConnectListener.onConnectCar(car);
        }
        //手动确认驾驶员 通知确认成功
        if (serviceConnectListener != null) {
            serviceConnectListener.onSuccess();
            serviceConnectListener = null;
        }
        //自动确认驾驶员
        if (autoConnectListener != null) {
            autoConnectListener.finsh();
        }
        //告知服务确认驾驶员成功
        if (confirmDriverSucessCallBack != null) {
            confirmDriverSucessCallBack.onSucess();

        }

        final String deviceToken = SpUtil.getPublicKey(App.getApp(),Constants.UMENG_DEVICE_TOKEN);
        if(!TextUtils.isEmpty(deviceToken)){

                    new ApiServiceImpl().queryUpdateTask(car.getCarNum(),deviceToken,"1").subscribeOn(Schedulers.computation())
                            .subscribe(new EmptySubscriber());



        }
        isTerminalUpdate=false;
        syncData();
    }

    /**
     * 确认驾驶员失败
     */

    public void confirmDriverFailer() {
        BlueToothScan.getInstance(App.getApp()).isScan(true);
        if (serviceConnectListener != null) {
            serviceConnectListener.onFailed("");
        }
        if (autoConnectListener != null) {
            autoConnectListener.finsh();
        }
    }

    /**
     * 请人移车回调
     */
    public void moverCallBack(String result) {
        if (serviceConnectListener != null) {
            serviceConnectListener.onResult(result);
        }
    }

    /**
     * 连接监听
     */
    private OnConnectListener connectListener = new OnConnectListener() {
        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailed(String result) {
            buleToothConnet.clearConnectListener();
            if (serviceConnectListener != null) {
                serviceConnectListener.onFailed(result);
                serviceConnectListener = null;
            }

            if (autoConnectListener != null) {
                autoConnectListener.finsh();
            }


        }

        @Override
        public void onResult(Object o) {

        }
    };

    /**
     * 反回数据包监听
     */
    private OnCmdListener onCmdListener = new OnCmdListener() {
        @Override
        public void onNewCmd(BaseCmd cmd) {
            buleToothDataAnalyze.setCmd(cmd);
        }
    };

    /**
     * 自动确认驾驶员监听 判断请求是否完成
     *
     * @param autoConnectListener
     */
    public void setAutoConnectListener(IConnectListener autoConnectListener) {
        this.autoConnectListener = autoConnectListener;
    }

    /**
     * 自动确认驾驶员监听
     *
     * @param autoConnectListener
     */
    public void setServiceAutoConnectListener(IAutoConfirmDriverListener autoConnectListener) {
        this.serviceAutoConnectListener = autoConnectListener;
    }

    /**
     * @param confirmDriverSucessCallBack
     */
    public void setConfirmDriverSucessCallBack(ConfirmDriverSucessCallBack confirmDriverSucessCallBack) {
        this.confirmDriverSucessCallBack = confirmDriverSucessCallBack;
    }

    /**
     * 同步终端数据
     */
    private void syncData() {
//        if (isTerminalUpdate) {
//            return;
//        }
        if (!App.getApp().isConfirmDriver()) {
            return;
        }
        car.setDevice(App.getApp().getiBridgeDevice());
        setCar(car, CONNECTTYPE, Constants.CHECK_ALIVE);



        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,
            CHECK_IS_DRIVER_TIME);

    }



    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            syncData();
        }
    };


    /***
     * 断开连接
     */
    public void disConnect() {
        if (buleToothConnet != null) {
            buleToothConnet.disConnected();
        }
    }


    public void setTerminalUpdate(boolean terminalUpdate) {
        isTerminalUpdate = terminalUpdate;
    }


    /**
     * 驾驶员离开
     */

    public  void driverLeave(){
        if(null!=confirmDriverSucessCallBack){
        confirmDriverSucessCallBack.oncancle();}
    }

    public boolean isTerminalUpdate() {
        return isTerminalUpdate;
    }
}
