package com.soarsky.policeclient.server.biz;

import android.content.Context;

import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.server.cmd.ElectronicTicketCommandRequest;
import com.soarsky.policeclient.server.cmd.ElectronicTicketCommandResponse;
import com.soarsky.policeclient.server.design.ICommand;
import com.soarsky.policeclient.server.design.OnMessageResponseListener;
import com.soarsky.policeclient.server.design.OnPacketListener;
import com.soarsky.policeclient.ui.violation.ViolationLicenseParam;
import com.soarsky.policeclient.uitl.ByteUtil;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.server.bluetooth.Blue;
import com.soarsky.policeclient.server.design.IElTicket;
import com.soarsky.policeclient.server.design.OnConnectListener;
import com.soarsky.policeclient.uitl.LogUtil;

import java.text.SimpleDateFormat;

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
 * 该类为 开电子罚单实现类<br>
 */

public class ElTicket implements IElTicket{

    /**
     * 上下文
     */
    private Context context;
    /**
     * 连接监听器
     */
    private OnConnectListener onConnectListener;
    /**
     * 开电子返单的车辆
     */
    private Car car;
    /**
     * 消息响应监听
     */
    private OnMessageResponseListener onMessageResponseListener;

    /**
     * 接收到消息包的监听
     */
    private OnPacketListener onPacketListener = new OnPacketListener() {
        @Override
        public void onNewPacket(ICommand cmd) {
            if(cmd instanceof ElectronicTicketCommandResponse){
                ElectronicTicketCommandResponse electronicTicketCommandResponse = (ElectronicTicketCommandResponse)cmd;
                String s = electronicTicketCommandResponse.getDrivingLicenseNumber();
                onMessageResponseListener.onSuccess(s);
            }
        }
    };

    public ElTicket(Context context){
        this.context = context;
    }
    /**
     * 连接该车辆开启的热点
     * @param car 开电子罚单的车辆
     */
    @Override
    public void connect(final Car car) {
        this.car= car;

        new Thread(new Runnable() {
            @Override
            public void run() {
                connectBlue();

            }
        }).start();
    }

    private void connectBlue(){
        if (car.getBluetoothIBridgeDevice().isConnected()) {
            onConnectListener.onSuccess();
        } else {
            Blue.getInstance(context).setOnConnectListener(onConnectListener);
            Blue.getInstance(context).setPacketListener(onPacketListener);
            Blue.getInstance(context).getmAdapter().connectDevice(car.getBluetoothIBridgeDevice());
        }
    }

    /**
     * 开电子罚单发送数据到智能终端
     * @param violationLicenseParam 发送给智能终端的数据
     */
    //2015年5月10日15时12分于广佛高速18公里处超速违章,罚300元，记2分
    @Override
    public void openElTicket(final ViolationLicenseParam violationLicenseParam){
        new Thread(new Runnable() {
            @Override
            public void run() {
                blueSendMessage(violationLicenseParam);

            }
        }).start();
    }

    private void blueSendMessage(ViolationLicenseParam violationLicenseParam){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateS = sDateFormat.format(violationLicenseParam.getTime());
        StringBuilder message =  new StringBuilder();
        message.append(violationLicenseParam.getType());
        message.append(dateS);
        message.append("E112.897818");
        message.append("N28.2205170");
        ElectronicTicketCommandRequest cmd = new ElectronicTicketCommandRequest(CarUtil.fromSsidToCarNum(violationLicenseParam.getSsid()),message.toString());
        Blue.getInstance(context).setPacketListener(onPacketListener);
        LogUtil.e("sendMsg", ByteUtil.bytearrayToHexString(cmd.toBytes(),cmd.toBytes().length));
        Blue.getInstance(context).getmAdapter().send(car.getBluetoothIBridgeDevice(), cmd.toBytes(), cmd.toBytes().length);
    }


    public void setOnConnectListener(OnConnectListener onConnectListener){
        this.onConnectListener = onConnectListener;
    }

    @Override
    public void setOnResponseListener(OnMessageResponseListener onResponseListener) {
        this.onMessageResponseListener = onResponseListener;
    }



    public Car getCar() {
        return car;
    }

}
