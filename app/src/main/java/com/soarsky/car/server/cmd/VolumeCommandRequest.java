package com.soarsky.car.server.cmd;


import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.SpUtil;

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
 * 该类为 设置声音请求协议的封装<br>
 */

public class VolumeCommandRequest extends BaseCmd{

    private static final int VOLUME = 5;
    private String authCode="1123581321345589";
    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 消息类型 01到10 共10级 01 最小
     */
    private String msgType;

    public  VolumeCommandRequest(){
        setMsgId(VOLUME);
    }

    private  byte[] message;
    public  void roundVolumeMsg(){
        StringBuilder stringBuilder=new StringBuilder();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(authCode.getBytes());
            buffer.write(intToBytes1(Integer.parseInt(SpUtil.get("volume"))));
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
            return message;
        }
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

    }





    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
