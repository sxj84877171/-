package com.soarsky.car.server.cmd;


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
 * 该类为 设置声音请求协议的封装
 */

public class VolumeCommandRequest extends BaseCmd{

    private static final int VOLUME = 5;

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

    private  String message;
    public  void roundVolumeMsg(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(carNum);
        stringBuilder.append(msgType);
        message=stringBuilder.toString();
        byte[] bytes = message.getBytes();
        int lenth = bytes.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();

    }


    @Override
    public byte[] toMsgBytes() {
        if(message!=null){
            return message.getBytes();
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
