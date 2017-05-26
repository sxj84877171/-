package com.soarsky.car.server.cmd;

import android.content.Context;
import android.text.TextUtils;

import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.server.wifi.ConnectCar;
import com.soarsky.car.ui.ticketed.TicketDb;
import com.soarsky.car.data.local.db.FamilyNumDb;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.WifiUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/2<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：同步数据到终端<br>
 * 该类为<br>
 */

public class SyncData {

    private Context context;
    /**
     * 连接终端类
     */
    private ConnectCar  connectCar;
    /**
     * 用来缓存罚单的集合
     */
    private List<Ticket>ticketList=new ArrayList<>();
    /**
     * 用来缓存亲情号的集合
     */
    private List<FamilyNum> familyNumList=new ArrayList<>();



    public SyncData(Context context,ConnectCar  connectCar){
        this.context=context;
        this.connectCar=connectCar;

    }

    public  void start(){
        getUnSyncTicket();
//        getUnSyncFamilyNum();
//        syncVolume();
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
            ICommand cmd=CmdManage.getInstance().syncTicket(ticket);
            connectCar.sendMessage(cmd);
            /**
             * TODO 发送请求默认成功 协议以后修改
             * 修改罚单状态
             */
            ticket.setTerminalStatus(2);
            TicketDb.getInstance(context).updateData(ticket);
        }
        if(ticketList.size()==0){
            getUnSyncFamilyNum();
        }
    }

    /**
     * 同步亲情号到终端
     * 默认4组电话号码  不够用F代替
     */
    public  void  syncFamilyNum(){
        StringBuilder stringBuilder=new StringBuilder();
        String  ownerNum="";
        for (FamilyNum familyNum:
                familyNumList) {
           if(familyNum.getIs_owner()==1) {
               ownerNum=familyNum.getPhone();
           }else{
               stringBuilder.append(familyNum.getPhone());
           }
        }
        String  dearNum=stringBuilder.toString();
        if(dearNum.length()<44){
            for(int i=0;i<44-dearNum.length();i++){
                stringBuilder.append("F");
            }
        }
        ICommand cmd=CmdManage.getInstance().getDearNumRequest(connectCar.getCar(),ownerNum,stringBuilder.toString());
        if(!TextUtils.isEmpty(ownerNum)){
            connectCar.sendMessage(cmd);
        }else{
            connectCar.onDestory();
            WifiUtil.getInstance().recoverWifi();
        }

    }
    /**
     * 更新声音
     */
    public   void  syncVolume(){
        ICommand cmd=CmdManage.getInstance().setVolume(SpUtil.get("volume"),connectCar.getCar());
        LogUtil.i("volume:"+SpUtil.get("volume"));
        if(connectCar!=null){
            connectCar.sendMessage(cmd);
        }

    }

    /**
     * 更新声音
     */
    public   void  syncVolume(int v){
        ICommand cmd=CmdManage.getInstance().setVolume(v+"",connectCar.getCar());
        LogUtil.i("volume:"+v);
        if(connectCar!=null){
            connectCar.sendMessage(cmd);
        }

    }



}
