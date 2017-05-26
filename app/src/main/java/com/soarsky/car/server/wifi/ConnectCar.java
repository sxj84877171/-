package com.soarsky.car.server.wifi;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;

import com.soarsky.car.base.Configure;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.cmd.CmdManage;
import com.soarsky.car.server.cmd.ReadData;
import com.soarsky.car.server.cmd.SyncData;
import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.server.design.IConnectCar;
import com.soarsky.car.server.design.IConnectListener;
import com.soarsky.car.server.design.ITransfer;
import com.soarsky.car.server.design.OnCmdListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.server.design.OnReaderThreadListener;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.WifiUtil;

import java.net.Socket;

/**
 * Andriod_Car_App
 * 作者：何明辉
 * 时间： 2016/11/15.
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：连接热点和数据发送
 * 该类为 与终端通讯服务类
 */

public class ConnectCar implements IConnectCar {

    private Context context;
    private Car car;
    private ITransfer iTransfer;
    private WifiConnectBroadcast wifiConnectBroadcast;
    private OnReaderThreadListener onReaderThreadListener;


    private OnCmdListener onCmdListener;//返回数据监听
    /**
     * 消息类型
     */
    private int meeeageType;
    /**
     * 是否有连接返回
     */
    private boolean isConnectResult=false;
    /**
     * 读取行驶异常数据
     */
    private static final int DANGEROUS_REAVER= 13;
    /**
     * 读取行驶异常数据
     */
    private static final int TRAVEL_NOMALY= 11;

    /**
     * 同步罚单
     */
    private static final int TICKET_SYNC= 10;
    /**
     * 读取未读罚单
     */
    private static final int TICKET_UNREAD= 9;

    /**
     * 故障查询
     */
    private static final int TROUBLE_REQUEST= 8;
    /**
     * 故障应答
     */
    private static final int TROUBLE_RESPONSE= 7;
    /**
     * 亲情号
     */
    private static final int DEAR_NUM= 6;
    /**
     * 申请一
     */
    private static final int APPLY_ONE= 1;
    /**
     * 申请二
     */
    private static final int APPLY_TWO= 2;
    /**
     * 申请三
     */
    private static final int APPLY_THREE= 3;

    /**
     * 申请四
     */
    private static final int APPLY_FOUR= 4;

    /**
     * 申请五
     */
    private static final int APPLY_FIVE= 5;

    /**
     * 检查司机是否还在
     */
    private static final int CHECK_ALIVE=12;




    public ConnectCar(Context context) {
        this.context = context;
    }






    /**
     * 连接车辆
     * @param car 车
     * @param type 消息类型
     */
    public synchronized void connect(final Car car,int type) {
        this.meeeageType=type;
        LogUtil.i("请求通道："+type+"SSID:"+car.getSsid());

        this.car = car;
        //TODO 先在wifi列表中查找是否已经存在该车的ssid
        // 如果存在该车ssid，先移除，再进行下面的添加并连接的操作
        int netWorkId=WifiUtil.getInstance().getNetWorkId(car.getSsid());
        if(netWorkId!=-1){
           WifiUtil.getInstance().removeNetWorkId(netWorkId);
        }
        registeWifiConnectBroadCast();
        boolean result = WifiUtil.getInstance().connectWifi(car);
        isConnectResult=false;
        timer.start();


        if (!result) {
//            onReaderThreadListener.onfail("connect");
        }
    }

    @Override
    public void setOnConnectListener(OnConnectListener onConnectListener) {

    }


    public Car getCar() {
        return car;
    }


    /**
     * 发送消息
     * @param car
     */
    public void sendMessage(final Car car) {
        LogUtil.i("connectssid="+car.getSsid());
        if(timer!=null){
            isConnectResult=true;
            timer.cancel();
        }
        unregisterReceiver();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int ipInt = WifiUtil.getInstance().getWifiManager().getDhcpInfo().gateway;
                String ip = String.valueOf(new StringBuilder()
                        .append((ipInt & 0xff)).append('.').append((ipInt >> 8) & 0xff)
                        .append('.').append((ipInt >> 16) & 0xff).append('.')
                        .append(((ipInt >> 24) & 0xff)).toString());
                try {
                    Thread.sleep(1000);
                    Socket socket = new Socket(ip, WifiUtil.PORT);

                    LogUtil.i("ip:"+ip);
                    socket.setSoTimeout(15000);
                    iTransfer = new WifiTransfer(onReaderThreadListener);
                    iTransfer.setOutputstream(socket.getOutputStream());
                    iTransfer.setConnectListener(connectListener);
                    switch (meeeageType){
                        case TROUBLE_REQUEST:
                            iTransfer.sendCmd(CmdManage.getInstance(context).getTroubleCheck());
                        break;
                        case TROUBLE_RESPONSE:
                            iTransfer.sendCmd(CmdManage.getInstance(context).getTroubleResponse());

                            break;
                        case DANGEROUS_REAVER:
//                            iTransfer.sendCmd(CmdManage.getInstance(context).);
                        break;
                        case APPLY_ONE:
                            iTransfer.sendCmd(CmdManage.getInstance(context).getApplyOne(car,context));
                            break;
                        case APPLY_TWO:
                            iTransfer.sendCmd(CmdManage.getInstance(context).getApplyTwo(car,context));
                            break;
                        case APPLY_THREE:
                            iTransfer.sendCmd(CmdManage.getInstance(context).getApplyThree(car,context));
                            break;
                        case APPLY_FOUR:
                            iTransfer.sendCmd(CmdManage.getInstance(context).getApplyFour(car,context));
                            break;
                        case APPLY_FIVE:
                            iTransfer.sendCmd(CmdManage.getInstance(context).getApplyFive(car,context));
                            break;
                        case TICKET_UNREAD:
                            iTransfer.sendCmd(CmdManage.getInstance(context).getUnReadTicket(car));
                            break;
                        case TRAVEL_NOMALY:
                            iTransfer.sendCmd(CmdManage.getInstance(context).getErrorDriver(car));
                            break;
                        case TICKET_SYNC:
                            iTransfer.sendCmd(CmdManage.getInstance(context).syncTicket(car));
                            break;
                        case CHECK_ALIVE:
                            SyncData syncData=new SyncData(context,ConnectCar.this);
                            ReadData readData=new ReadData(context,ConnectCar.this);
                            readData.start();
                            syncData.start();

                            break;
                    }
                    iTransfer.setInputstream(socket.getInputStream());
                    iTransfer.start();
                    iTransfer.setOnPacketListener(onCmdListener);
                } catch (Exception e) {
                    if(iTransfer!=null){
                        iTransfer.destroy();
                    }
                    if(onReaderThreadListener!=null){
                        onReaderThreadListener.onfail("sendMessage"+e.toString());
                    }

                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 在已有的连接上发送消息
     */
    public void  sendMessage(ICommand cmd){
        iTransfer.sendCmd(cmd);
    }





    /**
     *   WiFi连接失败
     */

    public  void connectFail(){
        if(timer!=null){
            isConnectResult=true;
            timer.cancel();
        }
        unregisterReceiver();
        if(onReaderThreadListener!=null){
            onReaderThreadListener.onfail("wificonnect");
        }

    }


    /**
     * 设置消息监听用来监听终端回复的消息
     * @param onCmdListener
     */
    public void setOnCmdListener(OnCmdListener onCmdListener) {
        this.onCmdListener = onCmdListener;
    }

    /**
     * 注册WiFi连接广播
     */
    private void registeWifiConnectBroadCast() {
        if(wifiConnectBroadcast==null){
            wifiConnectBroadcast = new WifiConnectBroadcast(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            context.registerReceiver(wifiConnectBroadcast, intentFilter);
        }

    }



    IConnectListener connectListener=new IConnectListener() {
        @Override
        public void finsh() {
            if(timer!=null){
                timer.onFinish();
            }
            if(iTransfer!=null){
                LogUtil.i("iTransfer destory");
                onReaderThreadListener=null;
                iTransfer.destroy();
            }
        }
    };


    /**
     * 定时器
     */
    private CountDownTimer timer = new CountDownTimer(Configure.WIFICONNNECTTIME, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
         LogUtil.i((millisUntilFinished / 1000) + "秒后连接下一个热点");

        }

        @Override
        public void onFinish() {
            LogUtil.i("timer onFinish");
            if(!isConnectResult){
                unregisterReceiver();
                if(onReaderThreadListener!=null){
                    onReaderThreadListener.onfail("wificonnect");
                }
                isConnectResult=true;
            }


        }
    };

    /***
     * 取消广播注册
     */

    public void  unregisterReceiver(){
        if(wifiConnectBroadcast!=null){
            try {
                context.unregisterReceiver(wifiConnectBroadcast);
            }catch (Exception e){

            };
            wifiConnectBroadcast=null;
        }
    }

    /**
     * 设置连接监听
     * @param onReaderThreadListener
     */
    public void setOnReaderThreadListener(OnReaderThreadListener onReaderThreadListener) {
        this.onReaderThreadListener = onReaderThreadListener;
    }

}
