package com.soarsky.policeclient.ui.accident.add;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.server.cmd.AccidentCommandRequest;
import com.soarsky.policeclient.server.cmd.AccidentCommandResponse;
import com.soarsky.policeclient.server.design.ICommand;
import com.soarsky.policeclient.server.design.OnMessageResponseListener;
import com.soarsky.policeclient.server.design.OnPacketListener;
import com.soarsky.policeclient.uitl.ByteUtil;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.server.bluetooth.Blue;
import com.soarsky.policeclient.server.design.OnConnectListener;
import com.soarsky.policeclient.uitl.LogUtil;

import java.util.ArrayList;


/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故线程类，负责发送消息到智能终端<br>
 */
public class AccidentHandlerThread extends HandlerThread implements IAccident{

    /**
     * 线程是否在运行
     */
    private boolean isRunning;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * log tag
     */
    public static final String TAG = AccidentHandlerThread.class.getSimpleName();
    /**
     * 发送handler消息
     */
    private Handler handler;
    /**
     * 选择的车辆
     */
    private ArrayList<Car> selectCar;

    /**
     * 当前正在连接的车辆
     */
    private Car connectCar;
    /**
     * 事故分析消息回调监听
     */
    private OnAccidentMessageResponseListener onAccidentMessageResponseListener;

    public AccidentHandlerThread(Context context) {
        this(TAG, Thread.NORM_PRIORITY, context);
    }

    public AccidentHandlerThread(String name, int priority, Context context) {
        super(name, priority);
        this.mContext = context;
    }


    /**
     * 事故分析内部消息循环
     */
    public void accident(){

        handler.post(new Runnable() {
            @Override
            public void run() {
                for (Car s:selectCar) {
                    if(!s.isHasRead()){
                        onAccidentMessageResponseListener.onUnFinished();
                        connectCar = s;
                        connect(s);
                        return;
                    }
                }
                onAccidentMessageResponseListener.onFinished();
                stopAccident();
            }
        });

    }

    /**
     * 解析出消息包的回调监听
     */
    private OnPacketListener onPacketListener = new OnPacketListener() {
        @Override
        public void onNewPacket(ICommand cmd) {
            if(cmd instanceof AccidentCommandResponse){
                connectCar.setHasRead(true);
                connectCar.setHasData(true);
                AccidentCommandResponse accidentCommandResponse = (AccidentCommandResponse)cmd;
                AccidentDataBean.AccidentItemBean.AccidentCarBean accidentDataBean = accidentCommandResponse.getAccidentDataBean();
                accidentDataBean.setCar(connectCar);
                onAccidentMessageResponseListener.onResponse(accidentDataBean);
                accident();
            }
        }
    };

    /**
     * 连接车辆
     * @param car 要去连接的车辆
     */
    private void connect(Car car){
        connectCar = car;
        connectBlue();
    }

    private OnConnectListener onConnectListener = new OnConnectListener() {
        @Override
        public void onSuccess() {
            blueSendMessage();
        }

        @Override
        public void onFailed() {
            onError();
        }
    };

    private void connectBlue(){
        if (connectCar.getBluetoothIBridgeDevice().isConnected()) {
            blueSendMessage();
        } else {
            Blue.getInstance(mContext).setOnConnectListener(onConnectListener);
            Blue.getInstance(mContext).getmAdapter().connectDevice(connectCar.getBluetoothIBridgeDevice());
        }
    }
    /**
     * 开启事故分析线程，根据selectCar从智能终端读取数据
     * @param selectCar 需要读取数据的车辆，来自于选择附近车辆界面
     * @param onMessageResponseListener 事故消息读取响应回调接口
     */
    @Override
    public void startAccident(ArrayList<Car> selectCar,OnAccidentMessageResponseListener onMessageResponseListener) {
        this.selectCar = selectCar;
        this.onAccidentMessageResponseListener = onMessageResponseListener;
        start();
        handler = new Handler(getLooper());
        isRunning = true;
        accident();
    }

    /**
     * 停止事故分析
     */
    @Override
    public void stopAccident(){
        isRunning = false;
        quit();
    }

    /**
     * 是否在运行
     * @return 是否在运行状态
     */
    @Override
    public boolean isAccident() {
        return isRunning;
    }

    @Override
    public void setOnAccidentMessageResponseListener(OnAccidentMessageResponseListener onMessageResponseListener) {
        this.onAccidentMessageResponseListener = onMessageResponseListener;
    }

    private void blueSendMessage(){
        AccidentCommandRequest accidentCommandRequest = new AccidentCommandRequest
                (connectCar.getCarNum(),"0");
        Blue.getInstance(mContext).setPacketListener(onPacketListener);
        LogUtil.e("sendMsg", ByteUtil.bytearrayToHexString(accidentCommandRequest.toBytes(),accidentCommandRequest.toBytes().length));
        Blue.getInstance(mContext).getmAdapter().send(connectCar.getBluetoothIBridgeDevice
                (), accidentCommandRequest.toBytes(), accidentCommandRequest.toBytes().length);
    }


    /**
     * 与终端交互出现错误的调用方法
     */
    public void onError(){

        connectCar.setHasRead(true);
        accident();
    }

}
