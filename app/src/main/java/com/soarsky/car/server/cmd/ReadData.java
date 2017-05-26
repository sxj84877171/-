package com.soarsky.car.server.cmd;

import android.content.Context;

import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.server.wifi.ConnectCar;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 读取终端消息同步到服务器
 */

public class ReadData {

    private ConnectCar connectCar;
    private Context context;


    public   ReadData(Context context,ConnectCar connectCar){
        this.connectCar=connectCar;
        this.context=context;
    }


    public void start(){
        readTicket();
        readDearNum();
        readCarStatus();
        readErrorDriver();
    }


    /**
     * 读取罚单
     */
    private void  readTicket(){
        ICommand cmd=CmdManage.getInstance(context).getUnReadTicket(connectCar.getCar());
        if(connectCar!=null){
            connectCar.sendMessage(cmd);
        }
    }

    /**
     * 读取亲情号
     */
    private void  readDearNum(){
        ICommand cmd=CmdManage.getInstance(context).getDearNum(connectCar.getCar());
        if(connectCar!=null){
            connectCar.sendMessage(cmd);
        }
    }
    /**
     * 读取车况
     */
    private void  readCarStatus(){
        ICommand cmd=CmdManage.getInstance(context).getCarStatus(connectCar.getCar());
        if(connectCar!=null){
            connectCar.sendMessage(cmd);
        }
    }
    /**
     * 读取异常行驶数据
     */
    private void  readErrorDriver(){
        ICommand cmd=CmdManage.getInstance(context).getErrorDriver(connectCar.getCar());
        if(connectCar!=null){
            connectCar.sendMessage(cmd);
        }
    }
}
