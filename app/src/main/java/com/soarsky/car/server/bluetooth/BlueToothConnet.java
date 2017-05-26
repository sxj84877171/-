package com.soarsky.car.server.bluetooth;

import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeAdapter;
import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.App;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.cmd.BaseCmd;
import com.soarsky.car.server.cmd.CmdManage;
import com.soarsky.car.server.design.OnCmdListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.LogUtil;

import java.io.ByteArrayOutputStream;

import static com.soarsky.car.Constants.APPLY_FIVE;
import static com.soarsky.car.Constants.APPLY_FOUR;
import static com.soarsky.car.Constants.APPLY_ONE;
import static com.soarsky.car.Constants.APPLY_THREE;
import static com.soarsky.car.Constants.APPLY_TWO;
import static com.soarsky.car.Constants.CAR_RECORD;
import static com.soarsky.car.Constants.CHECK_ALIVE;
import static com.soarsky.car.Constants.DANGEROUS_REAVER;
import static com.soarsky.car.Constants.DRIVER_LEAVE;
import static com.soarsky.car.Constants.MOVE_CAR;
import static com.soarsky.car.Constants.READ_TROUBLE;
import static com.soarsky.car.Constants.READ_UNREADTICKET_COUNT;
import static com.soarsky.car.Constants.SYNC_DERANUM;
import static com.soarsky.car.Constants.SYNC_TICKET;
import static com.soarsky.car.Constants.SYNC_TIME;
import static com.soarsky.car.Constants.TERMINAL_PARAM_SET;
import static com.soarsky.car.Constants.TERMINAL_SOUNT_PARAM;
import static com.soarsky.car.Constants.TERMINAL_TRANSFER_DATAPACKAGE;
import static com.soarsky.car.Constants.TERMINAL_UPDATE;
import static com.soarsky.car.Constants.TERM_SLEEP;
import static com.soarsky.car.Constants.TICKET_SYNC;
import static com.soarsky.car.Constants.TICKET_UNREAD;
import static com.soarsky.car.Constants.TRAVEL_NOMALY;
import static com.soarsky.car.Constants.VOLUME_SET;

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


public class BlueToothConnet implements BluetoothIBridgeAdapter.EventReceiver, BluetoothIBridgeAdapter.DataReceiver {


    private final static String IVT_GATT_SERVICE_UUID = "0000ff00-0000-1000-8000-00805f9b34fb";
    private final static String IVT_GATT_NOTIFY_CHARC_UUID = "0000ff01-0000-1000-8000-00805f9b34fb";
    private final static String IVT_GATT_WRITE_CHARC_UUID = "0000ff02-0000-1000-8000-00805f9b34fb";

    private final static String IVT_BRACELET_NOTIFY_CHARC_UUID = "0000ff01-0000-1000-8000-00805f9b34fb";

    private final static String ISSC_GATT_SERVICE_UUID = "49535343-FE7D-4AE5-8FA9-9FAFD205E455";
    private final static String ISSC_GATT_NOTIFY_CHARC_UUID = "49535343-1E4D-4BD9-BA61-23C647249616";
    private final static String ISSC_GATT_WRITE_CHARC_UUID = "49535343-8841-43F4-A8D4-ECBE34729BB3";



    private BluetoothIBridgeAdapter mAdapter;
    /**
     * 请求回调
     */
    private OnConnectListener connectListener;

    /**
     * 申请类型
     */
    private int applyType;


    private Context context;


    private OnCmdListener onCmdListener;

    /**
     * 数据缓存输出流
     */
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();


    /**
     * 返回数据长度
     */
    private int count = 0;


    /**
     * 连接的车辆
     */
    private Car car;


    /**
     * 发送数据包编号
     */
    private int  packageCode;


    /**
     * 发送的数据包内容
     */
    private String strContent;
    private Handler workHandler ;

    //懒汉式单例类.在第一次调用的时候实例化自己
    private BlueToothConnet() {
        HandlerThread handlerThread = new HandlerThread("receiver data");
        handlerThread.start();
        workHandler = new Handler(handlerThread.getLooper());
    }

    private static BlueToothConnet single = null;



    private BluetoothIBridgeDevice  mSelectedDevice;

    //静态工厂方法
    public static synchronized BlueToothConnet getInstance() {
        if (single == null) {
            single = new BlueToothConnet();
        }
        return single;
    }

    /**
     * 连接终端
     *
     * @param car
     * @param type 申请类型
     */


    public void connectDervice(Car car, int type) {
        timer.start();
        applyType = type;
        this.car = car;
        mSelectedDevice=car.getDevice();
        if (car.getDevice().isConnected()) {
            sendMessage(applyType);
        } else {
            if (mAdapter != null) {
                mAdapter.connectDevice(car.getDevice());
            }

        }
    }


    /**
     * 连接终端
     *
     * @param car
     * @param type 申请类型
     *  @param  packageCode  传送的数据包编号
     */


    public void connectDervice(Car car, int type,int packageCode) {
        timer.start();
        applyType = type;
        this.car = car;
        this.packageCode=packageCode;
        mSelectedDevice=car.getDevice();
        if (car.getDevice().isConnected()) {
            sendMessage(applyType);
        } else {
            if (mAdapter != null) {
                mAdapter.connectDevice(car.getDevice());
            }

        }
    }


    /**
     *
     * @param car
     * @param type
     * @param content 传送的数据内容
     */
    public void connectDervice(Car car, int type,String content) {
        timer.start();
        applyType = type;
        this.car = car;
        this.strContent=content;
        mSelectedDevice=car.getDevice();
        if (car.getDevice().isConnected()) {
            sendMessage(applyType);
        } else {
            if (mAdapter != null) {
                mAdapter.connectDevice(car.getDevice());
            }

        }
    }



    byte[] data = null;
    /**
     * 发送消息
     */
    public void sendMessage(int applyType) {

        switch (applyType) {
            case DANGEROUS_REAVER:
                data = CmdManage.getInstance().getDangerousDriving(car).toBytes();
                break;
            case MOVE_CAR:
                data = CmdManage.getInstance().getMoveCar(car).toBytes();
                break;
            case APPLY_ONE:
                data = CmdManage.getInstance().getApplyOne(car).toBytes();
                break;
            case APPLY_TWO:
                data = CmdManage.getInstance().getApplyTwo(car).toBytes();
                break;
            case APPLY_THREE:
                data = CmdManage.getInstance().getApplyThree(car).toBytes();
                break;
            case APPLY_FOUR:
                data = CmdManage.getInstance().getApplyFour(car).toBytes();
                break;
            case APPLY_FIVE:
                data = CmdManage.getInstance().getApplyFive(car).toBytes();
                break;
            case TICKET_UNREAD:
                data = CmdManage.getInstance().getUnReadTicket(car,packageCode).toBytes();
                break;
            case TRAVEL_NOMALY:
                data = CmdManage.getInstance().getErrorDriver(car).toBytes();
                break;

            case CHECK_ALIVE:
                data=CmdManage.getInstance().CheckDriverAlive().toBytes();
                break;
            case SYNC_DERANUM:
                data = CmdManage.getInstance().getDearNumRequestBlueTooth(car).toBytes();
                break;
            case CAR_RECORD:
                data = CmdManage.getInstance().getCarStatus(car).toBytes();
                break;
            case VOLUME_SET:
                data = CmdManage.getInstance().setVolume(car).toBytes();
                break;

            case DRIVER_LEAVE:
                data=CmdManage.getInstance().DriverLeave(car).toBytes();
                break;
            case READ_TROUBLE:
                data = CmdManage.getInstance().getTroubleCheck(car).toBytes();
                break;
            case SYNC_TIME:
                data=CmdManage.getInstance().SyncTime().toBytes();
                break;

            case TERMINAL_UPDATE:
                data=CmdManage.getInstance().TerminalUpdate().toBytes();
                break;
            case TERMINAL_TRANSFER_DATAPACKAGE:
                data=CmdManage.getInstance().TermialTransfer(packageCode).toBytes();
                break;

            case TERMINAL_PARAM_SET:
                data=CmdManage.getInstance().TerminalParamSet(car,strContent).toBytes();
                break;
            case TERMINAL_SOUNT_PARAM:
                data=CmdManage.getInstance().TerminalParamVoiceSet(car,strContent).toBytes();
                break;

            case READ_UNREADTICKET_COUNT:
                data=CmdManage.getInstance().getUnReadTicketCount(car).toBytes();
                break;
            case SYNC_TICKET:
                data=CmdManage.getInstance().syncTicket(car).toBytes();
                break;


        }

        try {
            Thread.sleep(TERM_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(null!=data&&null!=mAdapter){
            workHandler.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.send(car.getDevice(), data, data.length);
//                    workHandler.postDelayed(reSendRunnable,5*1000);

                }
            });

        }
        LogUtil.i("发送数据" + applyType + "     " + ByteUtil.bytearrayToHexString(data, data.length));
    }


    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setBluetoothAdapter(BluetoothIBridgeAdapter adapter) {
        if (adapter != null) {
            mAdapter = adapter;
            mAdapter.registerEventReceiver(this);
            mAdapter.registerDataReceiver(this);
        } else {
            mAdapter.unregisterEventReceiver(this);
            mAdapter.unregisterDataReceiver(this);
            mAdapter = null;
        }
    }


    /**
     * 设置连接监听
     */
    public void setConnectListener(OnConnectListener connectListener) {
        this.connectListener = connectListener;
    }

    /**
     * 清空连接监听
     */
    public void clearConnectListener() {
        this.connectListener = null;
    }


    /**
     * 设置监听器
     *
     * @param packetListener
     */
    public void setPacketListener(OnCmdListener packetListener) {
        this.onCmdListener = packetListener;
    }

    @Override
    public void onBluetoothOn() {

    }

    @Override
    public void onBluetoothOff() {

    }

    @Override
    public void onDiscoveryFinished() {

    }

    @Override
    public void onDeviceBonding(BluetoothIBridgeDevice bluetoothIBridgeDevice) {

    }

    @Override
    public void onDeviceBonded(BluetoothIBridgeDevice bluetoothIBridgeDevice) {

    }

    @Override
    public void onDeviceBondNone(BluetoothIBridgeDevice bluetoothIBridgeDevice) {

    }

    @Override
    public void onDeviceFound(BluetoothIBridgeDevice bluetoothIBridgeDevice) {

    }

    @Override
    public void onDeviceConnected(BluetoothIBridgeDevice bluetoothIBridgeDevice) {
        if (mSelectedDevice != null) {
            if (mSelectedDevice.getDeviceType() == BluetoothIBridgeDevice.DEVICE_TYPE_BLE){
                if (isServiceExist(mSelectedDevice, IVT_GATT_SERVICE_UUID.toString())) {
                    mAdapter.bleSetTargetUUIDs(mSelectedDevice, IVT_GATT_SERVICE_UUID.toString()
                            , IVT_GATT_NOTIFY_CHARC_UUID.toString(), IVT_GATT_WRITE_CHARC_UUID.toString());
                    mAdapter.bleSetMtu(mSelectedDevice, 10);

                } else if (isServiceExist(mSelectedDevice, ISSC_GATT_SERVICE_UUID.toString())){
                    mAdapter.bleSetTargetUUIDs(mSelectedDevice, ISSC_GATT_SERVICE_UUID.toString()
                            , ISSC_GATT_NOTIFY_CHARC_UUID.toString(), ISSC_GATT_WRITE_CHARC_UUID.toString());
                }

            }
        }


        sendMessage(applyType);
        LogUtil.i("onDeviceConnected");

    }

    @Override
    public void onDeviceDisconnected(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {
        LogUtil.i("onDeviceDisconnected" + s);
    }

    @Override
    public void onDeviceConnectFailed(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {
        LogUtil.i("onDeviceDisconnected" + s);
        timer.cancel();
        if (connectListener != null) {
            connectListener.onFailed("onDeviceConnectFailed");
        }

    }

    @Override
    public void onWriteFailed(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {
        timer.cancel();
        if (connectListener != null) {
            connectListener.onFailed("onWriteFailed");
        }

        LogUtil.i("onWriteFailed" + s);
    }

    @Override
    public void onLeServiceDiscovered(BluetoothIBridgeDevice bluetoothIBridgeDevice, String s) {

    }


    /**
     * 修改念包的问题，如果头包就没有达到10个字节，会解析头出问题。
     * 防止对方2个包一起发送，丢包的问题。
     * 防止包发送黏贴，使得包出现不完整情况
     * 防止包内容出错，后面包无法接收问题
     * @param bluetoothIBridgeDevice
     * @param bytes
     * @param i
     */
    @Override
    public void onDataReceived(final BluetoothIBridgeDevice bluetoothIBridgeDevice,final byte[] bytes,final int i) {
        workHandler.post(new Runnable() {
            @Override
            public void run() {

                // 如果接收到数据 取消重发
                workHandler.removeCallbacks(reSendRunnable);

                //把蓝牙接收到的数据流写入到缓冲区
                buffer.write(bytes, 0, i);
                // 从缓冲区读取内容
                byte[] buff = buffer.toByteArray();
                // 当前缓冲区的内容长度是否已经足够分析包头
                if (buff.length >= BaseCmd.getCmdHeaderLength()) {
                    //从当前缓冲区中的内容定位起点mark标志，同时对包做校验，如果不是以标志位开头，将会忽略该数据包。
                    int mark = BaseCmd.indexOfMark(buff);
                    // 包的标志已找到
                    if (mark != -1) {
                        // 包的标志不是从0开始，那丢掉标志位前面的数据
                        if (mark != 0) {
                            int len = buff.length - mark;
                            buff = new byte[len];
                            System.arraycopy(buffer.toByteArray(), mark, buff, 0, len);
                        }
                        // 重新校验新包的数据还是否足够解析长度
                        if (buff.length >= BaseCmd.getCmdHeaderLength()) {
                            // 从新的数据包里面去解析数据长度
                            int length = BaseCmd.parseLength(buff) + BaseCmd.getCmdHeaderLength() + BaseCmd.CRC_LENGTH;
                            // 数据包已经接收完整
                            if (length <= buff.length) {
                                // 分析数据包头里面的命令，更具对应的命令解析数据包
                                BaseCmd cmd = BaseCmd.parseCmd(buff, length);
                                LogUtil.i("返回类型：" + cmd.parseMsgId(buff));
                                LogUtil.i("返回结果2：" + ByteUtil.bytearrayToHexString(buff, buff.length));
                                // 解析出对应的命令，往外发送
                                if (cmd != null && onCmdListener != null) {
                                    onCmdListener.onNewCmd(cmd);

                                    timer.cancel();
                                }
                                // 已经解析出包的数据，重置当前缓冲区的内容
                                buffer.reset();
                                // 如果上个包里面已接收到下个包的数据，则把下个包的数据放到重新注入
                                if (buff.length - length > 0) {
                                    buffer.write(buff, length, buff.length - length);
                                }
                            }
                        }
                    }
                }

                // 防止第三方软件攻击，使得缓冲区爆炸
                if (buffer.size() >= 1024 * 1024) {
                    buffer.reset();
                }

                // 超时设置，如果数据在给定的2秒内没有接收完成，则直接丢弃当前数据
                workHandler.removeCallbacks(runnable);
                workHandler.postDelayed(runnable,2000);

            }
        });

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            buffer.reset();
        }
    };





    private Runnable reSendRunnable=new Runnable() {
        @Override
        public void run() {

            if(mAdapter!=null&&data!=null){
                mAdapter.send(car.getDevice(), data, data.length);
                LogUtil.i("发送数据" + applyType + "     " + ByteUtil.bytearrayToHexString(data, data.length));
            }

        }
    };



    /**
     * 定时器
     */
    private CountDownTimer timer = new CountDownTimer(35000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (connectListener != null) {
                connectListener.onFailed("1");
            }

        }
    };


    private boolean isServiceExist(BluetoothIBridgeDevice bluetoothDevice, String uuid) {
        boolean exist = false;

        if (bluetoothDevice.getGattServices() != null) {
            for (BluetoothGattService gattService : bluetoothDevice.getGattServices()) {
                if (gattService.getUuid().toString().toUpperCase().equals(uuid.toUpperCase())) {
                    exist = true;
                    break;
                }
            }
        }
        return exist;
    }


    /**
     * 断开连接
     */

    public   void disConnected(){
        if(mAdapter!=null){
            mAdapter.disconnectDevice(mSelectedDevice);
        }

    }

}
