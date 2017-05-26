package com.soarsky.car.server.cmd;

import com.soarsky.car.App;
import com.soarsky.car.ui.ticketed.TicketDb;
import  com.soarsky.car.data.local.db.bean.Ticket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/4/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 标记已上传的罚单
 */


public class SyncTicketCommandRequest extends BaseCmd {
    private App app;
    /**
     * 待同步的条数
     */
    private int num=0;
    /**
     * 罚单编号
     */
    private byte[]ticketIds;


    private  String carNum;

    public SyncTicketCommandRequest() {
        setMsgId(0x0010);
        app = App.getApp();
        getNuSyncTicket();
    }
    private byte[]message;
    public  void rundSyncTicketCommandRequest(){
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(intToBytes2(num));
            buffer.write(ticketIds);
        } catch (IOException e) {
            e.printStackTrace();
        }
        message=buffer.toByteArray();
        int length=message.length;
        setMsgLength(length);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();


    }




    @Override
    public byte[] toMsgBytes() {

        if(message!=null){
            return message;
        }

        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

    }


    /**
     * 获取待同步的罚单
     */
    public  void getNuSyncTicket() {
        List<Ticket> ticketList = TicketDb.getInstance(app).getNuTerminal();
        if (null == ticketList) {
            num = 0;
        } else {
            num = ticketList.size();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            for (Ticket ticket : ticketList
                    ) {
                int idno=Integer.parseInt(ticket.getId());
                try {
                    buffer.write(intToBytes(idno));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ticketIds=buffer.toByteArray();

        }


    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
