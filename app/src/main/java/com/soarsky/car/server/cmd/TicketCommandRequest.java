package com.soarsky.car.server.cmd;

import com.soarsky.car.Constants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
 * 该类为 读取罚单请求协议封装<br>
 */

public class TicketCommandRequest extends BaseCmd{
    /**
     *车牌号
     */
    private String carNum="";

    /**
     * 0 未已上传  1已上传 读取类型为3时才有此内容
     */
    private  int status;


    private   byte  readType=0x01;

    private  byte[]  xuhao;



    /**
     * 待读条数
     */
    private byte num=(byte) (Constants.READTICKET_COUNT & 0xFF);

    private static final int TICKET = 15;//
    public TicketCommandRequest() {
        setMsgId(TICKET);
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
     * 罚单消息体
     */
    private byte[] message;

    public  void   roundTicketMsg(){

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(readType);
            buffer.write(xuhao);
            buffer.write(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        message=buffer.toByteArray();
        int lenth = message.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();
    }





    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public void setXuhao(int index) {
        this.xuhao = intToBytes2(index);
    }

}
