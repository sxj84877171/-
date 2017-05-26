package com.soarsky.car.server.cmd;

import com.soarsky.car.App;
import com.soarsky.car.bean.Car;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.data.local.db.FamilyNumDb;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：保存终端获取的用车记录<br>
 * 该类为 与终端通讯协议的获取<br>
 */


public class CmdManage {
    private  App app;
    public  CmdManage(){
        app= App.getApp();
    }

    /**
     * 用来缓存亲情号的集合
     */
    private List<FamilyNum> familyNumList=new ArrayList<>();


    private static CmdManage single=null;

    public static CmdManage getInstance() {
             if (single == null) {
                 single = new CmdManage();
             }
            return single;
        }

    /**
     * 故障查询
     * @return
     */
    public  ICommand getTroubleCheck(Car car){
        TroubleCommandRequest cmd=new TroubleCommandRequest();
        cmd.setMsgType(0);
        cmd.setCarNum(car.getCarNum());
        cmd.roundTroubleMsg();
        return cmd;
    }
    /**
     * 故障应答
     * @return
     */
    public  ICommand getTroubleResponse(){
        TroubleCommandRequest cmd=new TroubleCommandRequest();
        cmd.setMsgType(1);
        cmd.roundTroubleMsg();
        return cmd;
    }

    /**
     * 设置亲情号码
     * @return
     */
    public  ICommand getDearNumRequest(Car car ,String dearNum,String dearNum1){
        DearNumCommandRequest cmd=new DearNumCommandRequest();
        cmd.setDearNum(dearNum);
        cmd.setCarNum(car.getCarNum());
        cmd.setDearNum1(dearNum1);
        cmd.setAuthCode(car.getAuthNum());
        cmd.roundDearNumMsg();
        return cmd;
    }




    /**
     * 设置亲情号码
     * @return
     */
    public  ICommand getDearNumRequestBlueTooth(Car car ){
        familyNumList= FamilyNumDb.getInstance(App.getApp()).getNuSyncTerminal();
        return syncFamilyNum(car);
    }


    /**
     * 同步亲情号到终端
     * 默认4组电话号码  不够用F代替
     */
    public  ICommand  syncFamilyNum(Car car){
        StringBuilder stringBuilder=new StringBuilder();
        String  ownerNum="";
        for (FamilyNum familyNum:
                familyNumList) {
            if(familyNum.getIs_owner()==1) {
                ownerNum=familyNum.getPhone();
            }else{

                stringBuilder.append(toAgreementPhone(familyNum.getPhone()));
            }
        }
        String  dearNum=stringBuilder.toString();
        if(dearNum.length()<64){
            for(int i=0;i<64-dearNum.length();i++){
                stringBuilder.append("F");
            }
        }
       return getDearNumRequest(car,ownerNum,stringBuilder.toString());

    }

    public String  toAgreementPhone(String carNum){
        int  phoneLength=carNum.length();
        if(phoneLength>16){
            return carNum.substring(0,16);
        }

        if(phoneLength==16){
            return carNum;
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(carNum);
        for (int i=0;i<16-carNum.length();i++){
            stringBuilder.append("F");
        }
        return stringBuilder.toString();
    }




    /**
     * 申请一
     * @return
     */
    public  ICommand getApplyOne(Car car ){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest();
        cmd.setType((byte)0x01);
        cmd.setCarNum(car.getCarNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }

    /**
     * 申请二
     * @return
     */
    public  ICommand getApplyTwo(Car car){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest();
        cmd.setType((byte)0x02);
        cmd.setCarNum(car.getCarNum());
        cmd.setAuthCode(car.getAuthNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }
    /**
     * 申请三
     * @return
     */
    public  ICommand getApplyThree(Car car){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest();
        cmd.setType((byte)0x03);
        cmd.setCarNum(car.getCarNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }
    /**
     * 申请四
     * @return
     */
    public  ICommand getApplyFour(Car car){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest();
        cmd.setType((byte)0x04);
        cmd.setCarNum(car.getCarNum());
        cmd.setAuthCode(car.getAuthNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }
    /**
     * 申请五
     * @return
     */
    public  ICommand getApplyFive(Car car){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest();
        cmd.setType((byte)0x05);
        cmd.setCarNum(car.getCarNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }
    /**
     * 读取未读罚单
     */
    public  ICommand getUnReadTicket(Car car,int index){
        TicketCommandRequest cmd=new TicketCommandRequest();
        cmd.setXuhao(index);
        cmd.setCarNum(car.getCarNum());
        cmd.roundTicketMsg();
        return cmd;
    }

    /**
     * 同步已上传的罚单
     */
    public  ICommand syncTicket(Car car){
        SyncTicketCommandRequest cmd=new SyncTicketCommandRequest();
        cmd.setCarNum(car.getCarNum());
        cmd.rundSyncTicketCommandRequest();
        return cmd;
    }


    /**
     * 同步已上传的罚单
     */
    public  ICommand syncTicket(Ticket ticket){
        ReadDataCommandRequest cmd=new ReadDataCommandRequest();
        cmd.setMsgType((byte)0x00);
        cmd.setCarNum(ticket.getCarnum());
//        cmd.setIdNo(ticket.getId());
//        cmd.setStatus(1);
        cmd.roundConnectCarMsg();
        return cmd;
    }





    /**
     * 设置声音大小
     */
    public   ICommand setVolume(String volume,Car car){
        VolumeCommandRequest cmd=new VolumeCommandRequest();
        LogUtil.i("carNum"+car.getCarNum());
        cmd.setCarNum(car.getCarNum());
        cmd.setMsgType(volume);
        cmd.roundVolumeMsg();
        return cmd;
    }

    /**
     * 设置声音大小
     */
    public   ICommand setVolume(Car car){
        VolumeCommandRequest cmd=new VolumeCommandRequest();
        LogUtil.i("carNum"+car.getCarNum());
        cmd.setCarNum(car.getCarNum());
        cmd.setMsgType(SpUtil.get("VOLUME"));
        cmd.roundVolumeMsg();
        return cmd;
    }

    /**
     * 读取车况（使用记录）
     */
    public  ICommand getCarStatus(Car car){
        ReadDataCommandRequest cmd=new ReadDataCommandRequest();
        cmd.setCarNum(car.getCarNum());
        cmd.setMsgType((byte)0x01);
        cmd.roundConnectCarMsg();
        return cmd;
    }


    /**
     * 读取异常行驶
     */
    public  ICommand getErrorDriver(Car car){
        ReadDataCommandRequest cmd=new ReadDataCommandRequest();
        cmd.setCarNum(car.getCarNum());
        cmd.setMsgType((byte)0x01);
        cmd.roundConnectCarMsg();
        return cmd;
    }



    /**
     * 危险驾驶
     */
    public  ICommand getDangerousDriving(Car car){
        DangerousDrivingCommandRequest cmd=new DangerousDrivingCommandRequest();
        LogUtil.i("carNum"+car.getCarNum());
        cmd.setCarNum(car.getCarNum());
        cmd.setMsgType(car.getDangerousType());
        cmd.roundDangerousDrivingMsg();
        return cmd;
    }


    /**
     * 请人移车
     */
    public  ICommand getMoveCar(Car car){
        MoveCarCommandRequest cmd=new MoveCarCommandRequest();
        LogUtil.i("carNum"+car.getCarNum());
        cmd.setCarNum(car.getCarNum());
        cmd.setPhone(car.getPhoneNum());
        cmd.roundMoveCarMsg();
        return cmd;
    }


    /**
     * 离车
     */
    public  ICommand DriverLeave(Car car){
        DriverLeaveCommandRequest cmd=new DriverLeaveCommandRequest();
        cmd.setAuthCode(car.getAuthNum());
        cmd.roundDriverLeaveMsg();
        return cmd;
    }

    /**
     * 驾驶证随行
     */
    public  ICommand CheckDriverAlive(){
        CheckDriverAliveCommandRequest cmd=new CheckDriverAliveCommandRequest();
        cmd.roundCheckDriverAliveMsg();
        return cmd;
    }

    /**
     * 同步声音
     */
    public ICommand SyncTime(){
        SyncTimeCommandRequest cmd=new SyncTimeCommandRequest();
        cmd.roundSyncTimeMsg();
        return cmd;

    }


    /**
     * 终端升级信息
     */
    public ICommand TerminalUpdate(){
        TerminalUpdateCommandRequest cmd=new TerminalUpdateCommandRequest();
        cmd.rundTerminalUpdateMessage();
        return cmd;

    }

    /**
     * 终端升级传送内容
     */

    public ICommand TermialTransfer(int packageCode){
        TerminalTransferDatapackageCommandRequest cmd=new TerminalTransferDatapackageCommandRequest();
        cmd.setPackageCode(packageCode);
        cmd.roudTerminalTransferDatapackage();
        return cmd;

    }


    /**
     * 更新指定编号参数
     */
    public   ICommand TerminalParamSet(Car car,String content){
        UpdateTerminalNumCommanRequest cmd=new UpdateTerminalNumCommanRequest();
        cmd.setContent(content);
        cmd.setCarNum(car.getCarNum());
        cmd.rundUpdateTerminalNumCommanRequest();
        return cmd;


    }



    public   ICommand TerminalParamVoiceSet(Car car,String content){
        UpdateTerminalNumVoiceCommandRequest cmd=new UpdateTerminalNumVoiceCommandRequest();
        cmd.setContent(content);
        cmd.setCarNum(car.getCarNum());
        cmd.rundUpdateTerminalNumCommanRequest();
        return cmd;
    }


    /**
     * 读取未读罚单条数
     */
    public  ICommand  getUnReadTicketCount(Car car){
        TicketCountCommadnRequest cmd= new TicketCountCommadnRequest();
        cmd.setCarNum(car.getCarNum());
        cmd.roundTicketCountMsg();
        return cmd;
    }
}
