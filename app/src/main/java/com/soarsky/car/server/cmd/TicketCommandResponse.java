package com.soarsky.car.server.cmd;

import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.LogUtil;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/30
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 读取罚单应答解析
 */

public class TicketCommandResponse extends  BaseCmd{
    /**
     * 罚单长度
     */
    private int ticketLength=0;
    /**
     * 罚单内容
     */
    private String  content;
    /**
     * 罚单编号
     */
    private String  ticketNo;

    /**
     * 车牌号
     */
    private String carNum;
    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {
        if(bytes.length>27){
            ticketLength=bytes[27];
        }
        ticketNo= parseTicketNo(bytes);
        content=new String(bytes,32,86);
        carNum=new String (bytes,34,9);
        LogUtil.i("ticketLength: "+ticketLength+"carNum:"+carNum+"ticketNo:"+ticketNo+"content:"+content);
    }


    private String parseTicketNo(byte[] bytes){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(bytes[28]);
        stringBuilder.append(bytes[29]);
        stringBuilder.append(bytes[30]);
        stringBuilder.append(bytes[31]);
        return stringBuilder.toString();
    }


    public String getContent() {
        return content;
    }

    public boolean hasTicket(){
        return ticketLength>0;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public String getCarNum() {
        return carNum;
    }
}
