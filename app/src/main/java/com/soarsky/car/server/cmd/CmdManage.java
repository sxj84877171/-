package com.soarsky.car.server.cmd;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.bean.Car;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.uitl.LogUtil;



/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/29
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：保存终端获取的用车记录
 * 该类为 与终端通讯协议的获取
 */


public class CmdManage {
    private  App app;
    public  CmdManage(Context context){
        app= (App) context.getApplicationContext();
    }

    private static CmdManage single=null;

    public static CmdManage getInstance(Context context) {
             if (single == null) {
                 single = new CmdManage(context);
             }
            return single;
        }

    /**
     * 故障查询
     * @return
     */
    public  ICommand getTroubleCheck(){
        TroubleCommandRequest cmd=new TroubleCommandRequest();
        cmd.setMsgType(0);
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
    public  ICommand getDearNumRequest(Context context,Car car ,String dearNum,String dearNum1){
        DearNumCommandRequest cmd=new DearNumCommandRequest(context);
        cmd.setDearNum(dearNum);
        cmd.setCarNum(car.getCarNum());
        cmd.setDearNum1(dearNum1);
        cmd.roundDearNumMsg();
        return cmd;
    }






    /**
     * 申请一
     * @return
     */
    public  ICommand getApplyOne(Car car ,Context context){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest(context);
        cmd.setType(1);
        cmd.setCarNum(car.getCarNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }

    /**
     * 申请二
     * @return
     */
    public  ICommand getApplyTwo(Car car,Context context){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest(context);
        cmd.setType(2);
        cmd.setCarNum(car.getCarNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }
    /**
     * 申请三
     * @return
     */
    public  ICommand getApplyThree(Car car,Context context){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest(context);
        cmd.setType(3);
        cmd.setCarNum(car.getCarNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }
    /**
     * 申请四
     * @return
     */
    public  ICommand getApplyFour(Car car,Context context){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest(context);
        cmd.setType(4);
        cmd.setCarNum(car.getCarNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }
    /**
     * 申请五
     * @return
     */
    public  ICommand getApplyFive(Car car,Context context){
        ConnectCarCommandRequest cmd=new ConnectCarCommandRequest(context);
        cmd.setType(5);
        cmd.setCarNum(car.getCarNum());
        cmd.roundConnectCarMsg();
        return cmd;
    }
    /**
     * 读取未读罚单
     */
    public  ICommand getUnReadTicket(Car car){
        TicketCommandRequest cmd=new TicketCommandRequest();
        cmd.setType(1);
        cmd.setCarNum(car.getCarNum());
        cmd.roundTicketMsg();
        return cmd;
    }

    /**
     * 同步已上传的罚单
     */
    public  ICommand syncTicket(Car car){
        TicketCommandRequest cmd=new TicketCommandRequest();
        cmd.setType(3);
        cmd.setTickerNo(car.getTicketNo());
        cmd.setStatus(1);
        cmd.roundTicketMsg();
        return cmd;
    }


    /**
     * 同步已上传的罚单
     */
    public  ICommand syncTicket(Ticket ticket){
        ReadDataCommandRequest cmd=new ReadDataCommandRequest();
        cmd.setMsgType("00");
        cmd.setCarNum(ticket.getCarNum());
        cmd.setTicketNo(ticket.getTicketNo());
        cmd.setStatus(1);
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
     * 读取车况
     */
    public  ICommand getCarStatus(Car car){
        ReadDataCommandRequest cmd=new ReadDataCommandRequest();
        LogUtil.i("carNum"+car.getCarNum());
        cmd.setCarNum(car.getCarNum());
        cmd.setMsgType("02");
        cmd.roundConnectCarMsg();
        return cmd;
    }


    /**
     * 读取异常行驶
     */
    public  ICommand getErrorDriver(Car car){
        ReadDataCommandRequest cmd=new ReadDataCommandRequest();
        LogUtil.i("carNum"+car.getCarNum());
        cmd.setCarNum(car.getCarNum());
        cmd.setMsgType("01");
        cmd.roundConnectCarMsg();
        return cmd;
    }

    /**
     * 读取亲情号
     */
    public  ICommand getDearNum(Car car){
        ReadDataCommandRequest cmd=new ReadDataCommandRequest();
        LogUtil.i("carNum"+car.getCarNum());
        cmd.setCarNum(car.getCarNum());
        cmd.setMsgType("03");
        cmd.roundConnectCarMsg();
        return cmd;
    }


    /**
     * 读取亲情号
     */
    public  ICommand getDangerousDriving(Car car){
        DangerousDrivingCommandRequest cmd=new DangerousDrivingCommandRequest();
        LogUtil.i("carNum"+car.getCarNum());
        cmd.setCarNum(car.getCarNum());
        cmd.setMsgType(car.getDangerousType());
        cmd.roundDangerousDrivingMsg();
        return cmd;
    }

}
