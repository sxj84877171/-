package com.soarsky.car.server.wifi;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.util.Log;

import com.soarsky.car.App;
import com.soarsky.car.base.Configure;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.cmd.CmdManage;
import com.soarsky.car.server.cmd.SyncData;
import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.server.design.IConnectCar;
import com.soarsky.car.server.design.IConnectListener;
import com.soarsky.car.server.design.ITransfer;
import com.soarsky.car.server.design.OnCmdListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.server.design.OnReaderThreadListener;
import com.soarsky.car.ui.home.main.OnVolumeListener;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.WifiUtil;

import java.net.Socket;

import static com.soarsky.car.Constants.APPLY_FIVE;
import static com.soarsky.car.Constants.APPLY_FOUR;
import static com.soarsky.car.Constants.APPLY_ONE;
import static com.soarsky.car.Constants.APPLY_THREE;
import static com.soarsky.car.Constants.APPLY_TWO;
import static com.soarsky.car.Constants.CHECK_ALIVE;
import static com.soarsky.car.Constants.DANGEROUS_REAVER;
import static com.soarsky.car.Constants.MOVE_CAR;
import static com.soarsky.car.Constants.SYNC_DERANUM;
import static com.soarsky.car.Constants.TICKET_SYNC;
import static com.soarsky.car.Constants.TICKET_UNREAD;
import static com.soarsky.car.Constants.TRAVEL_NOMALY;
import static com.soarsky.car.Constants.VOLUME_SET;

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
 * 程序功能：连接热点和数据发送<br>
 * 该类为 与终端通讯服务类<br>
 */

public class ConnectCar implements IConnectCar {

    private Context context;
    /**
     * 连接的对象
     */
    private Car car;
    /**
     * 与终端通讯服务类
     */
    public ITransfer iTransfer;
    /**
     * 热点广播
     */
    private WifiConnectBroadcast wifiConnectBroadcast;

    /**
     * 连接监听
     */
    private OnReaderThreadListener onReaderThreadListener;


    /**
     * 数据返回回调接口
     */
    private OnCmdListener onCmdListener;
    /**
     * 消息类型
     */
    private int meeeageType;
    /**
     * 是否有连接返回
     */
    private boolean isConnectResult=false;


    /**
     * 连接的ssid
     */
    private  String ssid;

    private int volume =3;

    private OnVolumeListener onVolumeListener;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

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
        this.car = car;
        ssid=car.getSsid();
        //保存当前连接的热点
        LogUtil.i("请求通道："+type+"SSID:"+car.getSsid());
        registeWifiConnectBroadCast();
        if(WifiUtil.getInstance().getCurrentnetId()!=-1){
            App.getApp().setNetId(WifiUtil.getInstance().getCurrentnetId());
            WifiUtil.getInstance().disconnectWifi();
            if(wifiConnectBroadcast!=null){
                wifiConnectBroadcast.setType(1);
            }
        }else{
            WifiUtil.getInstance().disconnectWifi();
            connectNext();
        }

    }

    /**
     * 连接车辆
     * @param car
     */
    public synchronized void connect(final Car car){

        this.car = car;
        ssid=car.getSsid();
        //保存当前连接的热点
        LogUtil.i("请求通道："+"SSID:"+car.getSsid());
        registeWifiConnectBroadCast();
        if(WifiUtil.getInstance().getCurrentnetId()!=-1){
            App.getApp().setNetId(WifiUtil.getInstance().getCurrentnetId());
            WifiUtil.getInstance().disconnectWifi();
            if(wifiConnectBroadcast!=null){
                wifiConnectBroadcast.setType(1);
            }
        }else{

            connectNext();
        }
    }


    /**
     * 连接指定热点
     */
    public void connectNext(){

        if(wifiConnectBroadcast!=null){
            wifiConnectBroadcast.setType(2);
        }
        int netWorkId=WifiUtil.getInstance().getNetWorkId(car.getSsid());
        if(netWorkId!=-1){
            WifiUtil.getInstance().connectWifi(netWorkId);
        }else{
            boolean result = WifiUtil.getInstance().connectWifi(car);
        }
        isConnectResult=false;
        timer.start();

    }




    @Override
    public void setOnConnectListener(OnConnectListener onConnectListener) {

    }

    public void  setVolumeListener(OnVolumeListener listener){
        this.onVolumeListener = listener;
    }

    /**
     * 获取当前连接的车辆
     * @return 车对象
     */
    public Car getCar() {
        return car;
    }


    /**
     * 发送消息
     * @param car 车对象
     */
    public void sendMessage(final Car car) {
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

                    socket.setSoTimeout(30000);
                    iTransfer = new WifiTransfer(onReaderThreadListener);
                    iTransfer.setOutputstream(socket.getOutputStream());
                    iTransfer.setConnectListener(connectListener);

                    iTransfer.setInputstream(socket.getInputStream());
                    iTransfer.start();
                    iTransfer.setOnPacketListener(onCmdListener);



                    switch (meeeageType){
                        case DANGEROUS_REAVER:
                            iTransfer.sendCmd(CmdManage.getInstance().getDangerousDriving(car));
                            break;
                        case MOVE_CAR:
                            iTransfer.sendCmd(CmdManage.getInstance().getMoveCar(car));
                            break;
                        case APPLY_ONE:
                            iTransfer.sendCmd(CmdManage.getInstance().getApplyOne(car));
                            break;
                        case APPLY_TWO:
                            iTransfer.sendCmd(CmdManage.getInstance().getApplyTwo(car));
                            break;
                        case APPLY_THREE:
                            iTransfer.sendCmd(CmdManage.getInstance().getApplyThree(car));
                            break;
                        case APPLY_FOUR:
                            iTransfer.sendCmd(CmdManage.getInstance().getApplyFour(car));
                            break;
                        case APPLY_FIVE:
                            iTransfer.sendCmd(CmdManage.getInstance().getApplyFive(car));
                            break;
                        case TICKET_UNREAD:
//                            iTransfer.sendCmd(CmdManage.getInstance().getUnReadTicket(car));
                            break;
                        case TRAVEL_NOMALY:
//                            iTransfer.sendCmd(CmdManage.getInstance().getErrorDriver(car));
                            break;
                        case TICKET_SYNC:
                            iTransfer.sendCmd(CmdManage.getInstance().syncTicket(car));
                            break;
                        case CHECK_ALIVE:
                            iTransfer.sendCmd(CmdManage.getInstance().getTroubleCheck(car));
                            break;
                        case SYNC_DERANUM:
                            SyncData syncData=new SyncData(context,ConnectCar.this);
                            syncData.getUnSyncFamilyNum();
                            break;
                        case VOLUME_SET:
//                            SyncData syn=new SyncData(context,ConnectCar.this);
//                            syn.syncVolume(getVolume());

                            Log.d("TAAG","go volume set......listener=="+onVolumeListener);
                            if(onVolumeListener!= null) {
                                onVolumeListener.onVolumeSuccess((WifiTransfer)iTransfer,socket);
                            }


                            break;

                    }

                } catch (Exception e) {
                    if(iTransfer!=null){
                        iTransfer.destroy();
                    }
                    if(onReaderThreadListener!=null){
                        onReaderThreadListener.onfail("sendMessage"+e.toString());
                    }

                    if(onVolumeListener!= null) {
                        onVolumeListener.onVolumeFailed();
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

        /**
         *  清空当前连接的配置信息
         */
        WifiUtil.getInstance().removeNetWorkId(ssid);
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


    /**
     *判断一次通讯交互是否完成
     */
    IConnectListener connectListener=new IConnectListener() {
        @Override
        public void finsh() {




            if(timer!=null){
                timer.onFinish();
            }
            if(iTransfer!=null){
                onReaderThreadListener=null;
                iTransfer.destroy();
            }
        }
    };


    /**
     * 一次连接任务完成
     */
    public  void  onDestory(){
        WifiUtil.getInstance().removeNetWorkId(ssid);
        if(connectListener!=null){
            connectListener.finsh();

        }
    }


    /**
     * 停止当前任务
     */
    public  void  stopCurrentTask(){
        WifiUtil.getInstance().removeNetWorkId(ssid);
        unregisterReceiver();
        if(timer!=null){
            timer.cancel();
        }

    }


    /**
     * 定时器
     */
    private CountDownTimer timer = new CountDownTimer(Configure.WIFICONNNECTTIME, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
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

    /**
     * 获取申请类型
     * @return 申请类型
     */
    public int getMeeeageType() {
        return meeeageType;
    }
}
