package com.soarsky.car.server.cmd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
 * 程序功能：<br>
 * 该类为 读取终端消息请求协议封装<br>
 */

public class ReadDataCommandRequest extends BaseCmd {


    private static final int READ_MESSAGE = 9;
    /**
     0x01: 读取行驶异常数据
     0x02: 读取车况(用车记录)

     */
    private  byte  msgType;

    /**
     * 车牌号
     */
    private String carNum;


    public ReadDataCommandRequest(){
        setMsgId(READ_MESSAGE);
    }
    private byte[] message;
    public void roundConnectCarMsg() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(msgType);
        } catch (IOException e) {
            e.printStackTrace();
        }


        message = buffer.toByteArray();

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


    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }
}
