package com.soarsky.car.server.cmd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.ui.ticketed.TicketDb;
import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 读取罚单应答解析<br>
 */

public class TicketCommandResponse extends BaseCmd {
    /**
     * 罚单长度
     */
    private int ticketLength = 0;
    /**
     * 罚单内容
     */
    private String content;
    /**
     * 罚单编号
     */
    private String ticketNo;

    /**
     * 车牌号
     */
    private String carNum;

    /**
     * 命令类型
     */

    private int messageType;

    /**
     * 罚单条数
     */
    private int messageCount;


    /**
     * 用来保存收到的罚单数据
     */
    private List<Ticket> ticketList;

    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {


    }


    public void paresBytes2(byte[] bytes, int length) {
        ticketList = new ArrayList<>();
        byte[] buffer = new byte[length-29];
        messageCount = (length - 29) / 87;
        System.arraycopy(bytes, 27 , buffer, 0, length-29);
         content=new String(buffer);
        LogUtil.i("罚单内容为："+content);
        parseTicketContent(content);



    }


    private void parseTicketContent(String jsonContent) {

        ticketList=new ArrayList<>();
        try {
            Gson gson = new Gson();
            ticketList= (List<Ticket>) gson.fromJson(jsonContent,new TypeToken<List<Ticket>>(){}.getType());

            for (Ticket ticket:ticketList
                    ) {
                ticket.setServiceStatus(1);
                ticket.setTerminalStatus(1);

            }
        }catch (Exception e){

        }




    }


    public List<Ticket> getTicketList() {
        return ticketList;
    }



    public String getContent() {
        return content;
    }

    public boolean hasTicket() {
        return ticketLength > 0;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public String getCarNum() {
        return carNum;
    }


    public int getMessageType() {
        return messageType;
    }
}
