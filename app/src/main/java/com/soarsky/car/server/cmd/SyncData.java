package com.soarsky.car.server.cmd;

import android.content.Context;
import android.text.TextUtils;

import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.server.wifi.ConnectCar;
import com.soarsky.car.ui.ticketed.TicketDb;
import com.soarsky.car.ui.family.FamilyNumDb;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;

import java.util.ArrayList;
import java.util.List;

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
 * 程序功能：同步数据到终端
 * 该类为
 */

public class SyncData {

    private Context context;
    private ConnectCar  connectCar;
    private List<Ticket>ticketList=new ArrayList<>();
    private List<FamilyNum> familyNumList=new ArrayList<>();



    public SyncData(Context context,ConnectCar  connectCar){
        this.context=context;
        this.connectCar=connectCar;

    }

    public  void start(){
        getUnSyncTicket();
        getUnSyncFamilyNum();
        syncVolume();
    }


    /**
     * 获取未同步到终端的罚单数据
     */
    public void getUnSyncTicket(){
        ticketList= TicketDb.getInstance(context).getNuTerminal();
        syncTicket();
    }


    /**
     * 获取未同步到终端的亲情号
     */
    public  void getUnSyncFamilyNum(){
        familyNumList= FamilyNumDb.getInstance(context).getNuSyncTerminal();
        syncFamilyNum();
    }

    /**
     * 同步罚单到终端
     */
    public void syncTicket(){
        for (Ticket ticket:ticketList) {
            ICommand cmd=CmdManage.getInstance(context).syncTicket(ticket);
            connectCar.sendMessage(cmd);
            /**
             * TODO 发送请求默认成功 协议以后修改
             * 修改罚单状态
             */
            ticket.setTerminalStatus(2);
            TicketDb.getInstance(context).updateData(ticket);

        }
    }

    /**
     * 同步亲情号到终端
     */
    public  void  syncFamilyNum(){
        StringBuilder stringBuilder=new StringBuilder();
        String  ownerNum="";
        for (FamilyNum familyNum:
                familyNumList) {
           if(familyNum.getIs_owner()==1) {
               ownerNum=familyNum.getPhone();
           }
            stringBuilder.append(familyNum.getPhone());
        }
        ICommand cmd=CmdManage.getInstance(context).getDearNumRequest(context,connectCar.getCar(),ownerNum,stringBuilder.toString());
        if(!TextUtils.isEmpty(ownerNum)){
            connectCar.sendMessage(cmd);
        }

    }


    /**
     * 更新声音
     */
    public   void  syncVolume(){
        ICommand cmd=CmdManage.getInstance(context).setVolume(SpUtil.get("volume"),connectCar.getCar());
        LogUtil.i("volume:"+SpUtil.get("volume"));
        if(connectCar!=null){
            connectCar.sendMessage(cmd);
        }

    }



}
