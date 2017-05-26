package com.soarsky.car.server.cmd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/5/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class TicketCountCommadnRequest extends BaseCmd {


    /**
     * 车牌号
     */
    private String  carNum;
    /**
     *  0x01：读取没有上传的罚单
     *    0x02：读取全部罚单
     */
    private byte messageType=0x01;
    /**
     * 每包读取罚单条数
     */
    private  byte[]  messageCount=new byte[]{0x00,0x01};

    public TicketCountCommadnRequest() {
        setMsgId(02);
    }



    /**
     * 罚单消息体
     */
    private byte[] message;

    public  void   roundTicketCountMsg(){

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(messageType);
            buffer.write(messageCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        message=buffer.toByteArray();
        int lenth = message.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();
    }




    @Override
    public byte[] toMsgBytes() {

        if(message!=null){
            return  message;
        }
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
