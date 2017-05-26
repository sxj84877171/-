package com.soarsky.car.server.bluetooth;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

import com.google.gson.Gson;
import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.bean.EmptySubscriber;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TermDataBean;
import com.soarsky.car.data.local.db.DriverRecordDb;
import com.soarsky.car.data.local.db.ErrorDriverDb;
import com.soarsky.car.data.local.db.ParamSetDb;
import com.soarsky.car.data.local.db.TroubleDb;
import com.soarsky.car.data.local.db.bean.DriverRecord;
import com.soarsky.car.data.local.db.bean.ErrorDriver;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.local.db.bean.ParamSet;
import com.soarsky.car.data.local.db.bean.Trouble;
import com.soarsky.car.data.remote.server1.ApiG;
import com.soarsky.car.data.remote.server1.ApiServiceImpl;
import com.soarsky.car.server.cmd.BaseCmd;
import com.soarsky.car.server.cmd.CheckDriverAliveCommandResponse;
import com.soarsky.car.server.cmd.ConnectCarCommandResponse;
import com.soarsky.car.server.cmd.DangerousDrivingCommandResponse;
import com.soarsky.car.server.cmd.DearNumCommandResponse;
import com.soarsky.car.server.cmd.DriverLeaveCommandResponse;
import com.soarsky.car.server.cmd.MoveCarCommandResponse;
import com.soarsky.car.server.cmd.ReadDataCommandResponse;
import com.soarsky.car.server.cmd.SyncParam;
import com.soarsky.car.server.cmd.SyncParamSound;
import com.soarsky.car.server.cmd.SyncTicketCommandResponse;
import com.soarsky.car.server.cmd.SyncTimeCommandResponse;
import com.soarsky.car.server.cmd.TerminalTransferDatapackageCommandResponse;
import com.soarsky.car.server.cmd.TerminalUpdateCommandResponse;
import com.soarsky.car.server.cmd.TicketCommandResponse;
import com.soarsky.car.server.cmd.TicketCountCommadnResponse;
import com.soarsky.car.server.cmd.TicketRead;
import com.soarsky.car.server.cmd.TroubleCommandResponse;
import com.soarsky.car.server.cmd.UpdateTerminalNumCommanResponse;
import com.soarsky.car.server.cmd.UpdateTerminalNumVoiceCommandResponse;
import com.soarsky.car.server.cmd.VolumeCommandResponse;
import com.soarsky.car.data.local.db.FamilyNumDb;
import com.soarsky.car.ui.ticketed.TicketDb;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.NotifictionUtils;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.TerminalUpdateUtil;

import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/3/22
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能： 与终端进行数据交互 交互流程  驾车随行  终端升级 升级数据传输 参数设置 语音设置 同步时间 同步亲情号 读取故障编号 读取罚单 同步罚单
 * 该类为 数据反回解析
 */


public class BuleToothDataAnalyze {
    /**
     * 同步参数
     */
    public static final int PARAM_SET = 1;
    /**
     * 同步语音
     */
    public static final int PARAM_SOUND = 1;


    private SyncParam syncParam;

    private SyncParamSound syncParamSound;

    BlueToothManage blueToothAutoConfirmDriver;
    private App app;

    TicketRead ticketRead;


    public BuleToothDataAnalyze(BlueToothManage blueToothAutoConfirmDriver) {
        this.blueToothAutoConfirmDriver = blueToothAutoConfirmDriver;
        app = App.getApp();
        init();
    }

    public void setCmd(BaseCmd cmd) {

        if (cmd instanceof ConnectCarCommandResponse) {
            ConnectCarCommandResponse connectCarCommandResponse = (ConnectCarCommandResponse) cmd;
            if (connectCarCommandResponse.isDriver()) {

                NotifictionUtils.getInstance().showNotifiction("确认驾驶员成功");
                blueToothAutoConfirmDriver.confirmDriverSucess();

            } else {
                NotifictionUtils.getInstance().showNotifiction("确认驾驶员失败");
                blueToothAutoConfirmDriver.confirmDriverFailer();
            }
        }

        //亲情号码
        if (cmd instanceof DearNumCommandResponse) {
            DearNumCommandResponse dearNumCommandResponse = (DearNumCommandResponse) cmd;
            if (dearNumCommandResponse.isSucess()) {
                LogUtil.i("设置亲情号码成功");
                NotifictionUtils.getInstance().showNotifiction("设置亲情号成功");
                FamilyNumDb.getInstance(App.getApp()).updateData();

            }
            blueToothAutoConfirmDriver.init(Constants.READ_TROUBLE);


        }
        //故障查询
        if (cmd instanceof TroubleCommandResponse) {

            NotifictionUtils.getInstance().showNotifiction("读取故障信息");
            TroubleCommandResponse troubleCommandResponse = (TroubleCommandResponse) cmd;
            TroubleDb.getInstance(app).deleteAll();
            if (troubleCommandResponse.hasTrouble()) {
                Trouble trouble = new Trouble();
                trouble.setCarNum(app.getCommonParam().getCarNum());
                trouble.setTroubleMessage(troubleCommandResponse.getTroubleStr());
                trouble.setCreateTime(new Date());
                trouble.setStatus("0");
                TroubleDb.getInstance(app).insertData(trouble);
                SpUtil.save("TROUBLESTR", troubleCommandResponse.getTroubleStr());
            } else {
                SpUtil.save("TROUBLESTR", "");
            }
            //TODO  读取罚单条数
            blueToothAutoConfirmDriver.init(Constants.READ_UNREADTICKET_COUNT);

        }
        if (cmd instanceof TicketCommandResponse) {

            if (null != ticketRead) {
                ticketRead.setCmd(cmd);
            }
            NotifictionUtils.getInstance().showNotifiction("读取罚单");


        }

        if (cmd instanceof ReadDataCommandResponse) {
            ReadDataCommandResponse readDataCommandResponse = (ReadDataCommandResponse) cmd;

            if (readDataCommandResponse.getRequestType() == 2) {
                //车况
                DriverRecord driverRecord = new DriverRecord();
                driverRecord.setContent(readDataCommandResponse.getContentStr());
                DriverRecordDb.getInstance(app).insertData(driverRecord);
            }
            if (readDataCommandResponse.getRequestType() == 1) {
                //行驶异常数据
                ErrorDriver errorDriver = new ErrorDriver();
                errorDriver.setContent(readDataCommandResponse.getContentStr());
                ErrorDriverDb.getInstance(app).insertData(errorDriver);

            }


        }
        if (cmd instanceof DangerousDrivingCommandResponse) {
            NotifictionUtils.getInstance().showNotifiction("开车玩手机");
        }
        if (cmd instanceof VolumeCommandResponse) {

        }
        if (cmd instanceof MoveCarCommandResponse) {
            NotifictionUtils.getInstance().showNotifiction("请人移车");
            MoveCarCommandResponse moveCarCommandResponse = (MoveCarCommandResponse) cmd;
            //TODO 告知界面返回结果
            blueToothAutoConfirmDriver.moverCallBack(moveCarCommandResponse.getResponseType() + "");

        }

        if (cmd instanceof CheckDriverAliveCommandResponse) {
            NotifictionUtils.getInstance().showNotifiction("驾车随行");
            CheckDriverAliveCommandResponse checkDriverAliveCommandResponse = (CheckDriverAliveCommandResponse) cmd;
            if (checkDriverAliveCommandResponse.isAlive()) {
                //更新获取终端时间
                BlueToothScan.getInstance(App.getApp()).upteTime();
//                blueToothAutoConfirmDriver.setTerminalUpdate(true);

            } else {
                blueToothAutoConfirmDriver.driverLeave();
                App.getApp().setConfirmDriver(false);

            }


        }
        if (cmd instanceof SyncTimeCommandResponse) {
            //同步亲情号
            List<FamilyNum> familyNumList = FamilyNumDb.getInstance(App.getApp()).getNuSyncTerminal();
            if (FamilyNumDb.getInstance(App.getApp()).isNuSyncTerminal()) {
                blueToothAutoConfirmDriver.init(Constants.SYNC_DERANUM);
            } else {
                //读取故障信息
                blueToothAutoConfirmDriver.init(Constants.READ_TROUBLE);
            }

        }
        if (cmd instanceof TerminalTransferDatapackageCommandResponse) {
            TerminalTransferDatapackageCommandResponse terminalTransferDatapackageCommandResponse = (TerminalTransferDatapackageCommandResponse) cmd;

            if (terminalTransferDatapackageCommandResponse.getResponseCode() == 0) {
                LogUtil.i("NextPackage" + terminalTransferDatapackageCommandResponse.getNextPackageNum());
                blueToothAutoConfirmDriver.init(Constants.TERMINAL_TRANSFER_DATAPACKAGE, terminalTransferDatapackageCommandResponse.getNextPackageNum());
            } else if (terminalTransferDatapackageCommandResponse.getResponseCode() == 6) {

                String terminalId = terminalTransferDatapackageCommandResponse.getTerminalId();
                String taskId = terminalTransferDatapackageCommandResponse.getTaskid();
                String ver=terminalTransferDatapackageCommandResponse.getVerCode();
                //TOdo 升级成功
                //通知网关升级完成

                new ApiServiceImpl().terminalUpdate(terminalId,"0","",ver,taskId).subscribeOn(Schedulers.computation())
                        .subscribe(new EmptySubscriber());
                blueToothAutoConfirmDriver.setTerminalUpdate(false);
                setTerminalParam();

            } else {
                //TOdo 升级失败
                String  terminalId=terminalTransferDatapackageCommandResponse.getTerminalId();
                String  taskId=terminalTransferDatapackageCommandResponse.getTaskid();
                String errmsg="";
                switch (terminalTransferDatapackageCommandResponse.getResponseCode()){
                    case 1:
                        errmsg="车牌号不对";
                    break;
                    case 2:
                        errmsg="驾照号不对";
                        break;
                    case 3:
                        errmsg="消息有误";
                        break;
                    case 4:
                        errmsg="授权码错误";
                        break;
                    case 5:
                        errmsg="不支持";
                        break;

                }

                new ApiServiceImpl().terminalUpdate(terminalId,"5",errmsg,App.getApp().getTerminalVer(),taskId).subscribeOn(Schedulers.computation())
                        .subscribe(new EmptySubscriber());

                setTerminalParam();


            }


        }
        if (cmd instanceof TerminalUpdateCommandResponse) {
            final TerminalUpdateCommandResponse terminalUpdateCommandResponse = (TerminalUpdateCommandResponse) cmd;
            //终端返回是否需要升级  需要就升级 否则进入终端参数设置
            if (terminalUpdateCommandResponse.hasUpdate()) {

                //智能终端提示有升级的时候 还需要请求网关是不是最新版本 是在升级

                String termData = SpUtil.get(Constants.TermData);
                String verCode = "";
                if (termData != null && !(termData.equals(""))) {
                    Gson gson = new Gson();
                    TermDataBean bean = gson.fromJson(termData, TermDataBean.class);
                    verCode = bean.getParams().getVer();

                }

                ApiG.getInstance().getService().queryVer(terminalUpdateCommandResponse.getTerminalId(), verCode).subscribeOn(Schedulers.computation())
                        .subscribe(new Subscriber<ResponseDataBean<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        setTerminalParam();
                    }

                    @Override
                    public void onNext(ResponseDataBean<String> stringResponseDataBean) {
                        if (stringResponseDataBean.getStatus().equals(Constants.REQUEST_SUCCESS)) {
                            blueToothAutoConfirmDriver.init(Constants.TERMINAL_TRANSFER_DATAPACKAGE, terminalUpdateCommandResponse.getNextPackageNum());
                        } else {
                            setTerminalParam();
                        }

                    }
                });




            } else {


                if (terminalUpdateCommandResponse.getResponseCode() == 6) {
                    new ApiServiceImpl().terminalUpdate(terminalUpdateCommandResponse.getTerminalId(), "1", "", App.getApp().getTerminalVer(), terminalUpdateCommandResponse.getTaskId()).subscribeOn(Schedulers.computation())
                            .subscribe(new EmptySubscriber());

                }
                setTerminalParam();
            }
        }
        if (cmd instanceof UpdateTerminalNumCommanResponse) {
            if (syncParam != null) {
                syncParam.setCmd(cmd);
            }
        }

        if (cmd instanceof UpdateTerminalNumVoiceCommandResponse) {
            if (syncParamSound != null) {
                syncParamSound.setCmd(cmd);
            }

        }


        if(cmd instanceof SyncTicketCommandResponse){
            //标记已上传的罚单，更新数据库数据
            SyncTicketCommandResponse syncTicketCommandResponse= (SyncTicketCommandResponse) cmd;
            if(syncTicketCommandResponse.issucess()){


                int[] ids = syncTicketCommandResponse.getIds();
                if (null != ids) {
                    for (int i = 0; i < ids.length; i++) {
                        TicketDb.getInstance(app).updateTerminalStutus(ids[i]);

                    }
                }

            }

        }


        if (cmd instanceof TicketCountCommadnResponse) {

            TicketCountCommadnResponse ticketCountCommadnResponse = (TicketCountCommadnResponse) cmd;

            if (ticketCountCommadnResponse.isSucess()) {
                int ticketCount = ticketCountCommadnResponse.getTicketCount();
                if (ticketCount > 0) {
                    ticketRead = new TicketRead(ticketCount, blueToothAutoConfirmDriver);
                    ticketRead.setParamSetCallback(new Callback() {
                        @Override
                        public void onfinsh(int type) {
                                //罚单读取完成 同步已上传罚单
                            blueToothAutoConfirmDriver.init(Constants.SYNC_TICKET);


                            blueToothAutoConfirmDriver.setTerminalUpdate(false);

                            blueToothAutoConfirmDriver.disConnect();
                            ticketRead = null;
                 //断开连接
//                            blueToothAutoConfirmDriver.disConnect();

                        }
                    });
                }


            } else {
                blueToothAutoConfirmDriver.setTerminalUpdate(false);
                blueToothAutoConfirmDriver.disConnect();
            }


        }if(cmd instanceof DriverLeaveCommandResponse){
            BlueToothScan.getInstance(App.getApp()).isScan(true);

        }


    }

    /**
     * 初始化一些数据
     */
    private void init() {
        syncParam = SyncParam.getInstance(blueToothAutoConfirmDriver);
        syncParam.setParamSetCallback(new Callback() {
            @Override
            public void onfinsh(int type) {
                syncParamSound.getNuTerminal();


            }
        });

        syncParamSound = SyncParamSound.getInstance(blueToothAutoConfirmDriver);
        syncParamSound.setParamSetCallback(new Callback() {
            @Override
            public void onfinsh(int type) {
                NotifictionUtils.getInstance().showNotifiction("设置终端语音完成");
                blueToothAutoConfirmDriver.init(Constants.SYNC_TIME);


            }
        });
    }


    /**
     * 回调接口 用来告知同步参数已经完成
     */
    public static interface Callback {
        void onfinsh(int type);
    }




    /**
     * 设置终端参数
     */
    public  void setTerminalParam() {
        List<ParamSet> paramSetList = ParamSetDb.getInstance(App.getApp()).getParamSetlist(0);
        if (null == paramSetList || paramSetList.size() == 0) {
            //终端参数设置
            SyncParamSound.getInstance(blueToothAutoConfirmDriver).getNuTerminal();
        } else {
            //终端语音设置
            SyncParam.getInstance(blueToothAutoConfirmDriver).getNuTerminal();
        }

    }

}
