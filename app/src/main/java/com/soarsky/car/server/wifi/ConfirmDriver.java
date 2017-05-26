package com.soarsky.car.server.wifi;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;

import com.soarsky.car.App;
import com.soarsky.car.base.Configure;
import com.soarsky.car.bean.Car;
import com.soarsky.car.data.local.db.BorrowDb;
import com.soarsky.car.data.local.db.DriverRecordDb;
import com.soarsky.car.data.local.db.ErrorDriverDb;
import com.soarsky.car.data.local.db.bean.DriverRecord;
import com.soarsky.car.data.local.db.bean.ErrorDriver;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.local.db.bean.Tborrow;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.server.cmd.BaseCmd;
import com.soarsky.car.server.cmd.CmdManage;
import com.soarsky.car.server.cmd.ConnectCarCommandResponse;
import com.soarsky.car.server.cmd.DangerousDrivingCommandResponse;
import com.soarsky.car.server.cmd.DearNumCommandResponse;
import com.soarsky.car.server.cmd.ReadDataCommandResponse;
import com.soarsky.car.server.cmd.TicketCommandResponse;
import com.soarsky.car.server.cmd.TroubleCommandResponse;
import com.soarsky.car.server.cmd.VolumeCommandResponse;
import com.soarsky.car.server.design.ConfirmDriverSucessCallBack;
import com.soarsky.car.server.design.IAutoConfirmDriverListener;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnCmdListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.server.design.OnReaderThreadListener;
import com.soarsky.car.ui.ticketed.TicketDb;
import com.soarsky.car.ui.family.FamilyNumDb;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.WifiHotUtils;
import com.soarsky.car.uitl.WifiUtil;

import java.util.Date;
import java.util.List;

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
 * 程序功能：与热点的自动和手动连接
 * 该类为 与终端通讯服务类
 */

public class ConfirmDriver extends HandlerThread {
    private Context context;
    public static final String TAG = ConfirmDriver.class.getSimpleName();
    //用来保存扫描到的热点车辆
    private static List<Car> autocarList;
    private Handler handler;
    private ConnectCar connectCar;
    private boolean isConnnetFinish = false;//判断所有热点是否全部连接完成
    private int index = 0;//请求次数
    private int resultCount = 0;//热点数
    private boolean isAuto = Configure.ISAUTOCOONECT;//是否是自动连接
    private boolean isConfirmDriver = false;//是否确认驾驶员
    private boolean isConnecting = true;//是否正在连接
    private Scan iScan;
    private OnConnectListener connectListener;//连接监听
    private IAutoConfirmDriverListener iAutoConfirmDriverListener;
    private Car connectingCar;//正在连接的车辆
    private String ssid;//终端的ssid
    private App app;
    private ConfirmDriverSucessCallBack confirmDriverSucessCallBack;//确认驾驶员成功回调

    public ConfirmDriver(Context context) {
//        this(TAG, Thread.NORM_PRIORITY, context);
        super(TAG, Thread.NORM_PRIORITY);
        this.context = context;
        connectCar = new ConnectCar(context);
        iScan = Scan.getInstance(context);
        connectCar.setOnCmdListener(listener);
        app = (App) context.getApplicationContext();
        SpUtil.init(context);
        init();

    }


    /**
     * 获取热点
     */
    public void getCarList() {
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
                LogUtil.i("AUTO:autocarList" + autocarList.size());
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
//        getCarList();
    }


    /**
     * 自动连接车辆
     */
    public void connectCar(final Car car) {
        LogUtil.i("connectCar:" + car.toString() + "carStatus:" + car.getCarStatus());
        connectingCar = car;
        ssid = car.getSsid();
        index++;
        isConnecting = true;
        final String authCode = getAuthCode(car);
        if (isAuto) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //断开当前连接的wifi
                    WifiUtil.getInstance().disconnectWifi();
                    if (isDearNum(car)) {
                        connectCar.connect(car, 1);
                        connectCar.setOnReaderThreadListener(onReaderThreadListener);
                    } else if (null != authCode) {
                        connectCar.connect(car, 2);
                        connectCar.setOnReaderThreadListener(onReaderThreadListener);
                    } else {
                        onReaderThreadListener.onfail("不是亲情号也不是借车");
                    }
                }
            });
        }


    }


    /**
     * 主动连接车辆
     */
    public void handConnectCar(final Car car, final int type) {
        ssid = car.getSsid();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //断开当前连接的wifi
                WifiUtil.getInstance().disconnectWifi();
                connectCar.connect(car, type);
                connectCar.setOnReaderThreadListener(onReaderThreadListener);
            }
        });

    }


    /**
     * 设置扫描监听
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


            if (cmd instanceof ConnectCarCommandResponse) {
                isConnecting = false;
                //断开当前连接的wifi
                WifiUtil.getInstance().disconnectWifi();
                ConnectCarCommandResponse connectCarCommandResponse = (ConnectCarCommandResponse) cmd;
                LogUtil.i("ConnectCarCommandResponse" + connectCarCommandResponse.toString());

                //保存最后一次成功连接终端的毫秒数
                long endSucessTime = new Date().getTime();
                SpUtil.save("endSucessTime", endSucessTime + "");
                if (connectCarCommandResponse.isDriver()) {
                    //保存确认驾驶员的ssid
                    LogUtil.i("确认驾驶员成功" + connectListener + "isAuto" + isAuto);
                    SpUtil.save("SSID", ssid.substring(0, 5) + "4" + ssid.substring(6));
                    isDriverAlive();
                    isConfirmDriver = true;
                    if (isAuto) {
                        iAutoConfirmDriverListener.onConnectCar(connectingCar);
                    } else {
                        if (connectListener != null) {
                            connectListener.onSuccess();
                        }
                    }
                    if(confirmDriverSucessCallBack!=null){
                        confirmDriverSucessCallBack.onSucess();
                    }

                } else {
                    if (isAuto) {
                        connectFinsh();
                    } else {
                        if (connectListener != null) {
                            connectListener.onFailed("未确认驾驶员");
                        }
                    }

                }
            }
            //亲情号码
            if (cmd instanceof DearNumCommandResponse) {
                if (connectListener != null) {
                    connectListener.onFailed("未确认驾驶员");
                }
                DearNumCommandResponse dearNumCommandResponse = (DearNumCommandResponse) cmd;
                /**
                 * 更新亲情号码状态
                 */

                LogUtil.i("更新亲情号码返回");
                if (dearNumCommandResponse.isSucess()) {
                    LogUtil.i("更新亲情号码成功 修改数据库亲情号码状态");
                    FamilyNumDb.getInstance(context).updateData();
                }


            }
            //故障查询
            if (cmd instanceof TroubleCommandResponse) {

                TroubleCommandResponse troubleCommandResponse = (TroubleCommandResponse) cmd;
                if (troubleCommandResponse.hasTrouble()) {
                    LogUtil.i("收到查询故障信息 发送应答消息" + troubleCommandResponse.getTroubleStr());
                    if (connectListener != null) {
                        connectListener.onResult(troubleCommandResponse.getTroubleStr());
                    }

                    /**
                     * 告诉终端已收到消息
                     */
                    connectCar.sendMessage(CmdManage.getInstance(context).getTroubleResponse());

                } else {
                    /**
                     * 没有故障
                     */
                    if (connectListener != null) {
                        connectListener.onSuccess();
                    }
                }

            }
            if (cmd instanceof TicketCommandResponse) {
                if (connectListener != null) {
                    connectListener.onFailed("未确认驾驶员");
                }
                TicketCommandResponse ticketCommandResponse = (TicketCommandResponse) cmd;
                if (ticketCommandResponse.hasTicket()) {
                    String content = ticketCommandResponse.getContent();
                    Ticket ticket = new Ticket();
                    ticket.setCarNum(ticketCommandResponse.getCarNum());
                    ticket.setContent(content);
                    ticket.setServiceStatus(1);
                    ticket.setTerminalStatus(1);
                    ticket.setTicketNo(ticketCommandResponse.getTicketNo());
                    TicketDb.getInstance(context).inserData(ticket);
                    LogUtil.i("收到罚单信息:" + content);

//                    connectCar.sendMessage(CmdManage.syncTicket(new Ticket()));

                }
            }

            if (cmd instanceof ReadDataCommandResponse) {
                if (connectListener != null) {
                    connectListener.onFailed("未确认驾驶员");
                }
                ReadDataCommandResponse readDataCommandResponse = (ReadDataCommandResponse) cmd;
                LogUtil.i("readDataCommandResponse:" + readDataCommandResponse.getMsgLength() + "messageType:" + readDataCommandResponse.getMessageType());
                if (readDataCommandResponse.getMessageType().equals("07")) {
                    //亲情号

                }
                if (readDataCommandResponse.getMessageType().equals("06")) {
                    //车况
                    DriverRecord driverRecord = new DriverRecord();
                    driverRecord.setContent(readDataCommandResponse.getContentStr());
                    driverRecord.setRecordNo(readDataCommandResponse.getTicketNo());
                    DriverRecordDb.getInstance(context).insertData(driverRecord);

                }
                if (readDataCommandResponse.getMessageType().equals("05")) {
                    //行驶异常数据
                    ErrorDriver errorDriver = new ErrorDriver();
                    errorDriver.setContent(readDataCommandResponse.getContentStr());
                    errorDriver.setDriverNo(readDataCommandResponse.getTicketNo());
                    ErrorDriverDb.getInstance(context).insertData(errorDriver);
                }

            }
            if (cmd instanceof DangerousDrivingCommandResponse) {
                if (connectListener != null) {
                    connectListener.onFailed("未确认驾驶员");
                }
            }
            if (cmd instanceof VolumeCommandResponse) {
                if (connectListener != null) {
                    connectListener.onFailed("未确认驾驶员");
                }
            }

        }
    };


    /**
     * 判断司机是否还在
     */
    public void isDriverAlive() {
        LogUtil.i("检测驾驶员是否还在");
        final Car car = new Car();
        //TODO 正式打包记得改上
        car.setSsid(SpUtil.get("SSID"));
//        car.setSsid(ssid);
        car.setCarNum(ssid.substring(8));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //断开当前连接的wifi
                WifiUtil.getInstance().disconnectWifi();
                connectCar.connect(car, 12);
                connectCar.setOnReaderThreadListener(onReaderThreadListener);

                if (isConfirmDriver) {
                    handler.postDelayed(this, Configure.ISDRIVERALIVE);
                }
            }
        }, Configure.ISDRIVERALIVE);
    }


    /**
     * 连接监听
     */
    private OnReaderThreadListener onReaderThreadListener = new OnReaderThreadListener() {
        @Override
        public void onSucess() {
        }

        /**
         *
         * @param reason 失败原因
         */
        @Override
        public void onfail(String reason) {
            if (!isConfirmDriver&&connectListener != null) {
                connectListener.onFailed("连接失败");
            }
            LogUtil.i("onfail:" + index + "   reason :=" + reason);
            //断开当前连接的wifi
            WifiUtil.getInstance().disconnectWifi();
            if (isConfirmDriver) {
                //获取当前毫秒数
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
                if (time - endtime > 10 * 60 * 1000) {
                    //// TODO: 2016/11/29 停止重复连接
                    isConfirmDriver = false;
                   if(confirmDriverSucessCallBack!=null) {
                       confirmDriverSucessCallBack.oncancle();
                   }

                }


            } else {
                isConnecting = false;
                connectFinsh();
            }


        }

    };


    /**
     * 判断一次连接是否完成
     */
    public void connectFinsh() {
        if (isAuto) {
            //如果是自动连接 循环执行未连接的热点
            if (index == resultCount) {
                //第一批热点全部连接完成
                isConnnetFinish = true;
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
            getCarList();
        }
    }

    /**
     * 设置连接监听
     */
    public void setConnectListener(OnConnectListener connectListener) {
        this.connectListener = connectListener;
    }


    public void setIAutoConfirmDriverListener(IAutoConfirmDriverListener iAutoConfirmDriverListener) {
        this.iAutoConfirmDriverListener = iAutoConfirmDriverListener;
    }


    /**
     * 判断是不是亲情号
     */
    public boolean isDearNum(Car car) {
        String owerPhone = app.getCommonParam().getOwerPhone();
        if (owerPhone == null) {
            return false;
        }
        List<FamilyNum> list = FamilyNumDb.getInstance(context).getFamilyNumList(WifiHotUtils.getInstance().getCarNum(car.getCarNum()), owerPhone);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 是不是借车
     *
     * @param car
     * @return 授权码
     */
    public String getAuthCode(Car car) {
        String borrower = app.getCommonParam().getUserName();
        if (borrower == null) {
            return null;
        }
        List<Tborrow> list = BorrowDb.getInstance(context).getTBorrlist(car.getCarNum(), borrower);
        if (list.size() > 0) {
            Tborrow tborrow = list.get(0);
            return tborrow.getAuthcode();
        }
        return null;
    }

    /**
     * 设置确认驾驶员成功回调
     * @param confirmDriverSucessCallBack
     */
    public void setConfirmDriverSucessCallBack(ConfirmDriverSucessCallBack confirmDriverSucessCallBack) {
        this.confirmDriverSucessCallBack = confirmDriverSucessCallBack;
    }
}
